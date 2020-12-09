package Second_2_Part.HW2_6.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Network {

    private static final int SERV_PORT = 8189;
    private static final String SERV_HOST = "localhost";

    private final int port;
    private final String host;
    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;

    public Network(String serverHost, int serverPort) {
        this.host = serverHost;
        this.port = serverPort;
    }

    public Network() {
        this(SERV_HOST, SERV_PORT);
    }

    public boolean connect() {
        try {
            socket = new Socket(host, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            return true;
        } catch (IOException e) {
            System.out.println("The connection is not established");
            e.printStackTrace();
            return false;
        }
     }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {

        }
    }

    public DataInputStream getIn() {
        return in;
    }

    public DataOutputStream getOut() {
        return out;
    }

    public void waitUserMessage(ViewController viewController) {
        // Отдельный поток под ожидание ввода текста для чата, иначе блокируется главный поток
        // Здесь же можно фигачить контролеры и под остальные окна, например других пользователей
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    String message = in.readUTF();
                    viewController.appendMessage("Me: " + message);
                }

            }
            catch (IOException e) {
                e.printStackTrace();
                EchoClient.showErrorInfo("Connection error", "", e.getMessage());
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
