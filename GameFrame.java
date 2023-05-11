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

    private Player p;
    private Opponent o;
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
        cs = new CharacterSelectGUI(buttonListener);
        mm = new MainMenuGUI(buttonListener);
        lm = new LobbyMenuGUI(buttonListener);
        g = new GameGUI(buttonListener);
        gc = new GameCanvas(w, h);
        gc.addMouseListener(gameMouseListener);
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

        cp.add(g);
        g.add(gc, BorderLayout.CENTER);

        cp.revalidate();
        cp.repaint();
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
                    o = new Opponent(challenger.getUsername(), (ImageIcon) challenger.getIcon(), challenger.getId());
                    gc.setPlayers(p, o);
                    setupGameGUI();
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
        connected.add(new ConnectedUser(id, username, icon, lobbyMouseListener));
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
                    System.out.println("Creating User");
                    out.writeUTF("create " + username + " " + Integer.toString(icon));
                } catch (Exception e) {
                    // TODO: handle exception
                } finally {
                    p = new Player(username, cs.icons.get((icon)));
                    lm.setPlayer(p);
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

    MouseListener lobbyMouseListener = new MouseAdapter() {
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

    MouseListener gameMouseListener = new MouseAdapter() {
        int totalKept = 0;
        int mouseX = 0;
        int mouseY = 0;
        @Override
        public void mouseClicked(MouseEvent me) {
            mouseX = me.getX();
            mouseY = me.getY();
            Object obj = gc.getSprite(mouseX,mouseY);
            if (obj != null){
                if (obj.getClass() == Roll.class){
                    gc.rollDice();

                    String command = "roll " + o.getId(); // dice values (to) opponent with {values}
                    ArrayList<Dice> dice = gc.getPlayerDice();
                    for (Dice d : dice) {
                        command += " " + d.getValue();
                    }
                    try {
                        out.writeUTF(command);
                    } catch (IOException ex) {
                        // TODO: handle exception
                    }
                }
                if (obj.getClass() == Dice.class){
                    Dice d = (Dice) obj;
                    totalKept += d.click(totalKept);
                    System.out.println("Total kept: " + totalKept);
                    moveDice(gc.getPlayerDice());
                }
            }
            gc.repaint();
        }
        
    };

    void moveDice(ArrayList<Dice> dice){
        int arrangeDice = 0; 
        for (Dice d : dice){
            if (d.isKept()){
                if (d.getKeptPosition() == arrangeDice){
                    arrangeDice++;
                } else if (d.getKeptPosition() != arrangeDice){
                    d.setKeptPosition(arrangeDice++);
                }                
            }
            
            double endX = (d.getKeptPosition() >=0) ? 200 + 60*(d.getKeptPosition()+1) : d.getRollPositionX();
            double endY = (d.getKeptPosition() >=0) ? 300 : d.getRollPositionY();

            int duration = 300; //Animation duration in ms
            int delay = 15;
            
            double x = d.getX();
            double distanceX = endX - x;
            double speedX = Math.abs(distanceX)/(duration/delay);
            double directionX = (distanceX < 0) ? -1 : 1;
            double velocityX = speedX * directionX;
            
            double y = d.getY();
            double distanceY = endY - y;                
            double speedY = Math.abs(distanceY)/(duration/delay);
            double directionY = (distanceY < 0) ? -1 : 1;
            double velocityY = speedY * directionY;

            Thread animationX = new Thread() {
                double distanceX = endX - d.getX();

                public void run(){
                    while(Math.abs(distanceX) > 10){
                        gc.repaint();
                        if (distanceX > 5 || distanceX < -5)
                        d.adjustX(velocityX);
                        try {
                            sleep(delay);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        distanceX = endX - d.getX();   
                    }
                    d.setX(endX);
                    gc.repaint();
                    this.interrupt();
                }
            };
            Thread animationY = new Thread() {
                double distanceY = endY - d.getY();

                public void run(){
                    while(Math.abs(distanceY) > 10){
                        gc.repaint();
                        if (distanceY > 5 || distanceY < -5)
                            d.adjustY(velocityY);
                        try {
                            sleep(delay);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        distanceY = endY - d.getY();                  
                    }
                    d.setY(endY);
                    gc.repaint();
                    this.interrupt();
                }
            };
            animationX.start();
            animationY.start();
        }
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
}
