import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class LobbyFrame {
    
    private JFrame frame;
    private Container cp;
    private MainMenuGUI mm;
    private CharacterSelectGUI cs;
    private LobbyMenuGUI lm;
    private GameCanvas gc;
    private JButton createUser, edit, challenge;
    private JRadioButton sixDice, specialDice;
    private Socket s;
    private DataInputStream in;
    private DataOutputStream out;

    private ArrayList<String> updates;
    private ArrayList<ConnectedUser> connected;
    private int currentUsers;

    public LobbyFrame(int w, int h) {
        frame = new JFrame();
        frame.setMinimumSize(new Dimension(w,h));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gc = new GameCanvas(w, h);
        cs = new CharacterSelectGUI(frame, buttonListener);
        mm = new MainMenuGUI(frame, buttonListener);
        lm = new LobbyMenuGUI(frame, buttonListener);
        cp = frame.getContentPane();

        connected = new ArrayList<ConnectedUser>();
    }

    public void setupMainMenu(){
        cp.add(mm);
        frame.pack();
        frame.setVisible(true);
    }

    public void setupCharSelect(){
        cp.removeAll();
        cp.add(cs);
        cp.revalidate();
        cp.repaint();
    }

    public void setupLobbyGUI(){
        cp.removeAll();
        cp.add(lm);
        cp.revalidate();
        cp.repaint();
    }

    public void setupGameGUI(){
        cp.removeAll();
        
    }

    ActionListener buttonListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae){
            Object source = ae.getSource();
            if (source == mm.play) {
                try{

                    s = new Socket("localhost", 64820);
                    in = new DataInputStream(s.getInputStream());
                    out = new DataOutputStream(s.getOutputStream());
                    setUpServerThread();
                } catch (IOException ex) {
                    System.out.println("Error connecting to server");
                } finally {
                    setupCharSelect();
                }                
            }
            if (source == mm.quit){
                frame.dispose();
            }
            if (source == cs.createUser){
                setupLobbyGUI();
                try {
                    String username = cs.usernameField.getText();
                    String icon = Integer.toString(cs.currentImage);
                    out.writeUTF("create " + username + " " + icon);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }
    };

    private void setUpServerThread(){
        Thread serverUpdatesThread = new Thread("Server Updates"){
            public void run(){
                try {
                    System.out.println("Thread is up");
                    while(true){
                        parseCommand(in.readUTF());                        
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        };

        serverUpdatesThread.start();
    }

    public void addUser(String username, int icon){
        connected.add(new ConnectedUser(username, icon, mouseListener));
        lm.updateConnected(connected);
    }

    private void parseCommand(String command){
        String[] c = command.split(" ");
        if (c[0].equals("addUser")){
            String username = c[2];
            int icon = Integer.parseInt(c[3]);
            addUser(username,icon);
        }
    }

    MouseListener mouseListener = new MouseAdapter() {
        ConnectedUser selected;
        ConnectedUser hovered;
        public void mouseEntered(MouseEvent me){
            hovered = (ConnectedUser) me.getSource();
            hovered.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
        }
        public void mouseExited(MouseEvent me){
            if (hovered != selected)
                hovered.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            }
            public void mouseClicked(MouseEvent me){
                if (selected != hovered){
                    if (selected != null){
                        selected.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                    }
                selected = (ConnectedUser) me.getSource();
                selected.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
                
                } else {
                    selected.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                    selected = null;
                }
        }
    };
}
