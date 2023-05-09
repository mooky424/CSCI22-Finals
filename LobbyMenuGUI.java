import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class LobbyMenuGUI extends JPanel{

    JPanel currentUsersPanel;

    protected JButton edit, challenge;
    protected JRadioButton sixDice, specialDice;
    protected ArrayList<JButton> currentUsers = new ArrayList<JButton>();

    LobbyMenuGUI(JFrame frame, ActionListener buttonListener){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        currentUsersPanel = new JPanel(new FlowLayout());
        currentUsersPanel.setBackground(Color.WHITE);
        
        JPanel optionsLobbyPanel = new JPanel(new GridLayout(1,3));
        optionsLobbyPanel.setPreferredSize(new Dimension(200, 200));
        optionsLobbyPanel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        
        JPanel editAvatarPanel = new JPanel();
        editAvatarPanel.setLayout(new BoxLayout(editAvatarPanel, BoxLayout.PAGE_AXIS));
        editAvatarPanel.setBorder(BorderFactory.createTitledBorder("Edit Avatar"));
        
        JPanel modeSelectPanel = new JPanel();
        modeSelectPanel.setBorder(BorderFactory.createTitledBorder("Game Mode"));
        
        JPanel challengePanel = new JPanel();

        ImageIcon icon = new ImageIcon((new ImageIcon("./assets/icons/sample.png")).getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
        JLabel avatar = new JLabel(icon);
        JLabel username = new JLabel("username");
        username.setFont(new Font("Arial", Font.BOLD, 16));
        edit = new JButton("Edit");
        avatar.setAlignmentX(CENTER_ALIGNMENT);
        username.setAlignmentX(CENTER_ALIGNMENT);
        edit.setAlignmentX(CENTER_ALIGNMENT);
        

        ButtonGroup modeSelect = new ButtonGroup();
        sixDice = new JRadioButton("Six Dice", true);
        specialDice = new JRadioButton("Special Dice", false);
        
        challenge = new JButton("Challenge");
        
        modeSelect.add(sixDice);
        modeSelect.add(specialDice);

        editAvatarPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        editAvatarPanel.add(avatar);
        editAvatarPanel.add(username);
        editAvatarPanel.add(Box.createRigidArea(new Dimension(0, 6)));
        editAvatarPanel.add(edit);

        modeSelectPanel.add(sixDice);
        modeSelectPanel.add(specialDice);

        challengePanel.add(challenge);

        optionsLobbyPanel.add(editAvatarPanel);
        optionsLobbyPanel.add(modeSelectPanel);
        optionsLobbyPanel.add(challengePanel);

        add(currentUsersPanel, BorderLayout.CENTER);
        add(optionsLobbyPanel, BorderLayout.SOUTH);
    }

    public void updateConnected(ArrayList<ConnectedUser> connected){

        currentUsersPanel.removeAll();

        for (ConnectedUser cu : connected){
            System.out.print(cu.getUsername()+"\n");
            currentUsersPanel.add(cu);
        }

        revalidate();
        repaint();
    }
}
