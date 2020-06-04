package readability;

public class TextSummary {
    private long words = 0;
    private long sentences = 0;
    private long characters = 0;
    private long syllables = 0;
    private long polysyllables = 0;

    public long getWords() {
        return words;
    }

    public void setWords(long words) {
        this.words = words;
    }

    public long getSentences() {
        return sentences;
    }

    public void setSentences(long sentences) {
        this.sentences = sentences;
    }

    public long getCharacters() {
        return characters;
    }

    public void setCharacters(long characters) {
        this.characters = characters;
    }

    public long getSyllables() {
        return syllables;
    }

    public void setSyllables(long syllables) {
        this.syllables = syllables;
    }

    public long getPolysyllables() {
        return polysyllables;
    }

    public void setPolysyllables(long polysyllables) {
        this.polysyllables = polysyllables;
    }
}
