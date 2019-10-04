package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JPanel;

import objetos.Puntuacion;
import ventanas.RecursoGrafico;

public class GameOverPanel extends JPanel{
		
	public GameOverPanel() {
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 80));
		g.drawString(String.valueOf(Puntuacion.puntos), 610, 400);
		

	}
}
