import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class GameCanvas extends JComponent{

    private double width, height;

    private Player p; //dummy for graphics
    private Opponent o; //dummy for graphics
    
    private ArrayList<Dice> playerDice;
    private Roll r;

    public GameCanvas(int w, int h) {
        width = w;
        height = h;
        setPreferredSize(new Dimension(w, h));

        p = new Player(300,400);
        o = new Opponent(500,400);

        playerDice = new ArrayList<Dice>();        
        for( int i = 0; i < 6; i++){
            playerDice.add(new Dice(100+(60*i),10,50,50));
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
        for (int i = 0; i < playerDice.size(); i++){
            playerDice.get(i).draw(g2d);
        }
    }

    public Object getSprite(int x, int y){
        if (r.onClickableArea(x, y)){
            return r; 
        } 
        for (Dice d : playerDice) {
            if (d.onClickableArea(x, y)){
                return d;
            }
        }
        return null;
    }

    public void rollDice(){
        for (Dice d : playerDice) {
            d.roll();
        }
    }

    public Roll getRoll(){
        return r;
    }

    public ArrayList<Dice> getPlayerDice(){
        return playerDice;
    }

    public void setPlayers(Player p, Opponent o){
        this.p.setDetails(p.getUsername(), (ImageIcon) p.getIcon());
        this.o.setDetails(o.getUsername(), (ImageIcon) p.getIcon());
    }
}
