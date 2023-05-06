import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class GameFrame {
    
    private JFrame frame;
    private Container cp;
    private GameCanvas gc;
    private JButton play, quit, createUser;

    public GameFrame(int w, int h) {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(w,h));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gc = new GameCanvas(w, h);
        cp = frame.getContentPane();
    }

    public void setupMainMenu(){
        cp.removeAll();
        cp.setLayout(new GridLayout(1,3));

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
        
        setupButtonListeners(play);
        setupButtonListeners(quit);

        frame.add(new JPanel());
        frame.add(optionsPanel);
        frame.add(new JPanel());

        frame.pack();
        frame.setVisible(true);
    }

    public void setupCharSelect(){
        cp.removeAll();

        cp.setLayout(new GridLayout(3,3));
        JPanel characterPanel = new JPanel(new BorderLayout());
        JPanel avatarPanel = new JPanel();
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.PAGE_AXIS));

        JTextField usernameField= new JTextField("Username");
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField.setMaximumSize(new Dimension(150,20));
        usernameField.setHorizontalAlignment(JTextField.CENTER);
        
        createUser = new JButton("Create User");
        createUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        createUser.setMaximumSize(new Dimension(120,20));

        JLabel temp = new JLabel("temp");

        avatarPanel.add(temp);

        userPanel.add(usernameField);
        userPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        userPanel.add(createUser);

        characterPanel.add(avatarPanel, BorderLayout.CENTER);
        characterPanel.add(userPanel, BorderLayout.SOUTH);

        characterPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        cp.add(new JPanel());
        cp.add(new JPanel());
        cp.add(new JPanel());
        cp.add(new JPanel());
        cp.add(characterPanel, BorderLayout.CENTER);
        cp.add(new JPanel());
        cp.add(new JPanel());
        cp.add(new JPanel());
        cp.add(new JPanel());

        setupButtonListeners(createUser);

        cp.revalidate();
        cp.repaint();
    }

    public void setupLobbyGUI(){
        cp.removeAll();


        cp.revalidate();
        cp.repaint();
    }

    public void setupGameGUI(){
        Container cp = frame.getContentPane();

        
    }

    public void setupButtonListeners(JButton button){
        ActionListener buttonListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                Object source = ae.getSource();

                if (source == play) {
                    setupCharSelect();
                }                
                if (source == quit){
                    frame.dispose();
                }
                if (source == createUser){
                    setupLobbyGUI();
                }
            }
        };
        button.addActionListener(buttonListener);
    }

}
