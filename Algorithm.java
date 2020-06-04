package readability;

import java.util.regex.Pattern;

public abstract class Algorithm {

    protected double score;
    protected static final int[] ageTable = {6, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 25};

    Algorithm(TextSummary ts) {
        calculateScore(ts);
    }

    public static TextSummary getSummary(String text) {

        String tmpText = text.replaceAll("[\\P{Print}]", " ").trim();
        TextSummary ts = new TextSummary();

        ts.setSentences(tmpText.split("[.!?]+\\s*").length);
        ts.setWords(tmpText.split("\\s").length);
        ts.setCharacters(tmpText.replaceAll("\\s", "").split("").length);

        Pattern pattern = Pattern.compile("([AIOUYaiouy]){1,3}|e\\w|E\\w|\\b\\w[^AIOUYEaiouye]*e\\b");
        ts.setSyllables(pattern.matcher(tmpText).results().count());

        pattern = Pattern.compile("\\b\\w*[AIOUYEaiouye]{1,2}\\w+[AIOUYEaiouye]{1,2}\\w+([AIOUYaiouy]{1,2}|e\\w)\\w*\\b");
        ts.setPolysyllables(pattern.matcher(tmpText).results().count());

        return ts;
    }

    public static int getAge(double score) {
        int index = (int) Math.round(score) >= Algorithm.ageTable.length ? Algorithm.ageTable.length - 1 : (int) Math.round(score);

        return Algorithm.ageTable[index];
    }

    abstract protected void calculateScore(TextSummary ts);

    protected double getScore() {
        return score;
    }

    protected void printScore() {
        System.out.format("%.2f (about %d year olds).\n", score, getAge(score));
    }
}

class ARIAlgorithm extends Algorithm {

    ARIAlgorithm(TextSummary textSummary) {
        super(textSummary);
    }

    @Override
    protected void calculateScore(TextSummary ts) {
        score = 4.71 * ((double) ts.getCharacters() / ts.getWords()) + 0.5 * ((double) ts.getWords() / ts.getSentences()) - 21.43;
    }

    @Override
    protected void printScore() {
        System.out.print("Automated Readability Index: ");
        super.printScore();
    }
}

class FKAlgorithm extends Algorithm {

    FKAlgorithm(TextSummary textSummary) {
        super(textSummary);
    }

    @Override
    protected void calculateScore(TextSummary ts) {
        score = 0.39 * ((double) ts.getWords() / ts.getSentences()) + 11.8 * ((double) ts.getSyllables() / ts.getWords()) - 15.59;
    }

    @Override
    protected void printScore() {
        System.out.print("Flesch–Kincaid readability tests: ");
        super.printScore();
    }
}

class SMOGAlgorithm extends Algorithm {

    SMOGAlgorithm(TextSummary ts) {
        super(ts);
    }

    @Override
    protected void calculateScore(TextSummary ts) {

        double radical = Math.sqrt((double) ts.getPolysyllables() * 30 / ts.getSentences());

        score = 1.043 * radical + 3.1291;
    }

    @Override
    protected void printScore() {
        System.out.print("Simple Measure of Gobbledygook: ");
        super.printScore();
    }
}

class CLAlgorithm extends Algorithm {

    CLAlgorithm(TextSummary ts) {
        super(ts);
    }

    @Override
    protected void calculateScore(TextSummary ts) {
        double s = (double) ts.getSentences() / ts.getWords() * 100;
        double l = (double) ts.getCharacters() / ts.getWords() * 100;

        score = 0.0588 * l - 0.296 * s - 15.8;
    }

    @Override
    protected void printScore() {
        System.out.print("Coleman–Liau index: ");
        super.printScore();
    }
}
