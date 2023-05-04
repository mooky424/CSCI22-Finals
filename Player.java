import java.net.*;
import java.io.*;
import java.util.*;

public class Player {

    private Socket s;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner userInput;

    public Player() {
        userInput = new Scanner(System.in);
        try {
            s = new Socket("localhost", 64820);
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());
            receiveMessages rm = new receiveMessages();
            rm.run();
            while(true) {
                out.writeUTF(userInput.nextLine());
                out.flush();
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }

    private class receiveMessages implements Runnable {
        public void run(){
            try {
                while (true){
                    String msg = in.readUTF();
                    System.out.println(msg);
                }
            } catch (IOException ex) {
                System.out.println("Error at receiveMessages: " + ex);
            }
        }
    }

    public static void main(String[] args){
        Player p = new Player();
    }
}
