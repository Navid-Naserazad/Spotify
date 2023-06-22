package Shared;

import User.User;
import org.json.JSONObject;

import java.io.*;
import java.sql.*;

public class UserResponse {

    // Attributes

    Connection connection;
    Statement statement;
    DataOutputStream output;
    // Constructor

    public UserResponse() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spotify",
                "root",
                "password");
        this.statement = connection.createStatement();
    }

    // Public Functions
    public void checkUsernameExist(String username) throws SQLException, IOException {
        String sqlCommand = "SELECT count(*) FROM user WHERE username = " + '"' + username + '"';
        ResultSet checkUsernameResult = statement.executeQuery(sqlCommand);
        checkUsernameResult.next();
        int numberCheck = Integer.parseInt(checkUsernameResult.getString("count(*)"));
        boolean answer = numberCheck != 0;
        output.writeUTF(String.valueOf(answer));
        output.flush();
    }

    public void checkPasswordForLoginOperation(String username, String password) throws SQLException, IOException {
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
        output.writeUTF(jsonData);
        output.flush();
    }

    public void addUserToDB(JSONObject jsonObject) throws SQLException, IOException {
        String sqlCommand = "INSERT INTO user VALUES (\"" + jsonObject.getString("iD") + "\",\"" +
                jsonObject.getString("username") + "\",\"" + jsonObject.getString("password") + "\",\"" +
                jsonObject.getString("emailAddress") + "\")";
        int addUser = statement.executeUpdate(sqlCommand);
        output.writeUTF("");
        output.flush();
    }

    public void numberOfAllMusics() throws SQLException, IOException {
        String sqlCommand = "SELECT count(*) FROM music";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        resultSet.next();
        output.writeUTF(resultSet.getString(1));
        output.flush();
    }

    public void getRow_iMusic(int n) throws SQLException, IOException {
        String sqlCommand = "SELECT * FROM music";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        for (int i = 0; i < n; i++) {
            resultSet.next();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", resultSet.getString("title"));
        jsonObject.put("genre", resultSet.getString("genre"));
        jsonObject.put("album", resultSet.getString("album"));
        jsonObject.put("artist", resultSet.getString("artist"));
        jsonObject.put("duration", resultSet.getTime("duration"));
        output.writeUTF(jsonObject.toString());
        output.flush();
    }

    public void numberOfAllArtists() throws SQLException, IOException {
        String sqlCommand = "SELECT count(*) FROM artist";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        resultSet.next();
        output.writeUTF(resultSet.getString(1));
        output.flush();
    }

    public void getRow_iArtist(int n) throws SQLException, IOException {
        String sqlCommand = "SELECT name, biography FROM artist";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        for (int i = 0; i < n; i++) {
            resultSet.next();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", resultSet.getString("name"));
        jsonObject.put("biography", resultSet.getString("biography"));
        output.writeUTF(jsonObject.toString());
        output.flush();
    }

    public void numberOfAllUsers() throws SQLException, IOException {
        String sqlCommand = "SELECT count(*) FROM user";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        resultSet.next();
        output.writeUTF(resultSet.getString(1));
        output.flush();
    }

    public void getRow_iUser(int n) throws SQLException, IOException {
        String sqlCommand = "SELECT username FROM user";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        for (int i = 0; i < n; i++) {
            resultSet.next();
        }
        output.writeUTF(resultSet.getString("username"));
        output.flush();
    }

    public void downloadTheMusic(String track_id) throws SQLException, IOException {
        String sqlCommand = "SELECT file_path FROM music WHERE track_id = '" + track_id + "'";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        resultSet.next();
        String file_path = resultSet.getString(1);
        File file = new File("D:\\SBU\\Term 2\\AP\\Assignments\\Spotify\\AllMusics\\" + file_path + "\\.mp3");
        FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
        String fileName = file.getName();
        byte[] fileNameBytes = fileName.getBytes();
        byte[] file_content = new byte[(int) file.length()];
        fileInputStream.read(file_content);
        output.writeInt(fileNameBytes.length);
        output.write(fileNameBytes);
        output.writeInt(file_content.length);
        output.write(file_content);
        output.flush();
        fileInputStream.close();
    }

















    // Setter

    public void setOutput(DataOutputStream output) {
        this.output = output;
    }
}
