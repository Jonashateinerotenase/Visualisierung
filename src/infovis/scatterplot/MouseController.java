package infovis.scatterplot;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

public class MouseController implements MouseListener, MouseMotionListener {

	private Model model = null;
	private View view = null;
	private int x = 0;
	private int y = 0;

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
		x = arg0.getX();
		y = arg0.getY();
		Iterator<Data> iter = model.iterator();
		view.getMarkerRectangle().setRect(x,y,0,0);
		view.repaint();
	}

	public void mouseReleased(MouseEvent arg0) {
	}

	public void mouseDragged(MouseEvent arg0) {

		double totalHeight = arg0.getY() - y;
		if(totalHeight < 0)totalHeight = -totalHeight;
		double totalWidth = arg0.getX() - x;
		if(totalWidth < 0)totalWidth = -totalWidth;		
		
		if(x < arg0.getX() && y < arg0.getY())view.getMarkerRectangle().setRect(         x,           y,totalWidth,totalHeight);		
		if(x > arg0.getX() && y < arg0.getY())view.getMarkerRectangle().setRect(arg0.getX(),          y,totalWidth,totalHeight);
		if(x < arg0.getX() && y > arg0.getY())view.getMarkerRectangle().setRect(         x, arg0.getY(),totalWidth,totalHeight);		
		if(x > arg0.getX() && y > arg0.getY())view.getMarkerRectangle().setRect(arg0.getX(),arg0.getY(),totalWidth,totalHeight);		

		
		view.repaint();
	}

	public void mouseMoved(MouseEvent arg0) {
	}

	public void setModel(Model model) {
		this.model  = model;	
	}

	public void setView(View view) {
		this.view  = view;
	}

}
