import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GameGUI extends JPanel{
    
    String player, opponent;

    JPanel scoresheetPanel;
    JTable scoresheet;
    String[] columnNames = {"", player, opponent};;
    String[][] data = { 
        {"Ones", "", ""},
        {"Twos", "", ""},
        {"Threes", "", ""},
        {"Fours", "", ""},
        {"Fives", "", ""},
        {"Sixes", "", ""},
        {"Fives", "", ""},
        {"Fives", "", ""},
    };

    public GameGUI(ActionListener buttonListener){
        scoresheetPanel = new JPanel();
        player = "";
    }
}
