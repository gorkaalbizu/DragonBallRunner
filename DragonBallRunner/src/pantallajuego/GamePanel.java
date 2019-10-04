package pantallajuego;

import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.lang.Thread;
import java.lang.Thread.State;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.DragonBallRunner;
import ventanas.GameOver;

public class GamePanel extends Thread implements Runnable, MouseListener {

	static JFrame Panel = new JFrame("Dragon Ball Runner");

	public static int WIDTH = 1000;
	public static int HEIGHT = 666;
	
	private static DragonBallRunner juego;


	public GamePanel(DragonBallRunner juego) {
		this.juego = juego;
		Panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container container = Panel.getContentPane();

		Runner gamePanel = new Runner(juego);
		Panel.addKeyListener(gamePanel);
		Panel.setFocusable(true);

		container.setLayout(new BorderLayout());

		container.add(gamePanel, BorderLayout.CENTER);

		Panel.setSize(WIDTH, HEIGHT);
		Panel.setLocation(200, 100);
		Panel.setResizable(false);
		Panel.setVisible(true);

		// HACER DESAPARECER Panel cuando un boton es pulsado.
		// container.removeAll();

		// Music musica = new Music("menu");
		// musica.run();
	}
	
	public static void dispose() {
		Panel.dispose();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	// public static void main(String[] args) {
	// GamePanel gp = new GamePanel();
	// gp.setVisible( true );
	// }

}
