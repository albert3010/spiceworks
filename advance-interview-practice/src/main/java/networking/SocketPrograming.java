package networking;

import org.junit.Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by oyadav on 12/20/16.
 */
public class SocketPrograming {

    @Test
    public void sampleSocketProgramingClient() throws IOException {

        Socket smtpSocket = null;
        DataOutputStream os = null;
        DataInputStream is = null;

        // Initialization section:
        // Try to open a socket on port 25
        // Try to open input and output streams
        smtpSocket = new Socket("localhost", 3000);

        os = new DataOutputStream(smtpSocket.getOutputStream());
        is = new DataInputStream(smtpSocket.getInputStream());

        // The capital string before each colon has a special meaning to SMTP
        // you may want to read the SMTP specification, RFC1822/3

        os.writeBytes("HELLO\n");
        os.writeBytes("MAIL From: k3is@fundy.csd.unbsj.ca\n");
        os.writeBytes("RCPT To: k3is@fundy.csd.unbsj.ca\n");
        os.writeBytes("DATA\n");
        os.writeBytes("From: k3is@fundy.csd.unbsj.ca\n");
        os.writeBytes("Subject: testing\n");
        os.writeBytes("Hi there this is om\n"); // message body
        os.writeBytes("\n.\n");
        os.writeBytes("QUIT");

// keep on reading from/to the socket till we receive the "Ok" from SMTP,
// once we received that then we want to break.
        String responseLine;
        while ((responseLine = is.readLine()) != null) {
            System.out.println("Server: " + responseLine);
            if (responseLine.contains("Ok")) {
                break;
            }
        }
    }

        @Test
        public void sampleSocketProgramingClientTest () throws IOException, InterruptedException {

            Socket s = new Socket("localhost", 6001);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF("Hello Server");
//            dout.writeUTF("Hello Server2");
//            dout.writeBytes("hello 2");

            dout.writeUTF("Hello Server3");
            Thread.sleep(2000);
//            dout.writeBytes("hello 3");
            dout.writeUTF("Hello Server4");
            dout.flush();
            dout.close();
            s.close();


        }
    }