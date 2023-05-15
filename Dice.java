/**
	This class implements Sprite. It is responsible for drawing
    the dice and adjusting its properties.
	
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
import java.util.*;
import javax.swing.*;

public class Dice implements Sprite {

    private double x,y,width,height,rollPositionX,rollPositionY;
    private double[][] clickableArea = {
        {0,0},
        {0,0}
    };
    private ArrayList<Image> diceImages;

    private int number, keptPosition;
    private boolean rollable, opponentPlaying;

    public Dice(int x, int y, int width, int height){

        this.x = x;
        this.y = y;
        rollPositionX = x;
        rollPositionY = y;
        this.width = width;
        this.height = height;
        clickableArea[0][0] = x;
        clickableArea[0][1] = x+width;
        clickableArea[1][0] = y;
        clickableArea[1][1] = y+height;
        
        diceImages = new ArrayList<Image>();
        diceImages.add(new ImageIcon("./assets/dice/1.png").getImage());
        diceImages.add(new ImageIcon("./assets/dice/2.png").getImage());
        diceImages.add(new ImageIcon("./assets/dice/3.png").getImage());
        diceImages.add(new ImageIcon("./assets/dice/4.png").getImage());
        diceImages.add(new ImageIcon("./assets/dice/5.png").getImage());
        diceImages.add(new ImageIcon("./assets/dice/6.png").getImage());

        keptPosition = -1;
        number = 1;
        rollable = true;
    }

    public boolean onClickableArea(int x, int y){
        if ( x > clickableArea[0][0] && x < clickableArea[0][1] && y > clickableArea[1][0] && y < clickableArea[1][1]){
            return true;
        }
        return false;
    }

    public int click(int totalKept){
        if (rollable) {
            rollable = false;
            System.out.println("You kept a dice");
            keptPosition = totalKept;
            System.out.println("Kept pos: " + keptPosition);            
            rollPositionX = x;
            rollPositionY = y;
            return 1;
        } else {
            rollable = true;
            System.out.println("You released a dice");
            keptPosition = -1;
            return -1;
        }
    }

    public void roll(){
        if (rollable) {
            number = 1 + (int) (Math.random() * 6);
        }
    }

    public int getKeptPosition(){
        return keptPosition;
    }

    public void setKeptPosition(int position){
        keptPosition = position;
    }

    public void setRollPosition(double x, double y){
        rollPositionX = x;
        rollPositionY = y;
    }

    public double getRollPositionX(){
        return rollPositionX;
    }

    public double getRollPositionY(){
        return rollPositionY;
    }

    public boolean isKept(){
        return (keptPosition == -1) ? false : true;
    }

    public boolean isOpponentPlaying(){
        return opponentPlaying;
    }

    public void setValue(int value){
        number = value;
    }

    public int getValue(){
        return number;
    }

    public double[][] getClickableArea(){
        return clickableArea;
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(diceImages.get(number-1), (int) x, (int) y, (int) width, (int) height, null);
    }

    @Override
    public void adjustX(double distance) {
        x+=distance;
        clickableArea[0][0] = x;
        clickableArea[0][1] = x+width;
    }

    @Override
    public void adjustY(double distance) {
        y+=distance;
        clickableArea[1][0] = y;
        clickableArea[1][1] = y+height;
    }

    @Override
    public void adjustAngle(double degree) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adjustAngle'");
    }

    public void setX(double xPos){
        x = (int) xPos;
        clickableArea[0][0] = x;
        clickableArea[0][1] = x+width;
    }

    public void setY(double yPos){
        y = (int) yPos;
        clickableArea[1][0] = y;
        clickableArea[1][1] = y+width;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getAngle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAngle'");
    }
    
}