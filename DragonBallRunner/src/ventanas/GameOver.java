package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.DragonBallRunner;
import pantallajuego.Runner;
import ventanas.GameOverPanel;
import ventanas.RankingPanel;

public class GameOver extends JFrame{
	private JButton JRanking;
	private JButton JJugar;
	private GameOverPanel gop;
	private static DragonBallRunner juego;
	private PanelConImagenFondo panel_fondo;
	private String fondo = "/img/gameover.png";
	
	public GameOver(DragonBallRunner juego) {
		this.juego = juego;
		setSize(1000, 666);
		setLocation(200, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		panel_fondo = new PanelConImagenFondo();
		panel_fondo.setImage(fondo);
		panel_fondo.setOpaque(false);
		setContentPane(panel_fondo);
		
		gop = new GameOverPanel();
		gop.setOpaque(false);
		gop.setSize(1000, 666);
		add(gop);
		setLayout(new BorderLayout());
//		crearPaneles();
		crearBotones();	
	}
	
//	public void crearPaneles() {
//		JPanel panel_1 = new JPanel();
//		panel_1.setPreferredSize( new Dimension( 20, 0 ));
//		panel_1.setOpaque(false);
//		JPanel panel_2 = new JPanel();
//		panel_2.setPreferredSize( new Dimension( 0, 20 ));
//		panel_2.setOpaque(false);
//		JPanel panel_3 = new JPanel();
//		panel_3.setPreferredSize( new Dimension( 20, 0 ));
//		panel_3.setOpaque(false);
//		JPanel panel_4= new JPanel();
//		panel_4.setPreferredSize( new Dimension( 20, 0 ));
//		panel_4.setOpaque(false);
//		add(panel_1, BorderLayout.NORTH);
//		add(panel_3, BorderLayout.SOUTH);
//		add(panel_2, BorderLayout.EAST);
//		add(panel_4, BorderLayout.WEST);
//		
//	}
	
	private void crearBotones() {
		JPanel botones = new JPanel();
		
		botones.setLayout(null);
		botones.setOpaque(false);
		
		JRanking = new JButton();
		JRanking.setBounds(750, 450, 125, 60);
		JRanking.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
            	ImageIcon icono = new ImageIcon(getClass().getResource("/img/botonrankingR.png"));
            	JRanking.setIcon(icono);
            }
            public void mouseExited(MouseEvent e) {
            	ImageIcon icono = new ImageIcon(getClass().getResource("/img/botonranking.png"));
            	JRanking.setIcon(icono);
            }
        });
		ImageIcon icono = new ImageIcon(getClass().getResource("/img/botonranking.png"));
		JRanking.setIcon(icono);
		JRanking.setBorder(null);
		JRanking.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RankingPanel rp = new RankingPanel(juego);
				rp.setVisible(true);
				dispose();
			}
		});

		
		JJugar = new JButton();
		JJugar.setBounds(550, 450, 125, 60);
		JJugar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
            	ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/botonjugarR1.png"));
        		JJugar.setIcon(icono_1);
            }
            public void mouseExited(MouseEvent e) {
            	ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/botonjugar1.png"));
        		JJugar.setIcon(icono_1);
            }
        });
		ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/botonjugar1.png"));
		JJugar.setIcon(icono_1);
		JJugar.setBorder(null);
		JJugar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DragonBallRunner dbr = new DragonBallRunner();
				dbr.jugar();
				dispose();
			}
		});
		
		botones.add(JRanking);
		botones.add(JJugar);
		add(botones);
	}
	
	public static void main (String[] args) {
		GameOver go = new GameOver(juego);
		go.setVisible(true);
		
	}
}
