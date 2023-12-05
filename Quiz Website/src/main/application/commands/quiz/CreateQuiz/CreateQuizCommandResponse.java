package main.application.commands.quiz.CreateQuiz;

public class CreateQuizCommandResponse {
    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    private int quizId;
}
