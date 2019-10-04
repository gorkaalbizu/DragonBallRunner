package pantallajuego;

import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import main.DragonBallRunner;
import ventanas.RecursoGrafico;

import java.lang.Thread;

import pantallajuego.FondoPanel;
import pantallajuego.InicioPanel;
import pantallajuego.ObstaculosPanel;
import pantallajuego.PuntuacionPanel;
import pantallajuego.SonGokuPanel;
import ficheros.Fichero;
import objetos.Audio;
import objetos.Puntuacion;
import ventanas.AyudaPanel;
import ventanas.GameOver;
import ventanas.GameOverPanel;
import ventanas.MenuPanel;
import pantallajuego.GamePanel;
import ventanas.LoginPanel;

public class Runner extends JPanel implements KeyListener, Runnable {

	public static int WIDTH;
	public static int HEIGHT;
	private Thread animacion;
	private Audio musicajuego;
	private Audio musicacolision;

	private static boolean corriendo = false;
	private static boolean gameOver = false;

	static FondoPanel fondo;
	static InicioPanel inicio;
	static ObstaculosPanel obstaculos;
	static SonGokuPanel songoku;
	static PuntuacionPanel puntuacion;
	static Puntuacion punt;

	private static DragonBallRunner juego;

	public Runner(DragonBallRunner juego) {
		// this.addMouseListener(menu);
		this.juego = juego;
		WIDTH = Panel.WIDTH;
		HEIGHT = Panel.HEIGHT;

		punt = new Puntuacion();

		fondo = new FondoPanel();
		obstaculos = new ObstaculosPanel(1100);
		songoku = new SonGokuPanel();
		puntuacion = new PuntuacionPanel(punt);

		Puntuacion.puntos = 0;

		setSize(WIDTH, HEIGHT);
		setVisible(true);
	}

	@SuppressWarnings("deprecation")
	public void paint(Graphics g) {

		// musicamenu.stop();
		// this.addMouseListener(new MouseInput());

		super.paint(g);

		fondo.paintComponent(g);
		obstaculos.paintComponent(g);
		songoku.paintComponent(g);
		puntuacion.paintComponent(g);

		// AL PRINCIPIO (HASTA PULSAR ESPACIO) PINTAMOS LA IMAGEN DE INICIO.
		if (animacion == null || !corriendo && !gameOver) {
			InicioPanel inicio = new InicioPanel();
			inicio.paintComponent(g);
		}
	}

	public void paintInicio(Graphics g) {
		inicio.paint(g);
	}

