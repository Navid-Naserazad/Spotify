package Artist;

import Shared.ArtistRequest;
import Shared.SharedQuestion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ArtistMain {

    // Constructor
    public ArtistMain(Scanner input, PrintWriter output, Scanner myScanner) throws IOException {
        ArtistRequest artistRequest = new ArtistRequest(input, output);
        SharedQuestion sharedQuestion = new SharedQuestion(artistRequest, myScanner);
        sharedQuestion.accountExisting();
    }
}
