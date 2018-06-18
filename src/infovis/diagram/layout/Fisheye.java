package infovis.diagram.layout;

import infovis.debug.Debug;
import infovis.diagram.Model;
import infovis.diagram.View;
import java.awt.geom.Point2D;
import java.lang.Math;
import infovis.diagram.elements.Edge;
import infovis.diagram.elements.Element;
import infovis.diagram.elements.Vertex;

import java.util.Iterator;

/*
 * 
 */

public class Fisheye implements Layout{

	int fishEyeCenterX = 0;
	int fishEyeCenterY = 0;
	
	public void setMouseCoords(int x, int y, View view) {
		setFish(x,y);
	}

	public Model transform(Model model, View view) {
		// TODO Auto-generated method stub
		double d = 2;
		
		Point2D center= new Point2D.Double(fishEyeCenterX , fishEyeCenterY);
		
		Model model2 = new Model();

		for (Vertex element: model.getVertices())
		{
			model2.addVertex(new Vertex(element.getX(), element.getY()));
		}
		for (int i = 0; i < model.getEdges().size(); i++)
		{
			
		}			
		for (Element element: model2.getElements())
		{
			
			Debug.p(String.valueOf(fishEyeCenterX));
			double DmaxX = view.getWidth() - center.getX();
			double DmaxY = view.getHeight() - center.getY();
			if (element.getX()  < center.getX()) DmaxX = - center.getX();
			if (element.getY()  < center.getY()) DmaxY = - center.getY();
			double DnormX = element.getX() - center.getX();
			double DnormY = element.getY() - center.getY();
			element.setX( G(DnormX / DmaxX,d) * DmaxX + center.getX() );
			element.setY( G(DnormY / DmaxY,d) * DmaxY + center.getY() );
			
		}
		for (Vertex element: model2.getVertices())
		{
			double distX = element.getCenterX()+element.getWidth()  - center.getX();
			double distY = element.getCenterY()+element.getHeight() - center.getY();
			double totalDist = Math.sqrt(distX*distX+distY*distY);
			
			double zoom = 5000/(totalDist+50);
			
			double ratio = element.getWidth()/element.getHeight(); 
			
			element.setHeight(zoom);
			element.setWidth(zoom*ratio);
			
		}
		return model2;
	}
	private double G(double x,double d)
	{
		return (x * (d + 1))  /  (d * x + 1);
	}
	
	public void setFish(int x, int y)
	{
		fishEyeCenterX = x;
		fishEyeCenterY = y;
	}
	
	
}
