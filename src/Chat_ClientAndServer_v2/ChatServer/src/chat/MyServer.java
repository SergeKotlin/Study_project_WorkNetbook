package Chat_ClientAndServer_v2.ChatServer.src.chat;

import Chat_ClientAndServer_v2.ChatServer.src.chat.auth.AuthService;
import Chat_ClientAndServer_v2.ChatServer.src.chat.auth.BaseAuthService;
import Chat_ClientAndServer_v2.ChatServer.src.chat.handler.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {

    private final ServerSocket serverSocket;
    private final AuthService authService;
    private final List<ClientHandler> clients = new ArrayList<>();

    public MyServer(int port) throws IOException {
        // Ошибка ввода-вывода, знакома. Будем обрабатывать в ServerApp, согласно SOLID#1

        // Запускается сервис - сразу есть сокет и подключенный сервис аутентификации:
        this.serverSocket = new ServerSocket(port);
        this.authService = new BaseAuthService();
    }

    // Основная работа в этом методе. Создаёт пользователей (личности-handler'ов)

    public void start() throws IOException {

        System.out.println("Сервер запущен! Поехали");
        authService.start();

        // Ждёмсъ подключения кого-та..
        try {
            while (true) {
                waitAndProcessNewClientConnection();
            }
        } catch (IOException e) {
            System.out.println("Ошибка создания нового подключения");
            e.printStackTrace();
        }
        finally {
            // И тут м.б ошибка.. Ну и фиг с ней, прокинем просто наверх. Это проблемы* ServerApp =)
            // (*что запускает сервер с неправильными значениями портов.. =/)
            serverSocket.close();
        }
    }
    public void waitAndProcessNewClientConnection() throws IOException {
        System.out.println("Ждёмсъ подключения (какого-то пользователя)..");
        // Socket тоже может выкинуть ошибку ввода-вывода.. Что мы делаем? Нет.. здесь обработаем,
        // чтобы закрыть потом этот Socket
        Socket clientSocket = serverSocket.accept();
        System.out.println("Клиент подкючился! (Дождались =)");
        // Реагируем на ново-подключенных клиентов - путём создания нового handler'а
        processClientConnection(clientSocket);
        // <- Socket мы только что получили от клиента, и прокидываем его дальше - тому,
        // кто создаст отдельный поток нашему клиенту

    }

    private void processClientConnection(Socket clientSocket) throws IOException {
        // Передаём socket - и handler сможет обратиться обратно к MyServer с юзерами и аутентификациями
        // MyServer - посредник handler и аутентификации
        ClientHandler clientHandler = new ClientHandler(this, clientSocket);
        // Логика подключения, создание потоков. (Ошибку наверх, по цепочке к методу start(), или даже выше =)
        clientHandler.handle();
    }

    public AuthService getAuthService() {
        return authService;
    }

    // Добавление (подписка) пользователя
    public void subscribe(ClientHandler clientHandler) {
        // Наполняем пользоту.. (пользователей)
        clients.add(clientHandler);
    }

    // Отписка!
    public void unSubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

    public boolean isUsernameBusy(String username) {
        for (ClientHandler client : clients) {
            if (client.getUsername().equals(username));
                return true;
        }
        return false;
    }

    public void broadcastMessage(String message, ClientHandler sender, boolean isServerInfoMsg, String DestinationHandler) throws IOException {
        for (ClientHandler client : clients) {
            // Самому себе не отпралвяем, что "мы зашли". Это как бы ясно =|
            if (client.equals(sender)) {
                continue;
            }
            // Следующий метод многофункциональный - будет отправлять "всё на свете":
            // и обычные (пользовательские) сообщения, и серверные (с необходимыми префиксами)
            // (Ошибки - наверх)
            if (DestinationHandler == null) {
                client.sendMessage(isServerInfoMsg ? null : sender.getUsername(), message, null);
            } else if (client.getUsername() == DestinationHandler) {
            // Upgrade для /w
                client.sendMessage(sender.getUsername(), message, DestinationHandler);
            }
        }
    }
}
