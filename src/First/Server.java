package First;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.*;
import java.io.*;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Server {
    public Socket socket[] = new Socket[5];
    private ServerSocket server = null;
    DataOutputStream dOUT = null;
    DataInputStream dIn = null;
    public int intLastSocket = 0;

    public void Server(int port) throws IOException {

        try {
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println(localhost);
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            while (true) {
                try {
                    socket[intLastSocket] = server.accept();
                    System.out.println("Client accepted");
                    dIn = new DataInputStream(socket[intLastSocket].getInputStream());
                    String N = dIn.readUTF();
                } catch (Exception e) {
                    socket[intLastSocket].close();
                    e.printStackTrace();
                }
                intLastSocket++;
            }
            } catch (IOException i) {
            System.out.println(i);
        }
    }

        public void passQ(Set<Integer> qNo) throws IOException {
        int i=intLastSocket-1;
            Iterator<Integer> itr = qNo.iterator();

            while(i>=0) {
                dOUT = new DataOutputStream(socket[i].getOutputStream());
                while (itr.hasNext()) {
                    dOUT.writeInt(itr.next());
                    dOUT.flush();
                }
                i--;
            }
        }

//        public int passM(int marks) throws IOException {
//            dOUT = new DataOutputStream(socket.getOutputStream());
//            dOUT.writeInt(marks);
//            dOUT.flush();
//            dIn = new DataInputStream(socket.getInputStream());
//            int M = dIn.readInt();
//            return M;
//        }

//        public void close() throws IOException {
//            System.out.println("Closing connection");
//            if(socket!=null)
//            socket.close();
//            if(dOUT!=null)
//            dOUT.close();
//            if(dIn!=null)
//                dIn.close();
//            if(server!=null)
//             server.close();
//        }

    }

