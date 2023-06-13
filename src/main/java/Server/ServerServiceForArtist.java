package Server;

import Shared.ArtistResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class ServerServiceForArtist implements Runnable{

    // Attributes
    private Socket artist;
    private Scanner input;
    private PrintWriter output;
    private ArtistResponse artistResponse;

    // Constructor
    public ServerServiceForArtist(Socket artist, Scanner input, PrintWriter output, ArtistResponse artistResponse) {
        this.artist = artist;
        this.input = input;
        this.output = output;
        this.artistResponse = artistResponse;
    }

    // Running Sever
    @Override
    public void run() {
        try {
            doService();
            artist.close();
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
            System.out.println(jsonObject);
            int number = Integer.parseInt(jsonObject.getString("number"));
            executeCommand(number, jsonObject);
        }
    }

    public void executeCommand(int number, JSONObject jsonObject) throws SQLException, IOException {
        String answer = null;
        switch (number) {
            case 1:
                answer = artistResponse.checkUsernameExist(jsonObject.getString("name"));
                break;
            case 2:
                answer = artistResponse.checkPasswordForLoginOperation(jsonObject.getString("name"),
                        jsonObject.getString("password"));
                break;
            case 3:
                answer = artistResponse.addArtistToDB(jsonObject.getJSONObject("artist"));
                break;
        }
        output.println(answer);
        output.flush();
    }
}
