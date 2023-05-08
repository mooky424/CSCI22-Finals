import java.awt.*;

public class Opponent implements Sprite {
    
    double x, y, angle;

    public Opponent(double x, double y){
        this.x = x;
        this.y = y;
        angle = 0;
    }

    public void draw(Graphics2D g2d){

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
