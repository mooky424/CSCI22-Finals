import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class GameFrame {
    
    private JFrame frame;
    private Container cp;
    private MainMenuGUI mm;
    private CharacterSelectGUI cs;
    private LobbyMenuGUI lm;
    private GameGUI g;
    private GameCanvas gc;
    
    private Socket s;
    private DataInputStream in;
    private DataOutputStream out;

    private ConnectedUser selected;
    private ArrayList<ConnectedUser> connected;

    public GameFrame(int w, int h) {
        frame = new JFrame();
        frame.setMinimumSize(new Dimension(w,h));
        frame.setMaximumSize(new Dimension(w,h));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                try {
                    out.writeUTF("disconnect");
                    if(in.readUTF().equals("ok")){
                        s.close();
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        });
        gc = new GameCanvas(w, h);
        cs = new CharacterSelectGUI(buttonListener);
        mm = new MainMenuGUI(buttonListener);
        lm = new LobbyMenuGUI(buttonListener);
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

    public void setupGameFrame(){
        cp.removeAll();
        cp.add(g);

    }

    private void setUpServerThread(){
        Thread serverUpdatesThread = new Thread("Server Updates"){
            public void run(){
                try {
                    System.out.println("ServerThread running");
                    while(true){
                        parseMessage(in.readUTF());                        
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        };

        serverUpdatesThread.start();
    }
    
    private void parseMessage(String command){
            String[] c = command.split(" ");
            if (c[0].equals("addUser")){
                int id = Integer.parseInt(c[1]);
                String username = c[2];
                int icon = Integer.parseInt(c[3]);
                addUser(id,username,icon);
            }
            if (c[0].equals("delUser")){
                int id = Integer.parseInt(c[1]);
                removeUser(id);
            }
            if (c[0].equals("challenger")){
                ConnectedUser challenger = findUser(Integer.parseInt(c[1]));
                String message = "User " + challenger.getUsername() + " is challenging you.";
                int choice = JOptionPane.showConfirmDialog(null, message, "User Challenge", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION){
                    System.out.println("Challenge Accepted");
                    setupGameFrame();
                }
            }
        }

    public ConnectedUser findUser(int id){
        for (ConnectedUser cu : connected){
            if (cu.getId() == id){
                return cu;
            }
        }
        return null;
    }

    public void addUser(int id, String username, int icon){
        connected.add(new ConnectedUser(id, username, icon, mouseListener));
        lm.updateConnected(connected);
    }

    public void removeUser(int id){
        for (int i = 0; i < connected.size(); i++){
            if (connected.get(i).id == id){
                connected.remove(i);
            }
        }
        lm.updateConnected(connected);
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
                String username = cs.usernameField.getText();
                int icon = cs.currentImage;
                try {
                    out.writeUTF("create " + username + " " + Integer.toString(icon));
                } catch (Exception e) {
                    // TODO: handle exception
                } finally {
                    lm.setPlayer(username, cs.icons.get((icon)));
                    setupLobbyGUI();
                }
            }
            if (source == lm.challenge){
                System.out.println("challenging");
                try {
                    out.writeUTF("challenge " + selected.getId());
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }
    };

    MouseListener mouseListener = new MouseAdapter() {
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
