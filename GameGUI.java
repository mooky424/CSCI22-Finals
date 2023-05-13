import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GameGUI extends JPanel{

    GameCanvas gc;
    GameRules gr;
    
    JPanel scoresheetPanel;
    JTable scoresheet;
    String[] columnNames = {"", "", ""};
    String[][] dataSixDice = { 
        {"Ones", "", ""},
        {"Twos", "", ""},
        {"Threes", "", ""},
        {"Fours", "", ""},
        {"Fives", "", ""},
        {"Sixes", "", ""},
        {"Sum", "", ""},
        {"Three of a Kind", "", ""},
        {"Four of a Kind", "", ""},
        {"Full House", "", ""},
        {"Small Straight", "", ""},
        {"Large Straight", "", ""},
        {"Barbie", "", ""},
        {"Chance", "", ""},
        {"Yahtzee", "", ""},
        {"TOTAL SCORE", "", ""}
    };

    public GameGUI(int w, int h, ActionListener buttonListener){
        
        setBounds(0,0,w-16,h-40);

        setLayout(new BorderLayout());
        
        scoresheetPanel = new JPanel(new BorderLayout());
        scoresheet = new JTable(dataSixDice, columnNames);

        add(scoresheetPanel, BorderLayout.EAST);
        scoresheetPanel.add(scoresheet.getTableHeader(), BorderLayout.NORTH);
        scoresheetPanel.add(scoresheet);

    }

    public void setPlayers(Player p, Opponent o){
        gr = new GameRules();
        scoresheet.getTableHeader().getColumnModel().getColumn(1).setHeaderValue(p.getUsername());
        scoresheet.getTableHeader().getColumnModel().getColumn(2).setHeaderValue(o.getUsername());
    }

    public void setPossibleScores(int[] diceValues){
        int[] possibleScores = gr.getPossibleScores(diceValues);
        for ( int i = 0; i < possibleScores.length; i++){
            scoresheet.setValueAt(Integer.toString(possibleScores[i]),i,1);
        }
    }
}
