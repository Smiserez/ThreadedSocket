/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadedserver;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author unouser
 */
public class ThreadedClient implements Runnable {

    private Socket socket;

    public ThreadedClient(Socket inSocket) {
        socket = inSocket;
    }

    @Override
    public void run() {

        try {
            System.out.println("A socket connected");
            PrintStream out = new PrintStream(socket.getOutputStream());
            Scanner scanner = new Scanner(socket.getInputStream());

            //out.println("Hello. Say something.");
            //out.flush();
            String requestLine = scanner.nextLine();
            System.out.println(requestLine);

            while (true) {
                String headerLine = scanner.nextLine();
                System.out.println(headerLine);
                if (headerLine.length() == 0) {
                    break;
                }

            }

            out.println("HTTP/1.0 200 OK");
            out.println();
            out.println("<html><body>Hello internet</body></html>");
            out.flush();
            out.close();

            //System.out.println(socket.getInetAddress() + line);
            //out.println(socket.getInetAddress() + line);
            //out.flush();
        } catch (IOException ex) {
            Logger.getLogger(ThreadedClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
