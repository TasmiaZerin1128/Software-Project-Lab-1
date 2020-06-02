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
import java.util.concurrent.TimeUnit;

public class examSM {
    public void SM (Stage primaryStage) throws FileNotFoundException {
        Pane root = new Pane();

        Text headning = new Text("Exam");
        headning.setScaleX(4);
        headning.setScaleY(4);
        headning.setTranslateX(750);
        headning.setTranslateY(50);
        headning.setFill(Color.WHITE);

        Text line = new Text("Show your skill, battle against others");
        line.setScaleX(2);
        line.setScaleY(2);
        line.setTranslateX(680);
        line.setTranslateY(120);
        line.setFill(Color.WHITE);

        Button Single = new Button("Single Player");
        Single.setTranslateX(650);
        Single.setTranslateY(280);
        setStyle(Single);
        Single.setPrefSize(250,80);

        Button Multi = new Button("MultiPlayer");
        Multi.setTranslateX(650);
        Multi.setTranslateY(450);
        setStyle(Multi);
        Multi.setPrefSize(250,80);

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
            try {
                solve goBack = new solve();
                goBack.start(primaryStage,0);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        Text wait = new Text("Waiting for others to join...");
        wait.setFill(Color.WHITE);
        wait.setTranslateX(700);
        wait.setTranslateY(400);
        wait.setScaleX(3);
        wait.setScaleY(3);
        //wait.setVisible(false);

        Button Start = new Button("Start");
        Start.setTranslateX(670);
        Start.setTranslateY(280);
        setStyle(Start);
        Start.setPrefSize(180,80);

        Single.setOnAction(e->{
            exam goExam = new exam();
            try {
                goExam.Exam(primaryStage,0);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        Multi.setOnAction(e->{
            root.getChildren().removeAll(Single,Multi);
            root.getChildren().addAll(wait,Start);
        });

        Start.setOnAction(e->{
            wait.setVisible(true);
            multiplayer goMulti = new multiplayer();
            try {
                goMulti.Multi(primaryStage);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        Scene S = new Scene(root,1600,800);
        Image background = new Image(new FileInputStream("src/Images/Back.png"));
        root.getChildren().addAll(Single,Multi,back,headning,line);

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
