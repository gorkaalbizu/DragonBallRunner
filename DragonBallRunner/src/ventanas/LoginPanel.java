package ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;
import ventanas.RecursoGrafico;
import main.DragonBallRunner;
import pantallajuego.GamePanel;
import pantallajuego.Runner;

public class LoginPanel extends JFrame {
	
	private JTextField texto;
	private JLabel nick;
	private JButton JAceptar;
	public static String  nombre;
	private static DragonBallRunner juego;
	private PanelConImagenFondo panel_fondo;
	private String fondo = "/img/login.png";

	
	public LoginPanel(DragonBallRunner juego) {
		this.juego = juego;
		setTitle("LOGIN");
		setSize(333, 220);
		setLocation(166, 110);
		
		panel_fondo = new PanelConImagenFondo();
		panel_fondo.setImage(fondo);
		panel_fondo.setOpaque(false);
		setContentPane(panel_fondo);
		setLayout(new BorderLayout());
		crearPaneles();
		crear();
		crearBotones();
	}
	
	public void crearPaneles() {
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize( new Dimension( 333, 71 ));
		panel_1.setOpaque(false);
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize( new Dimension( 77, 1 ));
		panel_2.setOpaque(false);
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize( new Dimension( 333, 71 ));
		panel_3.setOpaque(false);
		JPanel panel_4= new JPanel();
		panel_4.setPreferredSize( new Dimension( 177, 5 ));
		panel_4.setOpaque(false);
		add(panel_1, BorderLayout.NORTH);
		add(panel_3, BorderLayout.SOUTH);
		add(panel_2, BorderLayout.EAST);
		add(panel_4, BorderLayout.WEST);
		
	}
	
	public void crear() {
		nick = new JLabel("NICK:");
		nick.setBounds(33, 70, 50, 30);
		nick.setForeground(Color.RED);
		nick.setBackground(Color.WHITE);
		add(nick);
		
		texto = new JTextField(15);
		texto.setBounds(85, 70, 80, 30);
		add(texto);
	}
	
	public void crearBotones() {
		//ACEPTAR
		JAceptar = new JButton();
		JAceptar.setBounds(175, 100, 60, 30);
		JAceptar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
            	ImageIcon icono = new ImageIcon(getClass().getResource("/img/botonaceptarR.png"));
            	JAceptar.setIcon(icono);
            }
            public void mouseExited(MouseEvent e) {
            	ImageIcon icono = new ImageIcon(getClass().getResource("/img/botonaceptar.png"));
            	JAceptar.setIcon(icono);
            }
        });
		ImageIcon icono = new ImageIcon(getClass().getResource("/img/botonaceptar.png"));
		JAceptar.setIcon(icono);
		JAceptar.setBorder(null);
		JAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nombre = texto.getText();
				DragonBallRunner dbr = new DragonBallRunner();
				dbr.jugar();
				dispose();
			}
		});
		
		
		add(JAceptar);
	}
	public static void main(String[] args) {
		LoginPanel lp = new LoginPanel(juego);
		lp.setVisible(true);
	}
}
