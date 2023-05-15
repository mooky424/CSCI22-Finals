/**
	This class extends JPanel and is responsible for making a GUI
    in which the player can select their character. It populates
    an array list of ImageIcon objects and has a method to retrieve
    an object on the list based on the provided index. It also
    displays available character icons, allows navigation between
    icons using prev and next buttons, and provides text input for
    the username.
	
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

public class CharacterSelectGUI extends JPanel {

    protected JButton createUser;
    protected JTextField usernameField;
    protected ArrayList<ImageIcon> icons;
    protected int currentImage;

    CharacterSelectGUI(int w, int h, ActionListener buttonListener){
        setBounds(0,0,w,h);

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

        setLayout(new GridLayout(3,3));

        JPanel characterPanel = new JPanel(new BorderLayout());
        JPanel avatarHelperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel avatarPanel = new JPanel();
        avatarPanel.setLayout(new BoxLayout(avatarPanel, BoxLayout.PAGE_AXIS));
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.PAGE_AXIS));

        JButton prev = new JButton("<<<");
        prev.setAlignmentY(CENTER_ALIGNMENT);
        JButton next = new JButton(">>>");
        next.setAlignmentY(CENTER_ALIGNMENT);
        JLabel icon = new JLabel(icons.get(0));
        icon.setAlignmentY(CENTER_ALIGNMENT);

        usernameField = new JTextField("Username");
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField.setMaximumSize(new Dimension(150,20));
        usernameField.setHorizontalAlignment(JTextField.CENTER);
        
        createUser = new JButton("Create User");
        createUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        createUser.setMaximumSize(new Dimension(120,20));

        avatarHelperPanel.add(prev);
        avatarHelperPanel.add(icon);
        avatarHelperPanel.add(next);

        avatarPanel.add(Box.createRigidArea(new Dimension(0,24)));
        avatarPanel.add(avatarHelperPanel);

        userPanel.add(usernameField);
        userPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        userPanel.add(createUser);

        characterPanel.add(avatarPanel, BorderLayout.CENTER);
        characterPanel.add(userPanel, BorderLayout.SOUTH);

        characterPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        add(new JPanel());
        add(new JPanel());
        add(new JPanel());
        add(new JPanel());
        add(characterPanel, BorderLayout.CENTER);
        add(new JPanel());
        add(new JPanel());
        add(new JPanel());
        add(new JPanel());

        createUser.addActionListener(buttonListener);

        ActionListener changeImage = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                if(ae.getSource() == prev){
                    if (currentImage > 0){
                        currentImage--;
                    }
                } if (ae.getSource() == next){
                    if (currentImage < icons.size()-1){
                        currentImage++;
                    }
                }
                icon.setIcon(icons.get(currentImage));
                revalidate();
                repaint();
            }
        };

        prev.addActionListener(changeImage);
        next.addActionListener(changeImage);
    }

    public ImageIcon getIcon(int icon){
        return icons.get(icon);
    }
    
}