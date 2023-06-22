package Shared;

import User.User;
import org.json.JSONObject;

import java.io.*;

public class UserRequest {

    // Attributes
    private DataInputStream input;
    private DataOutputStream output;

    public UserRequest(DataInputStream input, DataOutputStream output) {
        this.input = input;
        this.output = output;
    }

    // Exit From App
    public void exitFromApp() {
        System.exit(1);
    }

    // Public Functions

    public void sendUserPosition() throws IOException {
        output.writeUTF("2");
        output.flush();
    }

    public boolean usernameCheckingRequest(String username) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "1");
        jsonObject.put("username", username);
        String jsonCommand = jsonObject.toString();
        output.writeUTF(jsonCommand);
        output.flush();
        String answer = input.readUTF();
        return Boolean.parseBoolean(answer);
    }

    public User checkPasswordForLoginOperation (String username, String password) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "2");
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        String jsonCommand = jsonObject.toString();
        output.writeUTF(jsonCommand);
        output.flush();
        String answer = input.readUTF();
        if (answer.equals("")) {
            return null;
        }
        else {
            JSONObject getUser = new JSONObject(answer);
            return new User(getUser.getString("iD"), getUser.getString("username")
                    , getUser.getString("password"), getUser.getString("email_address"));
        }
    }

    public void addClientToDB(User user) throws IOException {
        JSONObject userJson = new JSONObject();
        userJson.put("iD", user.getiD());
        userJson.put("username", user.getUsername());
        userJson.put("password", user.getPassword());
        userJson.put("emailAddress", user.getEmailAddress());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "3");
        jsonObject.put("client", userJson);
        String jsonCommand = jsonObject.toString();
        output.writeUTF(jsonCommand);
        output.flush();
        input.readUTF();
    }

    public int numberOfAllMusics() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "4");
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return Integer.parseInt(this.input.readUTF());
    }

    public JSONObject getRow_iMusic(int i) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "5");
        jsonObject.put("row", i);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return new JSONObject(this.input.readUTF());
    }

    public int numberOfAllArtist() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "6");
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return Integer.parseInt(this.input.readUTF());
    }

    public JSONObject getRow_iArtist(int i) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "7");
        jsonObject.put("row", i);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return new JSONObject(this.input.readUTF());
    }

    public int numberOfAllUsers() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "8");
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return Integer.parseInt(this.input.readUTF());
    }

    public String getRow_iUser(int i) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "9");
        jsonObject.put("row", i);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        ;return this.input.readUTF();
    }
}
