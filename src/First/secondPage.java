package First;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class secondPage {

    public void TheSecond(Stage primaryStage) throws Exception {

        Text headning = new Text("Formulas");
        headning.setScaleX(3);
        headning.setScaleY(3);
        headning.setTranslateX(700);
        headning.setTranslateY(50);
        headning.setFill(Color.WHITE);
        //headning.setFont(Font.font(Font.getFontNames().get(20),FontPosture.REGULAR,14));

        Button pro = new Button("Projectile");
        pro.setTranslateX(500);
        pro.setTranslateY(200);
        setStyle(pro);
        pro.setPrefSize(200, 80);

        Button momentum = new Button("Momentum");
        momentum.setTranslateX(800);
        momentum.setTranslateY(200);
        setStyle(momentum);
        momentum.setPrefSize(200, 80);

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
        root.getChildren().addAll(momentum,back,pro,headning);

        pro.setOnAction(e -> {
            try {
                ThirdPage PMenu = new ThirdPage();
                PMenu.TheThird(primaryStage);
            } catch (Exception excep) {
                excep.printStackTrace();
            }
        });

        back.setOnAction(e->{
            try {
                Main goBack = new Main();
                goBack.start(primaryStage);
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
                "    -fx-font-size: 1.3em;");
        return b;
    }
}
