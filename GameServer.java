import java.io.*;
import java.net.*;
import java.util.*;

 public class GameServer {

    public GameServer(){
        try {
            ServerSocket ss = new ServerSocket(64820)
            Socket s = ss.accept();
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            
            String msg = in.readUTF();
            System.out.println(msg);
            out.writeUTF("Pong");
            out.flush();
            
        } catch (IOException ex){
            System.out.println("Error: " + ex);
        }
    }
            
    public static void main(String[] args){
        GameServer gm = new GameServer();
    }
 }
