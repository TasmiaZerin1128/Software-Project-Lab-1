package fusics;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import static java.lang.Math.abs;
import static java.lang.Math.multiplyExact;
import static javafx.stage.StageStyle.TRANSPARENT;

public class medium {
    double t = 0;
    String Type,TypeB,TypeF,TypeL;
    String hhh = "";
    String ttt = "";
    String TTT = "";
    String rrr = "";
    String the = "";
    String xxx = "";
    String yyy = "";
    String v00 = "";
    String vvv = "";

    double Velo, Sin, C, vx, vy, TotalV,k,j, angle,angleR;
    double theta;
    int check=0;

    double Hmax=-1;
    double Tmax=-1;
    double time=-1;
    double Range=-1;
    double Theta=-1;
    double X=-1;
    double Y = -1;
    double V0=-1;
    double V = -1;

    double Hcount=-1;
    double Tcount=-1;
    double tcount=-1;
    double rcount=-1;
    double thcount=-1;
    double xcount=-1;
    double ycount=-1;
    double v0count=-1;
    double Vcount=-1;

    double H=0, T=0 , R=0, checkAnim=0,Ttemp=0;
    double centerX, centerY, mulX, mulY,posX,posY, mulXtemp,mulYtemp;
    int projectileType=-1,yesX=0,yesmulX=0, space=0;
    int countTime=0;

    TextField hh = new TextField();
    TextField TT = new TextField();
    TextField tt = new TextField();
    TextField rr = new TextField();
    TextField thh = new TextField();
    TextField xx = new TextField();
    TextField yy = new TextField();
    TextField v0 = new TextField();
    TextField vv = new TextField();

    Circle pointAns[] = new Circle[10];
    Circle r[] = new Circle[1000000];
    Circle w[] = new Circle[1000000];
    int point=0,rw=0;
    Label wrongH = new Label("Wrong Height");
    Label rightH = new Label("Correct Height");
    Label wrongR = new Label("Wrong Range");
    Label rightR = new Label("Correct Range");
    Line rightA = new Line();
    Line wrongA = new Line();
    Label rightAng = new Label("Correct Angle");
    Label wrongAng = new Label("Wrong Angle");
    Label wrongTime = new Label();
    Label wrongTmax = new Label();
    Label wrongY = new Label("Wrong Height");
    Label rightY = new Label("Correct Height");
    Label wrongX = new Label("Wrong Distance");
    Label rightX = new Label("Correct Distance");
    Label wrongV0 = new Label();
    Label wrongV = new Label();

    int num;
    int points=0,M=0;
    int minute,second,quesTotal,timeup=0;
    Label timerL,sub;
    Timeline timeline;

