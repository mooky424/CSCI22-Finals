/**
	This class extends JButton. It displays the icon of a connected
    user in a GUI. It has methods to retrieve the user's ID and username.
    It also handles mouse events.
	
	@author Gabriel L. Salvador (225593)
    @author Janel Zherry A. Esmeris (222455)
	@version May 15, 2023
**/

/*
	I have not discussed the Java language code in my program 
	with anyone other than my instructor or the teaching assistants 
	assigned to this course.

	I have not used Java language code obtained from another student, 
	or any other unauthorized source, either modified or unmodified.

	If any Java language code or documentation used in my program 
	was obtained from another source, such as a textbook or website, 
	that has been clearly noted with a proper citation in the comments 
	of my program.
*/

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
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/1yeji.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/2lia.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/3ryujin.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/4chaeryeong.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/5yuna.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/6karina.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/7giselle.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/8winter.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/9ningning.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/10jennie.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/11jisoo.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/12rose.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/13lisa.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/14irene.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/15seulgi.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/16wendy.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/17joy.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/18yeri.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/19yujin.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/20gaeul.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/21liz.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/22wonyoung.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/23rei.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/24leeseo.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));

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