import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuGUI extends JPanel {

    protected JButton play, quit;

    MainMenuGUI(int w, int h, ActionListener buttonListener){
        
        setBounds(0,0,w,h);

        setLayout(new GridLayout(1,3));

        JPanel optionsPanel = new JPanel(new GridLayout(3, 1));
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));
        
        play = new JButton("Play");
        quit = new JButton("Quit");

        play.setAlignmentX(Component.CENTER_ALIGNMENT);
        quit.setAlignmentX(Component.CENTER_ALIGNMENT);
        play.setMinimumSize(new Dimension (50,20));
        play.setMaximumSize(new Dimension (100,40));
        quit.setMinimumSize(new Dimension (50,20));
        quit.setMaximumSize(new Dimension (100,40));

        buttonsPanel.add(play);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonsPanel.add(quit);

        optionsPanel.add(new JPanel());
        optionsPanel.add(new JPanel());
        optionsPanel.add(buttonsPanel);

        add(new JPanel());
        add(optionsPanel);
        add(new JPanel());

        play.addActionListener(buttonListener);
        quit.addActionListener(buttonListener);
    }
}




