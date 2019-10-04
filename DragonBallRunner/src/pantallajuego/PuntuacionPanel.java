package pantallajuego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import objetos.Puntuacion;

public class PuntuacionPanel extends JPanel{
	

	private Puntuacion puntuacion;
	
	public PuntuacionPanel(Puntuacion puntuacion) {
		actualizar();
		this.puntuacion = puntuacion;
	}
	public void actualizar() {
		this.repaint();
	}
	@Override
	public void paintComponent(Graphics g) {
		//RECORD
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(300, 40, 100, 40, 5, 5);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString("RECORD", 265, 37);
		g.drawString(String.valueOf(puntuacion.puntMax()), 305, 75);
		//PUNTUACION ACTUAL
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(600, 40, 100, 40, 5, 5);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.setColor(Color.BLACK);
		g.drawString("ACTUAL", 575, 37);
		g.drawString(String.valueOf(Puntuacion.puntos), 605, 75);
	}
	public static void main(String[] args) {
		
	}
}
