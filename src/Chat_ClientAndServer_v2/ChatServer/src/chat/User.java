package Chat_ClientAndServer_v2.ChatServer.src.chat;

public class User {

    private final String login;
    private final String password;
    private final String username;

    // Юзеров хорошо бы хранить в базе данных, и тягать оттуда..
    // либо хэшировать - для работы поисковика (при большом приложении)

    public User(String login, String password, String username) {
        this.login = login;
        this.password = password;
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    /*1:13:05 код @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        ...
    }*/

}
