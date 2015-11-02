/**
 * 
 */
package jade;

import java.awt.Point;

import drawing.DrawingException;
import drawing.DrawingFrame;

/**
 * @author Eddy El Khatib
 *
 */
public class DrawingMachine implements JadeMachine{
	
	private DrawingFrame drawingFrame;
	private int stepLength;
	private boolean penMode;
	
	public DrawingMachine(){
		this.drawingFrame = new DrawingFrame();
		this.drawingFrame.reset();
		this.stepLength = 1;
		this.penMode = false;
	}
	
	public DrawingMachine(DrawingFrame drawingFrame){
		this.drawingFrame = drawingFrame;
		this.drawingFrame.reset();
		this.stepLength = 1;
		this.penMode= false;
	}
	
	@Override
	public void setStepLength(int n) {
		this.stepLength = n;
	}

	@Override
	public void setPenMode(boolean active) {
		this.penMode = active;
	}

	@Override
	public void move(Direction d) {
		if(this.penMode){
			Point newPoint = new Point(this.drawingFrame.getCurrentPoint().getLocation());
			newPoint.translate(d.getX()*this.stepLength, d.getY()*this.stepLength);
			try {
				this.drawingFrame.drawTo(newPoint);
			} catch (DrawingException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void jump(int x, int y) {
		Point newPoint = new Point(x,y);
		try {
			this.drawingFrame.goTo(newPoint);
		} catch (DrawingException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void bye(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
