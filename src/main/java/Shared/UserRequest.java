package Shared;

import User.User;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public User checkPasswordForLoginOperation (String username, String password) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "2");
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        String jsonCommand = jsonObject.toString();
        output.println(jsonCommand);
        output.flush();
        String answer = input.nextLine();
        return new ObjectMapper().readValue(answer, User.class);
    }

    public String addClientToDB(User user) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(user);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "3");
        jsonObject.put("client", jsonData);
        String jsonCommand = jsonObject.toString();
        output.println(jsonCommand);
        output.flush();
        return input.nextLine();
    }
}
