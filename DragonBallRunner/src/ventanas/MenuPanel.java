package ventanas;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

import ventanas.RecursoGrafico;
import ventanas.LoginPanel;
import ventanas.AyudaPanel;
import main.DragonBallRunner;
import objetos.Audio;
import pantallajuego.Runner;

public class MenuPanel extends JFrame{
	
	private JButton JJugar;
	private JButton JTutorial;
	private JButton JSalir;
	private PanelConImagenFondo panel_fondo;
	private static DragonBallRunner juego;
	private String fondo = "/img/menu.png";
	private Audio musicamenu;

	
	//public Rectangle jugar = new Rectangle(GamePanel.WIDTH/2 - 67, 388, 160, 85);
	//public Rectangle ayuda = new Rectangle(GamePanel.WIDTH/2 - 325, 400, 125, 60);
	//public Rectangle salir = new Rectangle(GamePanel.WIDTH/2 + 225, 400, 125, 60);

	public MenuPanel(DragonBallRunner juego) {
		this.juego = juego;
		setTitle("DragonBallRunner");
		setSize(1000, 666);
		setLocation(200, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel_fondo = new PanelConImagenFondo();
		panel_fondo.setImage(fondo);
		setContentPane(panel_fondo);
		setLayout(new BorderLayout());
		crearPaneles();
		crearBotones();	
		//MUSICA MENU
		musicamenu = new Audio("/audio/menu.wav");
		musicamenu.start();
	}
	
	public void crearPaneles() {
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize( new Dimension( 1000, 388 ));
		panel_1.setOpaque(false);
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize( new Dimension( 355, 666 ));
		panel_2.setOpaque(false);
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize( new Dimension( 1000, 70 ));
		panel_3.setOpaque(false);
		JPanel panel_4= new JPanel();
		panel_4.setPreferredSize( new Dimension( 355, 666 ));
		panel_4.setOpaque(false);
		add(panel_1, BorderLayout.NORTH);
		add(panel_3, BorderLayout.SOUTH);
		add(panel_2, BorderLayout.EAST);
		add(panel_4, BorderLayout.WEST);
	}
	
	
	/**Crea los 3 botones pulsables del menu principal.
	 * 
	 */
	public void crearBotones() {
		JPanel botones = new JPanel();
		//botones.setLayout(new GridLayout(3,1));
		
		//JUGAR
		JJugar = new JButton();
		JJugar.setBounds(MenuPanel.WIDTH/2 -67, 388, 160, 85);
		JJugar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
            	ImageIcon icono = new ImageIcon(getClass().getResource("/img/botonjugarR.png"));
        		JJugar.setIcon(icono);
            }
            public void mouseExited(MouseEvent e) {
            	ImageIcon icono = new ImageIcon(getClass().getResource("/img/botonjugar.png"));
        		JJugar.setIcon(icono);
            }
        });
		ImageIcon icono = new ImageIcon(getClass().getResource("/img/botonjugar.png"));
		JJugar.setIcon(icono);
		JJugar.setBorder(null);
		JJugar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				DragonBallRunner dbr = new DragonBallRunner();
//				dbr.jugar();
				LoginPanel login = new LoginPanel(new DragonBallRunner());
				login.setVisible(true);
				musicamenu.stop();
				dispose();
			}
		});
		
		//SALIR
		JSalir = new JButton();
		JSalir.setBounds(MenuPanel.WIDTH/2 - 325, 400, 125, 60);
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
				musicamenu.stop();
				dispose();
			}
		});
		
		//TUTORIAL
		JTutorial = new JButton();
		JTutorial.setBounds(MenuPanel.WIDTH/2 + 225, 400, 125, 60);
		JTutorial.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
            	ImageIcon icono_2 = new ImageIcon(getClass().getResource("/img/botonTutorialR.png"));
            	JTutorial.setIcon(icono_2);
            }
            public void mouseExited(MouseEvent e) {
            	ImageIcon icono_2 = new ImageIcon(getClass().getResource("/img/botonTutorial.png"));
            	JTutorial.setIcon(icono_2);
            }
        });
		ImageIcon icono_2 = new ImageIcon(getClass().getResource("/img/botonTutorial.png"));
		JTutorial.setIcon(icono_2);
		JTutorial.setBorder(null);
		JTutorial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					AyudaPanel.main(null);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				musicamenu.stop();
				dispose();
			}
		});
		botones.add(JJugar);
		botones.add(JTutorial);
		botones.add(JSalir);
		add(botones, BorderLayout.CENTER);
		botones.setBackground(new Color(0,0,0,0));
	}
	
	public static void main(String[] args) throws InterruptedException {
		MenuPanel mp = new MenuPanel(juego);
		mp.setVisible( true );
	}

}