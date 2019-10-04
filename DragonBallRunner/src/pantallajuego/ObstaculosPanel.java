package pantallajuego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JPanel;

import pantallajuego.FondoPanel;
import pantallajuego.SonGokuPanel;
import pantallajuego.Runner;
import pantallajuego.PuntuacionPanel;
import ventanas.RecursoGrafico;

public class ObstaculosPanel extends JPanel{
	private class Obstacle {
	BufferedImage image;
	int x;
	int y;
	
	Rectangle getObstacle() {
		Rectangle obstacle = new Rectangle();
	    obstacle.x = x + 10; //mejoramos colisiones reduciendo un poco el area de choque por la parte izquierda
	    obstacle.y = y; //no afecta a choques
	    obstacle.width = image.getWidth() - 10; //mejoramos colisiones reduciendo un poco el area de choque por la parte derecha
	    obstacle.height = image.getHeight() + 10; //mejoramos colisiones reduciendo un poco el area de choque por la parte superior
	
	    return obstacle;
	    }  
	}
  
	private int firstX;
	private int obstacleMinInterval;
	private int obstacleMaxInterval;
	private int movementSpeed;
	  
	private ArrayList<BufferedImage> imageList;
	private ArrayList<Obstacle> obList;
	
	private Obstacle blockedAt;
	  
	public ObstaculosPanel(int firstPos) {
		obList = new ArrayList<Obstacle>();
	    imageList = new ArrayList<BufferedImage>();
	    
	    firstX = firstPos;
	    obstacleMinInterval = 600;
	    obstacleMaxInterval = 900;
	    movementSpeed = 30;
	    
	    imageList.add(new RecursoGrafico().getResourceImage("/img/bola-3.png"));
	    imageList.add(new RecursoGrafico().getResourceImage("/img/bola-4.png"));
	    imageList.add(new RecursoGrafico().getResourceImage("/img/bola-7.png"));
	    imageList.add(new RecursoGrafico().getResourceImage("/img/bola-1.png"));
	    imageList.add(new RecursoGrafico().getResourceImage("/img/bola-6.png"));
	    imageList.add(new RecursoGrafico().getResourceImage("/img/bola-2.png"));
	    imageList.add(new RecursoGrafico().getResourceImage("/img/bola-5.png"));
	
	    int x = firstX;
	    
	    for(BufferedImage bi : imageList) {
	      
	    	Obstacle ob = new Obstacle();
	      
	    	ob.image = bi;
	    	ob.x = x; 
	    	ob.y = 0 - bi.getHeight() + 500; //EN CLASE FONDO VARIABLE FONDO_Y
	    	Random r = new Random();
	    	x += r.nextInt(obstacleMaxInterval-obstacleMinInterval) +  obstacleMinInterval; //La distancia entre obstaculos sera alatoria entre 500-900 pixels
	      
	    	obList.add(ob);
	    	}
	  	}
	
	public void update() {	  
    	Collections.shuffle(obList);;
	  	for (Obstacle ob : obList) {
	  		ob.x -= movementSpeed;
	  	}
	}
	  
	public void paintComponent(Graphics g) {
		for(Obstacle ob : obList) {
			//g.setColor(Color.Black);
			//g.drawRect(ob.getObstacle().x, ob.getObstacle().y, ob.getObstacle().width, ob.getObstacle().height);
			g.drawImage(ob.image, ob.x, ob.y, null);
	    }
	  }
	  
	  public boolean hasCollided() {
		  for(Obstacle ob : obList) {
			  if(SonGokuPanel.getSonGoku().intersects(ob.getObstacle())) {
				  System.out.println("SonGoku = " + SonGokuPanel.getSonGoku() + "\nObstacle = " + ob.getObstacle() + "\n\n");
				  blockedAt = ob;
				  return true;
			  }   
	    }
	    return false;
	  }
	
	  public boolean isOut() {
		  for(Obstacle ob : obList) {
			  if(ob.x <= 0) {
				  obList.remove(ob);
				  Obstacle newob = new Obstacle();
				  newob.image = ob.image;
				  newob.x = ob.x + 5000; //al borrar un obstaculo lo recreamos a 4900 pixels - valor aproximado (7 obstaculos * (600+900)/2 pixels de obstacleInterval)
				  newob.y = ob.y;
				  obList.add(newob);
				  
				  blockedAt = ob;
				  return true;
			  }
		  }
		  return false;
	  }
	  
	  public int getMovementSpeed() {
		return movementSpeed;
	}

	public void setMovementSpeed(int movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	public void resume() {
		  // this.obList = null;
		  int x = firstX/2;   
		  obList = new ArrayList<Obstacle>();
		    
		  for(BufferedImage bi : imageList) {
		      
			  Obstacle ob = new Obstacle();
		      
		      ob.image = bi;
		      ob.x = x;
		      ob.y = 0 - bi.getHeight() + 500;
		      Random r = new Random();
		      x += r.nextInt(obstacleMaxInterval-obstacleMinInterval) +  obstacleMinInterval;
		      
		      obList.add(ob);
		    }
	  }
	public static void main(String[] args) {
		
	}
	  
}
