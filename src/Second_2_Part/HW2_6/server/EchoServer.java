package Second_2_Part.HW2_6.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {

    private static final int SERV_PORT = 8189;

    public static void main(String[] args) {

        try(ServerSocket serverSocket = new ServerSocket(SERV_PORT)) {
            System.out.println("Waiting for connection...");
            // Блокируем поток и переходим в режим ожидания ("сидит на порте, прослушивает порт")
            // (Потоки Stream <> Thread - просто разные понятия, для передачи данных и распараллеливания процессов, соответственно)
            Socket clientSocket = serverSocket.accept();
            System.out.println("The connection is established!");

            // Вместо DataInputStream можно было бы использовать Scaner. Так:
//            Scanner in = new Scanner(clientSocket.getInputStream());

            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            while (true) {
                // Ожидаем ввода от пользователя
                String message = in.readUTF();
                System.out.println("User message: " + message);
                if (message.equals("/exit")) {
                    break;
                }
                out.writeUTF(message.toUpperCase());
            }
            System.out.println("The server is stopped");
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("The port is already occupied");
        }
    }
}
