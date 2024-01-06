package logic;

import ui.QuizOptionsGridPanel;
import ui.QuizPanel;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class QuizManager {
    public static final int PHASE_QUESTION = 0;
    public static final int PHASE_ANSWERED = 1;
    public static final int PHASE_ENDED = 2;

    private Random random;
    private int quizMode;
    private List<String> quizModes;
    private final int numOfOptions;
    private final int numOfQuestions;
    private Chord[] chosenChords;
    private int correctAnswer;
    private int questionNumber;
    private int cntCorreclyAnswered;
    private int phase;
    private List<List<Chord>> chords; /* a list of lists where the inner lists contain chords of the same quality (list1: majors, list2: minors, ...)*/
    private List<QuizRound> scores;

    private QuizPanel quizPanel;
    private QuizOptionsGridPanel quizOptionsGridPanel;

    public QuizManager() {
        random = new Random();
        quizMode = 0;
        numOfOptions = 4;
        numOfQuestions = 10;
        chosenChords = new Chord[numOfOptions];
        correctAnswer = 0;
        questionNumber = 0;
        cntCorreclyAnswered = 0;
        phase = PHASE_QUESTION;
        quizModes = new ArrayList<String>();

        try {
            initChords();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        scores = new ArrayList<QuizRound>();
        loadScores();
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

    public QuizPanel getQuizPanel() {
        return quizPanel;
    }

    public void setQuizPanel(QuizPanel quizPanel) {
        this.quizPanel = quizPanel;
    }

    public QuizOptionsGridPanel getQuizOptionsGridPanel() {
        return quizOptionsGridPanel;
    }

    public void setQuizOptionsGridPanel(QuizOptionsGridPanel quizOptionsGridPanel) {
        this.quizOptionsGridPanel = quizOptionsGridPanel;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public int getNumOfQuestions() {
        return numOfQuestions;
    }

    public int getCntCorreclyAnswered() {
        return cntCorreclyAnswered;
    }

    public void setCntCorreclyAnswered(int cntCorreclyAnswered) {
        this.cntCorreclyAnswered = cntCorreclyAnswered;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public List<QuizRound> getScores() {
        return scores;
    }

    public List<String> getQuizModes() {
        return quizModes;
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
        chords.getLast().add(majorChordFactory.createNormalChord(Note.C, new Fretting(-1, 3, 2, 0, 1, 0)));

        chords.getLast().add(majorChordFactory.createNormalChord(Note.CSHARP, new Fretting(-1, 4, 3, 1, 2, 1)));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.CSHARP, new Fretting(9, 11, 11, 10, 9, 9, Fretting.STRING_E1, Fretting.STRING_E2, 9)));

        chords.getLast().add(majorChordFactory.createNormalChord(Note.D, new Fretting(-1,-1,0,2,3,2)));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.D, new Fretting(10,12,12,11,10,10,Fretting.STRING_E1,Fretting.STRING_E2,10)));

        chords.getLast().add(majorChordFactory.createNormalChord(Note.DSHARP, new Fretting(-1,-1,1,3,4,3)));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.DSHARP, new Fretting(11,13,13,12,11,11,Fretting.STRING_E1,Fretting.STRING_E2,11)));

        chords.getLast().add(majorChordFactory.createNormalChord(Note.E, new Fretting(0,2,2,1,0,0)));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.E, new Fretting(-1,7,9,9,9,7,1,5,7)));

        chords.getLast().add(majorChordFactory.createBarreChord(Note.F, new Fretting(1,3,3,2,1,1,0,5,1)));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.F, new Fretting(-1,8,10,10,10,8,1,5,8)));

        chords.getLast().add(majorChordFactory.createBarreChord(Note.FSHARP, new Fretting(2,4,4,3,2,2,0,5,2)));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.FSHARP, new Fretting(-1,9,11,11,11,9,1,5,9)));

        chords.getLast().add(majorChordFactory.createNormalChord(Note.G, new Fretting(3,2,0,0,0,3)));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.G, new Fretting(-1,10,12,12,12,10,1,5,10)));

        chords.getLast().add(majorChordFactory.createBarreChord(Note.GSHARP, new Fretting(4,6,6,5,4,4,0,5,4)));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.GSHARP, new Fretting(-1,11,10,8,9,8,3,5,8)));

        chords.getLast().add(majorChordFactory.createNormalChord(Note.A, new Fretting(-1,0,2,2,2,0)));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.A, new Fretting(-1,12,11,9,10,9,3,5,9)));

        chords.getLast().add(majorChordFactory.createBarreChord(Note.ASHARP, new Fretting(-1,1,3,3,3,1,1,5,1)));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.ASHARP, new Fretting(6,8,8,7,6,6,0,5,6)));

        chords.getLast().add(majorChordFactory.createBarreChord(Note.B, new Fretting(-1,2,4,4,4,2,1,5,2)));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.B, new Fretting(7,9,9,8,7,7,0,5,7)));
        /* ----------------------------------- */

        /* minors */
        chords.add(new ArrayList<Chord>());
        chords.getLast().add(minorChordFactory.createNormalChord(Note.C, new Fretting(-1, 3, 1, 0, 1, 3)));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.C, new Fretting(8, 10, 10, 8, 8, 8, Fretting.STRING_E1, Fretting.STRING_E2, 8)));

        chords.getLast().add(minorChordFactory.createBarreChord(Note.CSHARP, new Fretting(-1,4,6,6,5,4,1,5,4)));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.CSHARP, new Fretting(9,11,11,9,9,9,0,5,9)));

        chords.getLast().add(minorChordFactory.createNormalChord(Note.D, new Fretting(-1,-1,0,2,3,1)));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.D, new Fretting(-1,5,7,7,6,5,1,5,5)));

        chords.getLast().add(minorChordFactory.createNormalChord(Note.DSHARP, new Fretting(-1,-1,1,3,4,2)));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.DSHARP, new Fretting(-1,6,8,8,7,6,1,5,6)));

        chords.getLast().add(minorChordFactory.createNormalChord(Note.E, new Fretting(0,2,2,0,0,0)));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.E, new Fretting(-1,7,9,9,8,7,1,5,7)));

        chords.getLast().add(minorChordFactory.createBarreChord(Note.F, new Fretting(1,3,3,1,1,1,0,5,1)));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.F, new Fretting(-1,8,10,10,9,8,1,5,8)));

        chords.getLast().add(minorChordFactory.createBarreChord(Note.FSHARP, new Fretting(2,4,4,2,2,2,0,5,2)));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.FSHARP, new Fretting(-1,9,11,11,10,9,1,5,9)));

        chords.getLast().add(minorChordFactory.createBarreChord(Note.G, new Fretting(3,5,5,3,3,3,0,5,3)));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.G, new Fretting(-1,10,12,12,11,10,1,5,10)));

        chords.getLast().add(minorChordFactory.createBarreChord(Note.GSHARP, new Fretting(4,6,6,4,4,4,0,5,4)));

        chords.getLast().add(minorChordFactory.createNormalChord(Note.A, new Fretting(-1,0,2,2,1,0)));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.A, new Fretting(5,7,7,5,5,5,0,5,5)));

        chords.getLast().add(minorChordFactory.createBarreChord(Note.ASHARP, new Fretting(-1,1,3,3,2,1,1,5,1)));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.ASHARP, new Fretting(6,8,8,6,6,6,0,5,6)));

        chords.getLast().add(minorChordFactory.createBarreChord(Note.B, new Fretting(-1,2,4,4,3,2,1,5,2)));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.B, new Fretting(7,9,9,7,7,7,0,5,7)));
        /* ----------------------------------- */

        /* dominant 7th's */
        chords.add(new ArrayList<Chord>());
        chords.getLast().add(dominant7ChordFactory.createNormalChord(Note.C, new Fretting(-1, 3, 2, 3, 1, 0)));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.C, new Fretting(8, 10, 8, 9, 8, 8, Fretting.STRING_E1, Fretting.STRING_E2, 8)));

        chords.getLast().add(dominant7ChordFactory.createNormalChord(Note.CSHARP, new Fretting(-1,4,3,4,2,-1)));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.CSHARP, new Fretting(9,11,9,10,9,9,0,5,9)));

        chords.getLast().add(dominant7ChordFactory.createNormalChord(Note.D, new Fretting(-1,-1,0,2,1,2)));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.D, new Fretting(10,12,10,11,10,10,0,5,10)));

        chords.getLast().add(dominant7ChordFactory.createNormalChord(Note.DSHARP, new Fretting(-1,-1,1,3,2,3)));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.DSHARP, new Fretting(-1,6,8,6,8,6,1,5,6)));

        chords.getLast().add(dominant7ChordFactory.createNormalChord(Note.E, new Fretting(0,2,0,1,0,0)));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.E, new Fretting(-1,7,9,7,9,7,1,5,7)));

        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.F, new Fretting(1,3,1,2,1,1,0,5,1)));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.F, new Fretting(-1,8,10,8,10,8,1,5,8)));

        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.FSHARP, new Fretting(2,4,2,3,2,2,0,5,2)));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.FSHARP, new Fretting(-1,9,11,9,11,9,1,5,9)));

        chords.getLast().add(dominant7ChordFactory.createNormalChord(Note.G, new Fretting(3,2,0,0,0,1)));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.G, new Fretting(3,5,3,4,3,3,0,5,3)));

        chords.getLast().add(dominant7ChordFactory.createNormalChord(Note.GSHARP, new Fretting(-1,-1,6,8,7,8)));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.GSHARP, new Fretting(4,6,4,5,4,4,0,5,4)));

        chords.getLast().add(dominant7ChordFactory.createNormalChord(Note.A, new Fretting(-1,0,2,0,2,0)));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.A, new Fretting(5,7,5,6,5,5,0,5,5)));

        chords.getLast().add(dominant7ChordFactory.createNormalChord(Note.ASHARP, new Fretting(-1,1,3,1,3,1)));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.ASHARP, new Fretting(6,8,6,7,6,6,0,5,6)));

        chords.getLast().add(dominant7ChordFactory.createNormalChord(Note.B, new Fretting(-1,2,1,2,0,2)));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.B, new Fretting(7,9,7,8,7,7,0,5,7)));
        /* ----------------------------------- */

        /* major 7th's */
        chords.add(new ArrayList<Chord>());
        chords.getLast().add(major7ChordFactory.createNormalChord(Note.C, new Fretting(-1, -1, 2, 4, 1, 3)));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.C, new Fretting(3, 3, 5, 4, 5, 3, Fretting.STRING_E1, Fretting.STRING_E2, 3)));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.C, new Fretting(8, 10, 9, 9,  8, 8, Fretting.STRING_E1, Fretting.STRING_E2, 8)));

        chords.getLast().add(major7ChordFactory.createBarreChord(Note.CSHARP, new Fretting(1,4,3,1,1,1,0,5,1)));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.CSHARP, new Fretting(9,11,10,10,9,9,0,5,9)));

        chords.getLast().add(major7ChordFactory.createBarreChord(Note.D, new Fretting(2,5,4,2,2,2,0,5,2)));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.D, new Fretting(10,12,11,11,10,10,0,5,10)));

        chords.getLast().add(major7ChordFactory.createBarreChord(Note.DSHARP, new Fretting(3,6,5,3,3,3,0,5,3)));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.DSHARP, new Fretting(-1,6,8,7,8,6,1,5,6)));

        chords.getLast().add(major7ChordFactory.createNormalChord(Note.E, new Fretting(0,2,1,1,0,0)));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.E, new Fretting(7,7,9,8,9,7,0,5,7)));

        chords.getLast().add(major7ChordFactory.createBarreChord(Note.F, new Fretting(1,3,2,2,1,1,0,5,1)));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.F, new Fretting(8,8,10,9,10,8,0,5,8)));

        chords.getLast().add(major7ChordFactory.createBarreChord(Note.FSHARP, new Fretting(2,4,3,3,2,2,0,5,2)));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.FSHARP, new Fretting(-1,9,11,10,11,9,1,5,9)));

        chords.getLast().add(major7ChordFactory.createNormalChord(Note.G, new Fretting(-1,2,0,0,0,2)));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.G, new Fretting(7,10,9,7,7,7,0,5,7)));

        chords.getLast().add(major7ChordFactory.createNormalChord(Note.GSHARP, new Fretting(-1,3,1,1,1,3)));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.GSHARP, new Fretting(8,11,10,8,8,8,0,5,8)));

        chords.getLast().add(major7ChordFactory.createNormalChord(Note.A, new Fretting(-1,0,2,1,2,0)));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.A, new Fretting(9,12,11,9,9,9,0,5,9)));

        chords.getLast().add(major7ChordFactory.createBarreChord(Note.ASHARP, new Fretting(-1,1,3,2,3,1,1,5,1)));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.ASHARP, new Fretting(6,8,7,7,6,6,0,5,6)));

        chords.getLast().add(major7ChordFactory.createBarreChord(Note.B, new Fretting(-1,2,4,3,4,2,1,5,2)));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.B, new Fretting(7,9,8,8,7,7,0,5,7)));
        /* ----------------------------------- */

        /* minor 7th's */
        chords.add(new ArrayList<Chord>());
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.C, new Fretting(-1, 3, 1, 3, 1, -1, Fretting.STRING_D, Fretting.STRING_B, 1)));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.C, new Fretting(8, 10, 8, 8, 8, 8, Fretting.STRING_E1, Fretting.STRING_E2, 8)));

        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.CSHARP, new Fretting(-1,4,2,4,2,-1,2,4,2)));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.CSHARP, new Fretting(9,11,9,9,9,9,0,5,9)));

        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.D, new Fretting(-1,5,3,5,3,-1,2,4,3)));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.D, new Fretting(10,12,10,10,10,10,0,5,10)));

        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.DSHARP, new Fretting(-1,6,4,6,4,-1,2,4,4)));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.DSHARP, new Fretting(-1,6,8,6,7,6,1,5,6)));

        chords.getLast().add(minor7ChordFactory.createNormalChord(Note.E, new Fretting(0,2,0,0,0,0)));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.E, new Fretting(-1,7,9,7,8,7,1,5,7)));

        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.F, new Fretting(1,3,1,1,1,1,0,5,1)));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.F, new Fretting(-1,8,10,8,9,8,1,5,8)));

        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.FSHARP, new Fretting(2,4,2,2,2,2,0,5,2)));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.FSHARP, new Fretting(-1,9,11,9,10,9,1,5,9)));

        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.G, new Fretting(3,5,3,3,3,3,0,5,3)));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.G, new Fretting(-1,10,8,10,8,-1,2,4,8)));

        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.GSHARP, new Fretting(4,6,4,4,4,4,0,5,4)));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.GSHARP, new Fretting(-1,11,9,11,9,-1,2,4,9)));

        chords.getLast().add(minor7ChordFactory.createNormalChord(Note.A, new Fretting(-1,0,2,0,1,0)));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.A, new Fretting(5,7,5,5,5,5,0,5,5)));

        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.ASHARP, new Fretting(-1,1,3,1,2,1,1,5,1)));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.ASHARP, new Fretting(6,8,6,6,6,6,0,5,6)));

        chords.getLast().add(minor7ChordFactory.createNormalChord(Note.B, new Fretting(-1,0,0,2,0,2)));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.B, new Fretting(7,9,7,7,7,7,0,5,7)));
        /* ----------------------------------- */

        /* diminished */
        chords.add(new ArrayList<Chord>());
        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.C, new Fretting(-1, 3, 4, 2, 4, -1)));
        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.C, new Fretting(-1, 9, 10, 8, 10, -1)));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.C, new Fretting(8, -1, 7, 8, 7, -1, Fretting.STRING_D, Fretting.STRING_B, 7)));

        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.CSHARP, new Fretting(-1,1,2,0,2,-1)));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.CSHARP, new Fretting(9,-1,8,9,8,-1,2,4,8)));

        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.D, new Fretting(-1,-1,0,1,0,1)));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.D, new Fretting(10,-1,9,10,9,-1,2,4,9)));

        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.DSHARP, new Fretting(-1,-1,1,2,1,2)));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.DSHARP, new Fretting(11,-1,10,11,10,-1,2,4,10)));

        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.E, new Fretting(-1,1,2,0,2,-1)));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.E, new Fretting(9,-1,8,9,8,-1,2,4,8)));

        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.F, new Fretting(-1,2,3,1,3,-1)));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.F, new Fretting(10,-1,9,10,9,-1,2,4,9)));

        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.FSHARP, new Fretting(-1,3,4,2,4,-1)));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.FSHARP, new Fretting(8,-1,7,8,7,-1,2,4,7)));

        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.G, new Fretting(-1,1,2,0,2,-1)));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.G, new Fretting(9,-1,8,9,8,-1,2,4,8)));

        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.GSHARP, new Fretting(-1,2,3,1,3,-1)));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.GSHARP, new Fretting(10,-1,9,10,9,-1,2,4,9)));

        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.A, new Fretting(-1,3,4,2,4,-1)));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.A, new Fretting(8,-1,7,8,7,-1,2,4,7)));

        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.ASHARP, new Fretting(-1,1,2,0,2,-1)));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.ASHARP, new Fretting(9,-1,8,9,8,-1,2,4,8)));

        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.B, new Fretting(-1,2,3,1,3,-1)));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.B, new Fretting(10,-1,9,10,9,-1,2,4,9)));
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
        SoundPlayer.getInstance().stopCurrentlyPlayingSong();
        questionNumber = 0;
        cntCorreclyAnswered = 0;
        nextQuestion();
    }

    public void nextQuestion() {
        questionNumber++;
        genQuestion();
        quizPanel.showQuestionView();
        quizPanel.updateInfoLabels();
        quizOptionsGridPanel.updateGrid();
        setPhase(PHASE_QUESTION);
    }

    public void answered(int answer) {
        setPhase(PHASE_ANSWERED);
        quizOptionsGridPanel.setCellBorder(correctAnswer, new Color(50, 255, 65, 255), 15);
        if (answer != correctAnswer) {
            quizOptionsGridPanel.setCellBorder(answer, new Color(255, 43, 43, 255), 15);
            quizPanel.updateFeedbackLabelWrong();
        } else {
            quizPanel.updateFeedbackLabelCorrect();
            cntCorreclyAnswered++;
        }

        if (questionNumber < numOfQuestions) {
            quizPanel.showAnsweredView();
        } else {
            setPhase(PHASE_ENDED);
            quizPanel.showEndedView();
            saveScore();
        }
    }

    public void saveScore() {
        /* Row format:
         *  <quizmode> <correct_answers> */

        scores.add(new QuizRound(quizMode, cntCorreclyAnswered));
        try {
            FileWriter writer = new FileWriter("data\\scores.txt", true);
            writer.write(quizMode + " " + cntCorreclyAnswered + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadScores() {
        /* Row format:
        *  <quizmode> <correct_answers> */

        try {
            BufferedReader reader = new BufferedReader(new FileReader("data\\scores.txt"));
            scores = reader.lines()
                    .map(line -> {
                        String[] tokens = line.split(" ");
                        return new QuizRound(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
                    })
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
