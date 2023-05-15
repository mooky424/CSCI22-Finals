/**
	This class extends JPanel and is responsible for making a GUI for
    the game's lobby menu. It displays the avatars of connected users.
    This also allows the player to send and receive game challenges.
	
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

public class LobbyMenuGUI extends JPanel{

    JPanel currentUsersPanel;
    JLabel avatar;
    JLabel username;

    protected JButton edit, challenge;
    protected JRadioButton sixDice, specialDice;
    protected ArrayList<JButton> currentUsers = new ArrayList<JButton>();

    LobbyMenuGUI(int w, int h, ActionListener buttonListener){
        
        setBounds(0,0,w,h);        
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        currentUsersPanel = new JPanel(new FlowLayout());
        currentUsersPanel.setBackground(Color.WHITE);
        
        JPanel optionsLobbyPanel = new JPanel(new GridLayout(1,2));
        optionsLobbyPanel.setPreferredSize(new Dimension(0, 200));
        optionsLobbyPanel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        
        JPanel editAvatarPanel = new JPanel();
        editAvatarPanel.setLayout(new BoxLayout(editAvatarPanel, BoxLayout.PAGE_AXIS));
        editAvatarPanel.setBorder(BorderFactory.createTitledBorder("Edit Avatar"));
        
        
        JPanel challengePanel = new JPanel(new BorderLayout());

        avatar = new JLabel();
        username = new JLabel();
        username.setFont(new Font("Arial", Font.BOLD, 16));
        edit = new JButton("Edit");
        edit.addActionListener(buttonListener);
        avatar.setAlignmentX(CENTER_ALIGNMENT);
        username.setAlignmentX(CENTER_ALIGNMENT);
        edit.setAlignmentX(CENTER_ALIGNMENT);
        
        challenge = new JButton("Challenge");
        challenge.addActionListener(buttonListener);

        editAvatarPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        editAvatarPanel.add(avatar);
        editAvatarPanel.add(username);
        editAvatarPanel.add(Box.createRigidArea(new Dimension(0, 6)));
        editAvatarPanel.add(edit);

        challengePanel.add(challenge, BorderLayout.CENTER);

        optionsLobbyPanel.add(editAvatarPanel);
        optionsLobbyPanel.add(challengePanel);

        add(currentUsersPanel, BorderLayout.CENTER);
        add(optionsLobbyPanel, BorderLayout.SOUTH);
    }

    public void setPlayer(Player p){
        avatar.setIcon(new ImageIcon(p.getIcon().getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
        this.username.setText(p.getUsername());
    }

    public void updateConnected(ArrayList<ConnectedUser> connected){

        currentUsersPanel.removeAll();

        for (ConnectedUser cu : connected){
            currentUsersPanel.add(cu);
        }

        revalidate();
        repaint();
    }

    public void setClickable(boolean state){
        edit.setEnabled(state);
        challenge.setEnabled(state);
    }

}