	public void run() {
		// this.addMouseListener(new MouseInput());

		corriendo = true;

		while (corriendo) {
			updateGame();
			repaint();
			try {
				Thread.sleep(45);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void updateGame() {
		// this.addMouseListener(new MouseInput());
		// ESTADO.values();

		Puntuacion.puntos += 1;
		fondo.update();
		// songoku.update();
		obstaculos.update();

		if (Puntuacion.puntos > 200) {
			obstaculos.setMovementSpeed(obstaculos.getMovementSpeed() + (1 / 2));
		}
		if (Puntuacion.puntos > 400) {
			obstaculos.setMovementSpeed(obstaculos.getMovementSpeed() + (1 / 2));
		}
		if (Puntuacion.puntos > 600) {
			obstaculos.setMovementSpeed(obstaculos.getMovementSpeed() + (1 / 2));
		}
		

		if (obstaculos.hasCollided()) {
			System.out.println("CHOQUE");
			GamePanel.dispose();

			// DETENEMOS AUDIO DE JUEGO
			musicajuego.stop();
			// AUDIO AL CHOCAR
			musicacolision = new Audio("/audio/colision.wav");
			musicacolision.start();

			// this.addMouseListener(new MouseInput());

			songoku.die();
			// DragonBallRunner dbr = new DragonBallRunner();
			corriendo = false;
			gameOver = true;
			GameOver go = new GameOver(juego);
			go.setVisible(true);
			
			Puntuacion.añadir(LoginPanel.nombre, Puntuacion.puntos);
			Puntuacion.ordenarArray();
			Fichero f = new Fichero();
			f.escribir();
			
			revalidate();
			repaint();
		}

		if (obstaculos.isOut()) {
			revalidate();
			repaint();
		}
		// El juego no tiene fin si no te chocas.
	}

	public static void reset() {
		// this.addMouseListener(new MouseInput());
		Puntuacion.puntos = 0;
		System.out.println("RESET");
		obstaculos.resume();
		gameOver = false;
	}

	public void keyTyped(KeyEvent e) {
		// System.out.println(e);
		// this.addMouseListener(new MouseInput());

		if (e.getKeyChar() == ' ') {

			if (animacion == null || !corriendo && !gameOver) {

				System.out.println("COMIENZA EL JUEGO");
				// CERRAR VENTANA MENU Y PARA SU MUSICA
				// MenuPanel.dispose();
				// musicamenu.stop();

				// MUSICA DE FONDO
				musicajuego = new Audio("/audio/juego.wav");
				musicajuego.start();

				// ANIMACION GOKU
				animacion = new Thread(this);
				animacion.start();

				songoku.startRunning();
			} else {
				songoku.jump();
			}
		}
	}

	public static boolean isGameOver() {
		return gameOver;
	}

	public static void setGameOver(boolean gameOver) {
		Runner.gameOver = gameOver;
	}

	public void keyPressed(KeyEvent e) {
		// this.addMouseListener(new MouseInput());

		// System.out.println("keyPressed: "+e);
	}

	public void keyReleased(KeyEvent e) {
		// this.addMouseListener(new MouseInput());

		// System.out.println("keyReleased: "+e);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GamePanel(juego);
			}
		});
	}
}

