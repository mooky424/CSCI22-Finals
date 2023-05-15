/**
	class desc w a min of two sentences
	
	@author Gabriel L. Salvador (225593)
	@version May ?, 2023
**/

public class GameStarter {
    public static void main (String[] args){
        GameFrame lf1 = new GameFrame(1024, 768);
        lf1.setupMainMenu();
        GameFrame lf2 = new GameFrame(1024, 768);
        lf2.setupMainMenu();
        GameServer gm = new GameServer();
    }

}