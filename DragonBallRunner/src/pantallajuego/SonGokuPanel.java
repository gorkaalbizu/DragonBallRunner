package pantallajuego;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import ventanas.GameOver;
import ventanas.GameOverPanel;
import ventanas.RecursoGrafico;
import main.DragonBallRunner;
import pantallajuego.FondoPanel;
import pantallajuego.ObstaculosPanel;
import pantallajuego.Runner;

public class SonGokuPanel extends JPanel {

	private static int MinY, MaxY, StartX, EndX; // Indican valores maximos y minimos que tendran las coordenadas del
													// personaje.
	private static int Top, Bottom, topPoint; // Indican la posicion del personaje (Top- coord. Y de parte superior,
												// Bottom- Y de parte inferior, topPoint - maxima altura de ventana)

	private static boolean topPointReached;
	private static int jumpFactor = 20;

	public static final int CORRIENDO = 1, SALTANDO = 2, MUERTE = 3;
	private final int MOV1 = 1, MOV2 = 2, MOV3 = 3, MOV4 = 4, MOV5 = 5, MOV6 = 6, MOV7 = 7, MOV8 = 8;

	private static int state;

	private int mov;

	static BufferedImage imagen1;
	BufferedImage imagen2;
	BufferedImage imagen3;
	BufferedImage imagen4;
	BufferedImage imagen5;
	BufferedImage imagen6;
	BufferedImage imagen7;
	BufferedImage imagen8;
	BufferedImage imagen9;
	BufferedImage imagen10;
	BufferedImage imagen11;
	BufferedImage imagen12;
	BufferedImage imagenMuerte;

	public SonGokuPanel() {
		// Corriendo
		imagen1 = new RecursoGrafico().getResourceImage("/img/goku-1.png");
		imagen2 = new RecursoGrafico().getResourceImage("/img/goku-2.png");
		imagen3 = new RecursoGrafico().getResourceImage("/img/goku-3.png");
		imagen4 = new RecursoGrafico().getResourceImage("/img/goku-4.png");
		imagen5 = new RecursoGrafico().getResourceImage("/img/goku-5.png");
		imagen6 = new RecursoGrafico().getResourceImage("/img/goku-6.png");
		imagen7 = new RecursoGrafico().getResourceImage("/img/goku-7.png");
		imagen8 = new RecursoGrafico().getResourceImage("/img/goku-8.png");

		imagen9 = new RecursoGrafico().getResourceImage("/img/gokusalto-1.png");
		imagen10 = new RecursoGrafico().getResourceImage("/img/gokusalto-2.png");
		imagen11 = new RecursoGrafico().getResourceImage("/img/gokusalto-3.png");

		imagenMuerte = new RecursoGrafico().getResourceImage("/img/goku-1.png");

		MinY = 0 + 500; // Parte inferior del rectangulo del personaje
		MaxY = 0 - imagen1.getHeight() + 500; // Parte superior del rectangulo del personaje
		StartX = 100; // coordenada X del personaje (parte izquierda de rectangulo)
		EndX = StartX + imagen1.getWidth(); // coordenada X del personaje (parte derecha de rectangulo)
		topPoint = MaxY - 140; // altura que alcanza el personaje al saltar

		state = 1;
		mov = MOV1;
	}

	public void paintComponent(Graphics g) {
		Bottom = Top + imagen1.getHeight();

		switch (state) {

		case CORRIENDO:
			if (mov == MOV1) {
				mov = MOV2;
				g.drawImage(imagen1, StartX, MaxY, null);
			} else if (mov == MOV2) {
				mov = MOV3;
				g.drawImage(imagen2, StartX, MaxY, null);
			} else if (mov == MOV3) {
				mov = MOV4;
				g.drawImage(imagen3, StartX, MaxY, null);
			} else if (mov == MOV4) {
				mov = MOV5;
				g.drawImage(imagen4, StartX, MaxY, null);
			} else if (mov == MOV5) {
				mov = MOV6;
				g.drawImage(imagen5, StartX, MaxY, null);
			} else if (mov == MOV6) {
				mov = MOV7;
				g.drawImage(imagen6, StartX, MaxY, null);
			} else if (mov == MOV7) {
				mov = MOV8;
				g.drawImage(imagen7, StartX, MaxY, null);
			} else if (mov == MOV8) {
				mov = MOV1;
				g.drawImage(imagen8, StartX, MaxY, null);
			}
			break;

		case SALTANDO:
			if (Top > topPoint && !topPointReached) {
				g.drawImage(imagen9, StartX, Top -= jumpFactor, null);
				break;
			}
			if (Top >= topPoint && !topPointReached) {
				topPointReached = true;
				g.drawImage(imagen10, StartX, Top += jumpFactor, null);
				break;
			}
			if (Top > topPoint && topPointReached) {
				if (MaxY == Top && topPointReached) {
					g.drawImage(imagen10, StartX, Top, null);
					state = CORRIENDO;
					topPointReached = false;
					break;
				}
				g.drawImage(imagen11, StartX, Top += jumpFactor, null);
				break;
			}
		case MUERTE:
			g.drawImage(imagenMuerte, StartX, Top, null);
			break;
		}
	}

	public void die() {
		state = MUERTE;
	}

	public static Rectangle getSonGoku() {
		Rectangle songoku = new Rectangle();
		songoku.x = StartX + 25; // lo aumentamos para que no colisionen los pixeles transparentes de la parte
									// izquierda del png al superar un obstaculo por poco.

		if (state == SALTANDO && !topPointReached)
			songoku.y = Top - jumpFactor;
		else if (state == SALTANDO && topPointReached)
			songoku.y = Top + jumpFactor;
		else if (state != SALTANDO)
			songoku.y = Top;

		songoku.width = imagen1.getWidth() - 50; // lo reducimos para que no colisionen los pixeles transparentes de la
													// parte derecha del png
		songoku.height = imagen1.getHeight(); // No afecta a choques

		return songoku;
	}

	public void startRunning() {
		Top = MaxY;
		state = CORRIENDO;
	}

	public void jump() {
		Top = MaxY;
		topPointReached = false;
		state = SALTANDO;
	}

	public static void main(String[] args) {

	}
}
