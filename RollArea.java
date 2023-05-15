/**
	This class implements the Sprite interface. It represents
    the area where the dice can be rolled in the game.
	
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

public class RollArea implements Sprite{

    private int x,y,width,height;
    private int[][] rollableArea = {
        {0,0},
        {0,0}
    };
    
    public RollArea(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        rollableArea[0][0] = x+10; //make room for curved edges
        rollableArea[0][1] = x+width-20;
        rollableArea[1][0] = y+10;
        rollableArea[1][1] = y+height-20;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(x, y, width, height, 16, 16);
    }

    public boolean onRollableArea(int x, int y){
        if ( x > rollableArea[0][0] && x < rollableArea[0][1] && y > rollableArea[1][0] && y < rollableArea[1][1]){
            return true;
        }
        return false;
    }

    @Override
    public void adjustX(double distance) {
        x += distance;
    }

    @Override
    public void adjustY(double distance) {
        y += distance;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }
}
