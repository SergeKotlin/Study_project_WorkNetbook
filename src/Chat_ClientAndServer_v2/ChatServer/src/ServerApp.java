package Chat_ClientAndServer_v2.ChatServer.src;

import Chat_ClientAndServer_v2.ChatServer.src.chat.MyServer;

import java.io.IOException;

public class ServerApp {
    // Отвечает за запуск сервера

    private static final int DEFAULT_PORT = 8189;
    // А вдруг, тот занят остался.. Но прописано не только здесь. Ещё в Network, как minimum
    // private static final int DEFAULT_PORT = 8190;

    public static void main(String[] args) {
        // Поднятие сервера разом
        int port = DEFAULT_PORT;

        if (args.length != 0 ) {
            port = Integer.parseInt(args[0]);
        }

        // Обработка ошибки ввода-вывода из MyServer
        try {
            new MyServer(port).start();
        } catch (IOException e) {
            e.printStackTrace();
            // Логируем ошибку
            System.out.println("Ашибачка! Пока");
            System.exit(1);
        }
    }
}
