import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class GameCanvas extends JComponent{

    private double width, height;

    private Player p; //dummy for graphics
    private Opponent o; //dummy for graphics
    
    private String[] dialogue = {
        "Click \"Roll\" to begin.",
        "Click the dice you want to keep. You have 2 rolls left.",
        "Click the dice you want to keep. You have 1 roll left.",
        "Select your move by clicking a cell on the scoresheet."
    };
    private ArrayList<Dice> gameDice;
    private Roll r;

    public GameCanvas(int w, int h) {

        setBounds(0,0,w,h);

        width = w;
        height = h;
        setPreferredSize(new Dimension(w, h));

        p = new Player(300,400);
        o = new Opponent(500,400);

        gameDice = new ArrayList<Dice>();        
        for( int i = 0; i < 6; i++){
            gameDice.add(new Dice(100+(60*i),10,50,50));
        }
        r = new Roll(10,200,100,100);
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        Rectangle2D.Double background = new Rectangle2D.Double(0,0,width,height);
        g2d.setRenderingHints(rh);
        g2d.setPaint(Color.WHITE);
        g2d.fill(background);

        p.draw(g2d);
        o.draw(g2d);

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

    public void setPlayers(Player p, Opponent o){
        System.out.println("Setting up canvas players");
        this.p.setDetails(p.getUsername(), (ImageIcon) p.getIcon());
        this.o.setDetails(o.getUsername(), (ImageIcon) p.getIcon());
    }
}
