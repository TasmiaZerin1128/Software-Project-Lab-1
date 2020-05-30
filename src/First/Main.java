package First;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Button Start = new Button("Start");
        Start.setTranslateX(700);
        Start.setTranslateY(500);
        Start.setStyle("-fx-padding: 8 15 15 15;\n" +
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
        Start.setPrefSize(150,100);
        Image background = new Image(new FileInputStream("src/Images/bg.jpg"));
        Image gif = new Image(new FileInputStream("src/Images/fusics.gif"));
        ImageView iv = new ImageView(gif);
        iv.setX(270);
        iv.setY(-200);
        iv.setFitHeight(3000);
        iv.setFitWidth(1200);
        iv.setPreserveRatio(true);
        Canvas canvas = new Canvas(1600,900);
        Group root = new Group();
        root.getChildren().addAll(canvas,Start,iv);

        Start.setOnAction(e->{
            try {
                secondPage SP = new secondPage();
                SP.TheSecond(primaryStage);
            }
            catch (Exception excep)
            {
               excep.printStackTrace();
            }
        });

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(background,0,0);
        Scene scene = new Scene(root,1600,800);
        primaryStage.setScene(scene);
        //primaryStage.setFullScreen(true);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
