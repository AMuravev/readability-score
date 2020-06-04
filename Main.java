package readability;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String text = FileManager.read(args[0]);
        TextParser textParser = new TextParser();

        textParser.printSummary(text);

        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");

        textParser.calculateScoreByAlg(scanner.next());
    }
}
