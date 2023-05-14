import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    Player p;
    Opponent o;

    public GameGUI(int w, int h, ActionListener buttonListener){
        
        setBounds(0,0,w,h);

        setLayout(new BorderLayout());
        
        scoresheetPanel = new JPanel(new BorderLayout());
        scoresheet = new JTable(dataSixDice, columnNames);
        scoresheet.setCellSelectionEnabled(true);
        scoresheet.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scoresheet.getSelectedRow();

        add(scoresheetPanel, BorderLayout.EAST);
        scoresheetPanel.add(scoresheet.getTableHeader(), BorderLayout.NORTH);
        scoresheetPanel.add(scoresheet);
        gr = new GameRules();
    }

    public void setPlayers(Player p, Opponent o){
        this.p = p;
        this.o = o;
        scoresheet.getTableHeader().getColumnModel().getColumn(1).setHeaderValue(p.getUsername());
        scoresheet.getTableHeader().getColumnModel().getColumn(2).setHeaderValue(o.getUsername());
    }

    public void setPossibleScores(int[] diceValues){
        String[] possibleScores = gr.getPossibleScores(diceValues);
        for ( int i = 0; i < possibleScores.length; i++){
            if (p.getScoresheet()[i] == ""){
                scoresheet.setValueAt(possibleScores[i],i,1);
            }
        }
    }

    public void updatePlayerScore(Player p){
        String[] scores = p.getScoresheet();
        for ( int i = 0; i < scores.length; i++){
            if (scores[i] != ""){
                scoresheet.setValueAt(scores[i],i,1);
            } else {
                scoresheet.setValueAt("",i,1);
            }
        }
    }
}