//package pantallajuego;
//
//import java.io.*;
//import javax.imageio.*;
//import javax.swing.*;
//
//import main.DragonBallRunner;
//
//import java.awt.*;
//import java.awt.event.*;
//import java.awt.image.BufferedImage;
//
//import ventanas.RecursoGrafico;
//
//import java.lang.Thread;
//
//import pantallajuego.FondoPanel;
//import pantallajuego.ObstaculosPanel;
//import pantallajuego.SonGokuPanel;
//import ficheros.Fichero;
//import objetos.Audio;
//import objetos.EscuchaTeclado;
//import objetos.Puntuacion;
//import ventanas.AyudaPanel;
//import ventanas.GameOver;
//import ventanas.GameOverPanel;
//import ventanas.MenuPanel;
//import ventanas.LoginPanel;
//
//import ventanas.LoginPanel;
//
//public class Runner extends JFrame implements KeyListener, Runnable {
//
//	public static int WIDTH;
//	public static int HEIGHT;
//	private Thread animacion;
//	private Audio musicajuego;
//	private Audio musicacolision;
//
//	private static boolean corriendo = false;
//	private static boolean gameOver = false;
//
//	static FondoPanel fondo;
//	static ObstaculosPanel obstaculos;
//	static SonGokuPanel songoku;
//	static PuntuacionPanel puntuacion;
//	static EscuchaTeclado et;
//	GameOverPanel gameover;
//
//	public Runner() {
//
//		Puntuacion punt = new Puntuacion();
//
//		fondo = new FondoPanel();
//		obstaculos = new ObstaculosPanel(800);
//		puntuacion = new PuntuacionPanel(punt);
//		songoku = new SonGokuPanel();
//
//		fondo.setSize(new Dimension(1000, 666));
//		obstaculos.setSize(new Dimension(1000, 666));
//		puntuacion.setSize(new Dimension(1000, 666));
//		songoku.setSize(new Dimension(1000, 666));
////		add(fondo);
////		add(obstaculos);
////		add(puntuacion);
////		add(songoku);
//		getContentPane().add(fondo);
//		getContentPane().add(obstaculos);
//		getContentPane().add(puntuacion);
//		getContentPane().add(songoku);
//
//		EscuchaTeclado et = new EscuchaTeclado(this, this);
//
//		setLocation(200, 100);
//		setSize(1000, 666);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setVisible(true);
//		addKeyListener(et);
//		setFocusable(true);
//	}
//
//	public void paint(Graphics g) {
//		super.paint(g);
//		fondo.paintComponent(g);
//		obstaculos.paintComponent(g);
//		songoku.paintComponent(g);
//		puntuacion.paintComponent(g);
//
//		// AL PRINCIPIO (HASTA PULSAR ESPACIO) PINTAMOS LA IMAGEN DE INICIO.
//		if (animacion == null || !corriendo) {
//			InicioPanel ip = new InicioPanel();
//			ip.setSize(new Dimension(1000, 666));
//			add(ip);
//			revalidate();
//			repaint();
//		}
//	}
//
//	public void run() {
//
//		corriendo = true;
//
//		while (corriendo) {
//			updateGame();
//			repaint();
//			try {
//				Thread.sleep(50);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public void updateGame() {
//		// this.addMouseListener(new MouseInput());
//		// ESTADO.values();
//
//		Puntuacion.puntos += 1;
//		fondo.update();
//		// songoku.update();
//		obstaculos.update();
//
//		if (Puntuacion.puntos >= 400) {
//			obstaculos.setMovementSpeed(obstaculos.getMovementSpeed() + (1 / 2));
//		}
//		
//		if (Puntuacion.puntos >= 800) {
//			obstaculos.setMovementSpeed(obstaculos.getMovementSpeed() + (1));
//		}
//		
//		if (Puntuacion.puntos >= 1500) {
//			obstaculos.setMovementSpeed(obstaculos.getMovementSpeed() + (1));
//		}
//
//		if (obstaculos.isOut()) {
//			revalidate();
//			repaint();
//		}
//		// El juego no tiene fin si no te chocas.
//	}
//
//	public void gameover() {
//		if (obstaculos.hasCollided()) {
//			
//			// DETENEMOS AUDIO DE JUEGO
//			musicajuego.stop();
//			
//			// AUDIO AL CHOCAR
//			musicacolision = new Audio("/audio/colision.wav");
//			musicacolision.start();
//
//			songoku.die();
//			revalidate();
//			repaint();
//			corriendo = false;
//			gameOver = true;
//			System.out.println("CHOQUE");
//			
//			
//
//			Puntuacion.añadir(LoginPanel.nombre, Puntuacion.puntos);
//			Puntuacion.ordenarArray();
//
//			Fichero f = new Fichero();
//			f.escribir();
//			
//			DragonBallRunner juego = new DragonBallRunner();
//			GameOver go = new GameOver(juego);
//			go.setVisible(true);
//
//			dispose();
//		}
//
//	}
//
//	public static PuntuacionPanel getPuntuacion() {
//		return puntuacion;
//	}
//
//	public static void setPuntuacion(PuntuacionPanel puntuacion) {
//		Runner.puntuacion = puntuacion;
//	}
//
//	public static void reset() {
//		// this.addMouseListener(new MouseInput());
//		Puntuacion.puntos = 0;
//		System.out.println("RESET");
//		obstaculos.resume();
//		gameOver = false;
//	}
//
//	public void keyTyped(KeyEvent e) {
//
//		if(e.getKeyChar() == ' ') {
//			
//			if (animacion == null || !corriendo) {
//				
//				System.out.println("COMIENZA EL JUEGO");
//
//				//MUSICA DE FONDO
//				musicajuego = new Audio("/audio/juego.wav");
//				musicajuego.start();
//	
//				//ANIMACION GOKU
//				animacion = new Thread();
//				animacion.start();    
//			
//				songoku.startRunning();
//				revalidate();
//				repaint();
//			} else {
//				songoku.jump();
//				revalidate();
//				repaint();
//			}			
//		}
//	}
//
//	public void keyPressed(KeyEvent e) {
//		// System.out.println("keyPressed: "+e);
//	}
//
//	public void keyReleased(KeyEvent e) {
//		// System.out.println("keyReleased: "+e);
//	}
//
//	public static void main(String[] args) {
//		javax.swing.SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				new Runner();
//			}
//		});
//	}
//
//}
