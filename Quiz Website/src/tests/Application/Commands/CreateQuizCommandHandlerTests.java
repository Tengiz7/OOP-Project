package Application.Commands;

import MockRepositories.MockQuestionRepository;
import MockRepositories.MockQuizRepo;
import MockRepositories.MockUserRepo;
import junit.framework.TestCase;
import main.application.commands.quiz.CreateQuiz.CreateQuestionCommand;
import main.application.commands.quiz.CreateQuiz.CreateQuizCommand;
import main.application.commands.quiz.CreateQuiz.CreateQuizCommandHandler;
import main.application.commands.quiz.CreateQuiz.CreateQuizCommandResponse;
import main.application.quiz.IQuestionRepository;
import main.application.quiz.IQuizRepository;
import main.application.users.IUserRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateQuizCommandHandlerTests extends TestCase {

    @Test
    public void testWhenEverythingIsFine_ShouldReturnQuizId() {
        // Arrange
        CreateQuizCommand command = new CreateQuizCommand();
        command.setCreator("creator");
        command.setTitle("title");
        command.setDescription("description");
        command.setCreatedAt(LocalDateTime.now());
        List<CreateQuestionCommand> questions = new ArrayList<>();
        questions.add(new CreateQuestionCommand());
        command.setQuestions(questions);

        IQuizRepository quizRepo = new MockQuizRepo() {
            @Override
            public int addQuiz(main.domain.Quiz quiz) {
                return 1;
            }
        };
        IUserRepository userRepo = new MockUserRepo() {
            @Override
            public boolean isUsernameTaken(String username) {
                return username.equals(command.getCreator());
            }
        };
        IQuestionRepository questionRepo = new MockQuestionRepository() {
            @Override
            public int addQuestion(main.domain.Question question) {
                return 1;
            }
        };
        // Act
        CreateQuizCommandHandler handler = new CreateQuizCommandHandler(quizRepo, userRepo, questionRepo);
        CreateQuizCommandResponse response = handler.handle(command);
        // Assert
        assertEquals(1, response.getQuizId());
    }

    @Test
    public void testWhenUserDontExist_ShouldThrowException() {
        // Arrange
        CreateQuizCommand command = new CreateQuizCommand();
        command.setCreator("creator");
        command.setTitle("title");
        command.setDescription("description");
        command.setCreatedAt(LocalDateTime.now());
        List<CreateQuestionCommand> questions = new ArrayList<>();
        questions.add(new CreateQuestionCommand());
        command.setQuestions(questions);

        IQuizRepository quizRepo = new MockQuizRepo() {
            @Override
            public int addQuiz(main.domain.Quiz quiz) {
                return 1;
            }
        };
        IUserRepository userRepo = new MockUserRepo() {
            @Override
            public boolean isUsernameTaken(String username) {
                return false;
            }
        };
        IQuestionRepository questionRepo = new MockQuestionRepository() {
            @Override
            public int addQuestion(main.domain.Question question) {
                return 1;
            }
        };
        // Act
        CreateQuizCommandHandler handler = new CreateQuizCommandHandler(quizRepo, userRepo, questionRepo);
        // Assert
        assertThrows(RuntimeException.class, () -> handler.handle(command));
        try {
            handler.handle(command);
            fail("Expected exception not thrown");
        } catch (RuntimeException e) {
            assertEquals("User with username " + command.getCreator() + " does not exist", e.getMessage());
        }
    }

    @Test
    public void testWhenSomethingWrongWithInsert_ShouldThrowException() {
        // Arrange
        CreateQuizCommand command = new CreateQuizCommand();
        command.setCreator("creator");
        command.setTitle("title");
        command.setDescription("description");
        command.setCreatedAt(LocalDateTime.now());
        List<CreateQuestionCommand> questions = new ArrayList<>();
        questions.add(new CreateQuestionCommand());
        command.setQuestions(questions);

        IQuizRepository quizRepo = new MockQuizRepo() {
            @Override
            public int addQuiz(main.domain.Quiz quiz) {
                return -1;
            }
        };
        IUserRepository userRepo = new MockUserRepo() {
            @Override
            public boolean isUsernameTaken(String username) {
                return true;
            }
        };
        IQuestionRepository questionRepo = new MockQuestionRepository() {
            @Override
            public int addQuestion(main.domain.Question question) {
                return 1;
            }
        };
        // Act
        CreateQuizCommandHandler handler = new CreateQuizCommandHandler(quizRepo, userRepo, questionRepo);
        // Assert
        assertThrows(RuntimeException.class, () -> handler.handle(command));
        try {
            handler.handle(command);
            fail("Expected exception not thrown");
        } catch (RuntimeException e) {
            assertEquals("Something happened while adding your quiz please try again later", e.getMessage());
        }
    }
}
