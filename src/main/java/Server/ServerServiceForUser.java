package Server;

import Shared.UserResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class ServerServiceForUser implements Runnable{

    // Attributes
    private Socket client;
    private Scanner input;
    private PrintWriter output;
    private UserResponse userResponse;

    // Constructor
    public ServerServiceForUser(Socket client, Scanner input, PrintWriter output, UserResponse userResponse) {
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
            if(!input.hasNext())
                return;
            String command = input.nextLine();
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
        }
        output.println(answer);
        output.flush();
    }
}
