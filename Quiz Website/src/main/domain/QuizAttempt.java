package main.domain;

public class QuizAttempt {

    public QuizAttempt(String username, int quizId, int score) {
        this.username = username;
        this.quizId = quizId;
        this.score = score;
    }

    public QuizAttempt() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    private int id;
    private String username;
    private int quizId;
    private int score;
}
