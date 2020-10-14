package First;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class LeaderBoard {

    Text rank[] = new Text[10];
    Text name[] = new Text[10];
    Text score[] = new Text[10];
    int position = 52;
    double posOwn = 54.4;
    pair marks[] = new pair[10];
    int signal  = 0;
    int playerNUmber = 0;
    double ownPlace=0;
    Text qNo[] = new Text[5];
    Text pMarks[] = new Text[5];
    ArrayList<Integer> marksInd = new ArrayList<Integer>();
    Set<Integer> generatedQ = new LinkedHashSet<Integer>();

    public void Board(Stage primaryStage,pair[] marks,String own) throws IOException {
        Pane root = new Pane();
        Canvas c = new Canvas(1600, 800);
        GraphicsContext gc = c.getGraphicsContext2D();

        MultiScore goMulti = Object.getMulti();
        marksInd = goMulti.sendInd();
        System.out.println(marksInd);
        wait goWait = Object.getWait();
        generatedQ = goWait.sendQSet();

        Button all = new Button("Scoreboard");
        all.setTranslateX(570);
        all.setTranslateY(40);
        if (signal == 0) {
            setStyleOClicked(all);
        } else {
            setStyleO(all);
        }
        all.setPrefSize(180,50);

        Button personal = new Button("Personal");
        personal.setTranslateX(820);
        personal.setTranslateY(40);
        if (signal == 1) {
            setStyleOClicked(personal);
        } else {
            setStyleO(personal);
        }
        personal.setPrefSize(180,50);

        root.getChildren().addAll(c,all,personal);

        Image ownS = new Image(new FileInputStream("src/Images/ownScore.png"));
        for (int i = 0; i < 10; i++) {
            if (!(marks[i].y.equals("end"))) {
                playerNUmber++;
                if (marks[i].y.equals(own)) {

                    gc.drawImage(ownS, 259, 167 + posOwn);
                    ownPlace = 167+ posOwn;
                }
                int mrk = marks[i].x;
                System.out.println("marks: " + mrk);
                String nam = marks[i].y;
                rank[i] = new Text(""+(i + 1));
                name[i] = new Text(nam);
                score[i] = new Text(""+mrk);
                setPos(rank[i],350,(210+position));
                setPos(name[i],600,210+position);
                setPos(score[i],1150,210+position);
                root.getChildren().addAll(rank[i],name[i],score[i]);
                position += 52;
                posOwn+= 54.4;
            } else {
                break;
            }
        }

        Image view = new Image(new FileInputStream("src/Images/view.png"));
        ImageView V1 = new ImageView(view);
        Button one = new Button(null,V1);
        viewB(one,248);
        ImageView V2 = new ImageView(view);
        Button two = new Button(null,V2);
        viewB(two,338);
        ImageView V3 = new ImageView(view);
        Button three = new Button(null,V3);
        viewB(three,438);
        ImageView V4 = new ImageView(view);
        Button four = new Button(null,V4);
        viewB(four,528);
        ImageView V5 = new ImageView(view);
        Button five = new Button(null,V5);
        viewB(five,618);
        for(int i=0;i<5;i++)
        {
            qNo[i] = new Text(""+ (i+1));
            setPos(qNo[i],400,(290+(i*90)));
            pMarks[i] = new Text(""+ marksInd.iterator().next());
            setPos(pMarks[i],760,290+(i*90));
            marksInd.remove(marksInd.iterator().next());
        }

        personal.setOnAction(e->{
            setStyleOClicked(personal);
            setStyleO(all);
            for(int i=0;i<playerNUmber;i++) {
                root.getChildren().removeAll(rank[i],name[i],score[i]);
            }
            for(int i=0;i<5;i++)
            {
                root.getChildren().addAll(qNo[i],pMarks[i]);
            }
            gc.clearRect(0,0,1600,800);
            root.getChildren().addAll(one,two,three,four,five);
            Image background = null;
            try {
                background = new Image(new FileInputStream("src/Images/personal.png"));
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }

            BackgroundImage bi = new BackgroundImage(background,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background bg = new Background(bi);
            root.setBackground(bg);
        });

        all.setOnAction(e->{
            setStyleO(personal);
            setStyleOClicked(all);
            root.getChildren().removeAll(one,two,three,four,five);
            for(int i=0;i<playerNUmber;i++)
            {
                root.getChildren().addAll(rank[i],name[i],score[i]);
            }
            for(int i=0;i<5;i++)
            {
                root.getChildren().removeAll(qNo[i],pMarks[i]);
            }
            gc.drawImage(ownS, 259, ownPlace);
            Image background = null;
            try {
                background = new Image(new FileInputStream("src/Images/leaderboard.png"));
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }

            BackgroundImage bi = new BackgroundImage(background,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background bg = new Background(bi);
            root.setBackground(bg);
        });

        one.setOnAction(e->{
            Pane sol = new Pane();
            Scene Solu = new Scene(sol, 1450, 800);
            Image Solution = null;
            try {
                Solution = new Image(new FileInputStream("src/Images/1easy.png"));
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

        Image Back = new Image(new FileInputStream("src/Images/backButton.png"));
        ImageView bb = new ImageView(Back);

        Button back = new Button(null,bb);
        back.setBackground(null);
        back.setTranslateX(50);
        back.setTranslateY(20);
        back.setOnAction(e->{
            examSM goSM = new examSM();
            try {
                goSM.SM(primaryStage);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        root.getChildren().add(back);
        Scene S = new Scene(root, 1600, 800);
        Image background = new Image(new FileInputStream("src/Images/leaderboard.png"));

        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        root.setBackground(bg);

        primaryStage.setScene(S);
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

    public Text setPos(Text T, int X, int Y)
    {
        T.setTranslateX(X);
        T.setTranslateY(Y);
        T.setFill(Color.WHITE);
        T.setScaleX(2);
        T.setScaleY(2);
        return T;
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
                "    -fx-font-size: 18px;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
        return b;
    }

    public Button setStyleOClicked(Button b)
    {
        b.setStyle("-fx-background-color: \n" +
                "        linear-gradient(#1289C4 0%, #40BAEC 25%, #0097CE 75%, #1289C4 100%),\n" +
                "        linear-gradient(#365caa 0%, #2a4893 20%, #1b2d75 80%, #001a33 100%),\n" +
                "        linear-gradient(#365caa 0%, #2a4893 20%, #1b2d75 80%, #001a33 100%),\n" +
                "        linear-gradient(#365caa 0%, #2a4893 40%, #1b2d75 80%, #001a33 100%);\n" +
                "    -fx-background-insets: 0,1,4,5,6;\n" +
                "    -fx-background-radius: 9,8,5,4,3;\n" +
                "    -fx-padding: 15 30 15 30;\n" +
                "    -fx-font-family: \"Helvetica\";\n" +
                "    -fx-font-size: 18px;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
        return b;
    }
    public Button viewB (Button b,int y)
    {
        b.setBackground(null);
        b.setTranslateX(1050);
        b.setTranslateY(y);
        return b;
    }
}
