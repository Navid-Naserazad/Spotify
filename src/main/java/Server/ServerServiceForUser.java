package Server;

import Shared.UserResponse;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class ServerServiceForUser implements Runnable{

    // Attributes
    private Socket client;
    private DataInputStream input;
    private DataOutputStream output;
    private UserResponse userResponse;

    // Constructor
    public ServerServiceForUser(Socket client, DataInputStream input, DataOutputStream output, UserResponse userResponse) {
        this.client = client;
        this.input = input;
        this.output = output;
        this.userResponse = userResponse;
    }

    // Running Menu
    @Override
    public void run() {
        try {
            userResponse.setOutput(output);
            doService();
            client.close();
        }
        catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Public Functions
    public void doService() throws SQLException, IOException {
        while (true) {
            String command = input.readUTF();
            JSONObject jsonObject = new JSONObject(command);
            int number = Integer.parseInt(jsonObject.getString("number"));
            executeCommand(number, jsonObject);
        }
    }

    public void executeCommand(int number, JSONObject jsonObject) throws SQLException, IOException {

        switch (number) {
            case 1:
                this.userResponse.checkUsernameExist(jsonObject.getString("username"));
                break;
            case 2:
                this.userResponse.checkPasswordForLoginOperation(jsonObject.getString("username"),
                        jsonObject.getString("password"));
                break;
            case 3:
                this.userResponse.addUserToDB(jsonObject.getJSONObject("client"));
                break;
            case 4:
                this.userResponse.numberOfAllMusics();
                break;
            case 5:
                this.userResponse.getRow_iMusic(jsonObject.getInt("row"));
                break;
            case 6:
                this.userResponse.numberOfAllArtists();
                break;
            case 7:
                this.userResponse.getRow_iArtist(jsonObject.getInt("row"));
                break;
            case 8:
                this.userResponse.numberOfAllUsers();
                break;
            case 9:
                this.userResponse.getRow_iUser(jsonObject.getInt("row"));
                break;
            case 10:
                this.userResponse.checkCurrentPassword(jsonObject.getString("user_id"),
                        jsonObject.getString("password"));
                break;
            case 11:
                this.userResponse.changePassword(jsonObject.getString("user_id"),
                        jsonObject.getString("password"));
                break;
            case 12:
                this.userResponse.checkCurrentEmail(jsonObject.getString("user_id"), jsonObject.getString("email"));
                break;
            case 13:
                this.userResponse.changeEmail(jsonObject.getString("user_id"), jsonObject.getString("email"));
                break;
            case 14:
                this.userResponse.numberOfAllPlayListForSpecificUser(jsonObject.getString("user_id"));
                break;
            case 15:
                this.userResponse.getRow_iPlayList(jsonObject.getInt("row"), jsonObject.getString("user_id"));
                break;
            case 16:
                this.userResponse.numberOfAllMusicsForSpecificPlayList(jsonObject.getString("playListId"));
                break;
            case 17:
                this.userResponse.getRow_iMusicFromPlayList(jsonObject.getInt("row"), jsonObject.getString("playListId"));
                break;
            case 18:
                this.userResponse.checkIfUserDownloadsSpecificMusic(jsonObject.getString("user_id"),
                        jsonObject.getString("track_id"));
                break;
            case 19:
                this.userResponse.downloadTheMusic(jsonObject.getString("track_id"));
                break;
            case 20:
                this.userResponse.getMusicAddress(jsonObject.getString("track_id"));
                break;
        }
    }
}
