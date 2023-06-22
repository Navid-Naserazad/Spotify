package Shared;

import User.User;
import org.json.JSONObject;

import java.io.*;
import java.util.jar.JarOutputStream;

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
        return this.input.readUTF();
    }

    public Boolean checkCurrentPassword(String user_id, String password) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "10");
        jsonObject.put("user_id", user_id);
        jsonObject.put("password", password);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return this.input.readBoolean();
    }

    public void changePassword(String user_id, String password) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "11");
        jsonObject.put("user_id", user_id);
        jsonObject.put("password", password);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        this.input.readUTF();
    }












    public boolean checkIfUserDownloadsSpecificMusic(String user_id, String track_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "13");
        jsonObject.put("user_id", user_id);
        jsonObject.put("track_id", track_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return this.input.readBoolean();
    }









    public void addMusicToFolder(String file_path, String username) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "12");
        jsonObject.put("file_path", file_path);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        int fileNameLen = this.input.readInt();
        byte[] fileNameBytes = new byte[fileNameLen];
        this.input.readFully(fileNameBytes);
        String fileName = new String(fileNameBytes);
        int file_content_len = this.input.readInt();
        byte[] file_content = new byte[file_content_len];
        this.input.readFully(file_content, 0, file_content_len);
        File file = new File("D:\\SBU\\Term 2\\AP\\Assignments\\Spotify\\UserDownloads\\" + username);
        if (!file.exists()) {
            boolean creat = file.mkdir();
        }
        String okFilePath = "D:\\SBU\\Term 2\\AP\\Assignments\\Spotify\\UserDownloads\\" + username + "\\" + fileName ;
        File okFile = new File(okFilePath);
        FileOutputStream fileOutputStream = new FileOutputStream(okFile);
        fileOutputStream.write(file_content);
        fileOutputStream.close();
    }
}
