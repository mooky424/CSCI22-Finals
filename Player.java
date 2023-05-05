import java.net.*;
import java.io.*;
import java.util.*;

public class Player {

    private Socket s;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner userInput;
    private int id;

    public Player() {
        userInput = new Scanner(System.in);
        try {
            s = new Socket("localhost", 64820);
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());
            id = in.readInt();

            Thread receiveMessages = new Thread() {
                public void run(){
                    while (true) {
                        try {
                            String msg = in.readUTF();
                            System.out.println(msg);
                        } catch (IOException ex) {
                            System.out.println("Error at receiveMessages: " + ex);
                        }
                    }
                }
            };

            Thread sendMessages = new Thread() {
                public void run(){
                    while (true) {
                        try {
                            //System.out.print("Client " + id + ": ");
                            out.writeUTF(userInput.nextLine());
                            out.flush();
                        } catch (IOException ex) {
                            System.out.println("Error at sendMessages: " + ex);
                        }
                    }
                }
            };

            receiveMessages.start();
            sendMessages.start();
            
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public static void main(String[] args){
        Player p = new Player();
    }
}
