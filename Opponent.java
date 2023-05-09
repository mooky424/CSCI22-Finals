import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class Opponent implements Sprite {
    
    private String username;
    private int id;
    private int x, y;
    private double angle;
    private Image icon;

    public Opponent(String username, int icon){
        angle = 0;
        x = 0;
        y = 0;
    }

    public void draw(Graphics2D g2d){
        Image icon = new ImageIcon("./assets/icons/sample.png").getImage();
        g2d.drawImage(icon, x, y, 150, 150, null);
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
}
