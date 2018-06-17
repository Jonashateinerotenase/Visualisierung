package infovis.scatterplot;

import infovis.debug.Debug;

import java.awt.Color;
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
            	//Maxmin below
            	g2D.drawString( String.valueOf(range.getMin()) ,20 + size  * tempX         ,30+size*(anzval));
            	g2D.drawString( String.valueOf(range.getMax()) ,(20 + size * (tempX+1))-40 ,30+size*(anzval));
            	//Maxmin beneath
            	g2D.drawString( String.valueOf(range.getMin()) ,size * anzval + 20, 30 + tempX* size);
            	g2D.drawString( String.valueOf(range.getMax()) ,size * anzval + 20, 20 + (tempX+1)* size); 	
            	
            	
            	
            	tempX++;
            }
            
            
            Rect =  new Rectangle2D.Double(0,0,0,0);
            //Fuer alle Rechtecker erstmal schauen, ob sie im marker sind & entsprechend die korrespondierende data färben
            for (Data d : model.getList()) 
            {
            	d.setColor(Color.BLACK);
            	for(int i = 0; i < anzval;i++)
            	{
            		for(int j = 0; j < anzval; j++)
            		{

			            double tempMinX = model.getRanges().get(j).getMin();
			            double tempMaxX = model.getRanges().get(j).getMax();

			            double tempMinY = model.getRanges().get(i).getMin();
			            double tempMaxY = model.getRanges().get(i).getMax();
            			
		                Rect.setRect( 	((d.getValue(j) - tempMinX)*(size/(tempMaxX- tempMinX))*(size-(size/20))/size + 20)+i*size,
		                				((d.getValue(i) - tempMinY)*(size/(tempMaxY- tempMinY))*(size-(size/20))/size + 20)+j*size,
		                				size/20,//komplexe mathematische überlegungen um den punkt ins kästchen zu bekommen
		                				size/20);    
		                
		                if(markerRectangle.contains(Rect))
		                {
		                	d.setColor(Color.RED);
		                }
           
	            	}
            	}          	
            	
            	
            }
            //Dann NOCHMAL ueber alle Rechtecke, um sie zu färben.
            for (Data d : model.getList()) //Effizient ist anders.
            {        	
            	
            	
            	
            	for(int i = 0; i < anzval;i++)
            	{
            		for(int j = 0; j < anzval; j++)
            		{

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

            g2D.draw(markerRectangle);
            

			
		}
		public void setModel(Model model) {
			this.model = model;
		}
}
