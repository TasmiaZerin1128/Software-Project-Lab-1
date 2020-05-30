package First;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class ThirdPage {
    public void TheThird(Stage primaryStage) throws Exception
    {
        Button ab = new Button("About");
        ab.setTranslateX(700);
        ab.setTranslateY(100);
        setStyle(ab);
        ab.setPrefSize(150, 100);

        Button exp = new Button("Experiment");
        exp.setTranslateX(650);
        exp.setTranslateY(300);
        setStyle(exp);
        exp.setPrefSize(250, 100);

        Button solve = new Button("Solve");
        solve.setTranslateX(700);
        solve.setTranslateY(500);
        setStyle(solve);
        solve.setPrefSize(150, 100);

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
        root.getChildren().addAll(exp,back,ab,solve);

        ab.setOnAction(e -> {
            try {
                About Pabout = new About();
                Pabout.showAbout(primaryStage);
            } catch (Exception excep) {
                excep.printStackTrace();
            }
        });

        exp.setOnAction(e->{
            try{
                experiment goExp = new experiment();
                goExp.start(primaryStage);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        solve.setOnAction(e->{
            try{
                solve goSolve = new solve();
                goSolve.start(primaryStage, 1);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        back.setOnAction(e->{
            try {
                secondPage goBack = new secondPage();
                goBack.TheSecond(primaryStage);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
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
}
