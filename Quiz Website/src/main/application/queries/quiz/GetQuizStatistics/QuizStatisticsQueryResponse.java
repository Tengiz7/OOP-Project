package main.application.queries.quiz.GetQuizStatistics;

public class QuizStatisticsQueryResponse {
    private int numberOfTimesTaken;
    private int numberOfUniqueTimesTaken;
    private int maxScore;
    private double averageScore;

    public int getNumberOfTimesTaken() {
        return numberOfTimesTaken;
    }

    public void setNumberOfTimesTaken(int numberOfTimesTaken) {
        this.numberOfTimesTaken = numberOfTimesTaken;
    }

    public int getNumberOfUniqueTimesTaken() {
        return numberOfUniqueTimesTaken;
    }

    public void setNumberOfUniqueTimesTaken(int numberOfUniqueTimesTaken) {
        this.numberOfUniqueTimesTaken = numberOfUniqueTimesTaken;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }
}
