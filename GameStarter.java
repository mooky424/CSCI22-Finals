/**
	This class sets up the game's main frame and starts the game
	from the player's side. It also loops a sound file in the
	background.
	
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

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class GameStarter {
    public static void main (String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		// Rollbar. (2021, March 24). How to use the Throws keyword in Java (and when to use Throw). https://rollbar.com/blog/how-to-use-the-throws-keyword-in-java-and-when-to-use-throw
        GameFrame lf = new GameFrame(1024, 768);
        lf.setupMainMenu();

		// Bro Code. (2020, October 19). Java audio 🔊 [Video file]. YouTube. https://youtu.be/SyZQVJiARTQ?list=RDCMUC4SVo0Ue36XCfOyb5Lh1viQ
		File file = new File("./assets/background/AquaBarbieGirlInstrumental.wav");
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		Clip clip = AudioSystem.getClip();
		clip.open(audioStream);

		while(true) {
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY); // Firnaz. (2013, October 30). Audio Clip won't loop continuously. Stack Overflow. https://stackoverflow.com/a/19678865
		}
    }

}