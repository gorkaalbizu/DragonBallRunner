package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


import main.DragonBallRunner;
import ventanas.RecursoGrafico;

public class AyudaPanel extends JFrame {
	
	private JButton JAtras;
	private static DragonBallRunner juego;

	public AyudaPanel(DragonBallRunner juego) {
		AyudaPanel.juego = juego;
		setTitle("Tutorial");
		setSize(1000, 666);
		setLocation(200, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		PanelConImagenFondo panel_fondo = new PanelConImagenFondo();
		panel_fondo.setImage("/img/tutorial.png");
		panel_fondo.setOpaque(false);
		setContentPane(panel_fondo);
		setLayout(new BorderLayout());
		crearPaneles();
		crearBotones();		
	}
	
	public void crearPaneles() {
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize( new Dimension( 200, 480 ));
		panel_1.setOpaque(false);
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize( new Dimension( 425, 500 ));
		panel_2.setOpaque(false);
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize( new Dimension( 200, 70 ));
		panel_3.setOpaque(false);
		JPanel panel_4= new JPanel();
		panel_4.setPreferredSize( new Dimension( 425, 500 ));
		panel_4.setOpaque(false);
		add(panel_1, BorderLayout.NORTH);
		add(panel_3, BorderLayout.SOUTH);
		add(panel_2, BorderLayout.EAST);
		add(panel_4, BorderLayout.WEST);
		
	}

	public void crearBotones() {
		JPanel botones = new JPanel();
		//botones.setLayout(new GridLayout(1,1));
		
		//MENU
		JAtras = new JButton();
		JAtras.setBounds(AyudaPanel.WIDTH/2 - 75, 500, 150, 80);
		JAtras.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
            	ImageIcon icono = new ImageIcon(getClass().getResource("/img/botonmenuR.png"));
            	JAtras.setIcon(icono);
            }
            public void mouseExited(MouseEvent e) {
            	ImageIcon icono = new ImageIcon(getClass().getResource("/img/botonmenu.png"));
            	JAtras.setIcon(icono);
            }
        });
		ImageIcon icono = new ImageIcon(getClass().getResource("/img/botonmenu.png"));
		JAtras.setIcon(icono);
		JAtras.setBorder(null);
		JAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuPanel mp = new MenuPanel(juego);
				mp.setVisible(true);
				dispose();
			}
		});

		botones.add(JAtras);
		add(botones, BorderLayout.CENTER);
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		AyudaPanel ap = new AyudaPanel(juego);
		ap.setVisible( true );
	}

}

