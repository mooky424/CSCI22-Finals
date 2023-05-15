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