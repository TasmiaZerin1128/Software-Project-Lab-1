package fusics;

import com.sun.source.doctree.TextTree;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.*;
import java.util.*;

import static javafx.stage.StageStyle.TRANSPARENT;

public class easy {
    double t = 0;
    String Type,TypeB,TypeF,TypeL;
    String hhh = "";
    String ttt = "";
    String TTT = "";
    String rrr = "";
    String the = "";

    int Velo;
    double theta;
    int check=0;

    double Hmax=-1;
    double Tmax=-1;
    double time=-1;
    double Range=-1;
    double Theta=-1;

    double Hcount=-1;
    double Tcount=-1;
    double tcount=-1;
    double rcount=-1;
    double thcount=-1;

    double H, T, Ttemp , R,Sin,C,angle,angleR,checkAnim=0;
    int num;
    double mulX=6, mulY=4.5;
    int space = 0;
    int countTime=0;

    TextField hh = new TextField();
    TextField TT = new TextField();
    TextField tt = new TextField();
    TextField rr = new TextField();
    TextField thh = new TextField();

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

    int points=0,M=0;
    int minute,second,timeup=0,quesTotal;
    Label timerL,sub;
    Timeline timeline;

    public void EASY(Stage primaryStage, int exam, Set<Integer> numbers, int qTotal, int marks, int min, int sec) throws FileNotFoundException, IOException {
        Pane root = new Pane();

        Stage size = new Stage();
        GridPane ans = new GridPane();
        Scene s = new Scene(ans, 500, 300);

        Alert a = new Alert(Alert.AlertType.NONE);
        minute=min;
        second=sec;
        quesTotal = qTotal;

        Scanner sc = new Scanner(new File("src/Questions/easy.txt"));
        int flag = 0,type=0, var=0;
        int position = 0;


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

        Canvas cn = new Canvas(1600,800);
        cn.setTranslateX(100);
        cn.setTranslateY(20);
        GraphicsContext gc = cn.getGraphicsContext2D();
        Image qB = new Image(new FileInputStream("src/Images/Bd.jpg"));
        Image notice = new Image(new FileInputStream("src/Images/notice.png"));
        gc.drawImage(qB,150,20);
        if(exam==0)
            gc.drawImage(notice,990,200);
        Text ANS = new Text();
        ANS.setTranslateX(1275);
        ANS.setTranslateY(365);
        ANS.setScaleX(2.5);
        ANS.setScaleY(2.5);

        Button ss = new Button("See Solution");
        ss.setTranslateX(1200);
        ss.setTranslateY(405);
        ss.setPrefSize(100,30);
        setStyleBoard(ss);
        ss.setVisible(false);
        Button rt = new Button("Retry");
        rt.setTranslateX(1260);
        rt.setTranslateY(450);
        rt.setPrefSize(100,30);
        setStyleBoard(rt);
        rt.setVisible(false);
        Button vs = new Button("Visualize Answer");
        vs.setTranslateX(1320);
        vs.setTranslateY(405);
        vs.setPrefSize(110,30);
        setStyleBoard(vs);
        vs.setVisible(false);

        Random random = new Random();

        Sphere ball = new Sphere(25);
        Ellipse shadow = new Ellipse();
        shadow.setCenterX(180.0f);
        shadow.setCenterY(670.0f);
        shadow.setRadiusX(40.0f);
        shadow.setRadiusY(15.0f);
        ball.setTranslateX(200);
        ball.setTranslateY(650);
        GaussianBlur gaussianBlur = new GaussianBlur();
        gaussianBlur.setRadius(30);
        shadow.setEffect(gaussianBlur);

        M=marks;
        String st;
        if(exam==0) {
            num = randInt(1, 7);
        } else
        {
            num = numbers.iterator().next();
            System.out.println(num);
            numbers.remove(num);
        }
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
                    Type = st + ".jpg";
                    TypeF = "src/Images/" + st + ".jpg";
                    TypeL = "src/Images/L" + st + ".jpg";
                    TypeB = "/Images/" + st + "B.jpg";
                    type = -1;
                } else if (type == 0) {
                    if (st.contains("#")) {
                        type = 1;
                    } else {
                        ques.add(st);
                    }
                } else if (type == -1) {
                    if (st.contains("VAR")) {
                        flag = 2;
                    }
                }
            }
            else if(flag==2)
            {
                st = sc.nextLine();
                if(st.contains("ANS"))
                {
                    flag=3;
                }
                else
                {
                    if(var==0) {
                        if (st.contains("v")) {
                            var = 1;
                        }
                        else if(st.contains("theta"))
                        {
                            var=2;
                        }

                    }
                    else if(var==1)
                    {
                        Velo= Integer.parseInt(st);
                        var=0;
                    }
                    else if(var==2)
                    {
                        theta = Integer.parseInt(st);
                        var=0;
                    }
                }
            }

            else if(flag==3)
            {
                st = sc.nextLine();
                if(st.contains("POINT"))
                {
                    flag=4;
                }
                else
                {
                    if(st.contains("Height"))
                    {
                        Label H = new Label("Hmax: ");
                        H.setTextFill(Color.WHITE);
                        H.setScaleX(1.5);
                        H.setScaleY(1.5);
                        ans.add(H,1,position);
                        ans.add(hh, 2, position);
                        position++;
                        Hcount=0;
                    }
                    else if(st.contains("time"))
                    {
                        Label hT = new Label("Time: ");
                        hT.setTextFill(Color.WHITE);
                        hT.setScaleX(1.5);
                        hT.setScaleY(1.5);
                        ans.add(hT,1,position);
                        ans.add(tt, 2, position);
                        position++;
                        tcount=0;
                    }
                    else if(st.contains("TimeMax"))
                    {
                        Label T = new Label("Tmax: ");
                        T.setTextFill(Color.WHITE);
                        T.setScaleX(1.5);
                        T.setScaleY(1.5);
                        ans.add(T,1,position);
                        ans.add(TT, 2, position);
                        position++;
                        Tcount=0;
                    }
                    else if(st.contains("Range"))
                    {
                        Label R = new Label("Range: ");
                        R.setTextFill(Color.WHITE);
                        R.setScaleX(1.5);
                        R.setScaleY(1.5);
                        ans.add(R,1,position);
                        ans.add(rr,2, position);
                        position++;
                        rcount=0;
                    }
                    else if(st.contains("theta"))
                    {
                        Label th = new Label("Angle: ");
                        th.setTextFill(Color.WHITE);
                        th.setScaleX(1.5);
                        th.setScaleY(1.5);
                        ans.add(th,1,position);
                        ans.add(thh,2, position);
                        position++;
                        thcount=0;
                    }
                }
            }
            else if(flag==4)
            {
                st = sc.nextLine();
                if(st.contains("END"))
                {
                    break;
                }
                else
                {
                    points = Integer.parseInt(st);
                }
            }
        }

        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream(TypeB)));
        ball.setMaterial(material);

        timerL = new Label("Remaining Time: " + String.format("%02d", minute) + ":" + String.format("%02d",second));
        setCorrectWrong(timerL, 700, 300, Color.MAROON, 2, 2);
        timerL.setPrefSize(150,10);

        Text L = new Text();
        L.setTranslateX(550);
        L.setTranslateY(130);
        L.setScaleX(2);
        L.setScaleY(2);

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
                    warning.setText(null);
                });

                Ok.setOnAction(E->{
                    if(Hcount==0 && hh.getText().isEmpty() || Tcount==0 && TT.getText().isEmpty() || tcount==0 && tt.getText().isEmpty() || rcount==0 && rr.getText().isEmpty() || thcount==0 && thh.getText().isEmpty())
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
                                System.out.println(Theta);
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

        angle = (theta * Math.PI) / 180;
        angleR = angle;
        Sin = Math.sin(angle);
        C = Math.cos(angle);
        H = Math.pow((Velo * Sin), 2) / (2 * 9.8);
        T = (2 * (Velo * Sin)) / 9.8;
        R = ((Velo * Velo) * Math.sin(2 * angle)) / 9.8;

        St.setOnAction(e -> {
            if (Hmax == -1 && Hcount == 0 || Theta == -1 && thcount == 0 || Tmax == -1 && Tcount == 0 || time == -1 && tcount == 0 || Range == -1 && rcount == 0) {
                a.setAlertType(Alert.AlertType.WARNING);
                a.setContentText("You have to first answer the questions");
                a.show();
            } else {
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
                    if (RoundUpDecimal.round(H, Hmax)) {
                        M += 1;
                        check = 0;
                    } else {
                        check = 1;
                        pointAns[point] = new Circle(6);
                        setPoint(pointAns[point], 200 + (Range * 3), 650 - (Hmax * 4.5), Color.MAROON);
                        pointAns[point].setVisible(false);
                        setCorrectWrong(wrongH, pointAns[point].getTranslateX(), pointAns[point].getTranslateY() - 60, Color.MAROON, 1.5, 1.5);
                        wrongH.setVisible(false);
                        root.getChildren().addAll(pointAns[point], wrongH);
                        point++;
                        pointAns[point] = new Circle(6);
                        setPoint(pointAns[point], 200 + (R * 3), 650 - (H * 4.5), Color.DARKGREEN);
                        pointAns[point].setVisible(false);
                        setCorrectWrong(rightH, pointAns[point].getTranslateX(), pointAns[point].getTranslateY() - 60, Color.DARKGREEN, 1.5, 1.5);
                        rightH.setVisible(false);
                        root.getChildren().addAll(pointAns[point], rightH);
                        point++;
                        mulY = (mulY * Hmax) / H;
                    }
                }
                if (time != -1) {
                    if (RoundUpDecimal.round(time, T / 2)) {
                        M += 1;
                        if (check == 0) {
                            check = 0;
                        }
                    } else {
                        check = 1;
                        if (time > (T / 2)) {
                            wrongTime.setText("Given time to reach Maximum Height is greater than the actual time!");
                        } else {
                            wrongTime.setText("Given time to reach Maximum Height is less than the actual time!");
                        }
                        setCorrectWrong(wrongTime, 1100, 530 + space, Color.MAROON, 1.1, 1.1);
                        wrongTime.setPrefSize(400, 10);
                        wrongTime.setVisible(false);
                        space += 50;
                        root.getChildren().add(wrongTime);
                    }
                }
                if (Tmax != -1) {
                    if (RoundUpDecimal.round(Tmax, T)) {
                        M += 1;
                        if (check == 0) {
                            check = 0;
                        }
                    } else {
                        check = 1;
                        if (Tmax > T) {
                            wrongTmax.setText("Given time to reach the ground is greater than the actual time!");
                        } else {
                            wrongTmax.setText("Given time to reach the ground is less than the actual time!");
                        }
                        setCorrectWrong(wrongTmax, 1100, 530 + space, Color.MAROON, 1.1, 1.1);
                        wrongTmax.setPrefSize(360, 10);
                        wrongTmax.setVisible(false);
                        space += 50;
                        root.getChildren().add(wrongTmax);
                    }
                }
                if (Range != -1) {

                    if (RoundUpDecimal.round(R, Range)) {
                        M += 1;
                        if (check == 0) {
                            check = 0;
                        }
                    } else {
                        check = 1;
                        pointAns[point] = new Circle(6);
                        setPoint(pointAns[point], 200 + (Range * 6), 650, Color.MAROON);
                        pointAns[point].setVisible(false);
                        setCorrectWrong(wrongR, pointAns[point].getTranslateX(), 650 + 20, Color.MAROON, 1.5, 1.5);
                        wrongR.setVisible(false);
                        root.getChildren().addAll(pointAns[point], wrongR);
                        point++;
                        pointAns[point] = new Circle(6);
                        setPoint(pointAns[point], 200 + (R * 6), 650, Color.DARKGREEN);
                        pointAns[point].setVisible(false);
                        setCorrectWrong(rightR, pointAns[point].getTranslateX(), 650 + 20, Color.DARKGREEN, 1.5, 1.5);
                        rightR.setVisible(false);
                        root.getChildren().addAll(pointAns[point], rightR);
                        point++;
                        mulX = (mulX * Range) / R;
                    }
                }
                if (Theta != -1) {

                    if (RoundUpDecimal.round(theta, Theta)) {
                        M += 2;
                        if (check == 0) {
                            check = 0;
                        }
                    } else {
                        check = 1;
                        rightA = new Line(200, 450, 200, 650);
                        rightA.setStroke(Color.DARKGREEN);
                        rightA.setStrokeWidth(4);
                        wrongA = new Line(200, 450, 200, 650);
                        wrongA.setStroke(Color.MAROON);
                        wrongA.setStrokeWidth(4);
                        Rotate rotateR = new Rotate();
                        rotateR.setAngle(90 - theta);
                        System.out.println(theta + " " + Theta);
                        Rotate rotateW = new Rotate();
                        rotateW.setAngle(90 - Theta);
                        rotateR.setPivotX(ball.getTranslateX());
                        rotateR.setPivotY(ball.getTranslateY());
                        rotateW.setPivotX(ball.getTranslateX());
                        rotateW.setPivotY(ball.getTranslateY());
                        rightA.getTransforms().add(rotateR);
                        wrongA.getTransforms().add(rotateW);
                        setCorrectWrong(rightAng, rightA.getStartX() + (Math.tan((90 - theta) * (Math.PI / 180)) * 200), rightA.getStartY() - 30, Color.DARKGREEN, 1.5, 1.5);
                        setCorrectWrong(wrongAng, wrongA.getStartX() + (Math.tan((90 - Theta) * (Math.PI / 180)) * 200), (450 + (200 / Math.cos((90 - Theta) * (Math.PI / 180))) - 200), Color.MAROON, 1.5, 1.5);
                        rightA.setVisible(false);
                        wrongA.setVisible(false);
                        rightAng.setVisible(false);
                        wrongAng.setVisible(false);
                        root.getChildren().addAll(rightA, wrongA, rightAng, wrongAng);
                    }
                }
                if (check == 0) {
                    ANS.setText("Correct Answer");
                    ANS.setFill(Color.WHITE);
                } else if (check == 1) {
                    ANS.setText("Wrong Answer");
                    ANS.setFill(Color.RED);
                }
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
                                        root.getChildren().removeAll(en,St);
                                    }
                                }
                            }));
            timeline.playFromStart();
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
            if(checkAnim==0) {
                mulX = 6;
                mulY = 4.5;
                check = 0;
                t = 0;
                Hmax = -1;
                Tmax = -1;
                time = -1;
                Range = -1;
                Theta = -1;
                ball.setTranslateX(200);
                ball.setTranslateY(650);
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
                root.getChildren().removeAll(wrongH, rightH, wrongR, rightR, rightA, wrongA, rightAng, wrongAng, wrongTime, wrongTmax);
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
            ball.setTranslateX(200);
            ball.setTranslateY(650);
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
                wrongAng.setVisible(true);
                rightAng.setVisible(true);
                wrongTime.setVisible(true);
                wrongTmax.setVisible(true);
                for (int i = 0; i < rw; i++) {
                    root.getChildren().removeAll(r[i],w[i]);
                }
                rw=0;
                shadow.setOpacity(0);
                double posX = ball.getTranslateX();
                double posY = ball.getTranslateY();
                if (Theta != -1)
                {
                    angle = (Theta * Math.PI) / 180;
                    Sin = Math.sin(angle);
                    C = Math.cos(angle);
                    Tmax = (2 * (Velo * Sin)) / 9.8;
                }
                checkAnim=1;
                new AnimationTimer() {
                    @Override
                    public void handle(long now) {
                        if(Tmax!=-1) {
                            Ttemp = Tmax;
                        }
                        else{ Ttemp=T;}
                        double k = (Velo * C * t);
                        double j = (Velo * Sin * t) - (0.5 * 9.8 * t * t);
                        if(t<=Ttemp) {
                            ball.setTranslateX(posX + k * mulX);
                            ball.setTranslateY(posY - j * mulY);
                            if(check==1) {
                                w[rw] = new Circle(3);
                                setPoint(w[rw], posX + k * mulX, posY - j * mulY, Color.MAROON);
                                root.getChildren().add(w[rw]);
                            }
                        }
                        if(t<=T) {
                            r[rw] = new Circle(3);
                            setPoint(r[rw], (posX + (Velo * Math.cos(angleR) * t) * 6), (posY - ((Velo * Math.sin(angleR) * t) - (0.5 * 9.8 * t * t)) * 4.5), Color.DARKGREEN);
                            root.getChildren().add(r[rw]);
                        }
                        rw++;
                        double vx = Velo * C;
                        double vy = Velo * Sin - (9.8 * t);
                        double TotalV = Math.sqrt((vx * vx) + (vy * vy));
                        if (t >= Ttemp && t>=T) {
                            stop();
                            checkAnim=0;
                        }
                        else {
                            t += 0.1;
                        }
                    }
                }.start();
            } catch (IllegalArgumentException il) {
                il.printStackTrace();
            }
        });

        Button back = new Button("Back");
        back.setTranslateX(50);
        back.setTranslateY(730);
        setStyle(back);
        back.setPrefSize(80, 30);

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
        next.setOnAction(e->{
            System.out.println("Marks:"+M);
            if(exam==0)
            {
                easy goEasy = new easy();
                try {
                    goEasy.EASY(primaryStage,0,numbers,qTotal,M,min,sec);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else if(exam==1)
            {
                if(timeup==1)
                {
                    MultiScore goMult = Object.getMulti();
                    goMult.Score(M-marks);
                    goMult.setFinalMarks(M);
                    try {
                        goMult.sendP(1);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                else {
                    MultiScore goMult = Object.getMulti();
                    goMult.Score(M-marks);
                    if (qTotal > 4) {
                        easy goEasy = new easy();
                        try {
                            goEasy.EASY(primaryStage, 1, numbers, (qTotal - 1), M, minute, second);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    } else {
                        medium goMedium = new medium();
                        try {
                            goMedium.Medium(primaryStage, 1, numbers, (qTotal - 1), M,minute,second);
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
                    scr.StoreScore(M-marks,points,num,countTime,0);
                    personalScoreBoard ps = new personalScoreBoard();
                    try {
                        ps.Result(primaryStage,10-quesTotal);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                }
                else {
                    SingleScore scr = Object.getSscore();
                    scr.StoreScore(M - marks, points, num,countTime,0);
                    if(timeup==1)
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

        Image background = new Image(new FileInputStream(TypeF));
        root.getChildren().addAll(cn,back, shadow,St,next,L,ball,en);
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
                eStage.initStyle(TRANSPARENT);
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

    public static int randInt(int min, int max) {

        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
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
        cw.setPrefSize(100,3);
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
