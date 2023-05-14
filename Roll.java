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
        clickableArea[1][1] = y+width;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(x, y, width, height, 5, 5);
        g2d.drawString("Roll", x+20, y+20);
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
