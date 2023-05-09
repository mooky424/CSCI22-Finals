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
        String icon;

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

        private String parseMessage(String command, int id){
            String[] c = command.split(" ");
            String response = "";
            if (c[0].equals("create")){
                System.out.println(c[0]+c[1]);
                response = "addUser";
                username = c[1];
                icon = c[2];
                response = response + " " + id + " " + username + " " + icon; 
                lobbyUserUpdates.add(response);
                sendToAll(response, id);
            }
            if (c[0].equals("challenge")){
                System.out.println(command);
                int challenger = id;
                int challenged = Integer.parseInt(c[1]);
                String challenge = "challenger " + challenger; 
                send(challenge, challenged);
            }
            return response;
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
