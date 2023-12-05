package Application.Queries;

import junit.framework.TestCase;
import main.application.queries.quiz.FriendAttemptedQuizes.FriendAttemptedQuizesHandler;
import main.application.queries.quiz.FriendAttemptedQuizes.FriendAttemptedQuizesQuery;
import main.application.quiz.IQuizAttemptRepository;
import main.application.quiz.IQuizRepository;
import main.application.users.IUserRepository;
import main.domain.Quiz;
import main.domain.QuizAttempt;
import main.domain.User;
import main.domain.enums.UserStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FriendAttemptedQuizesQueryHandlerTests extends TestCase {

    @Test
    public void testWhenEverythingFine_ShouldReturnCorrectResult() {
        FriendAttemptedQuizesQuery query = new FriendAttemptedQuizesQuery("user1");

        IQuizRepository quizRepository = new IQuizRepository() {
            @Override
            public List<Quiz> getNewQuizes() {
                return null;
            }

            @Override
            public List<Quiz> getPopularQuizes() {
                return null;
            }

            @Override
            public List<Quiz> getQuizesByCreatorId(String username) {
                return null;
            }

            @Override
            public List<Quiz> getQuizesByUserAttempts(String username) {
                return null;
            }

            @Override
            public List<Quiz> getQuizesByFriends(String username) {
                return null;
            }

            @Override
            public List<Quiz> searchQuizes(String title) {
                return null;
            }

            @Override
            public Quiz getQuizById(int id) {
                switch (id) {
                    case 1 -> {
                        Quiz q = new Quiz();
                        q.setId(1);
                        q.setCreatorId("user1");
                        q.setDescription("description1");
                        q.setTitle("title1");
                        return q;
                    }
                    case 2 -> {
                        Quiz q = new Quiz();
                        q.setId(2);
                        q.setCreatorId("user2");
                        q.setDescription("description2");
                        q.setTitle("title2");
                        return q;
                    }
                }
                return null;
            }

            @Override
            public void deleteQuiz(int id) {

            }

            @Override
            public int getQuizesNum() {
                return 0;
            }

            @Override
            public int addQuiz(Quiz quiz) {
                return 0;
            }
        };

        IQuizAttemptRepository attemptRepository = new IQuizAttemptRepository() {
            @Override
            public List<QuizAttempt> getQuizzesAttemptedByFriends(String username) {
                if (username.equals(query.getUsername())) {
                    return List.of(
                            new QuizAttempt("user1", 1, 10),
                            new QuizAttempt("user1", 2, 20)
                    );
                }
                throw new RuntimeException("Invalid username");
            }

            @Override
            public void deleteHistory(int quizId) {

            }

            @Override
            public int getQuizAttemptsNum() {
                return 0;
            }

            @Override
            public int getUniqueQuizAttemptsNum() {
                return 0;
            }

            @Override
            public void addQuizAttempt(QuizAttempt quizAttempt) {

            }

            @Override
            public List<QuizAttempt> getQuizAttemptsByUser(String username, int quizId) {
                return null;
            }

            @Override
            public List<QuizAttempt> getTopQuizAttemptsByQuiz(int quizId) {
                return null;
            }

            @Override
            public int getNumberOfTimesTaken(int quizId) {
                return 0;
            }

            @Override
            public int getNumberOfUniqueTimesTaken(int quizId) {
                return 0;
            }

            @Override
            public int getMaxScore(int quizId) {
                return 0;
            }

            @Override
            public double getAverageScore(int quizId) {
                return 0;
            }

            @Override
            public List<QuizAttempt> getFriendsQuizAttempts(String username, int quizId) {
                return null;
            }
        };

        IUserRepository userRepo = new IUserRepository() {
            @Override
            public void addUser(User user) {

            }

            @Override
            public User userExists(String username) {
                return null;
            }

            @Override
            public void removeUser(String username) {

            }

            @Override
            public boolean isUsernameTaken(String username) {
                return false;
            }

            @Override
            public User getUser(String username, String password) {
                return null;
            }

            @Override
            public void changeUserRole(String username, UserStatus newUserStatus) {

            }

            @Override
            public List<User> search(String username) {
                return null;
            }

            @Override
            public int getUsersNum() {
                return 0;
            }
        };

        FriendAttemptedQuizesHandler handler = new FriendAttemptedQuizesHandler(attemptRepository, quizRepository, userRepo);
    }
}
