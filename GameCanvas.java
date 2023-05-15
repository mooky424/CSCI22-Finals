/**
	class desc w a min of two sentences
	
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

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class GameCanvas extends JComponent{

    private File backgroundFile = new File("./assets/background/YahtzieGameScreenBg.png");
    private Image background;

    private Player p; //dummy for graphics
    private Opponent o; //dummy for graphics
    
    private Dialogue d;
    private RollArea ra;
    private ArrayList<Dice> gameDice;
    private Roll r;
    
    public GameCanvas(int w, int h) {
        
        setBounds(0,1,w,h);
        
        setPreferredSize(new Dimension(w, h));
        
        try {
            background = ImageIO.read(backgroundFile);
        } catch (IOException ex) {
            System.out.println("Error loading background");
        }

        p = new Player(20,598);
        o = new Opponent(592,27);

        d = new Dialogue(163, 184, 411, 72);
        ra = new RollArea(20, 266, 697, 318);
        r = new Roll(737,676,267,72);

        gameDice = new ArrayList<Dice>();        
        for( int i = 0; i < 6; i++){
            gameDice.add(new Dice(61+(95*i),385,80,80));
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2d.setRenderingHints(rh);

        g2d.drawImage(background, 0,1, null);

        p.draw(g2d);
        o.draw(g2d);

        d.draw(g2d);
        ra.draw(g2d);
        r.draw(g2d);

        for (int i = 0; i < gameDice.size(); i++){
            gameDice.get(i).draw(g2d);
        }
    }

    public Object getSprite(int x, int y){
        if (r.onClickableArea(x, y)){
            return r; 
        } 
        for (Dice d : gameDice) {
            if (d.onClickableArea(x, y)){
                return d;
            }
        }
        return null;
    }

    public void rollDice(){
        for (Dice d : gameDice) {
            d.roll();
        }
    }

    public Roll getRoll(){
        return r;
    }

    public ArrayList<Dice> getGameDice(){
        return gameDice;
    }

    public void setDice(int[] values){
        for (int i = 0; i < gameDice.size(); i++){
            System.out.println("setting dice " + gameDice.get(i).getValue() + " to " + values[i]);
            gameDice.get(i).setValue(values[i]);
        }
        repaint();
    }

    public void opponentKeptDice(int index, int position){
        gameDice.get(index).setKeptPosition(position);
    }

    public int findDice(Dice d){
        return gameDice.indexOf(d);
    }

    public void setDialogue(int choice){
        d.setDialogue(choice);
        repaint();
    }

    public void setPlayers(Player p, Opponent o){
        System.out.println("Setting up canvas players");
        this.p.setDetails(p.getUsername(), (ImageIcon) p.getIcon());
        this.o.setDetails(o.getUsername(), (ImageIcon) p.getIcon());
    }

    public void moveDiceToKeptPlayerPosition(ArrayList<Dice> dice){
        int arrangeDice = 0; 
        for (Dice d : dice){
            if (d.isKept()){
                if (d.getKeptPosition() == arrangeDice){
                    arrangeDice++;
                } else if (d.getKeptPosition() != arrangeDice){
                    d.setKeptPosition(arrangeDice++);
                }                
            }
            
            double endX = (d.getKeptPosition() >=0) ? 162 + 95*(d.getKeptPosition()) : d.getRollPositionX();
            double endY = (d.getKeptPosition() >=0) ? 630 : d.getRollPositionY();

            int duration = 300; //Animation duration in ms
            int delay = 15;
            
            double x = d.getX();
            double distanceX = endX - x;
            double speedX = Math.abs(distanceX)/(duration/delay);
            double directionX = (distanceX < 0) ? -1 : 1;
            double velocityX = speedX * directionX;
            
            double y = d.getY();
            double distanceY = endY - y;                
            double speedY = Math.abs(distanceY)/(duration/delay);
            double directionY = (distanceY < 0) ? -1 : 1;
            double velocityY = speedY * directionY;

            Thread animationX = new Thread() {
                double distanceX = endX - d.getX();

                public void run(){
                    while(Math.abs(distanceX) > 10){
                        repaint();
                        if (distanceX > 5 || distanceX < -5)
                        d.adjustX(velocityX);
                        try {
                            sleep(delay);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        distanceX = endX - d.getX();   
                    }
                    d.setX(endX);
                    repaint();
                    this.interrupt();
                }
            };
            Thread animationY = new Thread() {
                double distanceY = endY - d.getY();

                public void run(){
                    while(Math.abs(distanceY) > 10){
                        repaint();
                        if (distanceY > 5 || distanceY < -5)
                            d.adjustY(velocityY);
                        try {
                            sleep(delay);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        distanceY = endY - d.getY();                  
                    }
                    d.setY(endY);
                    repaint();
                    this.interrupt();
                }
            };
            animationX.start();
            animationY.start();
        }
    }

    public void moveDiceToKeptOpponentPosition(ArrayList<Dice> dice){
        int arrangeDice = 0; 
        for (Dice d : dice){
            if (d.isKept()){
                if (d.getKeptPosition() == arrangeDice){
                    arrangeDice++;
                } else if (d.getKeptPosition() != arrangeDice){
                    d.setKeptPosition(arrangeDice++);
                }                
            }
            
            double endX = (d.getKeptPosition() >=0) ? 20 + 95*(d.getKeptPosition()) : d.getRollPositionX();
            double endY = (d.getKeptPosition() >=0) ? 59 : d.getRollPositionY();

            int duration = 300; //Animation duration in ms
            int delay = 15;
            
            double x = d.getX();
            double distanceX = endX - x;
            double speedX = Math.abs(distanceX)/(duration/delay);
            double directionX = (distanceX < 0) ? -1 : 1;
            double velocityX = speedX * directionX;
            
            double y = d.getY();
            double distanceY = endY - y;                
            double speedY = Math.abs(distanceY)/(duration/delay);
            double directionY = (distanceY < 0) ? -1 : 1;
            double velocityY = speedY * directionY;

            Thread animationX = new Thread() {
                double distanceX = endX - d.getX();

                public void run(){
                    while(Math.abs(distanceX) > 10){
                        repaint();
                        if (distanceX > 5 || distanceX < -5)
                        d.adjustX(velocityX);
                        try {
                            sleep(delay);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        distanceX = endX - d.getX();   
                    }
                    d.setX(endX);
                    repaint();
                    this.interrupt();
                }
            };
            Thread animationY = new Thread() {
                double distanceY = endY - d.getY();

                public void run(){
                    while(Math.abs(distanceY) > 10){
                        repaint();
                        if (distanceY > 5 || distanceY < -5)
                            d.adjustY(velocityY);
                        try {
                            sleep(delay);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        distanceY = endY - d.getY();                  
                    }
                    d.setY(endY);
                    repaint();
                    this.interrupt();
                }
            };
            animationX.start();
            animationY.start();
        }
    }

}