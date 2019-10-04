package pantallajuego;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import ventanas.RecursoGrafico;
//import pantallajuego.InicioPanel;

public class InicioPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
    	Image imagen1 = new RecursoGrafico().getResourceImage("/img/inicio.png");
		g.drawImage(imagen1, 0, 0, null);
    }  		
    
	public static void main(String[] args) {
		InicioPanel i = new InicioPanel();
		i.setVisible( true );
	}

}
