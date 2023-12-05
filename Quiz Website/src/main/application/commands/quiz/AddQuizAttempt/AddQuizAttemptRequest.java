package main.application.commands.quiz.AddQuizAttempt;

import main.mediator.abstractions.IRequest;

public class AddQuizAttemptRequest implements IRequest<AddQuizAttemptResponse> {
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

    private String username;
    private int quizId;
    private int score;
}
