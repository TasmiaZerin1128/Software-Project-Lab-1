package First;

import java.net.*;
import java.io.*;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Server {
    private Socket socket = null;
    private ServerSocket server = null;

    public Server(int port,Set<Integer> qNo) throws IOException {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println(localhost);
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");

        } catch (IOException i) {
            System.out.println(i);
        }

        Iterator<Integer> itr = qNo.iterator();
        DataOutputStream dOUT = new DataOutputStream(socket.getOutputStream());
        while (itr.hasNext()) {
            dOUT.writeInt(itr.next());
            dOUT.flush();
        }
        System.out.println("Closing connection");
        socket.close();
        dOUT.close();
    }
}

