/**
	This class extends JPanel and is responsible for making a GUI
    component in which the players play the game. It also creates
    instances of the GameCanvas and GameRules classes.
	
	@author Gabriel L. Salvador (225593)
    @author Janel Zherry A. Esmeris (222455)
	@version May 15, 2023
**/

/*
	I have not discussed the Java language code in my program 
	with anyone other than my instructor or the teaching assistants 
	assigned to this course.

	I have not used Java language code obtained from another student, 
	or any other unauthorized source, either modified or unmodified.

	If any Java language code or documentation used in my program 
	was obtained from another source, such as a textbook or website, 
	that has been clearly noted with a proper citation in the comments 
	of my program.
*/

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionListener;

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

    JButton backToLobby, exit;

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

        scoresheet.setCellSelectionEnabled(false);
        scoresheet.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scoresheet.getSelectionModel().addListSelectionListener(scoreshseetListener);

        add(scoresheetPanel);
        scoresheetPanel.add(scoresheet.getTableHeader(), BorderLayout.NORTH);
        scoresheetPanel.add(scoresheet);

        gr = new GameRules();

        winnerPanel = new JPanel();
        winnerPanel.setLayout(new BoxLayout(winnerPanel, BoxLayout.PAGE_AXIS));
        winnerPanel.setOpaque(false);
        winnerPanel.setBounds(0,0,w,h);

        backToLobby = new JButton("Back to Lobby");
        backToLobby.setAlignmentX(CENTER_ALIGNMENT);
        exit = new JButton("Exit");        
        exit.setAlignmentX(CENTER_ALIGNMENT);
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
    }

    public void setWinner(ActionListener buttonListener){
        String playerScore = (String) scoresheet.getValueAt(15, 1);
        String opponentScore = (String) scoresheet.getValueAt(15, 2);    

        JLabel winner = new JLabel(Integer.parseInt(playerScore) > Integer.parseInt(opponentScore) ? "You win!" : (o.getUsername() + " wins!"));
        winner.setFont(new Font("Sans Serif", Font.BOLD, 36));
        winner.setAlignmentX(CENTER_ALIGNMENT);
        
        JLabel scores = new JLabel(playerScore + " : " + opponentScore);
        winner.setFont(new Font("Sans Serif", Font.BOLD, 36));
        winner.setAlignmentX(CENTER_ALIGNMENT);
        
        backToLobby.addActionListener(buttonListener);
        exit.addActionListener(buttonListener);

        removeAll();
        winnerPanel.add(Box.createVerticalGlue());
        winnerPanel.add(winner);
        winnerPanel.add(Box.createVerticalStrut(8));
        winnerPanel.add(backToLobby);
        winnerPanel.add(Box.createVerticalStrut(8));
        winnerPanel.add(exit);
        winnerPanel.add(Box.createVerticalGlue());
        add(winnerPanel);
        revalidate();
        repaint();
    }
    
}