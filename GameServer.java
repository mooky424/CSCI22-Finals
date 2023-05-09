import java.io.*;
import java.net.*;
import java.util.*;

 public class GameServer {

    private ServerSocket ss;

    private ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
    private ArrayList<String> serverResponses = new ArrayList<String>();
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
        Thread t;
        DataInputStream in;
        DataOutputStream out;

        private ClientThread(Socket s, int id) {
            this.id = id;
            try {
                in = new DataInputStream(s.getInputStream());
                out = new DataOutputStream(s.getOutputStream());
                updateUser();
            } catch (Exception ex) {
                System.out.println("Error at ClientThread constructor: " + ex);
            }
            t = new Thread(this, Integer.toString(id));
            t.start();
        }

        public void run(){
            try {
                while(true) {
                    String command = in.readUTF();
                    sendToAll(serverResponse(command,id), id);
                }
            } catch (IOException ex) {
                System.out.println("Error at ClientThread: " + ex);
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
    }

    private String serverResponse(String command, int id){
        String[] c = command.split(" ");
        String response = "";
        if (c[0].equals("create")){
            System.out.println(c[0]+c[1]);
            response = "addUser";
            String username = c[1];
            String icon = c[2];
            response = response + " " + id + " " + username + " " + icon; 
        }
        return response;
    }

    private void sendToAll(String msg, int id){
        for ( ClientThread ct : clients ){
            if (ct.id != id){
                ct.write(msg);
            }
        }
    }

    private void updateUser(){
        
    }
            
    public static void main(String[] args){
        GameServer gm = new GameServer();
    }
 }
