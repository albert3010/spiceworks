package practice.networking;

import org.junit.Test;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by oyadav on 12/20/16.
 */
public class Server {

    @Test
    public void sampleSocketProgramingEchoServer() throws IOException {

        ServerSocket echoServer = null;
        String line;
        DataInputStream is;
        PrintStream os;
        Socket clientSocket = null;


        echoServer = new ServerSocket(3000);

        // Create a socket object from the ServerSocket to listen and accept
        // connections.
        // Open input and output streams

        clientSocket = echoServer.accept();
        is = new DataInputStream(clientSocket.getInputStream());
        os = new PrintStream(clientSocket.getOutputStream());
// As long as we receive data, echo that data back to the client.
        while (true) {
            line = is.readLine();
            os.println(line);
        }
    }

        @Test
        public void sampleSocketProgramingEchoServerTest () throws IOException {

            ServerSocket ss = new ServerSocket(6001);
            Socket s = ss.accept();//establishes connection
            DataInputStream dis = new DataInputStream(s.getInputStream());

            while (true) {
                String str = (String) dis.readUTF();
                System.out.println("message= " + str);

            }
//            String str = (String) dis.readUTF();
//            System.out.println("message= " + str);
//            ss.close();

        }
    }