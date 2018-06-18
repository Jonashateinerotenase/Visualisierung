package infovis.paracoords;

import infovis.scatterplot.Data;
import infovis.scatterplot.Model;
import infovis.scatterplot.Range;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class View extends JPanel {
	private Model model = null;
	private Rectangle2D markerRectangle = new Rectangle2D.Double(0,0,0,0); 
	
	 public Rectangle2D getMarkerRectangle() {
		return markerRectangle;
	}
	@Override
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.clearRect(0, 0, getWidth(), getHeight());
        
        //calculate often used values
        int anzval = model.getDim();
        int firstLineX = (getWidth()/anzval)/2;
        int lineDist   = (getWidth()/anzval);
        //Draw lines (Very small rectangles)
        Rectangle2D line = new Rectangle2D.Double(0,0,0,0);
        for(int i = 0; i < anzval; i++)
        {
        	line.setRect( firstLineX + i *lineDist ,getHeight()*0.1, 1 ,getHeight()*0.8);
        	g2D.draw(line);        	
        }
        //Draw Labels of axis
        int tempX = 0;
        for (String l : model.getLabels()) {
        	g2D.drawString( String.valueOf(l) ,firstLineX + lineDist  * tempX -(String.valueOf(l).length()*3)  ,(int)(getHeight()*0.1) -20);
        	tempX ++;
		}
        //Draw minmax
        tempX = 0;
        for (Range range : model.getRanges())
        {
        	//Maxmin 
        	g2D.drawString( String.valueOf(range.getMin()) ,firstLineX + lineDist  * tempX  ,(int)(getHeight()*0.1)   );
        	g2D.drawString( String.valueOf(range.getMax()) ,firstLineX + lineDist  * tempX  ,(int)(getHeight()*0.9)+20);
        	tempX++;
        }
        //Draw actual lines wrt Values
        for (Data d : model.getList()) 
        {
        	boolean isRed = false;
        	Point2D[] point = new Point2D[anzval];
        	
        	for(int i = 0 ; i < anzval ; i++)
        	{
                double tempMinL = model.getRanges().get(i).getMin();
                double tempMaxL = model.getRanges().get(i).getMax();
        		point[i] = new Point2D.Double(firstLineX + i * lineDist, (int)(0.1 * getHeight() + (d.getValue(i) - tempMinL) / (tempMaxL - tempMinL) * 0.8 * getHeight() ));
        		if(markerRectangle.contains(point[i]))
        		{
        			isRed = true;
        			g2D.setColor(Color.RED);
        	
        		}
        	}
        	for(int i = 1 ; i < anzval ; i++)
        	{
        		g2D.drawLine((int)point[i-1].getX(),(int)point[i-1].getY(),(int)point[i].getX(),(int)point[i].getY());
        		
        		if(isRed) 
        		{
        			g2D.drawLine((int)point[i-1].getX(),(int)point[i-1].getY()+1,(int)point[i].getX(),(int)point[i].getY()+1);
        			g2D.drawLine((int)point[i-1].getX(),(int)point[i-1].getY()-1,(int)point[i].getX(),(int)point[i].getY()-1);
        		}
        	}
        	g2D.setColor(Color.BLACK);
        }
        g2D.draw(markerRectangle);

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
