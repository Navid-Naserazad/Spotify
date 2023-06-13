package Shared;

import Artist.Artist;
import Artist.ArtistQuestion;
import User.User;
import User.UserQuestion;

import java.io.IOException;
import java.util.Scanner;

public class SharedQuestion {

    // Attributes
    private UserRequest userRequest;
    private ArtistRequest artistRequest;
    private Scanner myScanner;
    private boolean identifier;

    // Constructor

    public SharedQuestion(ArtistRequest artistRequest, Scanner myScanner) {
        this.artistRequest = artistRequest;
        this.myScanner = myScanner;
        this.identifier = false;
    }

    public SharedQuestion(UserRequest userRequest, Scanner myScanner) {
        this.userRequest = userRequest;
        this.myScanner = myScanner;
        this.identifier = true;
    }

    // Public Functions
    public void accountExisting() throws IOException {
        System.out.println("1-login");
        System.out.println("2-signup");
        int answer = Integer.parseInt(myScanner.nextLine());
        switch (answer) {
            case 1:
                loginOperation();
                break;
            case 2:
                signupOperation();
                break;
            default:
                System.out.println("Please give us right answer.");
                accountExisting();
        }
    }

    public void loginOperation() throws IOException {
        System.out.println("Please enter you username: ");
        String username = myScanner.nextLine();
        boolean flag;
        if (identifier)
            flag = userRequest.usernameCheckingRequest(username);
        else
            flag = artistRequest.nameCheckingRequest(username);

        if (flag){
            System.out.println("Please enter your password: ");
            String password = myScanner.nextLine();
            if (identifier) {
                User user = userRequest.checkPasswordForLoginOperation(username, password);
                if (user != null){
                    UserQuestion userQuestion = new UserQuestion(userRequest, myScanner);
                    userQuestion.mainMenu(user);
                }
                else {
                    System.out.println("Your password is wrong.");
                    System.out.println("Please enter right one.");
                    loginOperation();
                }
            }
            else {
                Artist artist = artistRequest.checkPasswordForLoginOperation(username, password);
                if (artist != null){
                    ArtistQuestion artistQuestion = new ArtistQuestion(artistRequest, myScanner);
                    artistQuestion.mainMenu(artist);
                }
                else {
                    System.out.println("Your password is wrong.");
                    System.out.println("Please enter right one.");
                    loginOperation();
                }
            }
        }
        else {
            System.out.println("Your username does not exist.");
            System.out.println("Please try again.");
            loginOperation();
        }
    }

    public void signupOperation() throws IOException {
        System.out.println("Please enter your username: ");
        String username = myScanner.nextLine();
        boolean flag;
        if (identifier)
            flag = !userRequest.usernameCheckingRequest(username);
        else
            flag = !artistRequest.nameCheckingRequest(username);

        if (flag) {
            System.out.println("Please enter your password: ");
            String password = myScanner.nextLine();
            System.out.println("Please enter your email: ");
            String email = myScanner.nextLine();
            if (identifier) {
                User user = new User(username, password, email);
                String ans = userRequest.addClientToDB(user);
                System.out.println(ans);
                UserQuestion userQuestion = new UserQuestion(userRequest, myScanner);
                userQuestion.mainMenu(user);
            }
            else {
                System.out.println("Please enter your biography: ");
                String biography = myScanner.nextLine();
                Artist artist = new Artist(username, password, email, biography);
                String ans = artistRequest.addArtistToDB(artist);
                System.out.println(ans);
                ArtistQuestion artistQuestion = new ArtistQuestion(artistRequest, myScanner);
                artistQuestion.mainMenu(artist);
            }
        }
        else {
            System.out.println("This username is already exist.");
            System.out.println("Please try again.");
            signupOperation();
        }
    }
}
