package Application.Commands;

import MockRepositories.MockQuizAttemptRepo;
import MockRepositories.MockQuizRepo;
import MockRepositories.MockUserRepo;
import junit.framework.TestCase;
import main.application.commands.quiz.AddQuizAttempt.AddQuizAttemptCommandHandler;
import main.application.commands.quiz.AddQuizAttempt.AddQuizAttemptRequest;
import main.application.quiz.IQuizAttemptRepository;
import main.application.quiz.IQuizRepository;
import main.application.users.IUserRepository;
import main.domain.Quiz;
import main.domain.QuizAttempt;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddQuizAttemptCommandHandlerTests extends TestCase {

    @Test
    public void testWhenEverythingIsCorrect_ShouldInsertQuizAttempt() {
        // Arrange
        AddQuizAttemptRequest request = new AddQuizAttemptRequest();
        request.setQuizId(1);
        request.setUsername("username");
        request.setScore(10);

        IQuizAttemptRepository repo = new MockQuizAttemptRepo() {
            @Override
            public void addQuizAttempt(QuizAttempt quizAttempt) {
                if (!(quizAttempt.getQuizId() == request.getQuizId()
                        && quizAttempt.getUsername().equals(request.getUsername())
                        && quizAttempt.getScore() == request.getScore())) {
                    throw new RuntimeException("Invalid data");
                }
            }
        };
        IUserRepository userRepository = new MockUserRepo() {
            @Override
            public boolean isUsernameTaken(String username) {
                return username.equals(request.getUsername());
            }
        };
        IQuizRepository quizRepository = new MockQuizRepo() {
            @Override
            public Quiz getQuizById(int id) {
                if (id == request.getQuizId())
                    return new Quiz();
                return null;
            }
        };
        // Act
        AddQuizAttemptCommandHandler handler = new AddQuizAttemptCommandHandler(quizRepository, userRepository, repo);
        // Assert
        assertNotNull(handler.handle(request));
    }

    @Test
    public void testWhenUserDoesntExist_ShouldThrowException() {
        // Arrange
        AddQuizAttemptRequest request = new AddQuizAttemptRequest();
        request.setQuizId(1);
        request.setUsername("username");
        request.setScore(10);

        IQuizAttemptRepository repo = new MockQuizAttemptRepo();
        IUserRepository userRepository = new MockUserRepo() {
            @Override
            public boolean isUsernameTaken(String username) {
                return false;
            }
        };
        IQuizRepository quizRepository = new MockQuizRepo() {
            @Override
            public Quiz getQuizById(int id) {
                if (id == request.getQuizId())
                    return new Quiz();
                return null;
            }
        };
        // Act
        AddQuizAttemptCommandHandler handler = new AddQuizAttemptCommandHandler(quizRepository, userRepository, repo);
        // Assert
        assertThrows(RuntimeException.class, () -> handler.handle(request));
        try {
            handler.handle(request);
            fail("Expected exception");
        } catch (RuntimeException e) {
            assertEquals("User does not exist", e.getMessage());
        }
    }

    @Test
    public void testWhenQuizDoesntExist_ShouldThrowException() {
        // Arrange
        AddQuizAttemptRequest request = new AddQuizAttemptRequest();
        request.setQuizId(1);
        request.setUsername("username");
        request.setScore(10);

        IQuizAttemptRepository repo = new MockQuizAttemptRepo();
        IUserRepository userRepository = new MockUserRepo() {
            @Override
            public boolean isUsernameTaken(String username) {
                return true;
            }
        };
        IQuizRepository quizRepository = new MockQuizRepo() {
            @Override
            public Quiz getQuizById(int id) {
                return null;
            }
        };
        // Act
        AddQuizAttemptCommandHandler handler = new AddQuizAttemptCommandHandler(quizRepository, userRepository, repo);
        // Assert
        assertThrows(RuntimeException.class, () -> handler.handle(request));
        try {
            handler.handle(request);
            fail("Expected exception");
        } catch (RuntimeException e) {
            assertEquals("Quiz does not exist", e.getMessage());
        }
    }

}
