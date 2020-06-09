package First;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Group;
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
import java.time.temporal.Temporal;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class wait {
    Set<Integer> generatedQ = new LinkedHashSet<Integer>();
    Server nServer = new Server();
    public void Wait( Stage primaryStage) throws FileNotFoundException {
        Pane root = new Pane();
        multiplayer goMulti = new multiplayer();
        Server goServer  = new Server();

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

//        Task task = new Task<Void>() {
//            @Override public Void call() {
//                try {
//                    goMulti.Multi(primaryStage, 1);
//                    //goServer.Server(8900,generatedQ);
//                } catch (IOException ioException) {
//                    ioException.printStackTrace();
//                }
//                return null;
//            }
//        };
//
//        Thread thread = new Thread(task);


        Start.setOnAction(e->{
            //thread.setDaemon(true);
            //thread.start();
            try {
                goMulti.Multi(primaryStage, 1,nServer);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            wait.setText("Waiting for otherssss");
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
            try {
                goMulti.Multi(primaryStage,0,nServer);
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
    public  Server sendServer(){
        return nServer;
    }
}
