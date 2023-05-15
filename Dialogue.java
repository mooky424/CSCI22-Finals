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
