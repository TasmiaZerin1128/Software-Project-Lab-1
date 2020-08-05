package First;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedHashSet;
import java.util.Set;

public class ClientMaster {
    public Socket socket = null;
    DataInputStream dIn = null;
    DataOutputStream dOut = null;
    Set<Integer> generatedEasy = new LinkedHashSet<Integer>();

    public Set<Integer> Client(String address, int port) throws IOException {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
            dOut = new DataOutputStream(socket.getOutputStream());
            dOut.writeUTF("Zerin");
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
        return ques();
    }

    public Set<Integer> ques() throws IOException {

        generatedEasy.clear();
        dIn = new DataInputStream(socket.getInputStream());

        for (int i = 0; i < 5; i++) {
            int qNo = dIn.readInt();
            generatedEasy.add(qNo);
        }

        System.out.println(generatedEasy);
        return generatedEasy;
    }

    public int passM (int marks) throws IOException {
        dIn = new DataInputStream(socket.getInputStream());
        dOut = new DataOutputStream(socket.getOutputStream());
        int M = dIn.readInt();
        dOut.writeInt(marks);
        return  M;
    }

    public void close() throws IOException {
        if(socket!=null)
            socket.close();
        if(dOut!=null)
            dOut.close();
        if(dIn!=null)
            dIn.close();
    }
}
