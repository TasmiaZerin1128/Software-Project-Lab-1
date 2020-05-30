package First;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class multiplayer {
    Set<Integer> generatedEasy = new LinkedHashSet<Integer>();
    public void Multi(Stage primaryStage) throws IOException {
        Random rng = new Random();
        while (generatedEasy.size() < 6) {
            Integer next = rng.nextInt((6 - 1) + 1) + 1;
            generatedEasy.add(next);
        }
            Server goServer= new Server(8900,generatedEasy);
            easy goEasy = new easy();
            goEasy.EASY(primaryStage,1,generatedEasy,5);
            System.out.println(generatedEasy);
    }
}
