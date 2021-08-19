package fusics;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
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

public class personalScoreBoard {
    int[][] questionSet = new int[10][5];
    Text qNo[]= new Text[10];
    Text marks[] = new Text[10];
    Text time[] = new Text[10];
    Button sol[] = new Button[10];
    ImageView iv[] = new ImageView[10];
    int position = 52;
    double posOwn = 54.4;
    int mrk,mrkT,qNum,min,sec,diff;
    int bGap = 0;
    double T,timeTotal;
    public void Result(Stage primaryStage, int qTotal) throws FileNotFoundException {
        Pane root = new Pane();
        Canvas c = new Canvas(1600, 800);
        GraphicsContext gc = c.getGraphicsContext2D();
        SingleScore sc = Object.getSscore();
        questionSet = sc.sendScore();
        for(int i=0;i<qTotal;i++)
        {
            qNo[i] = new Text(""+ (i+1));
            mrk = questionSet[i][0];
            mrkT =questionSet[i][1];
            qNum = questionSet[i][2];
            min = questionSet[i][3];
            diff = questionSet[i][5];
            marks[i] = new Text(mrk+" out of "+ mrkT);
            T = min/60.0;
            time[i] = new Text(String.format("%.2f",T) +" min");
            setPos(qNo[i],330,(180+position));
            setPos(marks[i],620,180+position);
            setPos(time[i],890,180+position);
            position += 52;
            Image view = new Image(new FileInputStream("src/Images/viewSingle.png"));
            iv[i] = new ImageView(view);
            sol[i] = new Button(null,iv[i]);
            sol[i].setBackground(null);
            viewB(sol[i],210+bGap);
            sol[i].setOnAction(e->{
                Pane sol = new Pane();
                Scene Solu = new Scene(sol, 1450, 800);
                Image Solution = null;
                try {
                    System.out.println(qNum);
                    if(diff==0)
                    Solution = new Image(new FileInputStream("src/Images/"+qNum+"easy.png"));
                    else if(diff==1)
                        Solution = new Image(new FileInputStream("src/Images/"+qNum+"medium.png"));
                    else
                        Solution = new Image(new FileInputStream("src/Images/"+qNum+"hard.png"));
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
            bGap+=52;
            root.getChildren().addAll(qNo[i],marks[i],time[i],sol[i]);
            System.out.println("Printing on board "+ mrk+" "+ mrkT);
        }
        Scene S = new Scene(root, 1600, 800);
        Image background = new Image(new FileInputStream("src/Images/Sinperfo.png"));

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
    public Button viewB (Button b,int y)
    {
        b.setBackground(null);
        b.setTranslateX(1130);
        b.setTranslateY(y);
        return b;
    }
}
