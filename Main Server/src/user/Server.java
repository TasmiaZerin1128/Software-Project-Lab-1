package user;

import java.net.*;
import java.io.*;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class Server{
    Set<Integer> generatedQ = new LinkedHashSet<Integer>();

    public Socket socket[] = new Socket[5];
    private ServerSocket server = null;
    DataOutputStream dOUT = null;
    DataInputStream dIn = null;
    DataInputStream dInMaster;
    Thread[] t = new Thread[5];
    pair marks[] = new pair[5];
    public int intLastSocket = 1;
    int iterator = 0,playerNum;
    String pl[] = new String[5];

    public Server(int port) throws IOException {
        server = new ServerSocket(8900);
        System.out.println("Server started");
        System.out.println("Waiting for a client ...");
        socket[0] = server.accept();
        System.out.println("Client Master accepted");
        dInMaster = new DataInputStream(socket[0].getInputStream());
        pl[0] = dInMaster.readUTF();
        playerNum = dInMaster.readInt();
        System.out.println("Master " + pl[0]);
        
        for (int j = 0; j < playerNum-1; j++) {
            try {
                socket[intLastSocket] = server.accept();
                System.out.println("Client accepted");
                dIn = new DataInputStream(socket[intLastSocket].getInputStream());
                pl[intLastSocket] = dIn.readUTF();
                System.out.println(pl[intLastSocket]);
                dOUT = new DataOutputStream(socket[intLastSocket].getOutputStream());
                int i = intLastSocket-1;
                System.out.println(i);
                while (i >= 0) // send the new player name to all player
                {
                    DataOutputStream doutP = new DataOutputStream(socket[i].getOutputStream());
                    doutP.writeUTF(pl[intLastSocket]);
                    dOUT.writeUTF(pl[i]);
                    System.out.println("Sent " + pl[i]);
                    i--;
                }
                intLastSocket++;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        int ii = intLastSocket - 1;
        while (ii >= 0) {
            System.out.println("Start " + ii);
            dOUT = new DataOutputStream(socket[ii].getOutputStream());
            dOUT.writeUTF("0");
            dOUT.flush();
            ii--;
        }
            String strt = dInMaster.readUTF();
            if (strt.equals("100")) {
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
                try {
                    int i = intLastSocket - 1;
                    while (i >= 0) {
                        Iterator<Integer> itr = generatedQ.iterator();
                        dOUT = new DataOutputStream(socket[i].getOutputStream());
                        while (itr.hasNext()) {
                            dOUT.writeInt(itr.next());
                            dOUT.flush();
                        }
                        i--;
                    }
                } catch (Exception e) {
                    socket[intLastSocket].close();
                    e.printStackTrace();
                }
        }
            DataInputStream dInMarks = null;
            DataOutputStream dOutMarks = null;
            for(iterator=0;iterator<intLastSocket;iterator++)
            {
                socket[iterator] = server.accept();
                System.out.println("Client accepted");
                dInMarks= new DataInputStream(socket[iterator].getInputStream());
                String name = dInMarks.readUTF();
                int Marks = dInMarks.readInt();
                marks[iterator] = new pair(Marks,name);
                System.out.println("marks: "+ marks[iterator].x + " name: "+ marks[iterator].y);
            }
            for(int i=0;i<intLastSocket-1;i++)
           {
              for(int j=0; j< intLastSocket-i-1;j++)
              {
                  if(marks[j].x<marks[j+1].x)
                  {
                      pair tmp = marks[j];
                      marks[j] = marks[j+1];
                      marks[j+1] = tmp;
                  }
              }
           }

        for(iterator=0;iterator<intLastSocket;iterator++) {
            dOutMarks = new DataOutputStream(socket[iterator].getOutputStream());
            for(int j=0;j<intLastSocket;j++)
            {
                dOutMarks.writeUTF(marks[j].y);
                dOutMarks.writeInt(marks[j].x);
            }
            dOutMarks.writeUTF("done");
            dOutMarks.writeInt(-1);
        }
    }
            public static void main(String args[]) throws IOException {
                Server server = new Server(8900);
            }
        }

