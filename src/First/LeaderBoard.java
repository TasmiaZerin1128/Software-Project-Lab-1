package First;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LeaderBoard {
    public void Board(Stage primaryStage) throws FileNotFoundException {
        Pane root= new Pane();
        Text end = new Text("The End");
        end.setFill(Color.WHITE);
        end.setTranslateX(700);
        end.setTranslateY(400);
        end.setScaleX(4);
        end.setScaleY(4);
        root.getChildren().add(end);
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
}
