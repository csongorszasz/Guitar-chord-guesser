package logic;

import ui.QuizOptionsGridPanel;
import ui.QuizPanel;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
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
    private int numOfOptions;
    private int numOfQuestions;
    private Chord[] chosenChords;
    private int correctAnswer;
    private int questionNumber;
    private int cntCorreclyAnswered;
    private int phase;
    private List<List<Chord>> chords; /* a list of lists where the inner lists contain chords of the same quality (row1: majors, row2: minors, ...)*/

    private QuizPanel quizPanel;
    private QuizOptionsGridPanel quizOptionsGridPanel;

    public QuizManager() {
        random = new Random();
        quizMode = 0;
        numOfOptions = 4;
        numOfQuestions = 2;
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

        chords.getLast().add(majorChordFactory.createNormalChord(Note.CSHARP, new Fretting()));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.CSHARP, new Fretting()));

        chords.getLast().add(majorChordFactory.createNormalChord(Note.D, new Fretting()));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.D, new Fretting()));

        chords.getLast().add(majorChordFactory.createNormalChord(Note.DSHARP, new Fretting()));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.DSHARP, new Fretting()));

        chords.getLast().add(majorChordFactory.createNormalChord(Note.E, new Fretting()));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.E, new Fretting()));

        chords.getLast().add(majorChordFactory.createNormalChord(Note.F, new Fretting()));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.F, new Fretting()));

        chords.getLast().add(majorChordFactory.createNormalChord(Note.FSHARP, new Fretting()));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.FSHARP, new Fretting()));

        chords.getLast().add(majorChordFactory.createNormalChord(Note.G, new Fretting()));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.G, new Fretting()));

        chords.getLast().add(majorChordFactory.createNormalChord(Note.GSHARP, new Fretting()));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.GSHARP, new Fretting()));

        chords.getLast().add(majorChordFactory.createNormalChord(Note.A, new Fretting()));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.A, new Fretting()));

        chords.getLast().add(majorChordFactory.createNormalChord(Note.ASHARP, new Fretting()));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.ASHARP, new Fretting()));

        chords.getLast().add(majorChordFactory.createNormalChord(Note.B, new Fretting()));
        chords.getLast().add(majorChordFactory.createBarreChord(Note.B, new Fretting()));
        /* ----------------------------------- */

        /* minors */
        chords.add(new ArrayList<Chord>());
        chords.getLast().add(minorChordFactory.createNormalChord(Note.C, new Fretting(-1, 3, 1, 0, 1, 3)));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.C, new Fretting(8, 10, 10, 8, 8, 8, Fretting.STRING_E1, Fretting.STRING_E2, 8)));

        chords.getLast().add(minorChordFactory.createNormalChord(Note.CSHARP, new Fretting()));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.CSHARP, new Fretting()));

        chords.getLast().add(minorChordFactory.createNormalChord(Note.D, new Fretting()));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.D, new Fretting()));

        chords.getLast().add(minorChordFactory.createNormalChord(Note.DSHARP, new Fretting()));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.DSHARP, new Fretting()));

        chords.getLast().add(minorChordFactory.createNormalChord(Note.E, new Fretting()));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.E, new Fretting()));

        chords.getLast().add(minorChordFactory.createNormalChord(Note.F, new Fretting()));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.F, new Fretting()));

        chords.getLast().add(minorChordFactory.createNormalChord(Note.FSHARP, new Fretting()));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.FSHARP, new Fretting()));

        chords.getLast().add(minorChordFactory.createNormalChord(Note.G, new Fretting()));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.G, new Fretting()));

        chords.getLast().add(minorChordFactory.createNormalChord(Note.GSHARP, new Fretting()));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.GSHARP, new Fretting()));

        chords.getLast().add(minorChordFactory.createNormalChord(Note.A, new Fretting()));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.A, new Fretting()));

        chords.getLast().add(minorChordFactory.createNormalChord(Note.ASHARP, new Fretting()));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.ASHARP, new Fretting()));

        chords.getLast().add(minorChordFactory.createNormalChord(Note.B, new Fretting()));
        chords.getLast().add(minorChordFactory.createBarreChord(Note.B, new Fretting()));
        /* ----------------------------------- */

        /* dominant 7th's */
        chords.add(new ArrayList<Chord>());
        chords.getLast().add(dominant7ChordFactory.createNormalChord(Note.C, new Fretting(-1, 3, 2, 3, 1, 0)));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.C, new Fretting(8, 10, 8, 9, 8, 8, Fretting.STRING_E1, Fretting.STRING_E2, 8)));

        chords.getLast().add(dominant7ChordFactory.createNormalChord(Note.CSHARP, new Fretting()));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.CSHARP, new Fretting()));

        chords.getLast().add(dominant7ChordFactory.createNormalChord(Note.D, new Fretting()));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.D, new Fretting()));

        chords.getLast().add(dominant7ChordFactory.createNormalChord(Note.DSHARP, new Fretting()));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.DSHARP, new Fretting()));

        chords.getLast().add(dominant7ChordFactory.createNormalChord(Note.E, new Fretting()));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.E, new Fretting()));

        chords.getLast().add(dominant7ChordFactory.createNormalChord(Note.F, new Fretting()));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.F, new Fretting()));

        chords.getLast().add(dominant7ChordFactory.createNormalChord(Note.FSHARP, new Fretting()));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.FSHARP, new Fretting()));

        chords.getLast().add(dominant7ChordFactory.createNormalChord(Note.G, new Fretting()));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.G, new Fretting()));

        chords.getLast().add(dominant7ChordFactory.createNormalChord(Note.GSHARP, new Fretting()));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.GSHARP, new Fretting()));

        chords.getLast().add(dominant7ChordFactory.createNormalChord(Note.A, new Fretting()));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.A, new Fretting()));

        chords.getLast().add(dominant7ChordFactory.createNormalChord(Note.ASHARP, new Fretting()));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.ASHARP, new Fretting()));

        chords.getLast().add(dominant7ChordFactory.createNormalChord(Note.B, new Fretting()));
        chords.getLast().add(dominant7ChordFactory.createBarreChord(Note.B, new Fretting()));
        /* ----------------------------------- */

        /* major 7th's */
        chords.add(new ArrayList<Chord>());
        chords.getLast().add(major7ChordFactory.createNormalChord(Note.C, new Fretting(-1, -1, 2, 4, 1, 3)));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.C, new Fretting(3, 3, 5, 4, 5, 3, Fretting.STRING_E1, Fretting.STRING_E2, 3)));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.C, new Fretting(8, 10, 9, 9,  8, 8, Fretting.STRING_E1, Fretting.STRING_E2, 8)));

        chords.getLast().add(major7ChordFactory.createNormalChord(Note.CSHARP, new Fretting()));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.CSHARP, new Fretting()));

        chords.getLast().add(major7ChordFactory.createNormalChord(Note.D, new Fretting()));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.D, new Fretting()));

        chords.getLast().add(major7ChordFactory.createNormalChord(Note.DSHARP, new Fretting()));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.DSHARP, new Fretting()));

        chords.getLast().add(major7ChordFactory.createNormalChord(Note.E, new Fretting()));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.E, new Fretting()));

        chords.getLast().add(major7ChordFactory.createNormalChord(Note.F, new Fretting()));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.F, new Fretting()));

        chords.getLast().add(major7ChordFactory.createNormalChord(Note.FSHARP, new Fretting()));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.FSHARP, new Fretting()));

        chords.getLast().add(major7ChordFactory.createNormalChord(Note.G, new Fretting()));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.G, new Fretting()));

        chords.getLast().add(major7ChordFactory.createNormalChord(Note.GSHARP, new Fretting()));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.GSHARP, new Fretting()));

        chords.getLast().add(major7ChordFactory.createNormalChord(Note.A, new Fretting()));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.A, new Fretting()));

        chords.getLast().add(major7ChordFactory.createNormalChord(Note.ASHARP, new Fretting()));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.ASHARP, new Fretting()));

        chords.getLast().add(major7ChordFactory.createNormalChord(Note.B, new Fretting()));
        chords.getLast().add(major7ChordFactory.createBarreChord(Note.B, new Fretting()));
        /* ----------------------------------- */

        /* minor 7th's */
        chords.add(new ArrayList<Chord>());
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.C, new Fretting(-1, 3, 1, 3, 1, -1, Fretting.STRING_D, Fretting.STRING_B, 1)));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.C, new Fretting(8, 10, 8, 8, 8, 8, Fretting.STRING_E1, Fretting.STRING_E2, 8)));

        chords.getLast().add(minor7ChordFactory.createNormalChord(Note.CSHARP, new Fretting()));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.CSHARP, new Fretting()));

        chords.getLast().add(minor7ChordFactory.createNormalChord(Note.D, new Fretting()));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.D, new Fretting()));

        chords.getLast().add(minor7ChordFactory.createNormalChord(Note.DSHARP, new Fretting()));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.DSHARP, new Fretting()));

        chords.getLast().add(minor7ChordFactory.createNormalChord(Note.E, new Fretting()));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.E, new Fretting()));

        chords.getLast().add(minor7ChordFactory.createNormalChord(Note.F, new Fretting()));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.F, new Fretting()));

        chords.getLast().add(minor7ChordFactory.createNormalChord(Note.FSHARP, new Fretting()));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.FSHARP, new Fretting()));

        chords.getLast().add(minor7ChordFactory.createNormalChord(Note.G, new Fretting()));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.G, new Fretting()));

        chords.getLast().add(minor7ChordFactory.createNormalChord(Note.GSHARP, new Fretting()));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.GSHARP, new Fretting()));

        chords.getLast().add(minor7ChordFactory.createNormalChord(Note.A, new Fretting()));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.A, new Fretting()));

        chords.getLast().add(minor7ChordFactory.createNormalChord(Note.ASHARP, new Fretting()));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.ASHARP, new Fretting()));

        chords.getLast().add(minor7ChordFactory.createNormalChord(Note.B, new Fretting()));
        chords.getLast().add(minor7ChordFactory.createBarreChord(Note.B, new Fretting()));
        /* ----------------------------------- */

        /* diminished */
        chords.add(new ArrayList<Chord>());
        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.C, new Fretting(-1, 3, 4, 2, 4, -1)));
        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.C, new Fretting(-1, 9, 10, 8, 10, -1)));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.C, new Fretting(8, -1, 7, 8, 7, -1, Fretting.STRING_D, Fretting.STRING_B, 7)));

        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.CSHARP, new Fretting()));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.CSHARP, new Fretting()));

        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.D, new Fretting()));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.D, new Fretting()));

        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.DSHARP, new Fretting()));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.DSHARP, new Fretting()));

        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.E, new Fretting()));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.E, new Fretting()));

        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.F, new Fretting()));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.F, new Fretting()));

        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.FSHARP, new Fretting()));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.FSHARP, new Fretting()));

        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.G, new Fretting()));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.G, new Fretting()));

        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.GSHARP, new Fretting()));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.GSHARP, new Fretting()));

        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.A, new Fretting()));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.A, new Fretting()));

        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.ASHARP, new Fretting()));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.ASHARP, new Fretting()));

        chords.getLast().add(diminishedChordFactory.createNormalChord(Note.B, new Fretting()));
        chords.getLast().add(diminishedChordFactory.createBarreChord(Note.B, new Fretting()));
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

        try {
            FileWriter writer = new FileWriter("src\\data\\scores.txt", true);
            writer.write(quizMode + " " + cntCorreclyAnswered + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* this loads past scores into the statistics panel */
    public void loadScores() {
        /* Row format:
        *  <quizmode> <correct_answers> */

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\data\\scores.txt"));
            List<QuizRound> rounds = reader.lines()
                    .map(line -> {
                        String[] tokens = line.split(" ");
                        return new QuizRound(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
                    })
                    .sorted(Comparator.comparing(QuizRound::getQuizMode))
                    .toList();
            rounds.forEach(r -> System.out.println(r.getQuizMode() + " " + r.getCorrectAnswers()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
