/**
	class desc w a min of two sentences
	
	@author Gabriel L. Salvador (225593)
    @author Janel Zherry A. Esmeris (222455)
	@version May ?, 2023
**/

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class GameFrame {
    
    private JFrame frame;
    private JLayeredPane lp;
    private MainMenuGUI mm;
    private CharacterSelectGUI cs;
    private LobbyMenuGUI lm;
    private JPanel waiting;
    private GameRules gr;
    private GameGUI g;
    private GameCanvas gc;
    
    private boolean waitingforResponse;
    
    private Socket s;
    private DataInputStream in;
    private DataOutputStream out;

    private Player p;
    private Opponent o;
    private ConnectedUser selected;
    private ArrayList<ConnectedUser> connected;

    public GameFrame(int w, int h) {
        frame = new JFrame();
        frame.setMinimumSize(new Dimension(w+16,h+40));
        frame.setMaximumSize(new Dimension(w+16,h+40));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //https://stackoverflow.com/questions/16532478/jframe-will-not-close-on-x
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                try {
                    System.out.println("Disconnecting from server.");
                    out.writeUTF("disconnect");
                    if(in.readUTF().equals("ok")){
                        s.close();
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        });
        
        lp = new JLayeredPane();
        lp.setBounds(0, 0, w+16, h+40);
        
        mm = new MainMenuGUI(w,h,buttonListener);
        cs = new CharacterSelectGUI(w,h,buttonListener);
        lm = new LobbyMenuGUI(w,h,buttonListener);
        g = new GameGUI(w,h,scoreshseetListener);
        gc = new GameCanvas(w, h);
        gc.addMouseListener(gameMouseListener);
        frame.getContentPane().add(lp);
        
        JLabel background = new JLabel(new ImageIcon("./assets/background/YahtzieTitleScreen.png"));
        background.setBounds(0, 1, w, h);
        background.setOpaque(false);
        
        lp.add(background, 0);

        waiting = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2d = (Graphics2D) g;

                RenderingHints rh = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
                );
                g2d.setRenderingHints(rh);
                
                String msg = "Waiting for response...";
                g2d.setColor(new Color (0, 0, 0, 189));
                g2d.fillRect(0, 0, w, h);
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.BOLD, 48));
                g2d.drawString(msg, w/2 - g.getFontMetrics().stringWidth(msg)/2, h/2-24);
                
            };
        };
        waiting.setBounds(0,0,w,h);
        waiting.setOpaque(false);

        connected = new ArrayList<ConnectedUser>();
    }

    public void setupMainMenu(){
        lp.add(mm, JLayeredPane.MODAL_LAYER);
        frame.setVisible(true);
    }

    public void setupCharSelect(){
        lp.removeAll();
        lp.add(cs, JLayeredPane.MODAL_LAYER);
    }

    public void setupLobbyGUI(){
        if (lp.getComponentCountInLayer(JLayeredPane.POPUP_LAYER) > 0){
            lp.remove(waiting);
        }
        lp.removeAll();
        isLobbyEnabled = true;
        lm.setClickable(true);
        lp.removeAll();
        lp.add(lm, JLayeredPane.MODAL_LAYER);
    }

    public void setWaitingPopup(){
        isLobbyEnabled = false;
        lm.setClickable(false);
        lp.add(waiting, JLayeredPane.POPUP_LAYER);
    }

    public void setupGameGUI(){
        if (lp.getComponentCountInLayer(JLayeredPane.POPUP_LAYER) > 0){
            lp.remove(waiting);
        }
        lp.removeAll();
        lp.add(g, JLayeredPane.MODAL_LAYER);
        g.add(gc, BorderLayout.CENTER);
    }
    
    ActionListener buttonListener = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent ae){
            Object source = ae.getSource();
            if (source == mm.play) {
                try{
                    System.out.print("Connecting to server... ");
                    s = new Socket("localhost", 64820);
                    out = new DataOutputStream(s.getOutputStream());
                    in = new DataInputStream(s.getInputStream());
                    setUpServerThread();
                } catch (IOException ex) {
                    System.out.println("Error");
                } finally {
                    setupCharSelect();
                    System.out.println("Success");
                }                
            }
            if (source == mm.quit){
                frame.dispose();
            }
            if (source == cs.createUser){                
                String username = cs.usernameField.getText();
                int icon = cs.currentImage;
                System.out.println("Creating User");
                write("create " + username + " " + Integer.toString(icon));               
            }
            if (source == lm.challenge){
                System.out.println("Challenging " + selected.getUsername());
                write("challenge " + selected.getId());
                setWaitingPopup();
                waitingforResponse = true;
            }
        }
    };        

    private void parseMessage(String command){
        System.out.println("Received: " + command);
        String[] c = command.split(" ");
        if (c[0].equals("createdUser")){
            String username = c[1];
            int icon = Integer.parseInt(c[2]);
            int id = Integer.parseInt(c[3]);
            p = new Player(username, cs.icons.get(icon), id);
            lm.setPlayer(p);
            setupLobbyGUI();
        } 
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
                try {
                    out.writeUTF("accept " + challenger.getId());
                } catch (Exception e) {
                    // TODO: handle exception
                } finally {
                    o = new Opponent(challenger.getUsername(), (ImageIcon) challenger.getIcon(), challenger.getId());
                    g.setPlayers(p, o);
                    gc.setPlayers(p, o);
                    setupGameGUI();
                }
            }
            if (choice == JOptionPane.NO_OPTION){
                System.out.println("Challenge Declined");
                try {
                    out.writeUTF("decline " + challenger.getId());
                    out.writeUTF("create " + p.getUsername() + " " + cs.icons.indexOf(p.getIcon()));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }
        if (c[0].equals("accepted") && waitingforResponse){
            o = new Opponent(selected.getUsername(), (ImageIcon) selected.getIcon(), selected.getId());
            g.setPlayers(p, o);
            gc.setPlayers(p, o);
            setupGameGUI();
        }
        if (c[0].equals("declined") && waitingforResponse){
            try {
                out.writeUTF("create " + p.getUsername() + " " + cs.icons.indexOf(p.getIcon()));
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                setupLobbyGUI();
            }
        }
        if (c[0].equals("passedTurn")){
            p.setTurn(true);
            gc.setDialogue(1);
            isGameEnabled = true;
            System.out.println("It's my turn");
        }
        if (c[0].equals("waitTurn")){
            p.setTurn(false);
            gc.setDialogue(0);
            isGameEnabled = false;
            System.out.println("It's their turn");
        }
        if (c[0].equals("setDice")){
            int[] diceValues = new int[6];
            for (int i = 0; i < diceValues.length; i++){
                diceValues[i] = Integer.parseInt(c[i+1]);
            }
            gc.setDice(diceValues);
        }
        if (c[0].equals("keptDice")){
            gc.opponentKeptDice(Integer.parseInt(c[1]), Integer.parseInt(c[2]));
            gc.moveDiceToKeptOpponentPosition(gc.getGameDice());
        }
        if (c[0].equals("setScore")){
            o.updateScoresheet(Integer.parseInt(c[2]),c[3]);
            g.updateOpponentScore(o);
        }
        if (c[0].equals("")){
            
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

    boolean isLobbyEnabled = false;
    MouseListener lobbyMouseListener = new MouseAdapter() {
        ConnectedUser hovered;
        public void mouseEntered(MouseEvent me){
            if(!isLobbyEnabled){
                return;
            }
            hovered = (ConnectedUser) me.getSource();
            hovered.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
        }
        public void mouseExited(MouseEvent me){
            if(!isLobbyEnabled){
                return;
            }
            if (hovered != selected)
                hovered.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        }
        public void mouseClicked(MouseEvent me){
            if(!isLobbyEnabled){
                return;
            }
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

    boolean isGameEnabled = true;
    MouseListener gameMouseListener = new MouseAdapter() {
        int totalKept = 0;
        int mouseX = 0;
        int mouseY = 0;
        @Override
        public void mouseClicked(MouseEvent me) {
            if (!isGameEnabled){
                return;
            }
            mouseX = me.getX();
            mouseY = me.getY();
            Object obj = gc.getSprite(mouseX,mouseY);
            if (obj != null){
                if (obj.getClass() == Roll.class){

                    if(!p.getTurn() || p.getRolls() == 0){
                        return;
                    } else {
                        p.useRoll();
                        gc.setDialogue(4 - p.getRolls());
                    }

                    gc.rollDice();

                    ArrayList<Dice> dice = gc.getGameDice();

                    int[] diceValues = {
                        dice.get(0).getValue(),
                        dice.get(1).getValue(),
                        dice.get(2).getValue(),
                        dice.get(3).getValue(),
                        dice.get(4).getValue(),
                        dice.get(5).getValue()
                    };
                    g.setPossibleScores(diceValues);

                    //send opponent dice values
                    String command = "roll " + o.getId(); // dice values (to) opponent with {values}
                    for (Dice d : dice) {
                        command += " " + d.getValue();
                    }
                    write(command);
                }
                if (obj.getClass() == Dice.class){

                    Dice d = (Dice) obj;
                    totalKept += d.click(totalKept);
                    gc.moveDiceToKeptPlayerPosition(gc.getGameDice());
                    write("keptDice " + o.getId() + " " + gc.findDice(d) + " " + d.getKeptPosition());
                }
            }
            gc.repaint();
        }
    };

    ListSelectionListener scoreshseetListener = new ListSelectionListener() {
        public void valueChanged(ListSelectionEvent le){
            int selectedRow = g.scoresheet.getSelectedRow();
            int selectedColumn = g.scoresheet.getSelectedColumn();

            String selectedData = (String) g.scoresheet.getValueAt(selectedRow, selectedColumn);
            if (selectedData != p.getScoresheet()[selectedRow]){
                p.updateScoresheet(selectedRow, selectedData);
                g.updatePlayerScore(p);
                write("setScore " + o.getId() + " " + selectedRow + " " + selectedData);
                if (p.getTurn()){
                    write("passTurn " + o.getId());
                    gc.setDialogue(0);
                }
            }
        }
    };

    private void setUpServerThread(){
        Thread serverUpdatesThread = new Thread("Server Updates"){
            public void run(){
                try {
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

    private void write(String msg){
        System.out.println("Sending: " + msg);
        try {
            out.writeUTF(msg);
        } catch (IOException ex) {
            //TODO
        }
    }
    
}