package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.DragonBallRunner;
import objetos.Puntuacion;
import ventanas.MenuPanel;
import ventanas.PanelConImagenFondo;
import ventanas.RankingPanel;

public class RankingPanel extends JFrame {

	private JButton JMenu;
	private JButton JSalir;
	private PanelConImagenFondo fondo;
	private JButton JPrimero;
	private JButton JSegundo;
	private JButton JTercero;
	private JList<String> listaUsuarios = new JList<String>();
	private Puntuacion puntuacion;

	private static DragonBallRunner juego;

	public RankingPanel(DragonBallRunner juego) {
		this.juego = juego;
		setTitle("RANKING");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 666);
		setLocation(200, 100);
		setLayout(new BorderLayout());
		fondo = new PanelConImagenFondo();
		fondo.setImage("/img/ranking.png");
		fondo.setOpaque(false);
		setContentPane(fondo);
		setLayout(new BorderLayout());
		// setBackground(Color.BLACK);
		crearBotones();
		crearDatos();
		cargarJugadores();
		listaUsuarios.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if (event.getValueIsAdjusting()) {
					String jugador = listaUsuarios.getSelectedValue();
					ventanaJugador(jugador);
				}
			}
		});
	}

	private void ventanaJugador(String jugador) {
		JFrame ventana = new JFrame();
		ventana.setSize(400, 666);
		ventana.setLocation(200, 100);

		JPanel panel_arriba = new JPanel();
		panel_arriba.setBackground(Color.GRAY);
		JLabel l = new JLabel(jugador, SwingConstants.CENTER);
		l.setForeground(Color.WHITE);
		l.setFont(new Font("Arial", Font.BOLD, 20));
		panel_arriba.add(l);
		ventana.add(panel_arriba, BorderLayout.NORTH);

		JPanel panel_izq = new JPanel();
		panel_izq.setPreferredSize(new Dimension(45, 400));
		panel_izq.setBackground(Color.BLACK);
		ventana.add(panel_izq, BorderLayout.WEST);

		JPanel panel_central = new JPanel();
		panel_central.setLayout(new GridLayout(Puntuacion.puntuaciones.get(jugador).size(), 2));
		int contador = 0;
		int cont = 1;
		for (Integer i : Puntuacion.puntuaciones.get(jugador)) {
			if (contador % 2 == 0) {
				JLabel l_1 = new JLabel(String.valueOf(cont));
				l_1.setForeground(Color.WHITE);
				l_1.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
				panel_central.add(l_1);
				cont++;
				contador++;
			}
			if (contador % 2 != 0) {
				JLabel l_2 = new JLabel(String.valueOf(i));
				l_2.setForeground(Color.WHITE);
				l_2.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
				panel_central.add(l_2);
				contador++;
			}
		}
		panel_central.setBackground(Color.BLACK);
		ventana.add(panel_central, BorderLayout.CENTER);

		JPanel panel_abajo = new JPanel();
		JLabel mens = new JLabel("Puntuaciones del jugador ", SwingConstants.CENTER);
		mens.setForeground(Color.WHITE);
		panel_abajo.add(mens);
		panel_abajo.setBackground(Color.GRAY);
		ventana.add(panel_abajo, BorderLayout.SOUTH);

		ventana.setBackground(Color.BLACK);
		ventana.setVisible(true);

	}

	public void cargarJugadores() {
		DefaultListModel<String> lm = new DefaultListModel<String>();
		for (String s : Puntuacion.puntuaciones.keySet()) {
			lm.addElement(s);
			listaUsuarios.setModel(lm);
		}
		listaUsuarios.setBorder(BorderFactory.createLineBorder(Color.green, 3));
		listaUsuarios.setForeground(Color.YELLOW);
		listaUsuarios.setBackground(Color.BLACK);
		add(listaUsuarios, BorderLayout.WEST);
	}

	private void crearDatos() {
		JPanel datos = new JPanel();
		datos.setOpaque(false);
		datos.setPreferredSize(new Dimension(700, 666));
		datos.setLayout(null);

		int ancho = 110;
		int alto = 100;

		// PRIMERO
		JPrimero = new JButton("   " + String.valueOf(Puntuacion.ranking()[0]) + "  -  " + Puntuacion.puntuaciones.get(String.valueOf(Puntuacion.ranking()[0])).get(0));
		JPrimero.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
		JPrimero.setBounds(335, 220, 330, 100);
		JPrimero.setBorder(null);
		ImageIcon icono = new ImageIcon(getClass().getResource("/img/primero.png"));
		ImageIcon icono_1 = new ImageIcon(
				icono.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_DEFAULT));
		JPrimero.setIcon(icono_1);
		JPrimero.setBackground(Color.white);
		JPrimero.setBorder(null);
		JPrimero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaJugador(String.valueOf(Puntuacion.ranking()[0]));
			}
		});

		// SEGUNDO
		JSegundo = new JButton("   " + String.valueOf(Puntuacion.ranking()[1]) + "  -  " + Puntuacion.puntuaciones.get(String.valueOf(Puntuacion.ranking()[1])).get(0));
		JSegundo.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
		JSegundo.setBounds(80, 330, 330, 100);
		ImageIcon icono_2 = new ImageIcon(getClass().getResource("/img/segundo.png"));
		ImageIcon icono_2_1 = new ImageIcon(
				icono_2.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_DEFAULT));
		JSegundo.setIcon(icono_2_1);
		JSegundo.setBackground(Color.white);
		JSegundo.setBorder(null);
		JSegundo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaJugador(String.valueOf(Puntuacion.ranking()[1]));
			}
		});

		// TERCERO
		JTercero = new JButton("   " + String.valueOf(Puntuacion.ranking()[2]) + "  -  " + Puntuacion.puntuaciones.get(String.valueOf(Puntuacion.ranking()[2])).get(0));
		JTercero.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
		JTercero.setBounds(590, 330, 330, 100);
		ImageIcon icono_3 = new ImageIcon(getClass().getResource("/img/tercero.png"));
		ImageIcon icono_3_1 = new ImageIcon(
				icono_3.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_DEFAULT));
		JTercero.setIcon(icono_3_1);
		JTercero.setBackground(Color.white);
		JTercero.setBorder(null);
		JTercero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaJugador(String.valueOf(Puntuacion.ranking()[2]));
			}
		});

		datos.add(JPrimero);
		datos.add(JSegundo);
		datos.add(JTercero);
		add(datos, BorderLayout.NORTH);
	}

	private void crearBotones() {

		JPanel botones = new JPanel();
		botones.setOpaque(false);
		botones.setPreferredSize(new Dimension(300, 666));
		botones.setBounds(700, 0, 300, 666);
		botones.setLayout(null);

		// MENU
		JMenu = new JButton();
		JMenu.setBounds(275, 530, 125, 60);
		JMenu.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				ImageIcon icono = new ImageIcon(getClass().getResource("/img/botonmenuR.png"));
				JMenu.setIcon(icono);
			}

			public void mouseExited(MouseEvent e) {
				ImageIcon icono = new ImageIcon(getClass().getResource("/img/botonmenu.png"));
				JMenu.setIcon(icono);
			}
		});
		ImageIcon icono = new ImageIcon(getClass().getResource("/img/botonmenu.png"));
		JMenu.setIcon(icono);
		JMenu.setBorder(null);
		JMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuPanel mp = new MenuPanel(juego);
				mp.setVisible(true);
				dispose();
			}
		});

		// SALIR
		JSalir = new JButton();
		JSalir.setBounds(625, 530, 125, 60);
		JSalir.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/botonsalirR.png"));
				JSalir.setIcon(icono_1);
			}

			public void mouseExited(MouseEvent e) {
				ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/botonsalir.png"));
				JSalir.setIcon(icono_1);
			}
		});
		ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/botonsalir.png"));
		JSalir.setIcon(icono_1);
		JSalir.setBorder(null);
		JSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		botones.add(JMenu);
		botones.add(JSalir);
		add(botones, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		RankingPanel rp = new RankingPanel(juego);
		rp.setVisible(true);
	}
}
