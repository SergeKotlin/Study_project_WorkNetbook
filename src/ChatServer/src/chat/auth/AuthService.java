package ChatServer.src.chat.auth;

public interface AuthService {
    // Сервис Аутентификации

    // Запуск аутентификации (+ подключение к БД, и уничтожение подключения в close() )
    void start();

    // Метод валидации, верно ли входим или нет
    // Возвращает UserName, если всё хорошо
    String getUserNameByLoginAndPassword(String login, String password);

    // Закрыть аутентификацию
    void close();
}
