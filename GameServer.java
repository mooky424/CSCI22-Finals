/**
	class desc w a min of two sentences
	
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

import java.io.*;
import java.net.*;
import java.util.*;

 public class GameServer {

    private ServerSocket ss;
    private ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
    private ArrayList<String> lobbyUserUpdates = new ArrayList<String>();
    private int currentUsers;

    public GameServer(){
        currentUsers = 0;
        try {
            ss = new ServerSocket(64820);
            while (true){
                Socket s = ss.accept();
                clients.add(new ClientThread(s, currentUsers));
                currentUsers++;
                System.out.println("User Connected");
            }
            
        } catch (IOException ex){
            System.out.println("Error at GameServer: " + ex);
        }
    }

    private class ClientThread implements Runnable{

        int id;
        String username;
        
        Socket s;
        Thread t;
        DataInputStream in;
        DataOutputStream out;

        private ClientThread(Socket s, int id) {
            this.s = s;
            this.id = id;
            t = new Thread(this, Integer.toString(id));
            t.start();
        }

        public void run(){
            try {
                in = new DataInputStream(s.getInputStream());
                out = new DataOutputStream(s.getOutputStream());
                updateUser();
                while(true) {
                    String command = in.readUTF();
                    if (command.equals("disconnect")){
                        s.close();
                        disconnectUser();
                        break;
                    } else{
                        parseMessage(command,id);
                    }
                }
            } catch (IOException ex) {
                System.out.println("Error at ClientThread: " + ex);
            } finally {
                System.out.println("User Disconnected");
            }
        }

        public void write(String msg){
            try {
                System.out.println("Sending " + msg + " to " + id);
                out.writeUTF(msg);
                out.flush();
            } catch (IOException ex) {
                System.out.println("Error at write: " + ex);
            }
        }

        private void parseMessage(String command, int id){
            String[] c = command.split(" ");
            String msg = "";
            if (c[0].equals("create")){
                msg = "addUser";
                username = c[1];
                int icon = Integer.parseInt(c[2]);
                msg = msg + " " + id + " " + username + " " + icon; 
                createdUser(username, icon, id);
                sendToAll(msg, id);
                lobbyUserUpdates.add(msg);
                return;
            }
            if (c[0].equals("challenge")){
                System.out.println(command);
                int sender = id;
                int receiver = Integer.parseInt(c[1]);
                msg = "challenger " + sender; 
                send(msg, receiver);
                sendToAll("delUser " + sender, sender);
                lobbyUserUpdates.add("delUser " + sender);
                sendToAll("delUser " + receiver, receiver);
                lobbyUserUpdates.add("delUser " + receiver);
            }
            if (c[0].equals("accept")){
                int receiver = Integer.parseInt(c[1]);
                msg = "accepted";
                send(msg, receiver);

                msg = "turn ";
                if (Math.random() > 0.5){
                    write("passedTurn");
                    send("waitTurn", receiver);
                } else {
                    write("waitTurn");
                    send("passedTurn",receiver);
                }
            }
            if (c[0].equals("decline")){
                int receiver = Integer.parseInt(c[1]);
                msg = "declined";
                send(msg, receiver);
            }
            if (c[0].equals("passTurn")){
                int passedTo = Integer.parseInt(c[1]);
                if (passedTo == id){
                    write("passedTurn");
                    send("waitTurn", passedTo);
                } else{
                    write("waitTurn");
                    send("passedTurn", passedTo);
                }
            }
            if (c[0].equals("roll")){
                int receiver = Integer.parseInt(c[1]);
                msg = "setDice ";
                for ( int i = 2; i < c.length; i++){
                    msg += c[i] + " ";
                }
                send(msg, receiver);
            }
            if (c[0].equals("keptDice")){
                int receiver = Integer.parseInt(c[1]);
                msg = "keptDice " + c[2] + " " + c[3];
                send(msg, receiver);
            }
            if (c[0].equals("setScore")){
                int receiver = Integer.parseInt(c[1]);
                int selectedRow = Integer.parseInt(c[2]);
                int score = Integer.parseInt(c[3]);
                msg = "setScore " + id + " " + selectedRow + " " + score;
                send(msg, receiver);
            }            
        }

        private void createdUser(String username, int icon, int id){
            String msg = "createdUser " + username + " " + icon + " " + id;
            write(msg);
        }

        private void updateUser(){
            for (String lu : lobbyUserUpdates){
                write(lu);
            }
        }

        private void disconnectUser(){
            String disconnect = "delUser " + id; 
            lobbyUserUpdates.add(disconnect);
            sendToAll(disconnect, id);
            deleteClientThread(this);
        }
    }
    
    private void send(String msg, int receiver){
        for ( ClientThread ct : clients ){
            if (ct.id == receiver){
                ct.write(msg);
            }
        }
    }

    private void sendToAll(String msg, int excludedUser){
        for ( ClientThread ct : clients ){
            if (ct.id != excludedUser){
                ct.write(msg);
            }
        }
    }

    private void deleteClientThread(ClientThread ct){

        for (int i = 0; i< clients.size(); i++){
            if (clients.get(i).id == ct.id){
                clients.remove(i);
            }
        }
    }
            
    public static void main(String[] args){
        GameServer gm = new GameServer();
    }
    
 }