    public void Medium(Stage primaryStage,int exam, Set<Integer> numbers, int qTotal, int marks,int min, int sec) throws FileNotFoundException, IOException {
        Pane root = new Pane();

        Stage size = new Stage();
        GridPane ans = new GridPane();
        Scene s = new Scene(ans, 500, 300);

        Alert a = new Alert(Alert.AlertType.NONE);

        Scanner sc = new Scanner(new File("src/Questions/medium.txt"));
        int flag = 0, type = 0, var = 0;
        int position = 0;
        minute=min;
        second=sec;
        quesTotal = qTotal;

        hh.setPromptText("0.00");
        hh.setFocusTraversable(false);
        rr.setPromptText("0.00");
        rr.setFocusTraversable(false);
        tt.setPromptText("0.00");
        tt.setFocusTraversable(false);
        TT.setPromptText("0.00");
        TT.setFocusTraversable(false);
        thh.setPromptText("0.00");
        thh.setFocusTraversable(false);
        xx.setPromptText("0.00");
        xx.setFocusTraversable(false);
        yy.setPromptText("0.00");
        yy.setFocusTraversable(false);
        v0.setPromptText("0.00");
        v0.setFocusTraversable(false);
        vv.setPromptText("0.00");
        vv.setFocusTraversable(false);

        Canvas cn = new Canvas(1600, 800);
        cn.setTranslateX(0);
        cn.setTranslateY(0);
        GraphicsContext gc = cn.getGraphicsContext2D();
        Text ANS = new Text();
        ANS.setTranslateX(1300);
        ANS.setTranslateY(150);
        ANS.setScaleX(2.5);
        ANS.setScaleY(2.5);

        Button ss = new Button("See Solution");
        ss.setTranslateX(1235);
        ss.setTranslateY(190);
        ss.setPrefSize(100,30);
        setStyleBoard(ss);
        ss.setVisible(false);
        Button rt = new Button("Retry");
        rt.setTranslateX(1295);
        rt.setTranslateY(235);
        rt.setPrefSize(100,30);
        setStyleBoard(rt);
        rt.setVisible(false);
        Button vs = new Button("Visualize Answer");
        vs.setTranslateX(1345);
        vs.setTranslateY(190);
        vs.setPrefSize(110,30);
        setStyleBoard(vs);
        vs.setVisible(false);

        Random random = new Random();

        Ellipse ball = new Ellipse();
        Sphere Ball = new Sphere(25);

        M=marks;
        String st;
        int line = 0;
        if(exam==0) {
            num = randInt(1, 7);
        } else
        {
            num = numbers.iterator().next();
            System.out.println(num);
            numbers.remove(num);
        }
        //int num = 1;
        ArrayList<String> ques = new ArrayList<String>();
        while (sc.hasNextLine()) {
            if (flag == 0) {
                st = sc.nextLine();
                if (st.contains(num + ")")) {
                    flag = 1;
                }
            } else if (flag == 1) {
                st = sc.nextLine();
                if (type == 1) {
                    Type = st;
                    TypeF = "src/Images/" + st + ".jpg";
                    TypeL = "src/Images/L" + st + ".jpg";
                    type = -1;
                } else if (type == 0) {
                    if (st.contains("#")) {
                        type = 1;
                    } else {
                        ques.add(st);
                    }
                } else if (type == -1) {
                    if (st.contains("ellipse")) {
                        projectileType = 0;
                        ball.setRadiusX(20);
                        ball.setRadiusY(5);
                        ball.setFill(Color.BLACK);
                    } else if (st.contains("sphere")) {
                        projectileType = 1;
                        TypeB = "/Images/" + Type + "B.jpg";
                        PhongMaterial material = new PhongMaterial();
                        material.setDiffuseMap(new Image(getClass().getResourceAsStream(TypeB)));
                        Ball.setMaterial(material);
                    } else if (st.contains("VAR")) {
                        flag = 2;
                    }
                }
            } else if (flag == 2) {
                st = sc.nextLine();
                if (st.contains("ANS")) {
                    flag = 3;
                } else {
                    if (var == 0) {
                        if (st.contains("v")) {
                            var = 1;
                        } else if (st.contains("theta")) {
                            var = 2;
                        } else if (st.contains("T")) {
                            var = 3;
                        }
                    } else if (var == 1) {
                        Velo = Double.parseDouble(st);
                        var = 0;
                    } else if (var == 2) {
                        theta = Integer.parseInt(st);
                        var = 0;
                    } else if (var == 3) {
                        T = Double.parseDouble(st);
                        var = 0;
                    }
                }
            } else if (flag == 3) {
                st = sc.nextLine();
                if(st.contains("POINT"))
                {
                    flag=4;
                } else {
                    if (st.contains("Height")) {
                        Label H = new Label("Hmax: ");
                        H.setTextFill(Color.WHITE);
                        H.setScaleX(1.5);
                        H.setScaleY(1.5);
                        ans.add(H, 1, position);
                        ans.add(hh, 2, position);
                        position++;
                        Hcount = 0;
                    } else if (st.contains("time")) {
                        Label hT = new Label("Time: ");
                        hT.setTextFill(Color.WHITE);
                        hT.setScaleX(1.5);
                        hT.setScaleY(1.5);
                        ans.add(hT, 1, position);
                        ans.add(tt, 2, position);
                        position++;
                        tcount = 0;
                    } else if (st.contains("TimeMax")) {
                        Label T = new Label("Tmax: ");
                        T.setTextFill(Color.WHITE);
                        T.setScaleX(1.5);
                        T.setScaleY(1.5);
                        ans.add(T, 1, position);
                        ans.add(TT, 2, position);
                        position++;
                        Tcount = 0;
                    } else if (st.contains("Range")) {
                        Label R = new Label("Range: ");
                        R.setTextFill(Color.WHITE);
                        R.setScaleX(1.5);
                        R.setScaleY(1.5);
                        ans.add(R, 1, position);
                        ans.add(rr, 2, position);
                        position++;
                        rcount = 0;
                    } else if (st.contains("theta")) {
                        Label th = new Label("Angle: ");
                        th.setTextFill(Color.WHITE);
                        th.setScaleX(1.5);
                        th.setScaleY(1.5);
                        ans.add(th, 1, position);
                        ans.add(thh, 2, position);
                        position++;
                        thcount = 0;
                    } else if (st.contains("xAxisD")) {
                        Label x = new Label("X: ");
                        x.setTextFill(Color.WHITE);
                        x.setScaleX(1.5);
                        x.setScaleY(1.5);
                        ans.add(x, 1, position);
                        ans.add(xx, 2, position);
                        position++;
                        xcount = 0;
                    } else if (st.contains("yAxisD")) {
                        Label y = new Label("Y: ");
                        y.setTextFill(Color.WHITE);
                        y.setScaleX(1.5);
                        y.setScaleY(1.5);
                        ans.add(y, 1, position);
                        ans.add(yy, 2, position);
                        position++;
                        ycount = 0;
                    } else if (st.contains("initialVelo")) {
                        Label V0 = new Label("V0: ");
                        V0.setTextFill(Color.WHITE);
                        V0.setScaleX(1.5);
                        V0.setScaleY(1.5);
                        ans.add(V0, 1, position);
                        ans.add(v0, 2, position);
                        position++;
                        v0count = 0;
                    }else if (st.contains("Velocity")) {
                        Label Vv = new Label("Velocity: ");
                        Vv.setTextFill(Color.WHITE);
                        Vv.setScaleX(1.5);
                        Vv.setScaleY(1.5);
                        ans.add(Vv, 1, position);
                        ans.add(vv, 2, position);
                        position++;
                        Vcount = 0;
                    }
                }
            } else if (flag == 4) {
                st = sc.nextLine();
                if (st.contains("CENTER")) {
                    flag = 5;
                } else {
                    points = Integer.parseInt(st);
                }
            } else if(flag==5){
                st = sc.nextLine();
                if (st.contains("MUL")) {
                    flag = 6;
                } else {
                    if (yesX == 0) {
                        if (projectileType == 0) {
                            centerX = Double.parseDouble(st);
                            ball.setCenterX(Double.parseDouble(st));
                            yesX = 1;
                        } else if (projectileType == 1) {
                            centerX = Double.parseDouble(st);
                            Ball.setTranslateX(Double.parseDouble(st));
                            yesX = 1;
                        }
                    } else {
                        if (projectileType == 0) {
                            centerY = Double.parseDouble(st);
                            ball.setCenterY(Double.parseDouble(st));
                        } else {
                            centerY = Double.parseDouble(st);
                            Ball.setTranslateY(Double.parseDouble(st));
                        }
                    }
                }
            } else if (flag == 6) {
                st = sc.nextLine();
                if (st.contains("WALL")) {
                    flag = 7;
                } else if(st.contains("END"))
                {
                    break;
                }
                else {
                    if (yesmulX == 0) {
                        mulX = Double.parseDouble(st);
                        yesmulX = 1;
                    } else {
                        mulY = Double.parseDouble(st);
                    }
                }
            } else if (flag == 7) {
                st = sc.nextLine();
                if (st.contains("END")) {
                    break;
                } else {
                    Image wall = new Image(new FileInputStream("src/Images/wall.png"));
                    if (projectileType == 0)
                        gc.drawImage(wall, ball.getCenterX() + Double.parseDouble(st) * mulX, 0);
                    else
                        gc.drawImage(wall, Ball.getTranslateX() + Double.parseDouble(st) * mulX, 0);
                }
            }
        }

        Type = "src/Images/"+ Type + ".jpg";
        Image qB = new Image(new FileInputStream("src/Images/BdMed.jpg"));
        Image notice = new Image(new FileInputStream("src/Images/notice.png"));
        gc.drawImage(qB,270,20);
        if(exam==0)
            gc.drawImage(notice,1120,5);

        mulXtemp=mulX;
        mulYtemp=mulY;

        timerL = new Label("Remaining Time: " + String.format("%02d", minute) + ":" + String.format("%02d",second));
        setCorrectWrong(timerL, 700, 300, Color.MAROON, 2, 2);
        timerL.setPrefSize(150,10);

        Text L = new Text();
        L.setTranslateX(480);
        L.setTranslateY(100);
        L.setScaleX(1.7);
        L.setScaleY(1.7);

        for (int i = 0; i < ques.size(); i++) {
            L.setText(L.getText() + ques.get(i) + "\n");
        }

        L.setFill(Color.WHITE);
        L.setStroke(Color.WHITE);
        L.setStrokeWidth(0.5);
        Button St = new Button("Submit");
        St.setPadding(new Insets(15));
        setStyle(St);
        St.setTranslateX(870);
        St.setTranslateY(730);
        St.setPrefSize(150,50);

        Button en = new Button("Enter Answers");
        en.setPadding(new Insets(15));
        setStyle(en);
        en.setPrefSize(150,50);
        en.setTranslateX(700);
        en.setTranslateY(730);

        en.setOnAction(e -> {
            try {
                Label warning = new Label(null);
                warning.setTextFill(Color.RED);
                warning.setScaleX(1.2);
                warning.setScaleY(1.2);
                ans.add(warning,2,7);
                Button Ok = new Button("Enter");
                Ok.setStyle("-fx-background-color: \n" +
                        "        linear-gradient(#f2f2f2, #d6d6d6),\n" +
                        "        linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),\n" +
                        "        linear-gradient(#dddddd 0%, #f6f6f6 50%);\n" +
                        "    -fx-background-radius: 8,7,6;\n" +
                        "    -fx-background-insets: 0,1,2;\n" +
                        "    -fx-text-fill: black;\n" +
                        "    -fx-font-weight: bold;\n" +
                        "    -fx-font-size: 1.1em;\n" +
                        "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
                Ok.setPrefSize(90, 40);
                Button cancel = new Button("Cancel");
                cancel.setStyle("-fx-background-color: \n" +
                        "        linear-gradient(#f2f2f2, #d6d6d6),\n" +
                        "        linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),\n" +
                        "        linear-gradient(#dddddd 0%, #f6f6f6 50%);\n" +
                        "    -fx-background-radius: 8,7,6;\n" +
                        "    -fx-background-insets: 0,1,2;\n" +
                        "    -fx-text-fill: black;\n" +
                        "    -fx-font-weight: bold;\n" +
                        "    -fx-font-size: 1.1em;\n" +
                        "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
                cancel.setPrefSize(90, 40);
                Button clear = new Button("Clear");
                clear.setStyle("-fx-background-color: \n" +
                        "        linear-gradient(#f2f2f2, #d6d6d6),\n" +
                        "        linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),\n" +
                        "        linear-gradient(#dddddd 0%, #f6f6f6 50%);\n" +
                        "    -fx-background-radius: 8,7,6;\n" +
                        "    -fx-background-insets: 0,1,2;\n" +
                        "    -fx-text-fill: black;\n" +
                        "    -fx-font-weight: bold;\n" +
                        "    -fx-font-size: 1.1em;\n" +
                        "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
                clear.setPrefSize(90, 40);
                ans.add(Ok,4,10);
                ans.add(cancel,2,10);
                ans.add(clear,1,10);
                ans.setAlignment(Pos.CENTER);
                ans.setVgap(4);
                ans.setHgap(10);
                ans.setPadding(new Insets(5, 5, 5, 5));
                size.setTitle("Answers");
                Image BG = new Image(new FileInputStream("src/Images/Answer.jpg"));
                BackgroundImage bi = new BackgroundImage(BG,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
                Background Bg = new Background(bi);
                ans.setBackground(Bg);
//                        size.initStyle(StageStyle.TRANSPARENT);
//                        s.setFill(Color.TRANSPARENT);
                size.setScene(s);
                size.show();

                cancel.setOnAction(E->{
                    size.close();
                });

                clear.setOnAction(E->{
                    hh.clear();
                    rr.clear();
                    tt.clear();
                    TT.clear();
                    thh.clear();
                    xx.clear();
                    yy.clear();
                    v0.clear();
                    vv.clear();
                    warning.setText(null);
                });

                Ok.setOnAction(E->{
                    if(Hcount==0 && hh.getText().isEmpty() || Tcount==0 && TT.getText().isEmpty() || tcount==0 && tt.getText().isEmpty() || rcount==0 && rr.getText().isEmpty() || thcount==0 && thh.getText().isEmpty() || xcount==0 && xx.getText().isEmpty() || ycount==0 && yy.getText().isEmpty() || v0count==0 && v0.getText().isEmpty() || Vcount==0 && vv.getText().isEmpty())
                    {
                        System.out.println("dhukse");
                        warning.setText("Please enter all the answers!");
                    }
                    else {
                        try {
                            warning.setText(null);
                            size.close();
                            if (Hcount == 0 && hh.getText() != null) {
                                hhh = hh.getText();
                                hh.clear();
                                Hmax = Double.parseDouble(hhh);
                            }
                            if (Tcount == 0 && TT.getText() != null) {
                                TTT = TT.getText();
                                TT.clear();
                                Tmax = Double.parseDouble(TTT);
                            }
                            if (tcount == 0 && tt.getText() != null) {
                                ttt = tt.getText();
                                tt.clear();
                                time = Double.parseDouble(ttt);
                            }
                            if (rcount == 0 && rr.getText() != null) {
                                rrr = rr.getText();
                                rr.clear();
                                Range = Double.parseDouble(rrr);
                            }
                            if (thcount == 0 && thh.getText() != null) {
                                the = thh.getText();
                                thh.clear();
                                Theta = Double.parseDouble(the);
                            }
                            if (xcount == 0 && xx.getText() != null) {
                                xxx = xx.getText();
                                xx.clear();
                                X = Double.parseDouble(xxx);
                            }
                            if (ycount == 0 && yy.getText() != null) {
                                yyy = yy.getText();
                                yy.clear();
                                Y = Double.parseDouble(yyy);
                            }
                            if (v0count == 0 && v0.getText() != null) {
                                v00 = v0.getText();
                                v0.clear();
                                V0 = Double.parseDouble(v00);
                            }
                            if (Vcount == 0 && vv.getText() != null) {
                                vvv = vv.getText();
                                vv.clear();
                                V = Double.parseDouble(vvv);
                            }
                        } catch (IllegalArgumentException il) {
                            size.close();
                        }
                    }
                });
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        angle = (theta*Math.PI)/180;
        angleR=angle;
        Sin = Math.sin(angle);
        System.out.println(Sin);
        C = Math.cos(angle);
        vx = Velo * C;
        vy = Velo * Sin - (9.8 * t);
        TotalV = Math.sqrt((vx * vx) + (vy * vy));
        if(H==0)
            H = Math.pow((Velo * Sin), 2) / (2 * 9.8);
        if(T==0)
            T = (2 * (Velo * Sin)) / 9.8;
        if(R==0)
            R = ((Velo * Velo) * Math.sin(2 * angle)) / 9.8;
        k = (Velo * C * T);
        j = (Velo * Sin * T) - (0.5 * 9.8 * T * T);

        St.setOnAction(e -> {
            if(Hmax==-1 && Hcount==0||Theta==-1 && thcount==0 || Tmax==-1 && Tcount==0 || time==-1 && tcount==0 || Range==-1 && rcount==0 || X==-1 && xcount==0 || Y==-1 && ycount==0 || V0==-1 && v0count==0 || V==-1 && Vcount==0)
            {
                a.setAlertType(Alert.AlertType.WARNING);
                a.setContentText("You have to first answer the questions");
                a.show();
            }
            else {
                if (exam == 1) {
                    sub = new Label("Your answer has been submitted. Please go to next page");
                    setCorrectWrong(sub, 670, 400, Color.BLACK, 2.5, 2.5);
                    sub.setPrefSize(320, 30);
                    sub.setAlignment(Pos.CENTER);
                    en.setVisible(false);
                    St.setVisible(false);
                    root.getChildren().add(sub);
                }
                else if(exam==2)
                {
                    sub = new Label("Your answer has been submitted");
                    setCorrectWrong(sub, 670, 400, Color.BLACK, 2.5, 2.5);
                    sub.setPrefSize(320, 30);
                    sub.setAlignment(Pos.CENTER);
                    en.setVisible(false);
                    St.setVisible(false);
                    root.getChildren().add(sub);
                }
                if (Hmax != -1) {
                    if (RoundUpDecimal.round(H,Hmax)) {
                        check = 0;
                    }
                    else
                    {
                        check=1;
                    }
                }
                if (time != -1) {
                    if (RoundUpDecimal.round(time, Tmax / 2)) {
                        if(check==0) {
                            check = 0;
                        }
                    }
                    else
                    {
                        check=1;
                    }
                }
                if (Range != -1) {
                    if (RoundUpDecimal.round(R, Range)) {
                        if(check==0) {
                            check = 0;
                        }
                    }
                    else
                    {
                        check=1;
                    }
                }
                if (Theta != -1) {
                    if (RoundUpDecimal.round(theta, Theta)) {
                        if(check==0) {
                            check = 0;
                        }
                    }
                    else
                    {
                        check=1;
                        rightA = new Line(centerX,centerY-200,centerX,centerY);
                        rightA.setStroke(Color.DARKGREEN);
                        rightA.setStrokeWidth(4);
                        wrongA = new Line(centerX,centerY-200,centerX,centerY);
                        wrongA.setStroke(Color.MAROON);
                        wrongA.setStrokeWidth(4);
                        Rotate rotateR = new Rotate();
                        rotateR.setAngle(90-theta);
                        System.out.println(theta+" " +Theta);
                        Rotate rotateW = new Rotate();
                        rotateW.setAngle(90-Theta);
                        if(projectileType==0) {
                            rotateR.setPivotX(ball.getTranslateX());
                            rotateR.setPivotY(ball.getTranslateY());
                            rotateW.setPivotX(ball.getTranslateX());
                            rotateW.setPivotY(ball.getTranslateY());
                        } else
                        {
                            rotateR.setPivotX(Ball.getTranslateX());
                            rotateR.setPivotY(Ball.getTranslateY());
                            rotateW.setPivotX(Ball.getTranslateX());
                            rotateW.setPivotY(Ball.getTranslateY());
                        }
                        rightA.getTransforms().add(rotateR);
                        wrongA.getTransforms().add(rotateW);
                        setCorrectWrong(rightAng,rightA.getStartX()+(Math.tan((90-theta)*(Math.PI/180))*200),rightA.getStartY()-30,Color.DARKGREEN,1.5,1.5);
                        setCorrectWrong(wrongAng,wrongA.getStartX()+(Math.tan((90-Theta)*(Math.PI/180))*200),(centerY-200+(200/Math.cos((90-Theta)*(Math.PI/180)))-200),Color.MAROON,1.5,1.5);
                        rightA.setVisible(false);
                        wrongA.setVisible(false);
                        rightAng.setVisible(false);
                        wrongAng.setVisible(false);
                        root.getChildren().addAll(rightA,wrongA,rightAng,wrongAng);
                    }
                }
                if (X != -1) {
                    if (RoundUpDecimal.round(k, X)) {
                        if(check==0) {
                            check = 0;
                        }
                    }
                    else
                    {
                        check=1;
                        pointAns[point] = new Circle(6);
                        setPoint(pointAns[point],centerX+(X*mulX),centerY ,Color.MAROON);
                        pointAns[point].setVisible(false);
                        setCorrectWrong(wrongX,pointAns[point].getTranslateX()+40,pointAns[point].getTranslateY(),Color.MAROON,1.5,1.5);
                        wrongX.setVisible(false);
                        root.getChildren().addAll(pointAns[point],wrongX);
                        point++;
                        pointAns[point] = new Circle(6);
                        setPoint(pointAns[point],centerX+(k*mulX), centerY, Color.DARKGREEN);
                        pointAns[point].setVisible(false);
                        setCorrectWrong(rightX,pointAns[point].getTranslateX()+40,pointAns[point].getTranslateY(),Color.DARKGREEN,1.5,1.5);
                        rightX.setVisible(false);
                        root.getChildren().addAll(pointAns[point],rightX);
                        point++;
                        mulXtemp = (mulX*X)/k;
                    }
                }
                if (Y != -1) {
                    if (RoundUpDecimal.round(abs(j), Y)) {
                        if(check==0) {
                            check = 0;
                        }
                    }
                    else
                    {
                        check=1;
                        pointAns[point] = new Circle(6);
                        if(X==-1)
                            setPoint(pointAns[point],centerX+(k*mulX),centerY-(Y*mulY) ,Color.MAROON);
                        else {
                            if (j > 0)
                                setPoint(pointAns[point], centerX + (X * mulX), centerY - (Y * mulY), Color.MAROON);
                            else
                                setPoint(pointAns[point], centerX + (X * mulX), centerY - (-(Y) * mulY), Color.MAROON);
                        }
                        pointAns[point].setVisible(false);
                        setCorrectWrong(wrongY,pointAns[point].getTranslateX()+40,pointAns[point].getTranslateY(),Color.MAROON,1.5,1.5);
                        wrongY.setVisible(false);
                        root.getChildren().addAll(pointAns[point],wrongY);
                        point++;
                        pointAns[point] = new Circle(6);
                        setPoint(pointAns[point],centerX+(k*mulX), centerY-(j*mulY), Color.DARKGREEN);
                        pointAns[point].setVisible(false);
                        setCorrectWrong(rightY,pointAns[point].getTranslateX()+40,pointAns[point].getTranslateY(),Color.DARKGREEN,1.5,1.5);
                        rightY.setVisible(false);
                        root.getChildren().addAll(pointAns[point],rightY);
                        point++;
                        mulYtemp = (mulY*Y)/abs(j);
                    }
                }
                if (V0 != -1) {
                    if (RoundUpDecimal.round(V0, Velo)) {
                        if(check==0) {
                            check = 0;
                        }
                    }
                    else
                    {
                        check=1;
                        if(V0>Velo)
                        {
                            wrongV0.setText("Given Initial Velocity is greater than the actual Velocity!");
                        } else{
                            wrongV0.setText("Given Initial Velocity is less than the actual Velocity!");
                        }
                        setCorrectWrong(wrongV0,1150,330+space,Color.MAROON,1.1,1.1);
                        wrongV0.setPrefSize(320,10);
                        wrongV0.setVisible(false);
                        space+=50;
                        root.getChildren().add(wrongV0);
                    }
                }
                if (V != -1) {
                    if (RoundUpDecimal.round(V, TotalV)) {
                        if(check==0) {
                            check = 0;
                        }
                    }
                    else
                    {
                        check=1;
                        if(V>TotalV)
                        {
                            wrongV.setText("Given Velocity is greater than the actual Velocity!");
                        } else{
                            wrongV.setText("Given Velocity is less than the actual Velocity!");
                        }
                        setCorrectWrong(wrongV,1150,330+space,Color.MAROON,1.1,1.1);
                        wrongV.setPrefSize(300,10);
                        wrongV.setVisible(false);
                        space+=50;
                        root.getChildren().add(wrongV);
                    }
                }
                if (check == 0) {
                    ANS.setText("Correct Answer");
                    ANS.setFill(Color.WHITE);
                    M+=points;
                } else if (check == 1) {
                    ANS.setText("Wrong Answer");
                    ANS.setFill(Color.RED);
                }

                ss.setVisible(true);
                rt.setVisible(true);
                vs.setVisible(true);
                ss.setOnAction(eve -> {
                    Pane sol = new Pane();
                    Scene Solu = new Scene(sol, 1450, 800);
                    Image Solution = null;
                    try {
                        Solution = new Image(new FileInputStream("src/Images/" + num + "easy.png"));
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    BackgroundImage bi = new BackgroundImage(Solution,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundPosition.DEFAULT,
                            BackgroundSize.DEFAULT);
                    Background Bg = new Background(bi);
                    Stage SS = new Stage();
                    SS.setScene(Solu);
                    sol.setBackground(Bg);
                    SS.show();
                });
                rt.setOnAction(eve -> {
                    if(checkAnim==0)
                    {
                        M-=points;
                        check = 0;
                        t = 0;
                        Hmax = -1;
                        Tmax = -1;
                        time = -1;
                        Range = -1;
                        Theta = -1;
                        space=0;
                        mulXtemp=mulX;
                        mulYtemp=mulY;
                        if (projectileType == 0) {
                            ball.setCenterX(centerX);
                            ball.setCenterY(centerY);
                        } else {
                            Ball.setTranslateX(centerX);
                            Ball.setTranslateY(centerY);
                        }
                        ANS.setText(null);
                        ss.setVisible(false);
                        rt.setVisible(false);
                        vs.setVisible(false);
                        for (int i = 0; i < point; i++) {
                            root.getChildren().remove(pointAns[i]);
                        }
                        for (int i = 0; i < rw; i++) {
                            root.getChildren().removeAll(r[i],w[i]);
                        }
                        point = 0;
                        rw=0;
                        root.getChildren().removeAll(wrongH, rightH, wrongR, rightR, rightA, wrongA, wrongY, rightY,wrongX,rightX,rightAng,wrongAng,wrongV,wrongV0);
                        Image background = null;
                        try {
                            background = new Image(new FileInputStream(TypeF));
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                        BackgroundImage bi = new BackgroundImage(background,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT);
                        Background bg = new Background(bi);
                        root.setBackground(bg);
                    }
                });
                vs.setOnAction(eve -> {
                    if (projectileType == 0)
                    {
                        ball.setCenterX(centerX);
                        ball.setCenterY(centerY);
                    } else{
                        Ball.setTranslateX(centerX);
                        Ball.setTranslateY(centerY);
                    }
                    t=0;
                    Image back = null;
                    try {
                        back = new Image(new FileInputStream(TypeL));
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    BackgroundImage bi = new BackgroundImage(back,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundPosition.DEFAULT,
                            BackgroundSize.DEFAULT);
                    Background bg = new Background(bi);
                    root.setBackground(bg);
                    try {
                        for (int i = 0; i < point; i++) {
                            pointAns[i].setVisible(true);
                        }
                        wrongH.setVisible(true);
                        rightH.setVisible(true);
                        wrongR.setVisible(true);
                        rightR.setVisible(true);
                        wrongA.setVisible(true);
                        rightA.setVisible(true);
                        wrongX.setVisible(true);
                        rightX.setVisible(true);
                        wrongY.setVisible(true);
                        rightY.setVisible(true);
                        wrongAng.setVisible(true);
                        rightAng.setVisible(true);
                        wrongV.setVisible(true);
                        wrongV0.setVisible(true);
                        if (projectileType == 0) {
                            posX = ball.getCenterX();
                            posY = ball.getCenterY();
                        } else {
                            posX = Ball.getTranslateX();
                            posY = Ball.getTranslateY();
                        }
                        for (int i = 0; i < rw; i++) {
                            root.getChildren().removeAll(r[i],w[i]);
                        }
                        rw=0;
                        if (Theta != -1) {
                            angle = (Theta * Math.PI) / 180;
                            Sin = Math.sin(angle);
                            C = Math.cos(angle);
                            if(V0==-1) {
                                Tmax = (2 * (Velo * Sin)) / 9.8;
                            } else{
                                Tmax = (2 * (V0 * Sin)) / 9.8;
                            }
                        }
                        checkAnim=1;
                        new AnimationTimer() {
                            @Override
                            public void handle(long now) {
                                if(Tmax!=-1) {
                                    Ttemp = Tmax;
                                }
                                else{ Ttemp=T;}
                                if(V0!=-1)
                                {
                                    k = (V0 * C * t);
                                    j = (V0 * Sin * t) - (0.5 * 9.8 * t * t);
                                }
                                else {
                                    k = (Velo * C * t);
                                    j = (Velo * Sin * t) - (0.5 * 9.8 * t * t);
                                }
                                if(t<=Ttemp) {
                                    if (projectileType == 0) {
                                        ball.setCenterX(posX + k * mulXtemp);
                                        ball.setCenterY(posY - j * mulYtemp);
                                    } else {
                                        Ball.setTranslateX(posX + k * mulXtemp);
                                        Ball.setTranslateY(posY - j * mulYtemp);
                                    }
                                    if(check==1) {
                                        w[rw] = new Circle(2);
                                        setPoint(w[rw], posX + k * mulXtemp, posY - j * mulYtemp, Color.MAROON);
                                        root.getChildren().add(w[rw]);
                                    }
                                }
                                if(t<=T) {
                                    r[rw] = new Circle(2);
                                    setPoint(r[rw], (posX + (Velo * Math.cos(angleR) * t) * mulX), (posY - ((Velo * Math.sin(angleR) * t) - (0.5 * 9.8 * t * t)) * mulY), Color.DARKGREEN);
                                    root.getChildren().add(r[rw]);
                                }
                                rw++;
                                if (t >= T && t>=Ttemp) {
                                    checkAnim=0;
                                    stop();
                                }
                                else {
                                    t += 0.01;
                                }
                            }
                        }.start();
                    } catch (IllegalArgumentException il) {
                        il.printStackTrace();
                    }
                });
            }
        });

        if(exam==1|| exam==2) {
            timeline = new Timeline();
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(1),
                            new EventHandler() {
                                @Override
                                public void handle(Event event) {
                                    countTime++;
                                    second--;
                                    if (second < 0) {
                                        minute--;
                                        second = 59;
                                    }
                                    timerL.setText("Remaining Time: " + String.format("%02d", minute) + ":" + String.format("%02d", second));
                                    if (minute <= 0 && second <= 0) {
                                        timeline.stop();
                                        timeup = 1;
                                        sub = new Label("Time's Up!");
                                        setCorrectWrong(sub, 740, 400, Color.BLACK, 2.5, 2.5);
                                        sub.setTextFill(Color.RED);
                                        sub.setPrefSize(100, 30);
                                        sub.setPadding(new Insets(10));
                                        sub.setAlignment(Pos.CENTER);
                                        en.setVisible(false);
                                        St.setVisible(false);
                                        root.getChildren().add(sub);
                                    }
                                }
                            }));
            timeline.playFromStart();
        }

        Button back = new Button("Back");
        back.setTranslateX(50);
        back.setTranslateY(730);
        setStyle(back);
        back.setPrefSize(60, 30);

        if(exam==0) {
            back.setOnAction(e -> {
                try {
                    practice goBack = Object.getPractice();
                    goBack.start(primaryStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        } else {
            back.setOnAction(e->{
                Pane R = new Pane();
                Text exit = new Text("Do you want to leave the game?");
                exit.setScaleX(2.5);
                exit.setScaleY(2.5);
                exit.setTranslateX(280);
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
                Background b = new Background(bi);
                R.setBackground(b);
                S.setFill(Color.TRANSPARENT);
                eStage.initStyle(TRANSPARENT);
                eStage.show();

                yes.setOnAction(ev -> {
                    if(exam==1) {
                        MultiScore goMult = Object.getMulti();
                        goMult.Score(M - marks);
                        goMult.setFinalMarks(M);
                        try {
                            goMult.sendP(0);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                    try {
                        examSM goBack = new examSM();
                        goBack.SM(primaryStage);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    eStage.close();
                });
                no.setOnAction(ev -> {
                    eStage.close();
                });
            });
        }

        Button next = new Button("Next");
        next.setTranslateX(1400);
        next.setTranslateY(730);
        setStyle(next);
        next.setPrefSize(80, 30);
        next.setOnAction(e-> {
            System.out.println("Mark " + M);
            if (exam == 0) {
                easy goEasy = new easy();
                try {
                    goEasy.EASY(primaryStage, 0, numbers, qTotal, M, 5, 00);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else if (exam == 1) {
                if (timeup == 1) {
                    MultiScore goMult = Object.getMulti();
                    goMult.Score(M-marks);
                    goMult.setFinalMarks(M);
                    try {
                        goMult.sendP(1);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else {
                    MultiScore goMult = Object.getMulti();
                    goMult.Score(M - marks);
                    if (qTotal > 2) {
                        medium goMedium = new medium();
                        try {
                            goMedium.Medium(primaryStage, 1, numbers, (qTotal - 1), M, minute, second);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    } else {
                        hard goHard = new hard();
                        try {
                            goHard.Hard(primaryStage, 1, numbers, (qTotal - 1), M, minute, second);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
            }
            else
            {
                quesTotal--;
                if(quesTotal==0)
                {
                    SingleScore scr = Object.getSscore();
                    scr.StoreScore(M-marks,points,num,countTime,1);
                    personalScoreBoard ps = new personalScoreBoard();
                    try {
                        ps.Result(primaryStage,10-quesTotal);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                }
                else {
                    SingleScore scr = Object.getSscore();
                    scr.StoreScore(M - marks, points, num,countTime,1);
                    System.out.println("My marks: " + M);
                    if(timeup==0)
                    {
                        personalScoreBoard ps = new personalScoreBoard();
                        try {
                            ps.Result(primaryStage,10-quesTotal);
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                    }
                    else {
                        SingleQues sq = Object.getSq();
                        try {
                            sq.GiveQues(primaryStage, numbers, quesTotal, M, minute, second);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
            }
        });

        Image background = new Image(new FileInputStream(Type));
        root.getChildren().addAll(cn,back,next,St,L,en);
        if (projectileType == 0) {
            root.getChildren().add(ball);
        } else
        {
            root.getChildren().add(Ball);
        }
        if(exam==0)
            root.getChildren().addAll(ANS,ss,rt,vs);
        else
            root.getChildren().add(timerL);

        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        root.setBackground(bg);


        Scene scene = new Scene(root, 1600, 800);

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {

            }
        });
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

    public Button setStyle(Button b) {
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

    public static int randInt(int min, int max) {

        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public Button setStyleBoard(Button b)
    {
        b.setStyle("-fx-background-color: \n" +
                "        linear-gradient(#f2f2f2, #d6d6d6),\n" +
                "        linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),\n" +
                "        linear-gradient(#dddddd 0%, #f6f6f6 50%);\n" +
                "    -fx-background-radius: 8,7,6;\n" +
                "    -fx-background-insets: 0,1,2;\n" +
                "    -fx-text-fill: black;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 0.9em;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
        return b;
    }
    public Circle setPoint (Circle pointAns, double X, double Y, Color Colour )
    {
        pointAns.setTranslateX(X);
        pointAns.setTranslateY(Y);
        pointAns.setFill(Colour);
        pointAns.setStroke(Color.WHITE);
        pointAns.setStrokeWidth(0.8);
        return pointAns;
    }
    public Label setCorrectWrong ( Label cw, double X, double Y, Color Colour, double sx, double sy)
    {
        cw.setTranslateX(X);
        cw.setTranslateY(Y);
        cw.setScaleX(sx);
        cw.setScaleY(sy);
        cw.setTextFill(Color.WHITE);
        if(Colour==Color.MAROON) {
            cw.setStyle("-fx-background-color:MAROON;\n" +
                    "-fx-border-color: white;\n" +
                    "-fx-background-radius: 50px;\n" +
                    "-fx-border-radius: 50px;");
        }
        else if(Colour==Color.DARKGREEN) {
            cw.setStyle("-fx-background-color:DARKGREEN;\n" +
                    "-fx-border-color: white;\n" +
                    "-fx-background-radius: 50px;\n" +
                    "-fx-border-radius: 50px;");
        }
        else  {
            cw.setStyle("-fx-background-color:BLACK;\n" +
                    "-fx-border-color: SANDYBROWN;");
        }
        cw.setPadding(new Insets(9));
        if (cw == wrongX || cw == rightX) {
            cw.setPrefSize(120,3);
        } else {
            cw.setPrefSize(100, 3);
        }
        return cw;
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
}
