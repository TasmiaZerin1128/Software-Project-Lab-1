package First;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class exam {

    Set<Integer> generatedEasy = new LinkedHashSet<Integer>();
    Set<Integer> generatedMedium = new LinkedHashSet<Integer>();

    public void Exam(Stage primaryStage, int point) throws IOException {

        Random rng = new Random();
        while (generatedEasy.size() < 6)
        {
            Integer next = rng.nextInt((6 - 1) + 1) + 1;
            generatedEasy.add(next);
        }
        System.out.println(generatedEasy);

//        while (generatedMedium.size() < 7)
//        {
//            Integer next = rng.nextInt(6) + 1;
//            generatedMedium.add(next);
//        }

//        if(point<10)
//        {
//            easy goEasy = new easy();
//            goEasy.EASY(primaryStage,1,2);
//        }
    }
}
