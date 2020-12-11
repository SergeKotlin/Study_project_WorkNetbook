package ChatClient.src.client;


import ChatClient.src.client.controllers.AuthController;
import ChatClient.src.client.controllers.ChatController;
import ChatClient.src.client.models.Network;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class NetworkClient extends Application {

    public static final List<String> USERS_TEST_DATA = Arrays.asList("Борис_Николаевич", "Мартин_Некотятский", "Гендальф_Белый");
    // <- Нет у меня List.of()!
    private Stage primaryStage;
    private Stage authStage;
    private Network network;
    private ChatController chatController;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;

        network = new Network();
        if (!network.connect()) {
            showErrorMessage("Проблемы с соединением", "", "Ошибка подключения к серверу");
            return;
        }

        openAuthWindow();
        createMainChatWindow();
    }

    private void openAuthWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(NetworkClient.class.getResource("views/auth-view.fxml"));
        Parent root = loader.load();
        // Создаём сцену
        authStage = new Stage();

        authStage.setTitle("Авторизация");
        // Авторизация будет модальным окном
        authStage.initModality(Modality.WINDOW_MODAL);
        // К чему крепим эту модалку
        authStage.initOwner(primaryStage);
        // Новая сцена для отображения окна
        Scene scene = new Scene(root);
        authStage.setScene(scene);
        authStage.show();

        //Получаем объект от лоадера
        AuthController authController = loader.getController();
        authController.setNetwork(network);
        authController.setNetworkClient(this);


    }

    public void createMainChatWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(NetworkClient.class.getResource("views/chat-view.fxml"));
        // Отсутствует в VM Options! ХМ --module-path ${PATH_TO_FX} --add-modules javafx.controls,javafx.fxml

        Parent root = loader.load();

        primaryStage.setTitle("Messenger");
        primaryStage.setScene(new Scene(root, 600, 400));

        chatController = loader.getController();
        chatController.setNetwork(network);

        primaryStage.setOnCloseRequest(windowEvent -> network.close());
    }

    public static void showErrorMessage(String title, String message, String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    public void openMainChatWindow() {
        authStage.close();
        primaryStage.show();

        primaryStage.setTitle(network.getUsername());
        primaryStage.setAlwaysOnTop(true);
        chatController.setLabel(network.getUsername());
        network.waitMessage(chatController);
    }

    public static void main(String[] args) {
        launch(args);
    }

}