package Shared;

import User.User;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class UserRequest {

    // Attributes
    private Scanner input;
    private PrintWriter output;

    public UserRequest(Scanner input, PrintWriter output) {
        this.output = output;
        this.input = input;
    }

    // Exit From App
    public void exitFromApp() {
        System.exit(1);
    }

    // Public Functions

    public boolean usernameCheckingRequest(String username) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "1");
        jsonObject.put("username", username);
        String jsonCommand = jsonObject.toString();
        output.println(jsonCommand);
        output.flush();
        String answer = input.nextLine();
        return Boolean.parseBoolean(answer);
    }

    public User checkPasswordForLoginOperation (String username, String password) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "2");
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        String jsonCommand = jsonObject.toString();
        output.println(jsonCommand);
        output.flush();
        String answer = input.nextLine();
        if (answer.equals("")) {
            return null;
        }
        else {
            JSONObject getUser = new JSONObject(answer);
            return new User(getUser.getString("iD"), getUser.getString("username")
                    , getUser.getString("password"), getUser.getString("email_address"));
        }
    }

    public void addClientToDB(User user) {
        JSONObject userJson = new JSONObject();
        userJson.put("iD", user.getiD());
        userJson.put("username", user.getUsername());
        userJson.put("password", user.getPassword());
        userJson.put("emailAddress", user.getEmailAddress());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "3");
        jsonObject.put("client", userJson);
        String jsonCommand = jsonObject.toString();
        output.println(jsonCommand);
        output.flush();
        input.nextLine();
    }

    public int numberOFAllMusics() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "4");
        String jsonCommand = jsonObject.toString();
        this.output.println(jsonCommand);
        this.output.flush();
        return Integer.parseInt(this.input.nextLine());
    }

    public JSONObject getRow_iMusic(int i) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "5");
        jsonObject.put("row", i);
        String jsonCommand = jsonObject.toString();
        this.output.println(jsonCommand);
        this.output.flush();
        return new JSONObject(this.input.nextLine());
    }
}
