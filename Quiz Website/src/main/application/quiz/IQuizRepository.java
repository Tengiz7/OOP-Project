package main.application.quiz;

import main.domain.Quiz;

import java.util.List;

public interface IQuizRepository {
    public List<Quiz> getNewQuizes();
    public List<Quiz> getPopularQuizes();
    public List<Quiz> getQuizesByCreatorId(String username);
    public List<Quiz> getQuizesByUserAttempts(String username);
    public List<Quiz> getQuizesByFriends(String username);
    public List<Quiz> searchQuizes(String title);
    public Quiz getQuizById(int id);
    public void deleteQuiz (int id);
    int getQuizesNum();
    public int addQuiz(Quiz quiz);
}
