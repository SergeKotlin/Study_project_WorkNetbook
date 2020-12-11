package Chat_ClientAndServer_v2.ChatServer.src.chat.handler;

import Chat_ClientAndServer_v2.ChatServer.src.chat.MyServer;
import Chat_ClientAndServer_v2.ChatServer.src.chat.auth.AuthService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private static final String AUTH_CMD_PREFIX = "/auth";
    private static final String AUTHOK_CMD_PREFIX = "/authok";
    private static final String AUTHERR_CMD_PREFIX = "/autherr";
    private static final String PRIVATE_MSG_PREFIX = "/w";
    private static final String CLIENT_MSG_PREFIX = "/clientMsg";
    private static final String SERVER_MSG_PREFIX = "/serverMsg";
    private static final String END_CMD = "/end";

    private final MyServer myServer;
    private final Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private String username;

    public ClientHandler(MyServer myServer, Socket clientSocket) {
        // ClieHan, вот, ты создался. Что ты должен делать?
        // Ну, заполни myServer, clientSocket - для местных методов пригодится
        this.myServer = myServer;
        this.clientSocket = clientSocket;
    }

    public void handle() throws IOException {
        // Ошибку прокидываем вверх - нафиг здесь не нужна
        // Socket вытаскивает сам из себя in & out
        in = new DataInputStream(clientSocket.getInputStream());
        out = new DataOutputStream(clientSocket.getOutputStream());
        // Можно начинать взаимодействие =)

        new Thread(() -> {
            try {
                // Аутентификация
                authentication();
                // ожидание и чтение сообщений
                readMessage();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }).start();
    }

    // Main magic.. (основная магия)

    private void authentication() throws IOException {
        // Нужно получить имя и пароль (Ошибку - наверх! Обработаем в точке вызова метода)
        String message = in.readUTF();
        // Ща будет реализована система.. С сообщением будет посылать флаг-сноску "принадлежности" сообщения
        // Специальные префиксы! (/w (т.е whisper) for Home Work)
        // Например "/auth **login** **password**"

        // Зацикливаем авторизацию пользователя. Вход только приглашённым..
        while (true) {
            if (message.startsWith(AUTH_CMD_PREFIX)) {
                // Наш случай? Распарсим в массив
                String[] parts = message.split("\\s+", 3);
                // <- эта магическая строка означает: ожидаем один или несколько пробелов
                // и делим сообщение на 3 части
                // String[] parts = message.split(" ", 3); - разделение по пробелам. "\\s" - символ пробела, а плюсик - м.б не один
                String login = parts[1];
                String password = parts[2];

                // Обращаемся к сервису авторизации, передаём логин, принятый у клиента
                AuthService authService = myServer.getAuthService();
                username = authService.getUserNameByLoginAndPassword(login, password);
                if (username != null) {
                    // Никнейм не занят (если пользователь уже в сети)
                    if (myServer.isUsernameBusy(username)) {
                        out.writeUTF(String.format("%s %s", AUTHERR_CMD_PREFIX, "Логин уже используется. 102 Police"));
                    }

                    // Отправим на клиент никнейм
                    out.writeUTF(String.format("%s %s", AUTHOK_CMD_PREFIX, username));
                    // <- Отлично. Пользователь справился с авторизацией, выведен из её цикла и допущен к следующим этапам..

                    // Оповестим всех о подключение новичка
                    myServer.broadcastMessage(String.format(">>> %s среди нас, успешно подключился", username), this, true, null);
                    // <- в this передаём Handler, чтобры сравнивать с другими и исключать отправку сообщения текущему пользователю
                    // <- true - флаг, серверное сообщение либо же нет

                    // Зарегестрировать клиента
                    myServer.subscribe(this);
                    break;
                    // Всё хорошо, авторизация окончена
                } else {
                    out.writeUTF(String.format("%s %s", AUTHERR_CMD_PREFIX, "Логин и пароль не верны!"));
                }
            } else {
                out.writeUTF(String.format("%s %s", AUTHERR_CMD_PREFIX, "Ошибка авторизации"));
            }

        // В дальнейшем вместо префиксов будем передавать объекты, сделаем целый слой с ними
//      // через который будем общаться с клиентом и сервером, сериализовать
        }
    }

    private void readMessage() throws IOException {
        // Поток отдельный от всего сервера, не боимся!
        while (true) {
//            System.out.println("log (readMessage)");
            String message = in.readUTF();
            // Логирование в консоли сервера
            System.out.println("message | " + username + ": " + message);

            if (message.startsWith(END_CMD)) {
                return;
            } else if (message.startsWith(PRIVATE_MSG_PREFIX)) {
            // Ветка для варианта приватных сообщений /w
                //TODO
                String[] parts = message.split("\\s+", 3);
                String destinationHandler = parts[1];
                String msgBody = parts[2];
                //Platform.runLater(() -> chatController.appendMessage(String.format("%s -> %s: %s", sender, destinationHandler, msgBody)));
                myServer.broadcastMessage(msgBody, this, false, destinationHandler);
            } else {
                myServer.broadcastMessage(message, this, false, null);
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public void sendMessage(String sender, String message, String DestinationHandler) throws IOException {
        if (sender == null) {
            out.writeUTF(String.format("%s %s", SERVER_MSG_PREFIX, message));
        } else {
            // Если сообщение не сервное, значит - кто-то написал в чат
            // (Ошибки - наверх! Тут не нужны)
            if (DestinationHandler == null) {
                out.writeUTF(String.format("%s %s %s", CLIENT_MSG_PREFIX, sender, message));
            } else {
            // Upgrade для /w
                out.writeUTF(String.format("%s %s %s (Приватно: %s)", PRIVATE_MSG_PREFIX, sender, message, DestinationHandler));
            }
        }
    }

    // Upgrade для отправки личные сообщений /w
//    public void sendMessage(String sender, String message, String destinationHandler) {
//    }
    // И на Network тоже понадобится соответствующий метод
}
