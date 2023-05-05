import java.io.*;
import java.net.*;
import java.util.*;

 public class GameServer {

    private ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
    private int currentUsers;

    public GameServer(){
        currentUsers = 0;
        try {
            ServerSocket ss = new ServerSocket(64820);
            while (true){
                Socket s = ss.accept();
                clients.add(new ClientThread(s, currentUsers));
                currentUsers++;
            }
            
        } catch (IOException ex){
            System.out.println("Error at GameServer: " + ex);
        }
    }

    private class ClientThread implements Runnable{

        Socket s;
        int id;
        Thread t;
        DataInputStream in;
        DataOutputStream out;

        private ClientThread(Socket s, int id) {
            this.s = s;
            this.id = id;
            try {
                in = new DataInputStream(s.getInputStream());
                out = new DataOutputStream(s.getOutputStream());
                out.writeInt(currentUsers);
            } catch (Exception ex) {
                System.out.println("Error at ClientThread constructor: " + ex);
            }
            t = new Thread(this, Integer.toString(id));
            t.start();
        }

        public void run(){
            try {
                while(true) {
                    String msg = "Client " + id + ": " + in.readUTF();
                    sendToAll(msg, id);
                    System.out.println(msg);
                }
            } catch (IOException ex) {
                System.out.println("Error at ClientThread: " + ex);
            }

        }
        public void write(String msg){
            try {
                out.writeUTF(msg);
                out.flush();
            } catch (IOException ex) {
                System.out.println("Error at write: " + ex);
            }
        }
    }

    private void sendToAll(String msg, int id){
        for (ClientThread ct : clients){
            if (ct != clients.get(id))
                ct.write(msg);
        }
    }
            
    public static void main(String[] args){
        GameServer gm = new GameServer();
    }
 }
