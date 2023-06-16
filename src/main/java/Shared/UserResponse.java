package Shared;

import User.User;
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
                "password");
        this.statement = connection.createStatement();
    }

    // Public Functions
    public String checkUsernameExist(String username) throws SQLException {
        String sqlCommand = "SELECT count(*) FROM user WHERE username = " + '"' + username + '"';
        ResultSet checkUsernameResult = statement.executeQuery(sqlCommand);
        checkUsernameResult.next();
        int numberCheck = Integer.parseInt(checkUsernameResult.getString("count(*)"));
        boolean answer = numberCheck != 0;
        return String.valueOf(answer);
    }

    public String checkPasswordForLoginOperation(String username, String password) throws SQLException, IOException {
        User user = null;
        String sqlCommand = "SELECT user_id, password, email_address FROM user WHERE username = " + '"' + username + '"';
        ResultSet checkForUserResult = statement.executeQuery(sqlCommand);
        checkForUserResult.next();
        String dbPassword = checkForUserResult.getString("password");
        if (dbPassword.equals(password)) {
            String iD = checkForUserResult.getString("user_id");
            String email_address = checkForUserResult.getString("email_address");
            user = new User(iD, username, password, email_address);
        }
        String jsonData = "";
        if (user != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("iD", user.getiD());
            jsonObject.put("username", user.getUsername());
            jsonObject.put("password", user.getPassword());
            jsonObject.put("email_address", user.getEmailAddress());
            jsonData = jsonObject.toString();
        }
        return jsonData;
    }

    public String addUserToDB(JSONObject jsonObject) throws SQLException {
        String sqlCommand = "INSERT INTO user VALUES (\"" + jsonObject.getString("iD") + "\",\"" +
                jsonObject.getString("username") + "\",\"" + jsonObject.getString("password") + "\",\"" +
                jsonObject.getString("emailAddress") + "\")";
        int addUser = statement.executeUpdate(sqlCommand);
        return "";
    }

    public String numberOfAllMusics() throws SQLException {
        String sqlCommand = "SELECT count(*) FROM music";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        resultSet.next();
        return resultSet.getString(1);
    }

    public String getRow_iMusic(int n) throws SQLException {
        String sqlCommand = "SELECT * FROM music";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        for (int i = 0; i < n; i++) {
            resultSet.next();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", resultSet.getString("title"));
        jsonObject.put("genre", resultSet.getString("genre"));
        jsonObject.put("play_list", resultSet.getString("play_list"));
        jsonObject.put("artist", resultSet.getString("artist"));
        jsonObject.put("duration", resultSet.getTime("duration"));
        return jsonObject.toString();
    }
}
