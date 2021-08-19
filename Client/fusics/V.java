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

public class V extends Path {
    int startP=0;
    Circle ball = new Circle();
    Line line = new Line();
    Line arrowHead1 = new Line();
    Line arrowHead2 = new Line();
    Text def = new Text();

    public void start(Pane root, int n, Button back)
    {
        if(n==0) {
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

            def.setText("The velocity of a projectile consists of two components ( Vx & Vy ). So at any\ninstance, Velocity = Vx + Vy.");
            def.setTranslateX(420);
            def.setTranslateY(250);
            def.setScaleX(2);
            def.setScaleY(2);
            def.setFill(Color.rgb(0,106,143));
            def.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 14));

            double sx = 250;
            double sy = 705;
            double ex = 250;
            double ey = 605;

            double startx = 250;
            double starty = 705;
            double endx = 250;
            double endy = 605;

            double arrowAngle = Math.toRadians(45.0);
            double arrowLength = 10.0;

            double lineAngle = Math.atan2(starty - endy, startx - endx);

            double x1 = Math.cos(lineAngle + arrowAngle) * arrowLength + endx;
            double y1 = Math.sin(lineAngle + arrowAngle) * arrowLength + endy;

            double x2 = Math.cos(lineAngle - arrowAngle) * arrowLength + endx;
            double y2 = Math.sin(lineAngle - arrowAngle) * arrowLength + endy;

            line = new Line(sx, sy, ex, ey);
            line.setStrokeWidth(5);
            line.setStroke(Color.LIGHTGOLDENRODYELLOW);
            /*arrowHead1 = new Line(endx, endy, x1, y1);
            arrowHead1.setStrokeWidth(5);
            arrowHead1.setStroke(Color.LIGHTGOLDENRODYELLOW);
            arrowHead2 = new Line(endx, endy, x2, y2);
            arrowHead2.setStrokeWidth(5);
            arrowHead2.setStroke(Color.LIGHTGOLDENRODYELLOW);*/

            line.setRotate(35);
            //arrowHead1.setRotate(35);
            //arrowHead2.setRotate(35);



            Timeline animation1st = new Timeline(
                    new KeyFrame(Duration.seconds(3),new KeyValue(line.endYProperty(),330,Interpolator.TANGENT(Duration.seconds(3), 330,Duration.seconds(1),100))),
                    new KeyFrame(Duration.seconds(3),new KeyValue(line.startYProperty(),410,Interpolator.TANGENT(Duration.seconds(3), 410,Duration.seconds(1),100))),
                    new KeyFrame(Duration.seconds(3),new KeyValue(line.rotateProperty(),90))
            );
            Timeline animation2nd = new Timeline(
                    new KeyFrame(Duration.seconds(3),new KeyValue(line.endYProperty(),700,Interpolator.TANGENT(Duration.seconds(3), 700,Duration.seconds(1),100))),
                    new KeyFrame(Duration.seconds(3),new KeyValue(line.startYProperty(),800,Interpolator.TANGENT(Duration.seconds(3), 800,Duration.seconds(1),100))),
                    new KeyFrame(Duration.seconds(3),new KeyValue(line.rotateProperty(),150))
            );

            Timeline animationX = new Timeline(
                    new KeyFrame(Duration.seconds(6),new KeyValue(line.startXProperty(),1055,Interpolator.EASE_BOTH)),
                    new KeyFrame(Duration.seconds(6),new KeyValue(line.endXProperty(),1055,Interpolator.EASE_BOTH))
            );


            SequentialTransition seq = new SequentialTransition(animation1st,animation2nd);
            ParallelTransition animation = new ParallelTransition(seq,animationX);
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.setAutoReverse(false);
            animation.play();


            root.setOnMouseClicked(e -> {
                if (startP == 0) {
                    animation.pause();
                    pt.pause();
                    startP = 1;
                } else {
                    animation.play();
                    pt.play();
                    startP = 0;
                }

            });

            root.getChildren().add(path);
            root.getChildren().addAll(arrowHead1,arrowHead2,line,ball,def);
        }
        else if(n==1)
        {
            root.getChildren().remove(ball);
            root.getChildren().remove(arrowHead1);
            root.getChildren().remove(arrowHead2);
            root.getChildren().remove(line);
            root.getChildren().remove(def);
        }
    }
}
