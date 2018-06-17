package infovis.scatterplot;

import infovis.debug.Debug;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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

	        for (String l : model.getLabels()) {
				Debug.print(l);
				Debug.print(",  ");
				Debug.println("");
			}
	        Debug.println("ÖÖÖÖÖÖÖÖÖÖÖÖÖ");
			for (Range range : model.getRanges()) {
				Debug.print(range.toString());
				Debug.print(",  ");
				Debug.println("");
			}
			Debug.println("ÖÖÖÖÖÖÖÖÖÖÖÖÖ");
			for (Data d : model.getList()) {
				Debug.print(d.toString());
				Debug.println("");
			}
	        
			
			
			Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.clearRect(0, 0, getWidth(), getHeight());
            
            //Setting the overview window
            
            int anzval = model.getDim();
            
            float size; 
            
            if(getHeight() < getWidth()) size = (getHeight() / anzval)- 40/anzval;
            else size = (getWidth() / anzval) - 40/anzval;
            
            int tempX = 0;
	        for (String l : model.getLabels()) {
	        	g2D.drawString(l, 20 + size * tempX, 20);
	        	tempX ++;
			} 
	        g2D.rotate(1.5707963);
	        g2D.translate(0, -25);
	        
            tempX = 0;
	        for (String l : model.getLabels()) {
	        	g2D.drawString(l, 20 + size * tempX, 20);
	        	tempX ++;
			}
	        g2D.translate(0, 25);
	        g2D.rotate(-1.5707963);
            
            Rectangle2D Rect;
            for(int i = 0; i < anzval*anzval; i++)
            {
            	Rect =  new Rectangle2D.Double(0,0,0,0);
            	
            	Rect.setRect(20 + ((i%anzval)*size),20 + ((i/anzval)*size), size, size);
            	
            	g2D.draw(Rect);
            }
            tempX = 0;
            for (Range range : model.getRanges())
            {
            	g2D.drawString( String.valueOf(range.getMin()) ,20 + size * tempX ,(getHeight() -8));
            	g2D.drawString( String.valueOf(range.getMax()) ,(20 + size * (tempX+1))-40 ,(getHeight() -8));
            	tempX++;
            }
            for (Data d : model.getList()) 
            {
            	Rect =  new Rectangle2D.Double(0,0,0,0);
            	for(int i = 0; i < anzval;i++)
            	{
            		for(int j = 0; j < anzval; j++)
            		{
	            	/*Rect.setRect( 	(d.getValue(i) - 1973)*(size/(2004- 1973))*(size-(size/20))/size + 20,
	            					(d.getValue(i) - 1973)*(size/(2004- 1973))*(size-(size/20))/size + 20,
	            					size/20,//komplexe mathematische überlegungen um den punkt ins kästchen zu bekommen
	            					size/20);*/


			            	double tempMinX = model.getRanges().get(j).getMin();
			            	double tempMaxX = model.getRanges().get(j).getMax();

			            	double tempMinY = model.getRanges().get(i).getMin();
			            	double tempMaxY = model.getRanges().get(i).getMax();
            			
	                Rect.setRect( 	((d.getValue(j) - tempMinX)*(size/(tempMaxX- tempMinX))*(size-(size/20))/size + 20)+i*size,
	                				((d.getValue(i) - tempMinY)*(size/(tempMaxY- tempMinY))*(size-(size/20))/size + 20)+j*size,
	                				size/20,//komplexe mathematische überlegungen um den punkt ins kästchen zu bekommen
	                				size/20);    
	                
	            	g2D.setColor(d.getColor());
	            	g2D.fill(Rect);
	            	g2D.draw(Rect);
	            	}
            	}
			}
            /*if(markerRectangle.getWidth() < 0)
            {
            	markerRectangle.setRect(markerRectangle.getX() + markerRectangle.getWidth(),markerRectangle.getY(), -markerRectangle.getWidth(),markerRectangle.getHeight() );
            }*/
            g2D.draw(markerRectangle);
            

			
		}
		public void setModel(Model model) {
			this.model = model;
		}
}
