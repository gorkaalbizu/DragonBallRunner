package pantallajuego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import ventanas.RecursoGrafico;

public class FondoPanel extends JPanel {

	private class ImagenFondo {
		BufferedImage image;
		int x;
		int y;
	}

	private BufferedImage image;
	private ArrayList<ImagenFondo> imagenes;

	public FondoPanel() {
		try {
			image = new RecursoGrafico().getResourceImage("/img/fondo.png");
		} catch (Exception e) {
			e.printStackTrace();
		}

		imagenes = new ArrayList<ImagenFondo>();

		// primera imagen de fondo:
		for (int i = 0; i < 3; i++) {
			ImagenFondo obj = new ImagenFondo();
			obj.image = image;
			obj.x = 0;
			obj.y = 0;
			imagenes.add(obj);
		}
	}

	public void update() {
		Iterator<ImagenFondo> looper = imagenes.iterator();
		ImagenFondo first = looper.next();

		first.x -= 10;

		int previousX = first.x;
		while (looper.hasNext()) {
			ImagenFondo next = looper.next();
			next.x = previousX + image.getWidth();
			previousX = next.x;
		}

		if (first.x < -image.getWidth()) {
			imagenes.remove(first);
			first.x = previousX + image.getWidth();
			imagenes.add(first);
		}

	}

	public void paintComponent(Graphics g) {

		for (ImagenFondo img : imagenes) {
			g.drawImage(img.image, (int) img.x, img.y, null);
		}
	}

	public static void main(String[] args) {

	}
}
