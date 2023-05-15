/**
	This interface is to help in drawing and manipulating graphical
	components. It is implemented by other classes.
	
	@author Gabriel L. Salvador (225593)
    @author Janel Zherry A. Esmeris (222455)
	@version May 15, 2023
**/

/*
	I have not discussed the Java language code in my program 
	with anyone other than my instructor or the teaching assistants 
	assigned to this course.

	I have not used Java language code obtained from another student, 
	or any other unauthorized source, either modified or unmodified.

	If any Java language code or documentation used in my program 
	was obtained from another source, such as a textbook or website, 
	that has been clearly noted with a proper citation in the comments 
	of my program.
*/

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