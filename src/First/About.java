package First;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;

public class About {

    int clkVx=0,clkVy=0,clkV=0,clkX=0,clkY=0,clkHm=0,clkTm=0,clkR=0;

    public void showAbout(Stage primaryStage) throws Exception
    {
        Pane root = new Pane();

        Image formulaVx = new Image(new FileInputStream("src/Images/Vx.png"));
        ImageView Vx = new ImageView(formulaVx);

        Button BtVx = new Button(null,Vx);
        BtVx.setBackground(null);
        BtVx.setBorder(new Border(new BorderStroke(Color.DARKBLUE,BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(2))));

        Image formulaVy = new Image(new FileInputStream("src/Images/Vy.png"));
        ImageView Vy = new ImageView(formulaVy);

        Button BtVy = new Button(null,Vy);
        BtVy.setBackground(null);
        BtVy.setBorder(new Border(new BorderStroke(Color.DARKBLUE,BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(2))));

        Image formulaV = new Image(new FileInputStream("src/Images/V.png"));
        ImageView V = new ImageView(formulaV);

        Button BtV = new Button(null,V);
        BtV.setBackground(null);
        BtV.setBorder(new Border(new BorderStroke(Color.DARKBLUE,BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(2))));

        Image formulaX = new Image(new FileInputStream("src/Images/X.png"));
        ImageView X = new ImageView(formulaX);

        Button BtX = new Button(null,X);
        BtX.setBackground(null);
        BtX.setBorder(new Border(new BorderStroke(Color.DARKBLUE,BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(2))));

        Image formulaY = new Image(new FileInputStream("src/Images/Y.png"));
        ImageView Y = new ImageView(formulaY);

        Button BtY = new Button(null,Y);
        BtY.setBackground(null);
        BtY.setBorder(new Border(new BorderStroke(Color.DARKBLUE,BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(2))));

        Image formulaHm = new Image(new FileInputStream("src/Images/Hm.png"));
        ImageView Hm = new ImageView(formulaHm);

        Button BtHm = new Button(null,Hm);
        BtHm.setBackground(null);
        BtHm.setBorder(new Border(new BorderStroke(Color.DARKBLUE,BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(2))));

        Image formulaTm = new Image(new FileInputStream("src/Images/Tm.png"));
        ImageView Tm = new ImageView(formulaTm);

        Button BtTm = new Button(null,Tm);
        BtTm.setBackground(null);
        BtTm.setBorder(new Border(new BorderStroke(Color.DARKBLUE,BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(2))));

        Image formulaR = new Image(new FileInputStream("src/Images/R.png"));
        ImageView R = new ImageView(formulaR);

        Button BtR = new Button(null,R);
        BtR.setBackground(null);
        BtR.setBorder(new Border(new BorderStroke(Color.DARKBLUE,BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(2))));

        X showX = new X();
        Y showY = new Y();
        R showR = new R();
        H showH = new H();
        V showV = new V();
        Vx showVx = new Vx();
        Vy showVy = new Vy();
        T showT = new T();

        Label timeLabel = new Label("Time: 1");

        Button back = new Button("Back");
        back.setTranslateX(50);
        back.setTranslateY(20);
        setStyle(back);
        back.setPrefSize(60, 30);


        Circle ball = new Circle(30);
        ball.setFill(Color.WHITE);
        ball.setTranslateX(200);
        ball.setTranslateY(700);

        Canvas canvas = new Canvas(1000,700);
        canvas.setTranslateX(100);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image init = new Image(new FileInputStream("src/Images/iniVT.png"));
        gc.drawImage(init,100,305);

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
        pt.setDuration(Duration.seconds(6.0));
        pt.setNode(ball);
        pt.setPath(path);
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.setDelay(Duration.seconds(2.0));
        pt.setAutoReverse(false);
        pt.play();


        root.getChildren().add(path);
        root.getChildren().add(ball);

        Image background = new Image(new FileInputStream("src/Images/bg.jpg"));
        Label headning = new Label("Projectile");
        headning.setScaleX(3);
        headning.setScaleY(3);
        root.getChildren().add(headning);
        headning.setTranslateX(700);
        headning.setTranslateY(50);
        headning.setTextFill(Color.WHITE);
        Label definition = new Label("Projectile motion is a form of motion experienced by an object or particle (a projectile)\n" +
                "that is projected near the Earth's surface and moves along a curved path under the action of gravity only");
        definition.setScaleX(2);
        definition.setScaleY(2);
        root.getChildren().add(definition);
        definition.setTranslateX(400);
        definition.setTranslateY(150);
        definition.setTextFill(Color.WHITE);

        Rectangle Ground = new Rectangle();
        Ground.setHeight(5);
        Ground.setWidth(1000);
        Ground.translateXProperty().set(150);
        Ground.translateYProperty().set(700);
        Ground.setFill(Color.WHITE);

        back.setOnAction(e->{
            try {
                ThirdPage goTP = new ThirdPage();
                goTP.TheThird(primaryStage);
            }catch (Exception E)
            {
                E.printStackTrace();
            }
        });


        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        root.setBackground(bg);
        root.getChildren().addAll(Ground,back,BtVx,BtVy,BtV,BtX,BtY,BtHm,BtTm,BtR,canvas);


        BtVx.setTranslateX(1250);
        BtVx.setTranslateY(100);

        BtVy.setTranslateX(1250);
        BtVy.setTranslateY(180);

        BtV.setTranslateX(1250);
        BtV.setTranslateY(260);

        BtX.setTranslateX(1250);
        BtX.setTranslateY(340);

        BtY.setTranslateX(1250);
        BtY.setTranslateY(420);

        BtHm.setTranslateX(1250);
        BtHm.setTranslateY(500);

        BtTm.setTranslateX(1250);
        BtTm.setTranslateY(590);

        BtR.setTranslateX(1250);
        BtR.setTranslateY(690);

        BtX.setOnAction(e->{
            if(clkX==0) {
                try {
                    root.getChildren().remove(ball);
                    BtX.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                    BtVx.setBackground(null);
                    BtVy.setBackground(null);
                    BtV.setBackground(null);
                    BtY.setBackground(null);
                    BtTm.setBackground(null);
                    BtHm.setBackground(null);
                    BtR.setBackground(null);

                    showX.start(root, 0, back);
                    showY.start(root, 1, back);
                    showR.start(root, 1, back);
                    showH.start(root, 1, back);
                    showVx.start(root, 1, back);
                    showVy.start(root, 1, back);
                    showT.start(root, 1, back);
                    showV.start(root, 1, back);
                    clkX = 1;
                    clkVx = 0;
                    clkVy = 0;
                    clkV = 0;
                    clkY = 0;
                    clkHm = 0;
                    clkTm = 0;
                    clkR = 0;
                } catch ( Exception ev)
                {
                    ev.printStackTrace();
                }
            }
        });

        BtY.setOnAction(e->{
            if(clkY==0) {
                try {
                    root.getChildren().remove(ball);
                    BtY.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                    BtVx.setBackground(null);
                    BtVy.setBackground(null);
                    BtV.setBackground(null);
                    BtX.setBackground(null);
                    BtTm.setBackground(null);
                    BtHm.setBackground(null);
                    BtR.setBackground(null);

                    showX.start(root, 1, back);
                    showY.start(root, 0, back);
                    showR.start(root, 1, back);
                    showH.start(root, 1, back);
                    showVx.start(root, 1, back);
                    showVy.start(root, 1, back);
                    showT.start(root, 1, back);
                    showV.start(root, 1, back);
                    clkY = 1;
                    clkVx = 0;
                    clkVy = 0;
                    clkV = 0;
                    clkX = 0;
                    clkHm = 0;
                    clkTm = 0;
                    clkR = 0;
                } catch ( Exception ev)
                {
                    ev.printStackTrace();
                }
            }
        });

        BtR.setOnAction(e->{
            if(clkR==0) {
                try {
                    root.getChildren().remove(ball);
                    BtR.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                    BtVx.setBackground(null);
                    BtVy.setBackground(null);
                    BtV.setBackground(null);
                    BtY.setBackground(null);
                    BtTm.setBackground(null);
                    BtHm.setBackground(null);
                    BtX.setBackground(null);

                    showX.start(root, 1, back);
                    showY.start(root, 1, back);
                    showR.start(root, 0, back);
                    showH.start(root, 1, back);
                    showVx.start(root, 1, back);
                    showVy.start(root, 1, back);
                    showT.start(root, 1, back);
                    showV.start(root, 1, back);
                    clkR = 1;
                    clkVx = 0;
                    clkVy = 0;
                    clkV = 0;
                    clkX = 0;
                    clkHm = 0;
                    clkTm = 0;
                    clkY = 0;
                } catch ( Exception ev)
                {
                    ev.printStackTrace();
                }
            }
        });

        BtHm.setOnAction(e->{
            if(clkHm==0) {
                try {
                    root.getChildren().remove(ball);
                    BtHm.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                    BtVx.setBackground(null);
                    BtVy.setBackground(null);
                    BtV.setBackground(null);
                    BtY.setBackground(null);
                    BtTm.setBackground(null);
                    BtX.setBackground(null);
                    BtR.setBackground(null);

                    showX.start(root, 1, back);
                    showY.start(root, 1, back);
                    showR.start(root, 1, back);
                    showH.start(root, 0, back);
                    showVx.start(root, 1, back);
                    showVy.start(root, 1, back);
                    showT.start(root, 1, back);
                    showV.start(root, 1, back);
                    clkHm = 1;
                    clkVx = 0;
                    clkVy = 0;
                    clkV = 0;
                    clkX = 0;
                    clkR = 0;
                    clkTm = 0;
                    clkY = 0;
                } catch (Exception ev)
                {
                    ev.printStackTrace();
                }
            }
        });

        BtV.setOnAction(e->{
            if(clkV==0) {
                try {
                    root.getChildren().remove(ball);
                    BtV.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                    BtVx.setBackground(null);
                    BtVy.setBackground(null);
                    BtX.setBackground(null);
                    BtY.setBackground(null);
                    BtTm.setBackground(null);
                    BtHm.setBackground(null);
                    BtR.setBackground(null);

                    showX.start(root, 1, back);
                    showY.start(root, 1, back);
                    showR.start(root, 1, back);
                    showH.start(root, 1, back);
                    showV.start(root, 0, back);
                    showVx.start(root, 1, back);
                    showVy.start(root, 1, back);
                    showT.start(root, 1, back);
                    clkHm = 0;
                    clkVx = 0;
                    clkVy = 0;
                    clkV = 1;
                    clkX = 0;
                    clkR = 0;
                    clkTm = 0;
                    clkY = 0;
                } catch (Exception ev)
                {
                    ev.printStackTrace();
                }
            }
        });

        BtVx.setOnAction(e->{
            if(clkVx==0) {
                try {
                    root.getChildren().remove(ball);
                    BtVx.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                    BtX.setBackground(null);
                    BtVy.setBackground(null);
                    BtV.setBackground(null);
                    BtY.setBackground(null);
                    BtTm.setBackground(null);
                    BtHm.setBackground(null);
                    BtR.setBackground(null);

                    showX.start(root, 1, back);
                    showY.start(root, 1, back);
                    showR.start(root, 1, back);
                    showH.start(root, 1, back);
                    showV.start(root, 1, back);
                    showVx.start(root, 0, back);
                    showVy.start(root, 1, back);
                    showT.start(root, 1, back);
                    clkHm = 0;
                    clkVx = 1;
                    clkVy = 0;
                    clkV = 0;
                    clkX = 0;
                    clkR = 0;
                    clkTm = 0;
                    clkY = 0;
                } catch (Exception ev)
                {
                    ev.printStackTrace();
                }
            }
        });

        BtVy.setOnAction(e->{
            if(clkVy==0) {
                try {
                    root.getChildren().remove(ball);
                    BtVy.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                    BtVx.setBackground(null);
                    BtX.setBackground(null);
                    BtV.setBackground(null);
                    BtY.setBackground(null);
                    BtTm.setBackground(null);
                    BtHm.setBackground(null);
                    BtR.setBackground(null);

                    showX.start(root, 1, back);
                    showY.start(root, 1, back);
                    showR.start(root, 1, back);
                    showH.start(root, 1, back);
                    showV.start(root, 1, back);
                    showVx.start(root, 1, back);
                    showVy.start(root, 0, back);
                    showT.start(root, 1, back);
                    clkHm = 0;
                    clkVx = 0;
                    clkVy = 1;
                    clkV = 0;
                    clkX = 0;
                    clkR = 0;
                    clkTm = 0;
                    clkY = 0;
                } catch (Exception ev)
                {
                    ev.printStackTrace();
                }
            }
        });

        BtTm.setOnAction(e->{
            if(clkTm==0) {
                try {
                    root.getChildren().remove(ball);
                    BtTm.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                    BtVx.setBackground(null);
                    BtVy.setBackground(null);
                    BtV.setBackground(null);
                    BtY.setBackground(null);
                    BtX.setBackground(null);
                    BtHm.setBackground(null);
                    BtR.setBackground(null);

                    showX.start(root, 1, back);
                    showY.start(root, 1, back);
                    showR.start(root, 1, back);
                    showH.start(root, 1, back);
                    showV.start(root, 1, back);
                    showVx.start(root, 1, back);
                    showVy.start(root, 1, back);
                    showT.start(root, 0, back);
                    clkHm = 0;
                    clkVx = 0;
                    clkVy = 0;
                    clkV = 0;
                    clkX = 0;
                    clkR = 0;
                    clkTm = 1;
                    clkY = 0;
                } catch (Exception ev)
                {
                    ev.printStackTrace();
                }
            }
        });

        Scene scene = new Scene(root, 1600, 800);
        primaryStage.setScene(scene);
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
                "    -fx-font-size: 1.1em;");
        return b;
    }

}
