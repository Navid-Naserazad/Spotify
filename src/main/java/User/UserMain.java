package User;

import Shared.UserRequest;
import Shared.SharedQuestion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class UserMain {

    // Constructor
    public UserMain(Scanner input, PrintWriter output, Scanner myScanner) throws IOException {
        UserRequest userRequest = new UserRequest(input, output);
        SharedQuestion sharedQuestion = new SharedQuestion(userRequest, myScanner);
        sharedQuestion.accountExisting();
    }
}
