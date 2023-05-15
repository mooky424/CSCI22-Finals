/**
	This class implements the Sprite interface. It represents a player
    in the game and is responsible for their details and functionality.
	
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

import javax.swing.*;
import java.awt.*;

public class Player implements Sprite{

    private int id;
    private String[] scoresheet = {
        "", //ones
        "", //twos
        "", //threes
        "", //fours
        "", //fives
        "", //sixes
        "", //sum
        "", //three of a kind
        "", //four of a kind
        "", //full house
        "", //small straihgt
        "", //large straight
        "", //barbie
        "", //chance
        "", //yahtzee
        "" //total
    };
    private String username;
    private ImageIcon icon;
    private boolean turn;
    private int rollsLeft;

    private int x,y;

    public Player(String username, ImageIcon icon, int id){
        this.username = username;
        this.icon = icon;
        this.id = id;
    }

    public Player(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public void setDetails(String username, ImageIcon icon){
        this.username = username;
        this.icon = icon;
    }

    public String getUsername(){
        return username;
    }

    public ImageIcon getIcon(){
        return icon;
    }

    public void setTurn(boolean turn){
        this.turn = turn;
        rollsLeft = 3;
    }

    public boolean getTurn(){
        return turn;
    }

    public int getRolls(){
        return rollsLeft;
    }

    public void useRoll(){
        rollsLeft--;
    }

    public void updateScoresheet(int row, String score){
        scoresheet[row] = score;
        int sum = 0;
        int total = 0;
        for (int i = 0 ; i < 6; i++){
            if (scoresheet[i] == ""){
                sum = 0;
                break;
            } else {
                sum += Integer.parseInt(scoresheet[i]);
            }
        }
        for (int i = 0 ; i < 15; i++){
            if (scoresheet[i] == ""){
                total = 0;
                break;
            } else {
                total += Integer.parseInt(scoresheet[i]);
            }
        }
        scoresheet[6] = (sum == 0) ? "" : Integer.toString(sum); 
        scoresheet[15] = (total == 0) ? "" : Integer.toString(total); 
    }

    public String[] getScoresheet() {
        return scoresheet;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(icon.getImage(), x, y, null);
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