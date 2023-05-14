import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuGUI extends JPanel {

    protected JButton play, quit;

    MainMenuGUI(int w, int h, ActionListener buttonListener){
        
        setBounds(0,0,w,h);
        setOpaque(false);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        play = new JButton("Play");
        quit = new JButton("Quit");

        play.setAlignmentX(Component.CENTER_ALIGNMENT);
        quit.setAlignmentX(Component.CENTER_ALIGNMENT);
        play.setMinimumSize(new Dimension (50,40));
        play.setPreferredSize(new Dimension (100,40));
        play.setMaximumSize(new Dimension (100,40));
        quit.setMinimumSize(new Dimension (50,40));
        quit.setPreferredSize(new Dimension (50,40));
        quit.setMaximumSize(new Dimension (100,40));

        add(Box.createVerticalGlue());
        add(play);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(quit);
        add(Box.createVerticalStrut(156));

        play.addActionListener(buttonListener);
        quit.addActionListener(buttonListener);
    }
}




