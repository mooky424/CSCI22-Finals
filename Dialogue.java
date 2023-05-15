/**
	This class implements Sprite. It is responsible for
    showing game directions and guides to the player.
	
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

public class Dialogue implements Sprite{

    private int x,y,width,height,currentDialogue;
    private String[] dialogue = {
        "Opponent's Turn. Please Wait.",
        "Click \"Roll\" to begin.",
        "Click the dice you want to keep. You have 2 rolls left.",
        "Click the dice you want to keep. You have 1 roll left.",
        "Select your move by clicking a cell on the scoresheet."
    };

    public Dialogue(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        currentDialogue = 0;
    }

    public void setDialogue(int choice){
        currentDialogue = choice;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(null);
        g2d.setFont(new Font("Sans Serif", Font.BOLD, 16));
        g2d.drawString(dialogue[currentDialogue], (x + width/2) - g2d.getFontMetrics().stringWidth(dialogue[currentDialogue])/2, y+height/2 + 6);
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
