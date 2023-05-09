import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ConnectedUser extends JButton {
    
    String username;
    int icon;
    int id;
    boolean hover;
    
    public ConnectedUser(String username, int icon, MouseListener mouseListener){
        id = -1;
        this.username = username;
        this.icon = icon;
        setText(username);
        setIcon(new ImageIcon((new ImageIcon("./assets/icons/sample.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
       
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.BOTTOM);

        setMargin(new Insets(5, 5, 5, 5));

        setBackground(null);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        addMouseListener(mouseListener);
    }

    public ConnectedUser(ConnectedUser cu){
        
    }
        
    public String getUsername(){
        return username;
    }
}
