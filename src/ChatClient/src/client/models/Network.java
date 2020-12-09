package ChatClient.src.client.models;

import ChatClient.src.client.NetworkClient;
import ChatClient.src.client.controllers.ChatController;
import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class  Network {
    private static final String AUTH_CMD_PREFIX = "/auth";
    private static final String AUTHOK_CMD_PREFIX = "/authok";
    private static final String AUTHERR_CMD_PREFIX = "/autherr";
    private static final String PRIVATE_MSG_PREFIX = "/w";
    private static final String CLIENT_MSG_PREFIX = "/clientMsg";
    private static final String SERVER_MSG_PREFIX = "/serverMsg";
    private static final String END_CMD = "/end";

    private static final int SERVER_PORT = 8189;
    private static final String SERVER_HOST = "localhost";

    private final int port;

    private final String host;
    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;
    private String username;
    public Network() {
        this(SERVER_HOST, SERVER_PORT);
    }

    public Network(String serverHost, int serverPort) {
        this.host = serverHost;
        this.port = serverPort;
    }

    public boolean connect() {
        try {
            socket = new Socket(host, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            return true;
        } catch (IOException e) {
            System.out.println("Соединение не было установлено");
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

    public void waitMessage(ChatController chatController) {
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    String message = in.readUTF();

                    if(message.startsWith(CLIENT_MSG_PREFIX)) {
                        String[] parts = message.split("\\s+", 3);
                        String sender = parts[1];
                        String msgBody = parts[2];
                        // Вежливое изменение потока.. Как только процесс с "Юай" с может изменять сообщения
                        // - мы сразу это и сделаем runLater()
                        Platform.runLater(() -> chatController.appendMessage(String.format("%s: %s", sender, msgBody)));
                    }
                    else if(message.startsWith(SERVER_MSG_PREFIX)) {
                        String[] parts = message.split("\\s+", 2);
                        // Если сообщение серверное, то в вывод передаём текст серверного сообщения parts[1]
                        Platform.runLater(() -> chatController.appendMessage(parts[1]));
                    }
                    else {
                        // Если пришла непонятная хрень, формируем с ней модалку (в оригинальном переводе: "Неизвестная команда")
                        Platform.runLater(() -> NetworkClient.showErrorMessage("Непонятная хрень", message, ""));
                    }
                    }



            }
            catch (IOException e) {
                e.printStackTrace();
                NetworkClient.showErrorMessage("Ошибка подключения", "", e.getMessage());
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public String sendAuthCommand(String login, String password) {
        try {

            sendMessage(String.format("%s %s %s", AUTH_CMD_PREFIX, login, password));
            // Ждём ответ от сервера. Мож пароль не подошёл.. мож ещё что
            // Он ответит, непременно.. Чтобы не произошло. Он ответит...
            String response = in.readUTF();
            if (response.startsWith(AUTHOK_CMD_PREFIX)) {
                // Чтобы сервер не чудил, префиксы в будущем заменятся объектами
                this.username = response.split("\\s+", 2)[1];
                // Ну, если ошибки нет - то и возвращать не чего. Логично, далее null
                return null;
            }
            // Возвращаем ошибку
            return response.split("\\s+", 2)[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public void sendMessage(String message) throws IOException {
        out.writeUTF(message);
    }
}
