package logic;

public class QuizRound {
    private int quizMode;
    private int correctAnswers;

    public QuizRound(int quizMode, int correctAnswers) {
        this.quizMode = quizMode;
        this.correctAnswers = correctAnswers;
    }

    public int getQuizMode() {
        return quizMode;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }
}
