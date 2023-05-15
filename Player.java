/**
	class desc w a min of two sentences
	
	@author Gabriel L. Salvador (225593)
	@version May ?, 2023
**/

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