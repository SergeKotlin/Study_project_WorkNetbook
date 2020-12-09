package Chat_ClientAndServer_v2.ChatServer.src.chat.auth;

import Chat_ClientAndServer_v2.ChatServer.src.chat.User;

import java.util.Arrays;
import java.util.List;

public class BaseAuthService implements AuthService {

    // Создадим пользоту! (Пользователей)
    public static final List<User> clients = Arrays.asList(
        // Нет у меня "List.of()"!
        new User("user1", "1111", "Борис_Николаевич"),
        new User("user2", "2222", "Мартин_Некотятский"),
        new User("user3", "3333", "Гендальф_Белый"),
        // +
        new User("admin", "1234", "Знаток")
    );

    @Override
    public void start() {
        System.out.println("С Вами общается BaseAuthService. " +
                "Сервис аутентификации запущен..");
    }

    @Override
    public String getUserNameByLoginAndPassword(String login, String password) {
        for (User client : clients) {
            // Если принятое от пользователя соответствует..
            if (client.getLogin().equals(login) && client.getPassword().equals(password)) {
                return client.getUsername();
            }
         }
        return null;
    }

    @Override
    public void close() {
        System.out.println("С Вами общается BaseAuthService. " +
                "Сервис аутентификации завершён!");
    }
}
