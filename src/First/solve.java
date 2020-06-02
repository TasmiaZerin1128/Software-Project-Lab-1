package First;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class solve {
    Set<Integer> generatedEasy = new LinkedHashSet<Integer>();
    Set<Integer> generatedMedium = new LinkedHashSet<Integer>();

    public void start(Stage primaryStage, int signal) throws NullPointerException, FileNotFoundException {

        Text headning = new Text("Practice By");
        headning.setScaleX(3);
        headning.setScaleY(3);
        headning.setTranslateX(730);
        headning.setTranslateY(50);
        headning.setFill(Color.WHITE);

        final ToggleGroup group = new ToggleGroup();

        RadioButton Category = new RadioButton("Category");
        Category.setToggleGroup(group);
        Category.setTranslateX(620);
        Category.setTranslateY(100);
        Category.setScaleX(2.5);
        Category.setScaleY(2.5);
        Category.setTextFill(Color.WHITE);

        RadioButton Difficulty = new RadioButton("Difficulty");
        Difficulty.setToggleGroup(group);
        Difficulty.setTranslateX(850);
        Difficulty.setTranslateY(100);
        Difficulty.setScaleX(2.5);
        Difficulty.setScaleY(2.5);
        Difficulty.setTextFill(Color.WHITE);
        if(signal==0)
        Difficulty.setSelected(true);

        Button easy = new Button("Easy");
        easy.setTranslateX(710);
        easy.setTranslateY(200);
        setStyle(easy);
        easy.setPrefSize(150, 100);

        Button medium = new Button("Medium");
        medium.setTranslateX(710);
        medium.setTranslateY(350);
        setStyle(medium);
        medium.setPrefSize(150, 100);

        Button hard = new Button("Hard");
        hard.setTranslateX(710);
        hard.setTranslateY(500);
        setStyle(hard);
        hard.setPrefSize(150, 100);

        Button MH = new Button("Height");
        MH.setTranslateX(580);
        MH.setTranslateY(200);
        setStyleSmall(MH);
        MH.setPrefSize(150, 60);
        setAction(MH,primaryStage);

        Button time = new Button("Time");
        time.setTranslateX(580);
        time.setTranslateY(350);
        setStyleSmall(time);
        time.setPrefSize(150, 60);
        setAction(time,primaryStage);

        Button range = new Button("Range");
        range.setTranslateX(580);
        range.setTranslateY(500);
        setStyleSmall(range);
        range.setPrefSize(150, 60);
        setAction(range,primaryStage);

        Button dis = new Button("Distance");
        dis.setTranslateX(800);
        dis.setTranslateY(200);
        setStyleSmall(dis);
        dis.setPrefSize(150, 60);
        setAction(dis,primaryStage);

        Button velo = new Button("Velocity");
        velo.setTranslateX(800);
        velo.setTranslateY(350);
        setStyleSmall(velo);
        velo.setPrefSize(150, 60);
        setAction(velo,primaryStage);

        Button ang = new Button("Angle");
        ang.setTranslateX(800);
        ang.setTranslateY(500);
        setStyleSmall(ang);
        ang.setPrefSize(150, 60);
        setAction(ang,primaryStage);

        Text exam = new Text("Done Solving? Let's take an Exam");
        exam.setTranslateX(750);
        exam.setTranslateY(650);
        exam.setScaleX(2);
        exam.setScaleY(2);
        exam.setFill(Color.WHITE);
        Image exa = new Image(new FileInputStream("src/Images/arrow.png"));
        Button Exam = new Button();
        Exam.setGraphic(new ImageView(exa));
        Exam.setTranslateX(1020);
        Exam.setTranslateY(620);
        Exam.setPrefSize(30,5);
        setStyle(Exam);
        Exam.setOnAction(e->{
            examSM goExamSM = new examSM();
            try {
                goExamSM.SM(primaryStage);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
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

        Image background = new Image(new FileInputStream("src/Images/Back.png"));
        Pane root = new Pane();
        root.getChildren().addAll(back,headning,Category,Difficulty);

        if(signal==1) {
            Category.setSelected(true);
            root.getChildren().removeAll(easy,medium,hard,MH,dis,range,time,velo,ang);
            root.getChildren().addAll(MH,dis,range,time,velo,ang);

        } else {
            Difficulty.setSelected(true);
            root.getChildren().removeAll(MH, dis, range, time, velo, ang, easy, medium, hard);
            root.getChildren().addAll(easy, medium, hard);
        }

        easy.setOnAction(e->{
            try {
                easy goEasy = new easy();
                goEasy.EASY(primaryStage,0,generatedEasy,5);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        medium.setOnAction(e->{
            try {
                medium goMedium = new medium();
                goMedium.Medium(primaryStage,0,generatedMedium,5);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        hard.setOnAction(e->{
            try {
                hard goHard = new hard();
                goHard.Hard(primaryStage,0,generatedMedium,5);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        back.setOnAction(e->{
            try {
                ThirdPage goBack = new ThirdPage();
                goBack.TheThird(primaryStage);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        Difficulty.setOnAction(event -> {
            root.getChildren().removeAll(MH,dis,range,time,velo,ang,easy,medium,hard);
            root.getChildren().addAll(easy,medium,hard);
        });

        Category.setOnAction(event -> {
            root.getChildren().removeAll(easy,medium,hard,MH,dis,range,time,velo,ang);
            root.getChildren().addAll(MH,dis,range,time,velo,ang);
        });

        root.getChildren().addAll(exam,Exam);

        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        root.setBackground(bg);


        Scene scene = new Scene(root, 1600, 800);
        primaryStage.setScene(scene);
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public Button setStyle ( Button b)
    {
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
                "    -fx-font-size: 2.1em;");
        return b;
    }
    public Button setStyleSmall ( Button b)
    {
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
                "    -fx-font-size: 1.3em;");
        return b;
    }
    public Button setAction ( Button b, Stage primaryStage)
    {
        b.setOnAction(e->{
            try{
                category goCategory = new category();
                goCategory.Category(primaryStage, b.getText());
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        });
        return b;
    }
}

