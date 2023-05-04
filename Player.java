import java.net.*;
import java.io.*;
import java.util.*;

public class Player {

    public Player() {
        try {
            Socket s = new Socket("localhost", 64820);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            out.writeUTF("Ping");
            out.flush();
            String msg = in.readUTF();
            System.out.println(msg);
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }
}
