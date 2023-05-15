/**
	class desc w a min of two sentences
	
	@author Gabriel L. Salvador (225593)
    @author Janel Zherry A. Esmeris (222455)
	@version May ?, 2023
**/

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class GameGUI extends JPanel{

    int w,h;

    GameCanvas gc;
    GameRules gr;
    
    JPanel scoresheetPanel;
    JPanel winnerPanel;
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

    public GameGUI(int w, int h, ListSelectionListener scoreshseetListener){

        this.w = w;
        this.h = h;
        
        setBounds(0,0,w,h);

        setLayout(null);
        
        scoresheetPanel = new JPanel(new BorderLayout());
        scoresheetPanel.setBounds(737,20,267,646);
        scoresheetPanel.setOpaque(false);

        scoresheet = new JTable(dataSixDice, columnNames);

        scoresheet.getTableHeader().setPreferredSize(new Dimension(0, 39));
        scoresheet.getTableHeader().setFont(new Font("Sans Serif", Font.BOLD, 12));
        scoresheet.setRowHeight(38);
        scoresheet.getColumnModel().getColumn(0).setPreferredWidth(90);
        scoresheet.setIntercellSpacing(new Dimension(10,10));
        scoresheet.setFont(new Font("Sans Serif", Font.BOLD, 12));

        scoresheet.setCellSelectionEnabled(true);
        scoresheet.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scoresheet.getSelectionModel().addListSelectionListener(scoreshseetListener);

        add(scoresheetPanel);
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

        if(scoresheet.getValueAt(15, 1) != "" && scoresheet.getValueAt(15, 2) != ""){
            winnerPanel = new JPanel();
            winnerPanel.setLayout(new BoxLayout(winnerPanel, BoxLayout.Y_AXIS));
            winnerPanel.setOpaque(false);
            winnerPanel.setBounds(0,0,w,h);
            removeAll();
            int playerScore = (int) scoresheet.getValueAt(15, 1);
            int opponentScore = (int) scoresheet.getValueAt(15, 2);
            JLabel winner = new JLabel(playerScore > opponentScore ? "You win!" : (o.getUsername() + " wins!"));
            winner.setFont(new Font("Sans Serif", Font.BOLD, 36));

            winnerPanel.add(Box.createVerticalGlue());
            winnerPanel.add(winner);
            winnerPanel.add(Box.createVerticalGlue());

            add(winnerPanel);
        }
    }

    public void updateOpponentScore(Opponent o){
        System.out.println("Updating Opponent Score");
        String[] scores = o.getScoresheet();
        for ( int i = 0; i < scores.length; i++){
            if (scores[i] != ""){
                scoresheet.setValueAt(scores[i],i,2);
            } else {
                scoresheet.setValueAt("",i,2);
            }
        }

        if(scoresheet.getValueAt(15, 1) != "" && scoresheet.getValueAt(15, 2) != ""){
            winnerPanel = new JPanel();
            winnerPanel.setLayout(new BoxLayout(winnerPanel, BoxLayout.Y_AXIS));
            winnerPanel.setOpaque(isOpaque());
            winnerPanel.setBounds(0,0,w,h);
            removeAll();
            int playerScore = (int) scoresheet.getValueAt(15, 1);
            int opponentScore = (int) scoresheet.getValueAt(15, 2);
            JLabel winner = new JLabel(playerScore > opponentScore ? "You win!" : (o.getUsername() + " wins!"));
            winner.setFont(new Font("Sans Serif", Font.BOLD, 36));

            winnerPanel.add(Box.createVerticalGlue());
            winnerPanel.add(winner);
            winnerPanel.add(Box.createVerticalGlue());

            add(winnerPanel);
        }
    }
    
}