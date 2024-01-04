package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizManager {
    private int quizMode;
    private List<String> quizModes;

    private int numOfOptions;
    private Chord[] chosenChords;
    private int correctAnswer;

    private List<List<Chord>> chords; /* a list of lists where the inner lists contain chords of the same quality (row1: majors, row2: minors, ...)*/

    private Random random;

    public QuizManager() {
        numOfOptions = 4;
        chosenChords = new Chord[numOfOptions];

        quizModes = new ArrayList<String>();

        try {
            initChords();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        random = new Random();
    }

    public int getNumOfOptions() {
        return numOfOptions;
    }

    public Chord[] getChosenChords() {
        return chosenChords;
    }

    public void setChosenChords(Chord[] chosenChords) {
        this.chosenChords = chosenChords;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getQuizMode() {
        return quizMode;
    }

    public void setQuizMode(int quizMode) {
        this.quizMode = quizMode;
    }

    public void addQuizMode(String name) {
        quizModes.add(name);
    }

    private void initChords() throws Exception {
        chords = new ArrayList<>();
        MajorChordFactory majorChordFactory = new MajorChordFactory();
        MinorChordFactory minorChordFactory = new MinorChordFactory();
        Dominant7ChordFactory dominant7ChordFactory = new Dominant7ChordFactory();
        Major7ChordFactory major7ChordFactory = new Major7ChordFactory();
        Minor7ChordFactory minor7ChordFactory = new Minor7ChordFactory();
        DiminishedChordFactory diminishedChordFactory = new DiminishedChordFactory();

        /* majors */
        chords.add(new ArrayList<Chord>());
        chords.getLast().add(majorChordFactory.createNormalChord(Note.C, new Fretting(-1, 3, 2, 0, 1, 0)));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.C, new Fretting(8, 10, 10, 9, 8, 8, Fretting.STRING_E1, Fretting.STRING_E2, 8)));
        /* ----------------------------------- */

        /* minors */
        chords.add(new ArrayList<Chord>());
        chords.getLast().add(minorChordFactory.createNormalChord(Note.C, new Fretting(-1, 3, 1, 0, 1, 3)));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.C, new Fretting(8, 10, 10, 8, 8, 8, Fretting.STRING_E1, Fretting.STRING_E2, 8)));
        /* ----------------------------------- */

        /* dominant 7th's */
        chords.add(new ArrayList<Chord>());
        /* ----------------------------------- */

        /* major 7th's */
        chords.add(new ArrayList<Chord>());
        /* ----------------------------------- */

        /* minor 7th's */
        chords.add(new ArrayList<Chord>());
        /* ----------------------------------- */

        /* diminished */
        chords.add(new ArrayList<Chord>());
        /* ----------------------------------- */
    }

    private Chord chooseRandomChordOfQuality(int quality) {
        int chordIdx = random.nextInt(chords.get(quality).size());
        return chords.get(quality).get(chordIdx);
    }

    private Chord chooseRandomChord() {
        int quality = random.nextInt(chords.size());
        return chooseRandomChordOfQuality(quality);
    }

    /* puts "numOfOptions" amount of unique chords in "chosenChords"
    * and selects a correct answer that should be guessed */
    private void genQuestion() {
        boolean sharpAccidentals = random.nextBoolean(); /* decide if "#" or "b" representation will be used */
        int i = 0;
        while (i < numOfOptions) {
            if (quizModeIsAll()) {
                chosenChords[i] = chooseRandomChord();
            } else {
                chosenChords[i] = chooseRandomChordOfQuality(quizMode);
            }

            /* change name of sharps to flat or vice versa (if necessary) */
            if (sharpAccidentals) {
                chosenChords[i].setBase(Note.flatToSharp(chosenChords[i].getBase()));
            } else {
                chosenChords[i].setBase(Note.sharpToFlat(chosenChords[i].getBase()));
            }

            if (chosenIsUniqueToAlreadyChosen(i)) {
                i++; /* accept this chord and move on */
            }
            /* else choose a chord again */
        }

        correctAnswer = random.nextInt(numOfOptions); /* randomly choose which chord will be asked to guess */
    }

    private boolean quizModeIsAll() {
        return quizMode == quizModes.size()-1;
    }

    /* decides if the "i"-th element of "chosenChords" is unique to those before it */
    private boolean chosenIsUniqueToAlreadyChosen(int idx) {
        for (int i = 0; i < idx; i++) {
            if (chosenChords[i].getBase().equals(chosenChords[idx].getBase()) &&
                chosenChords[i].getQuality().equals(chosenChords[idx].getQuality())) {
                return false;
            }
        }
        return true;
    }

    public void startQuiz() {
        if (!quizModeIsAll()) {

        } else {

        }
    }
}
