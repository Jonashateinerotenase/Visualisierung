package infovis.paracoords;

import infovis.scatterplot.Model;

import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseController implements MouseListener, MouseMotionListener {
	private View view = null;
	private Model model = null;
	Shape currentShape = null;
	private int x = 0;
	private int y = 0;
	
	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent arg0) {
		x = arg0.getX();
		y = arg0.getY();
		//Iterator<Data> iter = model.iterator();
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

	public void mouseMoved(MouseEvent e) {

	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

}
