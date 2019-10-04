package objetos;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import pantallajuego.ObstaculosPanel;
import pantallajuego.Runner;
import pantallajuego.SonGokuPanel;


public class EscuchaTeclado implements KeyListener{
	public Runner runner;
	static ObstaculosPanel obstaculos;
	static SonGokuPanel songoku;
	public JFrame window;
	private Audio musicajuego;

	 
	private static boolean corriendo = false;
	
	public EscuchaTeclado(Runner runner, JFrame window) {
		this.runner = runner;
		this.window = window;
	}
	@Override
	public void keyTyped(KeyEvent e) {	
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		
		if (e.getKeyCode()== KeyEvent.VK_SPACE) {
			if(e.getKeyChar() == ' ') {
				if (!corriendo) {	
					System.out.println("COMIENZA EL JUEGO");
					
					//CERRAR VENTANA MENU Y PARA SU MUSICA
					//MenuPanel.dispose();
					//musicamenu.stop();
					
					//MUSICA DE FONDO
					musicajuego = new Audio("/audio/juego.wav");
					musicajuego.start();
					
					runner.run();  
				} else {
					songoku.jump();
				}			
			}
		}
	}
		

	
	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	}