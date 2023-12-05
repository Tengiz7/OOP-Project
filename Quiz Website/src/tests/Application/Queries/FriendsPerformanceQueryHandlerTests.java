package Application.Queries;

import MockRepositories.MockQuizAttemptRepo;
import MockRepositories.MockQuizRepo;
import MockRepositories.MockUserRepo;
import junit.framework.TestCase;
import main.application.queries.quiz.GetFriendsPerformance.FriendsPerformanceQuery;
import main.application.queries.quiz.GetFriendsPerformance.FriendsPerformanceQueryHandler;
import main.application.queries.quiz.GetFriendsPerformance.FriendsPerformanceQueryResponse;
import main.application.quiz.IQuizAttemptRepository;
import main.application.quiz.IQuizRepository;
import main.application.users.IUserRepository;
import main.domain.Quiz;
import main.domain.QuizAttempt;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FriendsPerformanceQueryHandlerTests extends TestCase {

    @Test
    public void testWhenEverythingFine_ShouldReturnCorrectResult() {
        FriendsPerformanceQuery query = new FriendsPerformanceQuery();
        query.setUsername("username");
        query.setQuizId(1);

        IUserRepository userRepo = new MockUserRepo() {
            @Override
            public boolean isUsernameTaken(String username) {
                return username.equals(query.getUsername());
            }
        };

        IQuizRepository quizRepo = new MockQuizRepo() {
            @Override
            public Quiz getQuizById(int id) {
                if (id == query.getQuizId()) {
                    return new Quiz();
                }
                throw new RuntimeException("Quiz with id " + id + " does not exist");
            }
        };

        IQuizAttemptRepository quizAttemptRepo = new MockQuizAttemptRepo() {
            @Override
            public List<QuizAttempt> getFriendsQuizAttempts(String username, int quizId) {
                if (username.equals(query.getUsername()) && quizId == query.getQuizId()) {
                    List<QuizAttempt> result = new ArrayList<>();
                    result.add(new QuizAttempt("friend1", 1, 10));
                    result.add(new QuizAttempt("friend2", 1, 20));
                    return result;
                }
                throw new RuntimeException("No attempts found");
            }
        };

        FriendsPerformanceQueryHandler handler = new FriendsPerformanceQueryHandler(userRepo, quizRepo, quizAttemptRepo);

        List<FriendsPerformanceQueryResponse> result = handler.handle(query);

        assertEquals(2, result.size());
        assertEquals("friend1", result.get(0).getUsername());
        assertEquals(10, result.get(0).getScore());
        assertEquals("friend2", result.get(1).getUsername());
        assertEquals(20, result.get(1).getScore());
    }

    @Test
    public void testWhenUserDoesntExist_ShouldThrowException() {
        FriendsPerformanceQuery query = new FriendsPerformanceQuery();
        query.setUsername("username");
        query.setQuizId(1);

        IUserRepository userRepo = new MockUserRepo() {
            @Override
            public boolean isUsernameTaken(String username) {
                return false;
            }
        };

        IQuizRepository quizRepo = new MockQuizRepo() {
            @Override
            public Quiz getQuizById(int id) {
                if (id == query.getQuizId()) {
                    return new Quiz();
                }
                throw new RuntimeException("Quiz with id " + id + " does not exist");
            }
        };

        IQuizAttemptRepository quizAttemptRepo = new MockQuizAttemptRepo() {
            @Override
            public List<QuizAttempt> getFriendsQuizAttempts(String username, int quizId) {
                if (username.equals(query.getUsername()) && quizId == query.getQuizId()) {
                    List<QuizAttempt> result = new ArrayList<>();
                    result.add(new QuizAttempt("friend1", 1, 10));
                    result.add(new QuizAttempt("friend2", 1, 20));
                    return result;
                }
                throw new RuntimeException("No attempts found");
            }
        };

        FriendsPerformanceQueryHandler handler = new FriendsPerformanceQueryHandler(userRepo, quizRepo, quizAttemptRepo);

        try {
            handler.handle(query);
            fail("Expected exception");
        } catch (RuntimeException ex) {
            assertEquals("User with username " + query.getUsername() + " does not exist", ex.getMessage());
        }
    }

    @Test
    public void testWhenQuizDoesntExist_ShouldThrowException() {
        FriendsPerformanceQuery query = new FriendsPerformanceQuery();
        query.setUsername("username");
        query.setQuizId(1);

        IUserRepository userRepo = new MockUserRepo() {
            @Override
            public boolean isUsernameTaken(String username) {
                return true;
            }
        };

        IQuizRepository quizRepo = new MockQuizRepo() {
            @Override
            public Quiz getQuizById(int id) {
                throw new RuntimeException("Quiz with id " + id + " does not exist");
            }
        };

        IQuizAttemptRepository quizAttemptRepo = new MockQuizAttemptRepo() {
            @Override
            public List<QuizAttempt> getFriendsQuizAttempts(String username, int quizId) {
                if (username.equals(query.getUsername()) && quizId == query.getQuizId()) {
                    List<QuizAttempt> result = new ArrayList<>();
                    result.add(new QuizAttempt("friend1", 1, 10));
                    result.add(new QuizAttempt("friend2", 1, 20));
                    return result;
                }
                throw new RuntimeException("No attempts found");
            }
        };

        FriendsPerformanceQueryHandler handler = new FriendsPerformanceQueryHandler(userRepo, quizRepo, quizAttemptRepo);

        try {
            handler.handle(query);
            fail("Expected exception");
        } catch (RuntimeException ex) {
            assertEquals("Quiz with id " + query.getQuizId() + " does not exist", ex.getMessage());
        }
    }
}
