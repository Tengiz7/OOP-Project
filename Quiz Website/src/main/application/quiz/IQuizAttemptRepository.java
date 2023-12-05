package main.application.quiz;

import main.domain.QuizAttempt;

import java.util.List;

public interface IQuizAttemptRepository {
    public List<QuizAttempt> getQuizzesAttemptedByFriends(String username);
    public void deleteHistory(int quizId);
    int getQuizAttemptsNum();
    int getUniqueQuizAttemptsNum();
    public void addQuizAttempt(QuizAttempt quizAttempt);
    public List<QuizAttempt> getQuizAttemptsByUser(String username, int quizId);
    public List<QuizAttempt> getTopQuizAttemptsByQuiz(int quizId);
    public int getNumberOfTimesTaken(int quizId);
    public int getNumberOfUniqueTimesTaken(int quizId);
    public int getMaxScore(int quizId);
    public double getAverageScore(int quizId);
    public List<QuizAttempt> getFriendsQuizAttempts(String username, int quizId);
}
