package core;

public class Settings {
    public static Settings instance;
    private boolean sharpAccidentals;

    private Settings() {
        sharpAccidentals = true;
    }

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public boolean isSharpAccidentals() {
        return sharpAccidentals;
    }

    public void setSharpAccidentals(boolean val) {
        if (val != sharpAccidentals) {
            sharpAccidentals = val;

            // TODO: notify ui elements to change sharp representations to flat ones
        }
    }
}
