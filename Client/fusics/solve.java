package fusics;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class solve {
    public void start(Stage primaryStage) throws FileNotFoundException {
        Pane root = new Pane();

        Text headning = new Text("Solve");
        headning.setScaleX(5);
        headning.setScaleY(5);
        headning.setTranslateX(740);
        headning.setTranslateY(60);
        headning.setFill(Color.WHITE);

        Image prac = new Image(new FileInputStream("src/Images/practice.png"));
        ImageView Pr = new ImageView(prac);

        Button Practice = new Button(null,Pr);
        Practice.setBackground(null);
        Practice.setTranslateX(310);
        Practice.setTranslateY(130);

        Image exam = new Image(new FileInputStream("src/Images/exam.png"));
        ImageView Ex = new ImageView(exam);

        Button Exam = new Button(null,Ex);
        Exam.setBackground(null);
        Exam.setTranslateX(790);
        Exam.setTranslateY(130);

        Canvas canvas = new Canvas(1600,800);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image danda = new Image(new FileInputStream("src/Images/solveDanda.png"));
        gc.drawImage(danda,740,150);

        Exam.setOnAction(e->{
            examSM goExamSM = new examSM();
            try {
                goExamSM.SM(primaryStage);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        Practice.setOnAction(e->{
            practice goPrac = Object.getPractice();
            try {
                goPrac.start(primaryStage);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        Image Back = new Image(new FileInputStream("src/Images/backButton.png"));
        ImageView bb = new ImageView(Back);

        Button back = new Button(null,bb);
        back.setBackground(null);
        back.setTranslateX(50);
        back.setTranslateY(20);
//        back.setStyle("-fx-padding: 8 15 15 15;\n" +
//                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
//                "    -fx-background-radius: 8;\n" +
//                "    -fx-background-color: \n" +
//                "        linear-gradient(from 0% 93% to 0% 100%, #8d9092 0%, #717375 100%),\n" +
//                "        #8d9092,\n" +
//                "        #717375,\n" +
//                "        radial-gradient(center 50% 50%, radius 100%, #ffffff, #a1a3a6);\n" +
//                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
//                "    -fx-font-weight: bold;\n" +
//                "    -fx-font-size: 1.1em;");
//        back.setPrefSize(60, 30);
        back.setOnAction(e->{
            ThirdPage goBack = new ThirdPage();
            try {
                goBack.TheThird(primaryStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        Image background = new Image(new FileInputStream("src/Images/bgAllnew.png"));
        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        root.setBackground(bg);

        root.getChildren().addAll(canvas,Practice,Exam,headning,back);


        Scene scene = new Scene(root, 1600, 800);
        primaryStage.setScene(scene);
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
