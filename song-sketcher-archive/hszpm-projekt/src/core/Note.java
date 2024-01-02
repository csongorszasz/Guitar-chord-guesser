package core;

public class Note {
    private String primaryName;
    private String alternativeName;

    public Note(String primaryName, String alternativeName) {
        this.primaryName = primaryName;
        this.alternativeName = alternativeName;
    }

    @Override
    public String toString() {
        if (Settings.getInstance().isSharpAccidentals()) {
            return primaryName;
        }
        return alternativeName;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public void setPrimaryName(String primaryName) {
        this.primaryName = primaryName;
    }

    public String getAlternativeName() {
        return alternativeName;
    }

    public void setAlternativeName(String alternativeName) {
        this.alternativeName = alternativeName;
    }
}
