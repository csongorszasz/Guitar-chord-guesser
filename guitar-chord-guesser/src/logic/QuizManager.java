package logic;

import java.util.ArrayList;
import java.util.List;

public class QuizManager {
    private int quizMode;
    private List<String> quizModes;

    private int numOfOptions;
    private Chord[] chosenChords;
    private int correctAnswer;

    private List<List<Chord>> chords; /* a list of lists where the inner lists contain chords of the same quality (row1: majors, row2: minors, ...)*/
    private MajorChordFactory majorChordFactory;

    public QuizManager() {
        numOfOptions = 4;
        quizModes = new ArrayList<String>();

        try {
            initChords();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
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
        majorChordFactory = new MajorChordFactory();

        /* majors */
        chords.add(new ArrayList<Chord>());
        chords.getLast().add(majorChordFactory.createNormalChord(Note.C, new Fretting(-1, 3, 2, 0, 1, 0)));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.C, new Fretting(-1, 3, 5, 5, 5, 3, Fretting.STRING_A, Fretting.STRING_E2, 3)));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.C, new Fretting(8, 10, 10, 9, 8, 8, Fretting.STRING_E1, Fretting.STRING_E2, 8)));
        /* ----------------------------------- */

        /* minors */
        chords.add(new ArrayList<Chord>());
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
        return null;
    }

    private Chord chooseRandomChord() {
        return null;
    }

    public void startQuiz() {
        if (quizMode < quizModes.size()-1) { /* a mode other than "ALL" was chosen */

        } else {

        }
    }
}
