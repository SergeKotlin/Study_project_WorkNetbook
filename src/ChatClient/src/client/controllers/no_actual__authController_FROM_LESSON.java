package ChatClient.src.client.controllers;

import ChatClient.src.client.NetworkClient;
import ChatClient.src.client.models.Network;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class no_actual__authController_FROM_LESSON {

    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;

    // Для авторизации через Network
    private Network network;
    private NetworkClient networkClient;

    // Метод проверки авторизации
    // Тут всё
    @FXML
    public void checkAuth() {
        // Для начала - логин из вьюхи
        String login = loginField.getText();
        String password = passwordField.getText();
        // Пустые поля не зачем отправлять!
        // Спросим у Network (даём данные ему, а он шлёт их на сервер, который проверит их
        // и вышлет в ответ username)
        if (login.isEmpty() || password.isEmpty()) {
            // <- Нет у меня никаких .isBlank()!
            NetworkClient.showErrorMessage("Ошибка авторизации", "Ошибка ввода", "Поля должны быть заполнены");
            return;
        }

        // На случай, если есть какая-то ошибка в авторизации
        String authErrorMessage = network.sendAuthCommand(login, password);
        if (authErrorMessage != null) {
            NetworkClient.showErrorMessage("Ошибка авторизации", "Пароль и логин не соответствуют", authErrorMessage);
        } else {
            // Ну если ровно автризовался.. Вызовём второе окно! И едем дальше.
            // Тогда и текущую вьюху надо закрыть. Сделаем её "модальным окном"
            networkClient.openMainChatWindow();
        }
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public void setNetworkClient(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }
}