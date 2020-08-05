package First;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.temporal.Temporal;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class wait {
    Set<Integer> generatedQ = new LinkedHashSet<Integer>();
    Server nServer = new Server();

    String Player[]= new String[10];
    Text Pt[] = new Text[10];
    int p=0;
    int position = 0;

//    public Socket socket[] = new Socket[5];
//    private ServerSocket server = null;
//    DataOutputStream dOUT = null;
//    DataInputStream dIn = null;
//    public int intLastSocket = 0;

    public Socket socket = null;
    DataInputStream dIn = null;
    DataOutputStream dOut = null;

    public void Wait(Stage primaryStage) throws IOException {
        Pane root = new Pane();
        multiplayer goMulti = new multiplayer();
        Server goServer = new Server();

        Text headning = new Text("Multiplayer");
        headning.setScaleX(4);
        headning.setScaleY(4);
        headning.setTranslateX(750);
        headning.setTranslateY(60);
        headning.setFill(Color.WHITE);

        Text wait = new Text("Waiting for other players to join");
        wait.setFill(Color.WHITE);
        wait.setTranslateX(700);
        wait.setTranslateY(630);
        wait.setScaleX(2.5);
        wait.setScaleY(2.5);

        Text code = new Text("Game code is 8900");
        code.setFill(Color.WHITE);
        code.setTranslateX(730);
        code.setTranslateY(150);
        code.setScaleX(3);
        code.setScaleY(3);

        Button Start = new Button("Start");
        Start.setTranslateX(670);
        Start.setTranslateY(700);
        setStyle(Start);
        Start.setPrefSize(180, 80);


        Random rng = new Random();
        while (generatedQ.size() < 2) {
            Integer next = rng.nextInt((7 - 1) + 1) + 1;
            generatedQ.add(next);
        }
        while (generatedQ.size() < 4) {
            Integer next = rng.nextInt((7 - 1) + 1) + 1;
            generatedQ.add(next);
        }
        while (generatedQ.size() < 5) {
            Integer next = rng.nextInt((7 - 1) + 1) + 1;
            generatedQ.add(next);
        }

        Task task1 = new Task<Void>() {
            @Override
            public Void call() {
                    //nServer.Server(8900);
//                    try {
//                        InetAddress localhost = InetAddress.getLocalHost();
//                        System.out.println(localhost);
//                        server = new ServerSocket(8900);
//                        System.out.println("Server started");
//                        System.out.println("Waiting for a client ...");
//
//                        while (true) {
//                            try {
//                                socket[intLastSocket] = server.accept();
//                                System.out.println("Client accepted");
//                                dIn = new DataInputStream(socket[intLastSocket].getInputStream());
//                                String pl = dIn.readUTF();
//                                dOUT = new DataOutputStream(socket[intLastSocket].getOutputStream());
//                                dOUT.writeUTF(Player[0]);
//                                int i=intLastSocket;
//                                System.out.println(i);
//                                while(i>=0) // send the new player name to all player
//                                {
//                                    dOUT = new DataOutputStream(socket[i].getOutputStream());
//                                    dOUT.writeUTF(pl);
//                                    System.out.println("Sent "+pl);
//                                    i--;
//                                }
//                                Player[p]=pl;
//                                Pt[p]= new Text(Player[p]);
//                                position+=50;
//                                addPlayer(Pt[p],position);
//                                Platform.runLater(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        root.getChildren().add(Pt[p-1]);
//                                    }
//                                });
//                                p++;
//                                intLastSocket++;
//                            } catch (Exception e) {
//                                socket[intLastSocket].close();
//                                e.printStackTrace();
//                            }
//                        }
//                    } catch (IOException i) {
//                        System.out.println(i);
//                    }
                try {
                    socket = new Socket("127.0.0.1", 8900);
                    System.out.println("Connected");
                    dOut = new DataOutputStream(socket.getOutputStream());
                    dOut.writeUTF(Player[0]);
                    dIn = new DataInputStream(socket.getInputStream());
                    while(true) {
                        String pl = dIn.readUTF();
                        System.out.println(pl);
                        if(pl.equals("0"))
                        {
                            break;
                        }
                    }
                } catch (UnknownHostException u) {
                    System.out.println(u);
                } catch (IOException i) {
                    System.out.println(i);
                }
                return null;
            }
        };
        Thread t = new Thread(task1);
        t.setDaemon(true);
        t.start();

        Start.setOnAction(e -> {
            try {
                dOut = new DataOutputStream(socket.getOutputStream());
                dOut.writeUTF("100");

                generatedQ.clear();
                dIn = new DataInputStream(socket.getInputStream());

                for (int i = 0; i < 5; i++) {
                    int qNo = dIn.readInt();
                    generatedQ.add(qNo);
                }

                System.out.println(generatedQ);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            easy goEasy = new easy();
            try {
                goEasy.EASY(primaryStage, 1, generatedQ, 5, 0, 1, 0);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        Pane Rr = new Pane();
        Text name = new Text("Enter your name");
        name.setTranslateX(300);
        name.setTranslateY(100);
        name.setFill(Color.WHITE);
        name.setScaleX(2);
        name.setScaleY(2);
        TextField Nam = new TextField();
        Nam.setAlignment(Pos.CENTER);
        Nam.setTranslateX(280);
        Nam.setTranslateY(200);
        Nam.setScaleX(1.5);
        Nam.setScaleY(1.5);
        Button enter = new Button("Enter");
        enter.setTranslateX(300);
        enter.setTranslateY(300);
        setStyleE(enter);
        enter.setPrefSize(100,20);

        Rr.getChildren().addAll(name,Nam,enter);
        Scene Ss = new Scene(Rr, 700, 400);
        Stage eeStage = new Stage();
        eeStage.setScene(Ss);
        Image bgg = null;
        try {
            bgg = new Image(new FileInputStream("src/Images/exit.png"));
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        BackgroundImage bii = new BackgroundImage(bgg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background backk = new Background(bii);
        Rr.setBackground(backk);
        Ss.setFill(Color.TRANSPARENT);
        eeStage.initStyle(StageStyle.TRANSPARENT);

        enter.setOnAction(e->
        {
            Player[p] = new String();
            Player[p] = Nam.getText();
            if(!(Player[p].isEmpty()))
            {
                MultiScore goM = new MultiScore();
                goM.setName(Player[p]);
                Pt[p] = new Text(Player[p]);
                position+=50;
                addPlayer(Pt[p],position);
                root.getChildren().add(Pt[p]);
                p++;
                eeStage.close();
            }
            else
            {
                Text alert = new Text("Please enter a name");
                alert.setFill(Color.RED);
                alert.setTranslateX(300);
                alert.setTranslateY(280);
                alert.setScaleX(1.4);
                alert.setScaleY(1.4);
                Rr.getChildren().addAll(alert);
            }
        });


        Button back = new Button("Back");
        back.setTranslateX(50);
        back.setTranslateY(20);
        back.setStyle("-fx-padding: 8 15 15 15;\n" +
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
        back.setPrefSize(60, 30);
        back.setOnAction(e -> {
            try {
                goMulti.Multi(primaryStage, 0, nServer);
                examSM goBack = new examSM();
                goBack.SM(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        root.getChildren().addAll(Start, code, back, headning, wait);

        Scene S = new Scene(root, 1600, 800);
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
        eeStage.show();

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
                "    -fx-font-size: 1.8em;");
        return b;
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

    public Text addPlayer(Text player,int pos)
    {
        player.setTranslateX(750);
        player.setTranslateY(200+pos);
        player.setScaleX(2);
        player.setScaleY(2);
        player.setFill(Color.WHITE);
        return player;
    }
}
