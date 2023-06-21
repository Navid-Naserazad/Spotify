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
        String answer = null;
        switch (number) {
            case 1:
                answer = userResponse.checkUsernameExist(jsonObject.getString("username"));
                break;
            case 2:
                answer = userResponse.checkPasswordForLoginOperation(jsonObject.getString("username"),
                        jsonObject.getString("password"));
                break;
            case 3:
                answer = userResponse.addUserToDB(jsonObject.getJSONObject("client"));
                break;
            case 4:
                answer = userResponse.numberOfAllMusics();
                break;
            case 5:
                answer = userResponse.getRow_iMusic(jsonObject.getInt("row"));
                break;
            case 6:
                answer = userResponse.numberOfAllArtists();
                break;
            case 7:
                answer = userResponse.getRow_iArtist(jsonObject.getInt("row"));
                break;
        }
        output.writeUTF(answer);
        output.flush();
    }
}
