/**
	class desc w a min of two sentences
	
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

public class GameStarter {
    public static void main (String[] args){
        GameFrame lf1 = new GameFrame(1024, 768);
        lf1.setupMainMenu();
        GameFrame lf2 = new GameFrame(1024, 768);
        lf2.setupMainMenu();
        GameServer gm = new GameServer();
    }

}