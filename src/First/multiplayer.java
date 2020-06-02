package First;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class multiplayer {
    Set<Integer> generatedQ = new LinkedHashSet<Integer>();
    public void Multi(Stage primaryStage) throws IOException {
        Random rng = new Random();
        while (generatedQ.size() < 2) {
            Integer next = rng.nextInt((7 - 1) + 1) + 1;
            generatedQ.add(next);
        }
        while (generatedQ.size() < 4) {
            Integer next = rng.nextInt((7 - 1) + 1) + 1;
            generatedQ.add(next);
        }
        while (generatedQ.size() < 5) {
            Integer next = rng.nextInt((7 - 1) + 1) + 1;
            generatedQ.add(next);
        }
            Server goServer= new Server(8900,generatedQ);
            System.out.println(generatedQ);
            easy goEasy = new easy();
            goEasy.EASY(primaryStage,1,generatedQ,5);


    }
}
