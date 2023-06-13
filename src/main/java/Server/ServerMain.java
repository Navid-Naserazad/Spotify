package Server;

import Shared.ArtistResponse;
import Shared.UserResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class ServerMain {
    public static void main(String[] args) throws IOException, SQLException {
        final int SBAP_PORT = 6666;
        ServerSocket serverSocket = new ServerSocket(SBAP_PORT);
        System.out.println("Waiting for user to connect...");
        while (true) {
            Socket myUser = serverSocket.accept();
            Scanner input = new Scanner(myUser.getInputStream());
            PrintWriter output = new PrintWriter(myUser.getOutputStream());
            String position = input.nextLine();
            switch (position) {
                case "1":
                    System.out.println("Artist Connected.");
                    ArtistResponse artistResponse = new ArtistResponse();
                    ServerServiceForArtist serverServiceForArtist = new ServerServiceForArtist(myUser, input,
                            output, artistResponse);
                    Thread artistThread = new Thread(serverServiceForArtist);
                    artistThread.start();
                    break;
                case "2":
                    System.out.println("Client Connected.");
                    UserResponse clientResponse = new UserResponse();
                    ServerServiceForUser serverServiceForUser = new ServerServiceForUser(myUser, input,
                            output, clientResponse);
                    Thread clientThread = new Thread(serverServiceForUser);
                    clientThread.start();
                    break;
            }
        }
    }
}
