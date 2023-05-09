import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ConnectedUser extends JButton {
    
    String username;
    int icon;
    int id;
    boolean hover;
    ArrayList<ImageIcon> icons;
    
    public ConnectedUser(int id, String username, int icon, MouseListener mouseListener){
        this.id = id;
        this.username = username;
        this.icon = icon;
        icons = new ArrayList<ImageIcon>();
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/sample.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/sample2.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));

        setText(username);
        setIcon(icons.get(icon));
       
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.BOTTOM);

        setMargin(new Insets(5, 5, 5, 5));

        setBackground(null);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        addMouseListener(mouseListener);
    }

    public int getId(){
        return id;
    }
        
    public String getUsername(){
        return username;
    }
}
