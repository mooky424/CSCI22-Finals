/**
	class desc w a min of two sentences
	
	@author Gabriel L. Salvador (225593)
	@version May ?, 2023
**/

import java.awt.*;
import javax.swing.*;

public class Opponent implements Sprite {
    
    private String username;
    private int id, x, y;
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
    private double angle;
    private ImageIcon icon;

    public Opponent(String username, ImageIcon icon, int id){
       this.username = username;
       this.icon = icon;
       this.id = id;
    }

    public Opponent(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2d){
        g2d.drawImage(icon.getImage(), x, y, null);
    }

    public void setID(int id){
        this.id = id;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getAngle(){
        return angle;
    }

    public void adjustX(double distance){
        x += distance;
    }

    public void adjustY(double distance){
        y += distance;
    }

    public void adjustAngle(double degree){
        angle += degree;
    }

    public void setDetails(String username, ImageIcon icon){
        this.username = username;
        this.icon = icon;
    }

    public void updateScoresheet(int row, String score){
        scoresheet[row] = score;
        int sum = 0;
        for (int i = 0 ; i < 6; i++){
            if (scoresheet[i] == ""){
                sum = 0;
                break;
            } else {
                sum += Integer.parseInt(scoresheet[i]);
            }
        }
        scoresheet[6] = (sum == 0) ? "" : Integer.toString(sum); 
    }

    public String[] getScoresheet() {
        return scoresheet;
    }

    public String getUsername(){
        return username;
    }

    public ImageIcon getIcon(){
        return icon;
    }

    public int getId(){
        return id;
    }

}