package ChatClient.src.client.controllers;

import ChatClient.src.client.NetworkClient;
import ChatClient.src.client.models.Network;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class ChatController {

    @FXML
    public ListView<String> usersList;

    @FXML
    private Button sendButton;
    @FXML
    private TextArea chatHistory;
    @FXML
    private TextField textField;
    @FXML
    private Label usernameTitle;

    private Network network;

    public void setLabel(String usernameTitle) {
        this.usernameTitle.setText(usernameTitle);
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    @FXML
    public void initialize() {
        usersList.setItems(FXCollections.observableArrayList(NetworkClient.USERS_TEST_DATA));
        sendButton.setOnAction(event -> ChatController.this.sendMessage());
        textField.setOnAction(event -> ChatController.this.sendMessage());
    }

    private void sendMessage() {
        String message = textField.getText();
        appendMessage("Me : " + message);
        textField.clear();

        try {
            // Предыдущая версия, напрямую network.getOut().writeUTF(message);
            network.sendMessage(message);

        } catch (IOException e) {
            e.printStackTrace();
            NetworkClient.showErrorMessage("Ошибка подключения", "Ошибка при отправке сообщения", e.getMessage());
        }

    }

    public void appendMessage(String message) {
        String timestamp = DateFormat.getInstance().format(new Date());
        chatHistory.appendText(timestamp);
        chatHistory.appendText(System.lineSeparator());
        chatHistory.appendText(message);
        chatHistory.appendText(System.lineSeparator());
        chatHistory.appendText(System.lineSeparator());
    }

    public void setUsernameTitle(String username) {

    }
}