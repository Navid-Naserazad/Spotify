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
        this.output.writeUTF(String.valueOf(answer));
        this.output.flush();
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
        this.output.writeUTF(jsonData);
        this.output.flush();
    }

    public void addUserToDB(JSONObject jsonObject) throws SQLException, IOException {
        String sqlCommand = "INSERT INTO user VALUES (\"" + jsonObject.getString("iD") + "\",\"" +
                jsonObject.getString("username") + "\",\"" + jsonObject.getString("password") + "\",\"" +
                jsonObject.getString("emailAddress") + "\")";
        int addUser = statement.executeUpdate(sqlCommand);
        this.output.writeUTF("");
        this.output.flush();
    }

    public void numberOfAllMusics() throws SQLException, IOException {
        String sqlCommand = "SELECT count(*) FROM music";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        resultSet.next();
        this.output.writeUTF(resultSet.getString(1));
        this.output.flush();
    }

    public void getRow_iMusic(int n) throws SQLException, IOException {
        String sqlCommand_1 = "SELECT * FROM music ORDER BY music.title ASC";
        ResultSet resultSet1 = statement.executeQuery(sqlCommand_1);
        for (int i = 0; i < n; i++) {
            resultSet1.next();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("track_id", resultSet1.getString("track_id"));
        jsonObject.put("title", resultSet1.getString("title"));
        jsonObject.put("genre", resultSet1.getString("genre"));
        jsonObject.put("album", resultSet1.getString("album"));
        jsonObject.put("duration", resultSet1.getTime("duration"));
        String track_id = resultSet1.getString(1);
        String sqlCommand_2 = "SELECT count(*) FROM music_artists WHERE track_id = '" + track_id + "'";
        ResultSet resultSet2 = statement.executeQuery(sqlCommand_2);
        resultSet2.next();
        int rows = resultSet2.getInt(1);
        System.out.println(rows);
        String sqlCommand_3 = "SELECT artist.name FROM music_artists ,artist " +
                "WHERE music_artists.track_id = '" + track_id + "'" +
                "AND music_artists.artist_id = artist.artist_id";
        ResultSet resultSet3 = statement.executeQuery(sqlCommand_3);
        String artists = null;
        resultSet3.next();
        for (int i = 0; i < rows; i++) {
            if (i == rows - 1) {
                artists = artists + resultSet3.getString(1);
            }
            else {
                artists = artists + resultSet3.getString(1) + '-';
            }
            resultSet3.next();
        }
        jsonObject.put("artist", artists);
        this.output.writeUTF(jsonObject.toString());
        this.output.flush();
    }

    public void numberOfAllArtists() throws SQLException, IOException {
        String sqlCommand = "SELECT count(*) FROM artist";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        resultSet.next();
        this.output.writeUTF(resultSet.getString(1));
        this.output.flush();
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
        this.output.writeUTF(jsonObject.toString());
        this.output.flush();
    }

    public void numberOfAllUsers() throws SQLException, IOException {
        String sqlCommand = "SELECT count(*) FROM user";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        resultSet.next();
        this.output.writeUTF(resultSet.getString(1));
        this.output.flush();
    }

    public void getRow_iUser(int n) throws SQLException, IOException {
        String sqlCommand = "SELECT username FROM user";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        for (int i = 0; i < n; i++) {
            resultSet.next();
        }
        this.output.writeUTF(resultSet.getString("username"));
        this.output.flush();
    }

    public void checkCurrentPassword(String user_id, String password) throws SQLException, IOException {
        String sqlCommand = "SELECT count(*) FROM user WHERE user_id = '" + user_id + "' AND password = '" + password + "'";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        resultSet.next();
        this.output.writeBoolean(resultSet.getInt(1) != 0);
        this.output.flush();
    }

    public void changePassword(String user_id, String password) throws SQLException, IOException {
        String sqlCommand = "UPDATE user SET password = '" + password + "' WHERE user_id = '" + user_id + "'";
        int result = statement.executeUpdate(sqlCommand);
        this.output.writeUTF("");
        this.output.flush();
    }

    public void checkCurrentEmail(String user_id, String email) throws SQLException, IOException {
        String sqlCommand = "SELECT count(*) FROM user WHERE user_id = '" + user_id + "' AND email_address = '" + email + "'";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        resultSet.next();
        this.output.writeBoolean(resultSet.getInt(1) != 0);
        this.output.flush();
    }

    public void changeEmail(String user_id, String email) throws SQLException, IOException {
        String sqlCommand = "UPDATE user SET email_address = '" + email + "' WHERE user_id = '" + user_id + "'";
        int result = statement.executeUpdate(sqlCommand);
        this.output.writeUTF("");
        this.output.flush();
    }

    public void numberOfAllPlayListForSpecificUser(String user_id) throws SQLException, IOException {
        String sqlCommand = "SELECT count(*) FROM play_list WHERE user_id = '" + user_id + "'";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        resultSet.next();
        this.output.writeInt(resultSet.getInt(1));
        this.output.flush();
    }

    public void getRow_iPlayList(int n, String user_id) throws SQLException, IOException {
        String sqlCommand = "SELECT * FROM play_list WHERE user_id = '" + user_id + "'" + " ORDER BY title ASC";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        for (int i = 0; i < n; i++) {
            resultSet.next();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("playlist_id", resultSet.getString("playlist_id"));
        jsonObject.put("title", resultSet.getString("title"));
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
    }

    public void numberOfAllMusicsForSpecificPlayList(String playListId) throws SQLException, IOException {
        String sqlCommand = "SELECT count(*) FROM playlist_musics WHERE playlist_id = '" + playListId + "'";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        resultSet.next();
        this.output.writeInt(resultSet.getInt(1));
        this.output.flush();
    }

    public void getRow_iMusicFromPlayList(int n, String playListId) throws SQLException, IOException {
        String sqlCommand_1 = "SELECT * FROM playlist_musics WHERE playlist_id = '" + playListId + "'";
        ResultSet resultSet1 = statement.executeQuery(sqlCommand_1);
        for (int i = 1; i <= n; i++) {
            resultSet1.next();
        }
        String track_id = resultSet1.getString(2);
        String sqlCommand_2 = "SELECT * FROM music WHERE track_id = '" + track_id + "'";
        ResultSet resultSet2 = statement.executeQuery(sqlCommand_2);
        resultSet2.next();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", resultSet2.getString("title"));
        jsonObject.put("genre", resultSet2.getString("genre"));
        jsonObject.put("album", resultSet2.getString("album"));
        jsonObject.put("duration", resultSet2.getTime("duration"));
        String sqlCommand_3 = "SELECT count(*) FROM music_artists WHERE track_id = '" + track_id + "'";
        ResultSet resultSet3 = statement.executeQuery(sqlCommand_3);
        resultSet3.next();
        int rows = resultSet3.getInt(1);
        System.out.println(rows);
        String sqlCommand3 = "SELECT artist.name FROM music_artists ,artist " +
                "WHERE music_artists.track_id = '" + track_id + "'" +
                "AND music_artists.artist_id = artist.artist_id";
        ResultSet resultSet4 = statement.executeQuery(sqlCommand3);
        String artists = null;
        resultSet4.next();
        for (int i = 0; i < rows; i++) {
            if (i == rows - 1) {
                artists = artists + resultSet4.getString(1);
            }
            else {
                artists = artists + resultSet4.getString(1) + '-';
            }
            resultSet4.next();
        }
        jsonObject.put("artist", artists);
        this.output.writeUTF(jsonObject.toString());
        this.output.flush();
    }

    public void checkIfUserDownloadsSpecificMusic(String user_id, String track_id) throws SQLException, IOException {
        String sqlCommand = "  SELECT count(*) FROM music_downloads WHERE user_id = '" + user_id +
                "' AND track_id = '" + track_id + "'";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        resultSet.next();
        output.writeBoolean(resultSet.getInt(1) != 0);
        output.flush();
    }

    public void downloadTheMusic(String track_id) throws SQLException, IOException {
        String sqlCommand = "SELECT file_path FROM music WHERE track_id = '" + track_id + "'";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        resultSet.next();
        String file_path = resultSet.getString(1);
        File file = new File("D:\\SBU\\Term 2\\AP\\Assignments\\Spotify\\src\\main\\resources\\AllMusics\\" + file_path + ".mp3");
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

    public void getMusicAddress(String track_id) throws SQLException, IOException {
        String sqlCommand = "SELECT file_path FROM music WHERE track_id = '" + track_id + "'";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        resultSet.next();
        String file_path = resultSet.getString(1);
        String answer = "D:\\SBU\\Term 2\\AP\\Assignments\\Spotify\\src\\main\\resources\\AllMusics\\" + file_path + ".mp3";
        this.output.writeUTF(answer);
    }

    public void checkUserLike(String user_id, String track_id) throws IOException, SQLException {
        String sqlCommand = "SELECT count(*) from liked WHERE user_id = '" + user_id + "' AND track_id = '" +
                track_id + "'";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        resultSet.next();
        this.output.writeBoolean(resultSet.getInt(1) != 0);
        this.output.flush();
    }

    public void addLike(String user_id, String track_id) throws SQLException, IOException {
        String sqlCommand = "INSERT INTO liked VALUES ('" + user_id + "','" + track_id + "')";
        int result = statement.executeUpdate(sqlCommand);
        this.output.writeUTF("");
        this.output.flush();
    }

    public void addDisLike(String user_id, String track_id) throws SQLException, IOException {
        String sqlCommand = "DELETE FROM liked WHERE user_id = '" + user_id + "' AND track_id = '" +
                track_id + "'";
        int result = statement.executeUpdate(sqlCommand);
        this.output.writeUTF("");
        this.output.flush();
    }

    public void addDownload(String user_id, String track_id) throws IOException, SQLException {
        String sqlCommand = "INSERT INTO music_downloads VALUES ('" + user_id + "','" + track_id + "')";
        int result = statement.executeUpdate(sqlCommand);
        this.output.writeUTF("");
        this.output.flush();
    }

    public void checkFollowUser(String user_id_1, String user_id_2) throws SQLException, IOException {
        String sqlCommand = "SELECT count(*) FROM user_user_follow WHERE user_id_1 = '" + user_id_1 + "' AND user_id_2 = '" +
                user_id_2 + "'";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        resultSet.next();
        this.output.writeBoolean(resultSet.getInt(1) != 0);
        this.output.flush();
    }

    public void addFollowUser(String user_id_1, String user_id_2) throws SQLException, IOException {
        String sqlCommand = "INSERT INTO user_user_follow VALUES (" + user_id_1 + ',' + user_id_2 + ')';
        int result = statement.executeUpdate(sqlCommand);
        this.output.writeUTF("");
        this.output.flush();
    }

    public void numberOfFollowings_UserToUser(String user_id) throws SQLException, IOException {
        String sqlCommand = "SELECT count(*) FROM user_user_follow WHERE user_id_1 = '" + user_id + "'";
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        resultSet.next();
        this.output.writeInt(resultSet.getInt(1));
        this.output.flush();
    }

    public void getRow_i_UsernameOfUserToUserFollowings(int n, String user_id) throws SQLException, IOException {
        String sqlCommand1 = "SELECT user_id_2 FROM user_user_follow WHERE user_id_1 = '" + user_id + "'" +
                "ORDER BY ASC";
        ResultSet resultSet1 = statement.executeQuery(sqlCommand1);
        for (int i = 0; i < n; i++) {
            resultSet1.next();
        }
        String user_id_2 = resultSet1.getString(1);
        String sqlCommand2 = "SELECT username FROM user WHERE user_id = '" + user_id_2 + "'";
        ResultSet resultSet2 = statement.executeQuery(sqlCommand2);
        resultSet2.next();
        this.output.writeUTF(resultSet2.getString(1));
        this.output.flush();
    }













    // Setter

    public void setOutput(DataOutputStream output) {
        this.output = output;
    }
}
