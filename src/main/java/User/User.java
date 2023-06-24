package User;

import java.util.UUID;

public class User {

    // Attributes
    private String iD;
    private String username;
    private String password;
    private String emailAddress;

    // private profile pricture;
    // private playlist creates or liked

    /*Constructor*/

    //Constructor For Login
    public User(String iD, String username, String password, String emailAddress) {
        this.iD = iD;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    // Constructor For Signup

    public User(String username, String password, String emailAddress) {
        this.iD = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    // Constructor For Observable
    public User(String username) {
        this.username = username;
    }

    // Getter

    public String getiD() {
        return iD;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

}
