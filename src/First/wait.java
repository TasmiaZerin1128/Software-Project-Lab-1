package First;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.temporal.Temporal;

public class wait {
    Thread thread = new Thread();
    public void Wait( Stage primaryStage) throws FileNotFoundException {
        Pane root = new Pane();
        multiplayer goMulti = new multiplayer();

        Text headning = new Text("Battle with others");
        headning.setScaleX(4);
        headning.setScaleY(4);
        headning.setTranslateX(720);
        headning.setTranslateY(80);
        headning.setFill(Color.WHITE);

        Text wait = new Text();
        wait.setFill(Color.WHITE);
        wait.setTranslateX(700);
        wait.setTranslateY(450);
        wait.setScaleX(3);
        wait.setScaleY(3);

        Button Start = new Button("Start");
        Start.setTranslateX(670);
        Start.setTranslateY(300);
        setStyle(Start);
        Start.setPrefSize(180,80);

        Start.setOnAction(e->{
            thread = new Thread(){
                public void run(){
                    System.out.println("Thread Running");
                    try {
                        goMulti.Multi(primaryStage,1);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            };
            thread.start();
            wait.setText("Waiting for others to join....");
        });

        Button back = new Button("Back");
        back.setTranslateX(50);
        back.setTranslateY(20);
        back.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #8d9092 0%, #717375 100%),\n" +
                "        #8d9092,\n" +
                "        #717375,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #ffffff, #a1a3a6);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.1em;");
        back.setPrefSize(60, 30);
        back.setOnAction(e->{
            thread.suspend();
            try {
                goMulti.Multi(primaryStage,0);
                examSM goBack = new examSM();
                goBack.SM(primaryStage);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        root.getChildren().addAll(Start,wait,back,headning);

        Scene S = new Scene(root,1600,800);
        Image background = new Image(new FileInputStream("src/Images/Back.png"));

        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        root.setBackground(bg);

        primaryStage.setScene(S);
        primaryStage.show();
    }
    public Button setStyle(Button b) {
        b.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #8d9092 0%, #717375 100%),\n" +
                "        #8d9092,\n" +
                "        #717375,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #ffffff, #a1a3a6);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.8em;");
        return b;
    }
}
