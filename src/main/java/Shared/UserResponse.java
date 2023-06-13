package Shared;

import User.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.*;

public class UserResponse {

    // Attributes

    Connection connection;
    Statement statement;

    // Constructor

    public UserResponse() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spotify",
                "root",
                "Nariman@1383");
        this.statement = connection.createStatement();
    }

    // Public Functions
    public String checkUsernameExist(String username) throws SQLException {
        String sqlCommand = "SELECT count(*) FROM client WHERE username = " + '"' + username + '"';
        ResultSet checkUsernameResult = statement.executeQuery(sqlCommand);
        checkUsernameResult.next();
        int numberCheck = Integer.parseInt(checkUsernameResult.getString("count(*)"));
        boolean answer = numberCheck != 0;
        return String.valueOf(answer);
    }

    public String checkPasswordForLoginOperation(String username, String password) throws SQLException, IOException {
        User user = null;
        String sqlCommand = "SELECT client_id, password, email_address FROM client WHERE username = " + '"' + username + '"';
        ResultSet checkForClientResult = statement.executeQuery(sqlCommand);
        checkForClientResult.next();
        String dbPassword = checkForClientResult.getString("password");
        if (dbPassword.equals(password)) {
            String id = checkForClientResult.getString("id");
            String email_address = checkForClientResult.getString("email_address");
            user = new User(id, username, dbPassword, email_address);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(user);
        return jsonData;
    }

    public String addClientToDB(JSONObject jsonObject) throws SQLException {
        String sqlCommand = "INSERT INTO client VALUES (\"" + jsonObject.getString("iD") + "\",\"" +
                jsonObject.getString("username") + "\",\"" + jsonObject.getString("password") + "\",\"" +
                jsonObject.getString("emailAddress") + "\")";
        int addClient = statement.executeUpdate(sqlCommand);
        return "";
    }
}
