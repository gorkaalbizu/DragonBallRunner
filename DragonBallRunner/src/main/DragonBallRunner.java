package main;

import java.awt.*;

import javax.swing.JFrame;

import java.lang.Thread;

import pantallajuego.FondoPanel;
import pantallajuego.GamePanel;
import pantallajuego.InicioPanel;
import pantallajuego.ObstaculosPanel;
import pantallajuego.PuntuacionPanel;
import pantallajuego.SonGokuPanel;
import objetos.EscuchaTeclado;
import objetos.Puntuacion;
import pantallajuego.Runner;
import ficheros.Fichero;
import main.DragonBallRunner;
import objetos.Audio;
import ventanas.MenuPanel;

public class DragonBallRunner implements Runnable{

	public static boolean jugando = false;
	private JFrame window;
	private Thread game;
	private GamePanel runner;
	
	public void jugar() {
		runner = new GamePanel(this);
	}
	
	public static void main(String[] args) {
		Fichero f = new Fichero();
		f.leer();
		DragonBallRunner dbr = new DragonBallRunner();
		Puntuacion.valorMayor();
		Puntuacion.ordenarArray();
		MenuPanel mp = new MenuPanel(dbr);
		mp.setVisible(true);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
