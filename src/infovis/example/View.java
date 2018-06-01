package infovis.example;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

import javax.swing.JPanel;

public class View extends JPanel {
	private Model model = null;

	@Override
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D)g;
		g2D.scale(1, 1);
        
	    Color color2 = Color.YELLOW;
		
	        
        Rectangle2D rect = new Rectangle2D.Double(50,50,200,200);
        Rectangle2D rect2 = new Rectangle2D.Double(10,10,100,50);
        Ellipse2D.Double elli;
        
        for(int i=0; i<=100; i++)
        {
        	elli = new Ellipse2D.Double(i*2,i*2,i*3,i*3);
        	g2D.setColor(new Color(i*2,255-i,0));
        	g2D.fill(elli);
        }
        
                
        //g2D.fill(rect);
        
        //Color color2 = Color.RED;
        //g2D.draw(rect);
        
        //g2D.fill(rect2);
        
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
}
