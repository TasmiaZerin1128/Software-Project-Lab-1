package fusics;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class single {

    Set<?>[] questionSet = new Set<?>[5];


    public void Single(Stage primaryStage, int point) throws IOException {

        Random rng = new Random();
        for (int i = 0; i < questionSet.length; ++i) {
            questionSet[i] = new LinkedHashSet<Integer>();
        }
        while (questionSet[0].size() < 4)
        {
            Integer next = rng.nextInt((4 - 1) + 1) + 1;
            ((Set<Integer>)questionSet[0]).add(next);
        }

        System.out.println(questionSet[0]);

        while (questionSet[1].size() < 3)
        {
            Integer next = rng.nextInt((7-5)+1) + 5;
            ((Set<Integer>)questionSet[1]).add(next);
        }
        System.out.println(questionSet[1]);

        while (questionSet[2].size() < 4)
        {
            Integer next = rng.nextInt((4 - 1) + 1) + 1;
            ((Set<Integer>)questionSet[2]).add(next);
        }

        System.out.println(questionSet[2]);

        while (questionSet[3].size() < 3)
        {
            Integer next = rng.nextInt((7-5)+1) + 5;
            ((Set<Integer>)questionSet[3]).add(next);
        }
        System.out.println(questionSet[3]);

        while (questionSet[4].size() < 7)
        {
            Integer next = rng.nextInt((7-1)+1) + 1;
            ((Set<Integer>)questionSet[4]).add(next);
        }
        System.out.println(questionSet[4]);

        SingleQues goQues = Object.getSq();
        goQues.QuesPass(primaryStage,questionSet,0);

//        easy goEasy = new easy();
//        goEasy.EASY(primaryStage,2, (Set<Integer>) questionSet[1],0,0,5,0);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                e.consume();
                Pane R = new Pane();
                Text exit = new Text("Do you want to exit?");
                exit.setScaleX(3);
                exit.setScaleY(3);
                exit.setTranslateX(300);
                exit.setTranslateY(100);
                exit.setFill(Color.WHITE);
                Button yes = new Button("Yes");
                setStyleE(yes);
                yes.setTranslateX(170);
                yes.setTranslateY(200);
                yes.setPrefSize(150, 50);
                Button no = new Button("No");
                setStyleE(no);
                no.setTranslateX(400);
                no.setTranslateY(200);
                no.setPrefSize(150, 50);
                R.getChildren().addAll(exit, yes, no);
                Scene S = new Scene(R, 700, 400);
                Stage eStage = new Stage();
                eStage.setScene(S);
                Image bg = null;
                try {
                    bg = new Image(new FileInputStream("src/Images/exit.png"));
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                BackgroundImage bi = new BackgroundImage(bg,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
                Background back = new Background(bi);
                R.setBackground(back);
                S.setFill(Color.TRANSPARENT);
                eStage.initStyle(StageStyle.TRANSPARENT);
                eStage.show();

                yes.setOnAction(ev -> {
                    System.out.println("Closing");
                    System.exit(0);
                });
                no.setOnAction(ev -> {
                    eStage.close();
                });
            }
        });
    }
    public Button setStyleE(Button b) {
        b.setStyle("-fx-background-color: \n" +
                "        linear-gradient(#f2f2f2, #d6d6d6),\n" +
                "        linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),\n" +
                "        linear-gradient(#dddddd 0%, #f6f6f6 50%);\n" +
                "    -fx-background-radius: 8,7,6;\n" +
                "    -fx-background-insets: 0,1,2;\n" +
                "    -fx-text-fill: black;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.6em;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
        return b;
    }
}
