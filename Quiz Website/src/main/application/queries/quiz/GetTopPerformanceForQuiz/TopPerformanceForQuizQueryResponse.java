package main.application.queries.quiz.GetTopPerformanceForQuiz;

public class TopPerformanceForQuizQueryResponse {
    private String username;
    private int score;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
