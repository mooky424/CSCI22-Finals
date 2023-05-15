/**
	This class implements the Sprite interface. It represents
    the roll button in the game.
	
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

public class Roll implements Sprite {

    private int x,y,width,height;
    private int[][] clickableArea = {
        {0,0},
        {0,0}
    };

    public Roll(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        clickableArea[0][0] = x;
        clickableArea[0][1] = x+width;
        clickableArea[1][0] = y;
        clickableArea[1][1] = y+height;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(x, y, width, height, 16, 16);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Sans Serif", Font.BOLD, 36));
        g2d.drawString("ROLL", (x + width/2) - g2d.getFontMetrics().stringWidth("ROLL")/2, y+height/2 + 14);
    }

    public boolean onClickableArea(int x, int y){
        if ( x > clickableArea[0][0] && x < clickableArea[0][1] && y > clickableArea[1][0] && y < clickableArea[1][1]){
            return true;
        }
        return false;
    }

    public void test(){        
        System.out.println("You clicked on roll");
    }

    @Override
    public void adjustX(double distance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adjustX'");
    }

    @Override
    public void adjustY(double distance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adjustY'");
    }

    @Override
    public void adjustAngle(double degree) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adjustAngle'");
    }

    @Override
    public double getX() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getX'");
    }

    @Override
    public double getY() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getY'");
    }

    @Override
    public double getAngle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAngle'");
    }    

}