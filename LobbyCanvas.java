import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class LobbyCanvas extends JComponent{

    private double width;
    private double height;

    public void LobbyCanvas(int w, int h){
        width = w;
        height = h;
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
        g2d.setPaint(Color.BLACK);
        g2d.fill(background);
    }
}
