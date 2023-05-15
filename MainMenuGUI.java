/**
	This class extends JPanel and is responsible for making
    the GUI for the title screen of the game. It uses a
    background image and two buttons that allow the player
    to play and quit the game.
	
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
        add(Box.createVerticalStrut(250));

        play.addActionListener(buttonListener);
        quit.addActionListener(buttonListener);
    }

}