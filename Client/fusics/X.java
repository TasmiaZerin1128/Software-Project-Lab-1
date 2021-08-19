package fusics;

import javafx.animation.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.zip.CheckedOutputStream;

public class X {
    int startP=0;
    Circle ball = new Circle();
    Line X = new Line();
    Line arrowHead1 = new Line();
    Line arrowHead2 = new Line();
    Text def = new Text();

    public void start(Pane root, int n, Button back)
    {
        if(n==0) {
            def.setText("The horizontal displacement of a projectile is only influenced by the speed at which\nit moves horizontally (Vx) and the amount of time (T) that it has been moving horizontally.\nThe force of gravity does not influence the horizontal motion");
            def.setTranslateX(420);
            def.setTranslateY(250);
            def.setScaleX(2);
            def.setScaleY(2);
            def.setFill(Color.rgb(0,106,143));
            def.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 14));

            double startx = 200;
            double starty = 700;
            double endx = 205;
            double endy = 700;

            double arrowAngle = Math.toRadians(45.0);
            double arrowLength = 10.0;

            double lineAngle = Math.atan2(starty - endy, startx - endx);

            double x1 = Math.cos(lineAngle + arrowAngle) * arrowLength + endx;
            double y1 = Math.sin(lineAngle + arrowAngle) * arrowLength + endy;

            double x2 = Math.cos(lineAngle - arrowAngle) * arrowLength + endx;
            double y2 = Math.sin(lineAngle - arrowAngle) * arrowLength + endy;

            X = new Line(startx, starty, endx, endy);
            X.setStrokeWidth(5);
            X.setStroke(Color.LIGHTGOLDENRODYELLOW);
            arrowHead1 = new Line(endx, endy, x1, y1);
            arrowHead1.setStrokeWidth(5);
            arrowHead1.setStroke(Color.LIGHTGOLDENRODYELLOW);
            arrowHead2 = new Line(endx, endy, x2, y2);
            arrowHead2.setStrokeWidth(5);
            arrowHead2.setStroke(Color.LIGHTGOLDENRODYELLOW);

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

            Timeline animation = new Timeline(
                    new KeyFrame(Duration.seconds(6), new KeyValue(X.endXProperty(), 1020, Interpolator.EASE_BOTH)),
                    new KeyFrame(Duration.seconds(6), new KeyValue(arrowHead1.endXProperty(), 1012.08, Interpolator.EASE_BOTH)),
                    new KeyFrame(Duration.seconds(6), new KeyValue(arrowHead2.endXProperty(), 1012.08, Interpolator.EASE_BOTH)),
                    new KeyFrame(Duration.seconds(6), new KeyValue(arrowHead1.startXProperty(), 1020, Interpolator.EASE_BOTH)),
                    new KeyFrame(Duration.seconds(6), new KeyValue(arrowHead2.startXProperty(), 1020, Interpolator.EASE_BOTH))
            );
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.setAutoReverse(false);
            animation.play();


            root.setOnMouseClicked(e->{
                if(startP ==0) {
                    animation.pause();
                    pt.pause();
                    startP = 1;
                }
                else
                {
                    animation.play();
                    pt.play();
                    startP=0;
                }

            });

            root.getChildren().addAll(arrowHead1,arrowHead2,X,def);
            root.getChildren().add(path);
            root.getChildren().add(ball);
        }
        else if(n==1)
        {
            root.getChildren().remove(ball);
            root.getChildren().remove(X);
            root.getChildren().remove(arrowHead1);
            root.getChildren().remove(arrowHead2);
            root.getChildren().remove(def);
        }
    }
}
