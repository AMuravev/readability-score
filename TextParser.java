package readability;

import java.util.ArrayList;
import java.util.Arrays;

public class TextParser {

    protected TextSummary ts = null;
    protected ArrayList<Algorithm> algorithms = new ArrayList<>();

    public void printSummary(String text) {
        setTs(Algorithm.getSummary(text));
        printResult(ts);
    }

    private void printResult(TextSummary ts) {
        System.out.format("Words: %s\nSentences: %s\nCharacters: %s\nSyllables: %s\nPolysyllables: %s\n",
                ts.getWords(),
                ts.getSentences(),
                ts.getCharacters(),
                ts.getSyllables(),
                ts.getPolysyllables());
    }

    public void calculateScoreByAlg(String alg) {

        switch (alg) {
            case "ARI":
                algorithms.add(new ARIAlgorithm(ts));
                break;
            case "FK":
                algorithms.add(new FKAlgorithm(ts));
                break;
            case "SMOG":
                algorithms.add(new SMOGAlgorithm(ts));
                break;
            case "CL":
                algorithms.add(new CLAlgorithm(ts));
                break;
            case "all":
                algorithms.addAll(Arrays.asList(new ARIAlgorithm(ts), new FKAlgorithm(ts), new SMOGAlgorithm(ts), new CLAlgorithm(ts)));
                break;
            default:
        }

        calculate();
    }

    private void calculate() {

        double age = 0;

        for (Algorithm algorithm : algorithms) {
            age += Algorithm.getAge(algorithm.getScore());
            algorithm.printScore();
        }

        if (algorithms.size() > 1) {
            System.out.println("\nThis text should be understood in average by " + age / algorithms.size() + " year olds.");
        }

        algorithms.clear();

    }

    public void setTs(TextSummary ts) {
        this.ts = ts;
    }
}
