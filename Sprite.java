/**
	class desc w a min of two sentences
	
	@author Gabriel L. Salvador (225593)
	@version May ?, 2023
**/

import java.awt.*;

public interface Sprite {
	public abstract void draw(Graphics2D g2d);
	public abstract void adjustX(double distance);
	public abstract void adjustY(double distance);
	public abstract void adjustAngle(double degree);
	public abstract double getX();
	public abstract double getY();
	public abstract double getAngle();
}