package First;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.Scanner;

public class experiment extends Application {
    int x=0,y=0,r,flag=0;
    double h, w;
    double v=0,vofObj1=0,vofObj2=0;
    double ballXpos= 12, ballYpos=10.7;
    double ballAllposX=0, ballAllposY=0,obsAllX=0;
    int countGiveV=0, dire = 1;
    double g=9.8, t=0,theta=0,posX,posY;
    double R,T;
    double time=0;
    int animationFlag=0;

    Sphere[] ball = new Sphere[100];
    Circle[] path = new Circle[1000];
    Box[] rectangle = new Box[100];
    int point=0;
    int checkR=1,checkB=0;

    @Override
    public void start(Stage primaryStage) throws NullPointerException, FileNotFoundException {

        Pane root = new Pane();

        Alert a = new Alert(Alert.AlertType.NONE);

        Image background = new Image(new FileInputStream("src/Images/expbg.png"));

        NumberAxis xAxis = new NumberAxis(0, 100, 5);

        NumberAxis yAxis = new NumberAxis(0, 50, 5);

        LineChart lc = new LineChart(xAxis,yAxis);

        lc.setMinHeight(625);
        lc.setMinWidth(1250);

        lc.setTranslateX(50);
        lc.setTranslateY(115);
        lc.setBlendMode(BlendMode.SCREEN);

        root.addEventHandler(KeyEvent.KEY_PRESSED,e->{
            if(e.getCode()==KeyCode.R)
            {
                if(xAxis.getUpperBound()==300) {
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Maximum limit of X reached");
                    a.show();
                }
                else {
                    xAxis.setUpperBound(xAxis.getUpperBound() + 50);
                    ballXpos = 24 /(xAxis.getUpperBound() / 50);
                    if(checkR==0)
                        rectangle[y - 1].scaleXProperty().set(50 / (xAxis.getUpperBound() - 50));
                    if(checkB==0)
                        ball[x-1].scaleXProperty().set(50 / (xAxis.getUpperBound() - 50));
                }
            }
            else if(e.getCode()==KeyCode.L)
            {
                if(xAxis.getUpperBound()==100) {
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Minimum limit of X reached");
                    a.show();
                }
                else {
                    xAxis.setUpperBound(xAxis.getUpperBound() - 50);
                    ballXpos = 24 /(xAxis.getUpperBound() / 50);
                    if(checkR==0)
                        rectangle[y - 1].scaleXProperty().set(50 / (xAxis.getUpperBound() - 50));
                }
            }
            else if(e.getCode()==KeyCode.U)
            {
                if(yAxis.getUpperBound()==250) {
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Maximum limit of Y reached");
                    a.show();
                }
                else {
                    yAxis.setUpperBound(yAxis.getUpperBound() + 50);
                    ballYpos = 10.7 /(yAxis.getUpperBound() / 50);
                    if (checkR == 0) {
                        rectangle[y - 1].scaleYProperty().set(50 / yAxis.getUpperBound());
                        rectangle[y - 1].setTranslateY(700 - ((yAxis.getUpperBound()-50) * (700 - rectangle[y - 1].getTranslateY())) / yAxis.getUpperBound());
                    }
                }
            }
            else if(e.getCode()==KeyCode.D)
            {
                if(yAxis.getUpperBound()==50) {
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Minimum limit of Y reached");
                    a.show();
                }
                else {
                    yAxis.setUpperBound(yAxis.getUpperBound() - 50);
                    ballYpos = 10.7 / (yAxis.getUpperBound() / 50);
                    if (checkR == 0) {
                        rectangle[y - 1].scaleYProperty().set(50 / yAxis.getUpperBound());
                        rectangle[y - 1].setTranslateY(700 - ((yAxis.getUpperBound()+50) * (700 - rectangle[y - 1].getTranslateY())) / yAxis.getUpperBound());
                    }
                }
            }
        });

        Scanner sc = new Scanner(new File("src/First/Instructions"));
        ArrayList<String> Ins = new ArrayList<String>();
        while (sc.hasNextLine()) {
            String S = sc.nextLine();
            Ins.add(S);
        }

        Label SVelo = new Label("Velocity: 0 m/s" );
        SVelo.setTranslateX(1350);
        SVelo.setTranslateY(70);
        SVelo.setTextFill(Color.WHITE);
        SVelo.setScaleX(2);
        SVelo.setScaleY(2);

        Label STime = new Label("Time: 0 s" );
        STime.setTranslateX(1350);
        STime.setTranslateY(120);
        STime.setTextFill(Color.WHITE);
        STime.setScaleX(2);
        STime.setScaleY(2);

        Label SHmax = new Label( "Hmax: 0 m" );
        SHmax.setTranslateX(1350);
        SHmax.setTranslateY(170);
        SHmax.setTextFill(Color.WHITE);
        SHmax.setScaleX(2);
        SHmax.setScaleY(2);

        Label SR = new Label("Range: 0 m");
        SR.setTranslateX(1350);
        SR.setTranslateY(220);
        SR.setTextFill(Color.WHITE);
        SR.setScaleX(2);
        SR.setScaleY(2);

        Label STmax = new Label("Tmax: 0 s");
        STmax.setTranslateX(1350);
        STmax.setTranslateY(270);
        STmax.setTextFill(Color.WHITE);
        STmax.setScaleX(2);
        STmax.setScaleY(2);

        Label SX = new Label("X: 0 m");
        SX.setTranslateX(1350);
        SX.setTranslateY(320);
        SX.setTextFill(Color.WHITE);
        SX.setScaleX(2);
        SX.setScaleY(2);

        Label SY = new Label("Y: 0 m");
        SY.setTranslateX(1350);
        SY.setTranslateY(370);
        SY.setTextFill(Color.WHITE);
        SY.setScaleX(2);
        SY.setScaleY(2);

        Label ballposition = new Label("Position of ball while hovering:\nX: 0 m \nY: 0 m");
        ballposition.setTranslateX(400);
        ballposition.setTranslateY(40);
        ballposition.setTextFill(Color.WHITE);
        ballposition.setScaleX(1.5);
        ballposition.setScaleY(1.5);

        Label Obsposition = new Label("Position of obstacle while hovering:\nX: 0 m");
        Obsposition.setTranslateX(700);
        Obsposition.setTranslateY(40);
        Obsposition.setTextFill(Color.WHITE);
        Obsposition.setScaleX(1.5);
        Obsposition.setScaleY(1.5);

        Label collision = new Label("Collision Occured!!");
        collision.setTranslateX(650);
        collision.setTranslateY(735);
        collision.setTextFill(Color.RED);
        collision.setScaleX(2);
        collision.setScaleY(2);
        collision.setOpacity(0);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), collision);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Animation.INDEFINITE);

        ball[x]= new Sphere();
        rectangle[y]= new Box();

        Rectangle Ground = new Rectangle();
        Ground.setHeight(5);
        Ground.setWidth(2000);
        Ground.translateXProperty().set(0);
        Ground.translateYProperty().set(700);
        Ground.setFill(Color.WHITE);

        Menu m1 = new Menu("Add");

        MenuItem item1 = new MenuItem("Circle");
        FileInputStream it1 = new FileInputStream("src/Images/circle.png");
        Image inp1 = new Image(it1);
        item1.setGraphic(new ImageView(inp1));

        MenuItem item2 = new MenuItem("Rectangle");
        FileInputStream it2 = new FileInputStream("src/Images/rectangle.png");
        Image inp2 = new Image(it2);
        item2.setGraphic(new ImageView(inp2));

        m1.getItems().addAll(item1, item2);
        FileInputStream input = new FileInputStream("src/Images/A.png");
        Image im = new Image(input);
        m1.setGraphic(new ImageView(im));

        ContextMenu cm = new ContextMenu();
        MenuItem Ivelo = new MenuItem("Add/Change Initial Velocity");
        MenuItem Itheta = new MenuItem("Add/Change Initial Angle");
        MenuItem Igrav = new MenuItem("Add/Change Gravity");
        cm.getItems().addAll(Ivelo,Itheta,Igrav);

        ContextMenu CM1 = new ContextMenu();
        MenuItem V = new MenuItem("Add/Change Velocity");
        MenuItem Id = new MenuItem("Add/Change Direction");
        CM1.getItems().addAll(V,Id);

        x++;
        item1.setOnAction(e -> {
            if(checkB==1)
            {
                a.setAlertType(Alert.AlertType.WARNING);
                a.setContentText("Not permitted to use more than one circle");
                a.show();
            }
            else {
                try {
                    Stage radius = new Stage();
                    GridPane R = new GridPane();
                    R.setAlignment(Pos.CENTER);
                    Label setRL = new Label("Radius");
                    TextField Rad = new TextField();
                    Label CL = new Label("m");
                    R.setVgap(4);
                    R.setHgap(10);
                    R.setPadding(new Insets(5, 5, 5, 5));
                    R.add(setRL, 0, 0);
                    R.add(Rad, 1, 0);
                    R.add(CL, 2, 0);
                    Scene S = new Scene(R, 500, 200);
                    radius.setTitle("Radius");
                    radius.setScene(S);
                    radius.show();

                    S.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                        if (event.getCode() == KeyCode.ENTER) {
                            if(Rad.getText()==null)
                            {
                                throw new IllegalArgumentException();
                            }
                            try {
                                String Radius = Rad.getText();
                                r = Integer.parseInt(Radius);

                                if(r<=0 || r>25)
                                {
                                    throw new Exception();
                                }
                                else {
                                    System.out.println("radius "+r);
                                    checkB++;
                                    ball[x - 1].setRadius(r);
                                    root.getChildren().add(ball[x - 1]);
                                    ball[x - 1].setTranslateX(700);
                                    ball[x - 1].setTranslateY(300);
                                    PhongMaterial M = new PhongMaterial();
                                    M.setDiffuseMap(new Image(new FileInputStream("src/Images/ball.jpg")));
                                    ball[x - 1].setMaterial(M);
                                    radius.close();
                                }
                            }
                            catch (IllegalArgumentException il)
                            {
                                radius.close();
                                a.setAlertType(Alert.AlertType.ERROR);
                                a.setContentText("Please enter a number");
                                a.showAndWait();
                                Rad.clear();
                                radius.show();
                            }
                            catch (Exception exc)
                            {
                                radius.close();
                                a.setAlertType(Alert.AlertType.ERROR);
                                a.setContentText("Please enter Positive radius and within 25");
                                a.showAndWait();
                                Rad.clear();
                                radius.show();
                            }
                        }
                    });

                    ball[x-1].setOnMouseDragged(E ->{
                        if((ball[x-1].getTranslateY()+E.getY())<(700-r) && (ball[x-1].getTranslateY()+E.getY())>(110+r) && (ball[x-1].getTranslateX() + E.getX()>=(84)) && (ball[x-1].getTranslateX()+E.getX()<(1275))){
                            ball[x-1].setTranslateX(ball[x-1].getTranslateX() + E.getX());
                            ball[x-1].setTranslateY(ball[x-1].getTranslateY() + E.getY());
                            ballAllposX = ((xAxis.getUpperBound()*(ball[x-1].getTranslateX()-84))/1205);
                            ballAllposY = (yAxis.getUpperBound()*(700-ball[x-1].getRadius()-ball[x-1].getTranslateY())/540);
                            ballposition.setText("Position of ball while hovering: \nX: " + String.format("%.1f",ballAllposX) +" m \nY: " + String.format("%.1f", ballAllposY) +" m");
                        }
                    });

                    ball[x-1].setOnContextMenuRequested(event -> cm.show(ball[x-1], event.getScreenX(), event.getScreenY()));

                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        y++;
        item2.setOnAction(e -> {
            if(checkR==0)
            {
                a.setAlertType(Alert.AlertType.WARNING);
                a.setContentText("Not permitted to use more than one rectangle");
                a.show();
            }
            else {
                try {
                    Stage size = new Stage();
                    GridPane Hw = new GridPane();
                    Hw.setAlignment(Pos.CENTER);
                    Label setH = new Label("Height");
                    Label setW = new Label("Width");
                    TextField He = new TextField();
                    TextField We = new TextField();
                    Label CL = new Label("m");
                    Label CL1 = new Label("m");
                    Hw.setVgap(4);
                    Hw.setHgap(10);
                    Hw.setPadding(new Insets(5, 5, 5, 5));
                    Hw.add(setH, 0, 0);
                    Hw.add(setW, 0, 1);
                    Hw.add(He, 1, 0);
                    Hw.add(We, 1, 1);
                    Hw.add(CL, 2, 0);
                    Hw.add(CL1, 2, 1);
                    Scene S = new Scene(Hw, 500, 200);
                    size.setTitle("Size");
                    size.setScene(S);
                    size.show();

                    S.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                        if (event.getCode() == KeyCode.ENTER) {
                            if (He.getText() == null || We.getText() == null) {
                                throw new IllegalArgumentException();
                            }
                            try {
                                String Height = He.getText();
                                String width = We.getText();
                                if(Double.parseDouble(Height)<1 || Double.parseDouble(width)<1)
                                {
                                    throw new Exception();
                                }
                                else {
                                    checkR--;
                                    h = Double.parseDouble(Height) * 11.4;
                                    w = Double.parseDouble(width) * 11.3;
                                    rectangle[y-1].setHeight(h);
                                    rectangle[y - 1].setWidth(w);
                                    rectangle[y - 1].setTranslateX(600);
                                    rectangle[y - 1].setTranslateY(700 - h / 2);

                                    PhongMaterial M = new PhongMaterial();
                                    M.setDiffuseMap(new Image(new FileInputStream("src/Images/wall.jpg")));
                                    rectangle[y - 1].setMaterial(M);
                                    root.getChildren().add(rectangle[y - 1]);
                                    size.close();

                                    rectangle[y - 1].setOnMouseDragged(E ->{
                                        if((rectangle[y-1].getTranslateX() + E.getX()>(70+ w/2)) && (rectangle[y-1].getTranslateX()+E.getX()<(1305-w/2))) {
                                            rectangle[y - 1].setTranslateX(rectangle[y - 1].getTranslateX() + E.getX());
                                            obsAllX = ((xAxis.getUpperBound()*(rectangle[y-1].getTranslateX()-84))/1205);
                                            Obsposition.setText("Position of obstacle while hovering:\nX: " + String.format("%.1f",obsAllX)+ " m");
                                        }
                                    });
                                    rectangle[y-1].setOnContextMenuRequested(Event -> CM1.show(rectangle[y-1], Event.getScreenX(), Event.getScreenY()));
                                }
                            }
                            catch (IllegalArgumentException ex) {
                                size.close();
                                a.setAlertType(Alert.AlertType.ERROR);
                                a.setContentText("Please enter numbers in all the parameters");
                                a.showAndWait();
                                He.clear();
                                We.clear();
                                size.show();
                            }
                            catch (Exception EX)
                            {
                                size.close();
                                a.setAlertType(Alert.AlertType.ERROR);
                                a.setContentText("Please enter positive numbers");
                                a.showAndWait();
                                He.clear();
                                We.clear();
                                size.show();
                            }
                        }
                    });

                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });


        V.setOnAction(e->{
            try {
                Stage velo = new Stage();
                GridPane VELO = new GridPane();
                VELO.setAlignment(Pos.CENTER);
                Label setV = new Label("Velocity:");
                Label CL = new Label("m/s");
                TextField Ve = new TextField();
                VELO.add(setV, 3, 0, 2, 1);
                VELO.add(Ve, 3, 10, 1, 1);
                VELO.add(CL, 25, 10, 1, 1);
                Scene S = new Scene(VELO, 300, 200);
                velo.setTitle("Velocity");
                velo.setScene(S);
                velo.show();

                S.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        String Velocity = Ve.getText();
                        vofObj1 = Double.parseDouble(Velocity);
                        velo.close();
                    }
                });
            }catch (Exception E) {
                E.printStackTrace();
            }
        });

        Id.setOnAction(e->{
            try {
                Stage dir = new Stage();
                Pane DIR = new Pane();
                ToggleGroup group = new ToggleGroup();
                Label setd = new Label("Select Direction");
                RadioButton l = new RadioButton("LEFT");
                l.setToggleGroup(group);
                RadioButton r = new RadioButton("RIGHT");
                r.setToggleGroup(group);
                Button enter = new Button("Enter");
                setd.setTranslateX(100);
                setd.setTranslateY(20);
                l.setTranslateX(80);
                l.setTranslateY(90);
                r.setTranslateX(180);
                r.setTranslateY(90);
                enter.setTranslateX(130);
                enter.setTranslateY(150);
                DIR.getChildren().addAll(setd,l,r,enter);
                dir.setTitle("Direction");
                Scene S = new Scene(DIR, 300, 200);
                dir.setScene(S);
                dir.show();

                enter.setOnAction(E -> {
                    try {
                        if(group.getSelectedToggle()==null)
                        {
                            dir.close();
                            a.setAlertType(Alert.AlertType.ERROR);
                            a.setContentText("Please enter a direction");
                            a.showAndWait();
                            dir.show();
                        }
                        else
                        {
                            dir.close();
                        }
                        if(r.isSelected()==true)
                        {
                            dire = 1;
                        }
                        else
                        {
                            dire= -1;
                        }
                    } catch (Exception Ev) {
                        Ev.printStackTrace();
                    }
                });
            }catch (Exception E) {
                E.printStackTrace();
            }
        });


        Ivelo.setOnAction(e -> {
            try {
                Stage velo = new Stage();
                GridPane VELO = new GridPane();
                VELO.setAlignment(Pos.CENTER);
                Label setV = new Label("Velocity:");
                TextField Ve = new TextField();
                VELO.add(setV,3,0,2,1);
                VELO.add(Ve,3,10,1,1);
                Scene S = new Scene(VELO, 300, 200);
                velo.setTitle("Velocity");
                velo.setScene(S);
                velo.show();

                S.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        String Velocity = Ve.getText();
                        v = Double.parseDouble(Velocity);
                        velo.close();
                    }
                });
            } catch (Exception E) {
                E.printStackTrace();
            }
        });

        Igrav.setOnAction(e -> {
            try {
                Stage grav = new Stage();
                GridPane G = new GridPane();
                G.setAlignment(Pos.CENTER);
                Label setG = new Label("Gravity:");
                TextField Gr = new TextField();
                G.add(setG,3,0,2,1);
                G.add(Gr,3,10,1,1);
                Scene S = new Scene(G, 300, 200);
                grav.setTitle("Gravity");
                grav.setScene(S);
                grav.show();

                S.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        String Gravity = Gr.getText();
                        g = Double.parseDouble(Gravity);
                        grav.close();
                    }
                });
            } catch (Exception E) {
                E.printStackTrace();
            }
        });

        Itheta.setOnAction(e -> {
            try {
                Stage thet = new Stage();
                GridPane T = new GridPane();
                T.setAlignment(Pos.CENTER);
                Label setT = new Label("Angle:");
                TextField Th = new TextField();
                T.add(setT,3,0,2,1);
                T.add(Th,3,10,1,1);
                Scene S = new Scene(T, 300, 200);
                thet.setTitle("Angle");
                thet.setScene(S);
                thet.show();

                S.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        String Theta = Th.getText();
                        theta = (Double.parseDouble(Theta)*Math.PI)/180;
                        thet.close();
                    }
                });
            } catch (Exception E) {
                E.printStackTrace();
            }
        });


        Button St = new Button("Start");
        St.setPadding(new Insets(15));
        setStyleO(St);
        St.setTranslateX(1160);
        St.setTranslateY(730);

        Button Pa = new Button("Pause");
        Pa.setPadding(new Insets(15));
        setStyleO(Pa);
        Pa.setTranslateX(1280);
        Pa.setTranslateY(730);

        St.setOnAction(e ->{
            if(checkB==0)
            {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Have to add a ball for projectile motion!");
                a.showAndWait();
            }
            else {
                if (animationFlag == 0) {
                    animationFlag=1;
                    posX = ball[x - 1].getTranslateX();
                    posY = ball[x - 1].getTranslateY();
                    double S = Math.sin(theta);
                    double C = Math.cos(theta);
                    double H = Math.pow((v * S), 2) / (2 * g);
                    if (theta != 0) {
                        T = (2 * (v * S)) / g;
                        R = ((v * v) * Math.sin(2 * theta)) / g;
                    } else if (theta == 0) {
                        T = Math.sqrt(((yAxis.getUpperBound() * (700 - ball[x - 1].getRadius() - posY) / 540) * 2) / g);
                        System.out.println("T" + T);
                        R = T * v;
                    }
                    // linechart er start hoy 84 e, box ta
                    if ((((xAxis.getUpperBound() * (posX - 84)) / 1205) + R) > xAxis.getUpperBound()) {
                        double oldupperX = xAxis.getUpperBound();
                        System.out.println("yes");
                        int half = (int) (((oldupperX * (posX - 84)) / 1205) + R) / 50;
                        xAxis.setUpperBound(half * 50 + 50);
                        ballXpos = 24 / (xAxis.getUpperBound() / 50);
                        ball[x - 1].setTranslateX(84 + (oldupperX * (posX - 84)) / xAxis.getUpperBound());
                        posX = ball[x - 1].getTranslateX();
                        if (checkR == 0) {
                            rectangle[y - 1].scaleXProperty().set(50 / (xAxis.getUpperBound() - 50));
                            rectangle[y - 1].setTranslateX(84 + (oldupperX * (rectangle[y - 1].getTranslateX() - 84)) / xAxis.getUpperBound());
                        }
                    }
                    if ((((yAxis.getUpperBound() * (700 - ball[x - 1].getRadius() - posY) / 540)) + H) > yAxis.getUpperBound()) {
                        double oldupperY = yAxis.getUpperBound();
                        System.out.println(" " + posY);
                        int half = (int) (((oldupperY * (700 - ball[x - 1].getRadius() - posY)) / 540) + H) / 50;
                        yAxis.setUpperBound(half * 50 + 50);
                        ballYpos = 10.7 / (yAxis.getUpperBound() / 50);
                        ball[x - 1].setTranslateY(700 - ball[x - 1].getRadius() - (oldupperY * (700 - ball[x - 1].getRadius() - posY)) / yAxis.getUpperBound());
                        System.out.println((oldupperY * (700 - ball[x - 1].getRadius() - posY)) / yAxis.getUpperBound());
                        posY = ball[x - 1].getTranslateY();
                        if (checkR == 0) {
                            rectangle[y - 1].scaleYProperty().set(50 / yAxis.getUpperBound());
                            rectangle[y - 1].setTranslateY(700 - (oldupperY * (700 - rectangle[y - 1].getTranslateY())) / yAxis.getUpperBound());
                        }
                    }

                    new AnimationTimer() {
                        @Override
                        public void handle(long now) {
                            double k = (v * C * t);
                            double j = (v * S * t) - (0.5 * g * t * t);
                            ball[x - 1].setTranslateX(posX + k * ballXpos);
                            ball[x - 1].setTranslateY(posY - j * ballYpos);//50 er jonno 9, 100 r jonno 4.5
                            path[point] = new Circle(1);
                            path[point].setFill(Color.DARKBLUE);
                            path[point].setCenterX(posX + k * ballXpos);
                            path[point].setCenterY(posY - j * ballYpos);
                            root.getChildren().add(path[point]);
                            point++;
                            System.out.println(point);
                            if (checkCollisions(xAxis.getUpperBound(), j, k) == true) {
                                collision.setOpacity(100);
                                fadeTransition.play();
                                stop();
                                animationFlag=2;
                                double coAngle = Math.atan((v * S - (g * t)) / (v * C));
                                v = Math.sqrt((v * C * v * C) + ((v * S - (g * t)) * (v * S - (g * t))));
                                posX = ball[x - 1].getTranslateX();
                                posY = ball[x - 1].getTranslateY();
                                double si = Math.sin(coAngle);
                                double co = Math.cos(coAngle);
                                new AnimationTimer() {
                                    @Override
                                    public void handle(long l) {
                                        double k = (v * co * time);
                                        double j = (v * si * time) - (0.5 * g * time * time);
                                        ball[x - 1].setTranslateX(posX - k * ballXpos);
                                        ball[x - 1].setTranslateY(posY - j * ballYpos);
                                        if (ball[x - 1].getTranslateX() <= (84 + r) || ball[x - 1].getTranslateX() >= (1205 - r) || ball[x - 1].getTranslateY() <= (110 + r) || ball[x - 1].getTranslateY() >= (700 - r)) {
                                            stop();
                                            animationFlag=2;
                                            ball[x - 1].setOpacity(0);
                                        } else {
                                            time += 0.01;
                                        }
                                    }
                                }.start();
                            } else {
                                double vx = v * C;
                                double vy = v * S - (g * t);
                                double TotalV = Math.sqrt((vx * vx) + (vy * vy));
                                SVelo.setText("Velocity: " + String.format("%.2f", TotalV) + "m/s");
                                if (t >= T && ((yAxis.getUpperBound() * (700 - ball[x - 1].getRadius() - posY) / 540)) < 1) {
                                    STime.setText("Time: " + String.format("%.2f", T) + "s");
                                } else {
                                    STime.setText("Time: " + String.format("%.2f", t) + "s");
                                }
                                SHmax.setText("Hmax: " + String.format("%.2f", H) + "m");
                                SR.setText("Range: " + String.format("%.2f", R) + "m");
                                STmax.setText("Tmax: " + String.format("%.2f", T) + "s");
                                if (k >= R && (yAxis.getUpperBound() * (700 - ball[x - 1].getRadius() - ball[x - 1].getTranslateY()) / 540) == 0)
                                    SX.setText("X: " + String.format("%.2f", R) + "m");
                                else
                                    SX.setText("X: " + String.format("%.2f", k) + "m");
                                if (j <= 0 && ((yAxis.getUpperBound() * (700 - ball[x - 1].getRadius() - posY) / 540)) < 1)
                                    SY.setText("Y: 0" + "m");
                                else
                                    SY.setText("Y: " + String.format("%.2f", j) + "m");

                                if (rectangle[y - 1].getTranslateX() > 84 && rectangle[y - 1].getTranslateX() < (1205 - w / 2)) {
                                    if (dire == -1)
                                        rectangle[y - 1].setTranslateX(rectangle[y - 1].getTranslateX() - (12 * 0.01 * vofObj1));
                                    else
                                        rectangle[y - 1].setTranslateX(rectangle[y - 1].getTranslateX() + (12 * 0.01 * vofObj1));
                                } else
                                    rectangle[y - 1].setOpacity(0);

                                if (ball[x - 1].getTranslateY() >= (700 - ball[x - 1].getRadius())) {
                                    stop();
                                    animationFlag=2;
                                } else if (t >= T && T != 0 && ball[x - 1].getTranslateY() >= (700 - ball[x - 1].getRadius())) {
                                    stop();
                                    animationFlag=2;
                                } else {
                                    t += 0.01;
                                }

                                Pa.setOnAction(event -> {
                                    if (flag == 1) {
                                        start();
                                        Pa.setText("Pause");
                                        flag = 0;
                                    } else {
                                        Pa.setText("Resume");
                                        stop();
                                        flag = 1;
                                        root.setOnKeyPressed(E -> {
                                            if (E.getCode() == KeyCode.S) {
                                                t += 0.01;
                                                double Sx = (v * C * t);
                                                double Sy = (v * S * t) - (0.5 * g * t * t);
                                                if ((posY - Sy * ballYpos) <= (700 - r)) {
                                                    ball[x - 1].setTranslateX(posX + Sx * ballXpos);
                                                    ball[x - 1].setTranslateY(posY - Sy * ballYpos);
                                                    path[point] = new Circle(1);
                                                    path[point].setFill(Color.DARKBLUE);
                                                    path[point].setCenterX(posX + Sx * ballXpos);
                                                    path[point].setCenterY(posY - Sy * ballYpos);
                                                    root.getChildren().add(path[point]);
                                                    point++;
                                                    if (checkCollisions(xAxis.getUpperBound(), j, k) == true) {
                                                        collision.setOpacity(100);
                                                        fadeTransition.play();
                                                    /*double coAngle = Math.atan((v * S - (g * t)) / (v * C));
                                                    coAngle = (coAngle) * (180 / Math.PI);
                                                    v = Math.sqrt((v * C * v * C) + ((v * S - (g * t)) * (v * S - (g * t))));
                                                    posX = ball[x - 1].getTranslateX();
                                                    posY = ball[x - 1].getTranslateY();
                                                    double si = Math.sin(coAngle);
                                                    double co = Math.cos(coAngle);
                                                    double k = (v * co * t);
                                                    double j = (v * si * t) - (0.5 * g * t * t);
                                                    ball[x - 1].setTranslateX(posX - k * ballXpos);
                                                    ball[x - 1].setTranslateY(posY - j * ballYpos);
                                                    if (ball[x - 1].getTranslateX() <= (84 + r) || ball[x - 1].getTranslateY() >= (1205 - r)) {
                                                        ball[x - 1].setOpacity(0);
                                                    } else {
                                                        t += 0.01;
                                                    }*/
                                                    }
                                                    double Vx = v * C;
                                                    double Vy = v * S - (g * t);
                                                    double TotalVelo = Math.sqrt((Vx * Vx) + (Vy * Vy));
                                                    SVelo.setText("Velocity: " + String.format("%.2f", TotalVelo) + "m/s");
                                                    STime.setText("Time: " + String.format("%.2f", t) + "s");
                                                    SHmax.setText("Hmax: " + String.format("%.2f", H) + "m");
                                                    SR.setText("Range: " + String.format("%.2f", R) + "m");
                                                    STmax.setText("Tmax: " + String.format("%.2f", T) + "s");
                                                    SX.setText("X: " + String.format("%.2f", Sx) + "m");
                                                    SY.setText("Y: " + String.format("%.2f", Sy) + "m");
                                                }
                                            } else if (E.getCode() == KeyCode.A) {
                                                t -= 0.01;
                                                double Sx = (v * C * t);
                                                double Sy = (v * S * t) - (0.5 * g * t * t);
                                                if ((posY - Sy * ballYpos) <= (700 - r)) {
                                                    ball[x - 1].setTranslateX(posX + Sx * ballXpos);
                                                    ball[x - 1].setTranslateY(posY - Sy * ballYpos);
                                                    root.getChildren().remove(path[point - 1]);
                                                    point--;
                                                    if (checkCollisions(xAxis.getUpperBound(), j, k) == true) {
                                                        collision.setOpacity(100);
                                                        fadeTransition.play();
                                                        stop();
                                                    }
                                                    double Vx = v * C;
                                                    double Vy = v * S - (g * t);
                                                    double TotalVelo = Math.sqrt((Vx * Vx) + (Vy * Vy));
                                                    SVelo.setText("Velocity: " + String.format("%.2f", TotalVelo) + "m/s");
                                                    STime.setText("Time: " + String.format("%.2f", t) + "s");
                                                    SHmax.setText("Hmax: " + String.format("%.2f", H) + "m");
                                                    SR.setText("Range: " + String.format("%.2f", R) + "m");
                                                    STmax.setText("Tmax: " + String.format("%.2f", T) + "s");
                                                    SX.setText("X: " + String.format("%.2f", Sx) + "m");
                                                    SY.setText("Y: " + String.format("%.2f", Sy) + "m");
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                        }
                    }.start();
                }
                else if(animationFlag==1)
                {
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Can't start another simulation one is ongoing!");
                    a.showAndWait();
                }
                else
                {
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("You have to reset for starting a new simulation");
                    a.showAndWait();
                }
            }
        });

        Button Re = new Button("Reset");
        setStyleO(Re);
        Re.setTranslateX(1400);
        Re.setTranslateY(730);

        Re.setOnAction(e->{
            if(animationFlag==1)
            {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Have to reset after the end of the simulation");
                a.showAndWait();
            }
            else {
                vofObj1 = 0;
                countGiveV = 0;
                t = 0;
                r = 0;
                v = 0;
                flag = 0;
                theta = 0;
                checkR = 1;
                checkB = 0;
                h = 0;
                h = 0;
                w = 0;
                w = 0;
                ball[x-1].setOpacity(100);
                xAxis.setUpperBound(100);
                yAxis.setUpperBound(50);
                fadeTransition.stop();
                collision.setOpacity(0);
                ballXpos = 12;
                ballYpos = 10.7;
                SVelo.setText("Velocity: 0 m/s");
                STime.setText("Time: 0 s");
                SHmax.setText("Hmax: 0 m");
                SR.setText("Range: 0 m");
                STmax.setText("Tmax: 0 s");
                SX.setText("X: 0 m");
                SY.setText("Y: 0 m");
                ballposition.setText("Position of ball while hovering:\nX: 0 m \nY: 0 m");
                Obsposition.setText("Position of obstacle while hovering:\nX: 0 m");
                root.getChildren().removeAll(ball[x - 1], rectangle[y - 1]);
                y += 1;
                rectangle[y - 1] = new Box();
                for (int i = 0; i < point; i++) {
                    root.getChildren().remove(path[i]);
                }
                point = 0;
                animationFlag = 0;
            }
        });

        Button back = new Button("Back");
        back.setTranslateX(50);
        back.setTranslateY(730);
        setStyleO(back);
        back.setPrefSize(100, 30);

        back.setOnAction(e->{
            try{
                ThirdPage goBack = new ThirdPage();
                goBack.TheThird(primaryStage);
            }catch (Exception exc)
            {
                exc.printStackTrace();
            }
        });

        Menu m2 = new Menu("Settings");
        MenuItem item3 = new MenuItem("Instructions");
        m2.getItems().addAll(item3);
        item3.setOnAction(e->{
            try {
                Stage ins = new Stage();
                Pane R = new Pane();
                Text INs = new Text();
                for (int i = 0; i < Ins.size(); i++) {
                    INs.setText(INs.getText() + Ins.get(i) + "\n");
                }
                R.getChildren().add(INs);
                INs.setTranslateX(200);
                INs.setTranslateY(100);
                INs.setScaleX(1.5);
                INs.setScaleY(1.5);
                Scene S = new Scene(R, 800, 500);
                ins.setTitle("Instructions");
                ins.setScene(S);
                ins.show();
            }catch (Exception E)
            {
                E.printStackTrace();
            }
        });

        FileInputStream input1 = new FileInputStream("src/Images/settings.png");
        Image im1 = new Image(input1);
        m2.setGraphic(new ImageView(im1));

        MenuBar menubar = new MenuBar();

        menubar.getMenus().addAll(m1, m2);
        root.getChildren().addAll(menubar);
        root.getChildren().addAll(Ground,lc,St,Pa,Re);
        root.getChildren().addAll(SVelo,STime,SHmax,SR,STmax,SX,SY,ballposition, Obsposition,collision,back);

        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        root.setBackground(bg);

        Scene scene = new Scene(root, 1600, 800);
        primaryStage.setScene(scene);
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


    public Button setStyle( Button b)
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
        return  b;
    }

    public boolean checkCollisions(double bound, double j, double k)
    {
//        double hei = h/11.4;
//        double pos = ((bound*(rectangle[y-1].getTranslateX()-84))/1205);
//        if(k>=pos && j<=hei && k<(pos+1))
//        {
        if(w>0) {
            if (ball[x - 1].getTranslateX() >= (rectangle[y - 1].getTranslateX() - (w / 2))) {
                return true;
            } else {
                return false;
            }
        } else
        {
            return false;
        }
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
                "    -fx-font-size: 1.1em;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
        return b;
    }
}
