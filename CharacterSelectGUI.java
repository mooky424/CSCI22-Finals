import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*; // try am i here

public class CharacterSelectGUI extends JPanel {

    protected JButton createUser;
    protected JTextField usernameField;
    protected ArrayList<ImageIcon> icons;
    protected int currentImage;

    CharacterSelectGUI(int w, int h, ActionListener buttonListener){
        
        setBounds(0,0,w,h);

        icons = new ArrayList<ImageIcon>();
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/sample.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));
        icons.add(new ImageIcon((new ImageIcon("./assets/icons/sample2.png")).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH)));

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




