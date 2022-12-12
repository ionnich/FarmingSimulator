import controller.MainViewController;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


/**
 * The Main class.
 */
public class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        System.out.println("Hello world!");
        MainViewController mvc = new MainViewController();
    }
}