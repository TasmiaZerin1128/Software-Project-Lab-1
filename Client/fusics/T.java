package fusics;

import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class T {
    int clk=0, timeSec=1;
    int startP=0;
    Circle ball = new Circle();
    Timeline time = new Timeline();
    Label timeLabel = new Label();
    Text def = new Text();

    public void start(Pane root, int n, Button back)
    {
        if(n==0) {
            def.setText("T is the total time taken for the projectile to fall back to the same plane from\nwhich it was projected.");
            def.setTranslateX(420);
            def.setTranslateY(250);
            def.setScaleX(2);
            def.setScaleY(2);
            def.setFill(Color.rgb(0,106,143));
            def.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 14));

            ball = new Circle(30);
            ball.setFill(Color.WHITE);
            ball.setTranslateX(200);
            ball.setTranslateY(700);

            Path path = new Path();
            path.getElements().add(new MoveTo(200f, 700f));
            CubicCurveTo cct = new CubicCurveTo();
            cct.setControlX1(550.0f);
            cct.setControlY1(250.0f);
            cct.setControlX2(670.0f);
            cct.setControlY2(250.0f);
            cct.setX(1017.0f);
            cct.setY(700.0f);
            path.getElements().add(cct);
            path.setOpacity(100.0);
            path.setStroke(Color.WHITE);

            PathTransition pt = new PathTransition();
            pt.setDuration(Duration.seconds(6));
            pt.setNode(ball);
            pt.setPath(path);
            pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pt.setCycleCount(Timeline.INDEFINITE);
            pt.setAutoReverse(false);
            pt.play();

            timeLabel.setText("Time: "+ timeSec+ " s");
            timeLabel.setTranslateX(900);
            timeLabel.setTranslateY(400);
            timeLabel.setScaleX(4);
            timeLabel.setScaleY(4);
            timeLabel.setTextFill(Color.LIGHTGOLDENRODYELLOW);

            time = new Timeline();
            time.setCycleCount(Timeline.INDEFINITE);
            time.setAutoReverse(false);
            time.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(1),
                            event -> {
                                timeSec++;
                                timeLabel.setText("Time: " + timeSec+ " s");
                                if (timeSec == 6) {
                                    timeSec=0;
                                    time.playFromStart();
                                }
                            })
            );

            time.play();

            root.setOnMouseClicked(e->{
                if(startP ==0) {
                    time.pause();
                    pt.pause();
                    startP = 1;
                }
                else
                {
                    time.play();
                    pt.play();
                    startP=0;
                }

            });

            root.getChildren().add(path);
            root.getChildren().addAll(ball,timeLabel,def);
        }
        else if(n==1)
        {
            root.getChildren().remove(ball);
            root.getChildren().remove(timeLabel);
            root.getChildren().remove(def);
            time.getKeyFrames().clear();
            timeSec=1;
        }
    }
}
