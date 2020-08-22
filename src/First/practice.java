package First;

import javafx.application.Application;
import javafx.event.EventHandler;
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
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class practice {
    Set<Integer> generatedEasy = new LinkedHashSet<Integer>();
    Set<Integer> generatedMedium = new LinkedHashSet<Integer>();
    int signal=1;

    public void start(Stage primaryStage) throws NullPointerException, FileNotFoundException {

        Text headning = new Text("Practice By");
        headning.setScaleX(3);
        headning.setScaleY(3);
        headning.setTranslateX(730);
        headning.setTranslateY(40);
        headning.setFill(Color.WHITE);

        Image ez = new Image(new FileInputStream("src/Images/easyiconfusics.png"));
        ImageView eB = new ImageView(ez);

        Button easy = new Button(null,eB);
        easy.setBackground(null);
        easy.setTranslateX(150);
        easy.setTranslateY(170);

        Image mz = new Image(new FileInputStream("src/Images/mediconfusics.png"));
        ImageView mB = new ImageView(mz);

        Button medium = new Button(null,mB);
        medium.setBackground(null);
        medium.setTranslateX(560);
        medium.setTranslateY(165);

        Image hz = new Image(new FileInputStream("src/Images/hardiconfusics.png"));
        ImageView hB = new ImageView(hz);

        Button hard = new Button(null,hB);
        hard.setBackground(null);
        hard.setTranslateX(980);
        hard.setTranslateY(175);

        Button category = new Button("Category");
        category.setTranslateX(550);
        category.setTranslateY(70);
        if(signal==1)
        {
            setStyleOClicked(category);
        }
        else {
            setStyleO(category);
        }
        category.setPrefSize(180, 50);

        Button difficulty = new Button("Difficulty");
        difficulty.setTranslateX(800);
        difficulty.setTranslateY(70);
        if(signal==0)
        {
            setStyleOClicked(difficulty);
        }
        else {
            setStyleO(difficulty);
        }
        difficulty.setPrefSize(180, 50);

        Image H = new Image(new FileInputStream("src/Images/heightIcon.png"));
        ImageView height = new ImageView(H);

        Button MH = new Button(null,height);
        MH.setBackground(null);
        MH.setTranslateX(320);
        MH.setTranslateY(200);
        MH.setText("Height");
        MH.setTextFill(null);
        setAction(MH,primaryStage);

        Image T = new Image(new FileInputStream("src/Images/timeIcon.png"));
        ImageView Time = new ImageView(T);

        Button time = new Button(null,Time);
        time.setBackground(null);
        time.setTranslateX(1020);
        time.setTranslateY(200);
        time.setText("Time");
        time.setTextFill(null);
        setAction(time,primaryStage);

        Image R = new Image(new FileInputStream("src/Images/rangeIcon.png"));
        ImageView rrange = new ImageView(R);
        Button range = new Button(null,rrange);
        range.setBackground(null);
        range.setTranslateX(670);
        range.setTranslateY(500);
        range.setText("Range");
        range.setTextFill(null);
        setAction(range,primaryStage);

        Image D = new Image(new FileInputStream("src/Images/distanceIcon.png"));
        ImageView distance = new ImageView(D);
        Button dis = new Button(null,distance);
        dis.setBackground(null);
        dis.setTranslateX(670);
        dis.setTranslateY(200);
        dis.setText("Distance");
        dis.setTextFill(null);
        setAction(dis,primaryStage);

        Image V = new Image(new FileInputStream("src/Images/veloIcon.png"));
        ImageView velocity = new ImageView(V);
        Button velo = new Button(null,velocity);
        velo.setBackground(null);
        velo.setTranslateX(320);
        velo.setTranslateY(500);
        velo.setText("Velocity");
        velo.setTextFill(null);
        setAction(velo,primaryStage);

        Image A = new Image(new FileInputStream("src/Images/angleIcon.png"));
        ImageView angle = new ImageView(A);
        Button ang = new Button(null,angle);
        ang.setBackground(null);
        ang.setTranslateX(1020);
        ang.setTranslateY(500);
        ang.setText("Angle");
        ang.setTextFill(null);
        setAction(ang,primaryStage);

        Image Back = new Image(new FileInputStream("src/Images/backButton.png"));
        ImageView bb = new ImageView(Back);

        Button back = new Button(null,bb);
        back.setBackground(null);
        back.setTranslateX(50);
        back.setTranslateY(20);

        Image background = new Image(new FileInputStream("src/Images/bgSolve.png"));
        Pane root = new Pane();
        root.getChildren().addAll(back,headning,category,difficulty);

        if(signal==1) {
            root.getChildren().removeAll(easy,medium,hard,MH,dis,range,time,velo,ang);
            root.getChildren().addAll(MH,dis,range,time,velo,ang);

        } else {
            root.getChildren().removeAll(MH, dis, range, time, velo, ang, easy, medium, hard);
            root.getChildren().addAll(easy, medium, hard);
        }

        easy.setOnAction(e->{
            try {
                easy goEasy = new easy();
                goEasy.EASY(primaryStage,0,generatedEasy,5,0,5,00);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        medium.setOnAction(e->{
            try {
                medium goMedium = new medium();
                goMedium.Medium(primaryStage,0,generatedMedium,5,0,5,00);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        hard.setOnAction(e->{
            try {
                hard goHard = new hard();
                goHard.Hard(primaryStage,0,generatedMedium,5,0,5,00);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        back.setOnAction(e->{
            try {
                solve goSolve = new solve();
                goSolve.start(primaryStage);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        difficulty.setOnAction(event -> {
            signal=0;
            setStyleOClicked(difficulty);
            setStyleO(category);
            root.getChildren().removeAll(MH,dis,range,time,velo,ang,easy,medium,hard);
            root.getChildren().addAll(easy,medium,hard);
        });

        category.setOnAction(event -> {
            signal=1;
            setStyleOClicked(category);
            setStyleO(difficulty);
            root.getChildren().removeAll(easy,medium,hard,MH,dis,range,time,velo,ang);
            root.getChildren().addAll(MH,dis,range,time,velo,ang);
        });

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
    public Button setStyleO(Button b)
    {
        b.setStyle("-fx-background-color: \n" +
                "        linear-gradient(#1289C4 0%, #40BAEC 25%, #0097CE 75%, #1289C4 100%),\n" +
                "        linear-gradient(#40baec 0%, #0097ce 20%, #118cc6 80%, #1476a9 100%),\n" +
                "        linear-gradient(#40baec 0%, #0097ce 20%, #118cc6 80%, #1476a9 100%),\n" +
                "        linear-gradient(#40baec 0%, #0097ce 40%, #118cc6 80%, #1476a9 100%);\n" +
                "    -fx-background-insets: 0,1,4,5,6;\n" +
                "    -fx-background-radius: 9,8,5,4,3;\n" +
                "    -fx-padding: 15 30 15 30;\n" +
                "    -fx-font-family: \"Helvetica\";\n" +
                "    -fx-font-size: 18px;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
        return b;
    }

    public Button setStyleOClicked(Button b)
    {
        b.setStyle("-fx-background-color: \n" +
                "        linear-gradient(#1289C4 0%, #40BAEC 25%, #0097CE 75%, #1289C4 100%),\n" +
                "        linear-gradient(#365caa 0%, #2a4893 20%, #1b2d75 80%, #001a33 100%),\n" +
                "        linear-gradient(#365caa 0%, #2a4893 20%, #1b2d75 80%, #001a33 100%),\n" +
                "        linear-gradient(#365caa 0%, #2a4893 40%, #1b2d75 80%, #001a33 100%);\n" +
                "    -fx-background-insets: 0,1,4,5,6;\n" +
                "    -fx-background-radius: 9,8,5,4,3;\n" +
                "    -fx-padding: 15 30 15 30;\n" +
                "    -fx-font-family: \"Helvetica\";\n" +
                "    -fx-font-size: 18px;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
        return b;
    }
}

