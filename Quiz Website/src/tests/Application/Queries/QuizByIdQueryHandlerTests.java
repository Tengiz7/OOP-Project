package Application.Queries;

import MockRepositories.MockQuizRepo;
import junit.framework.TestCase;
import main.application.queries.quiz.QuizById.QuizByIdQuery;
import main.application.queries.quiz.QuizById.QuizByIdQueryHandler;
import main.application.queries.quiz.QuizById.QuizByIdResponse;
import main.application.quiz.IQuizRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class QuizByIdQueryHandlerTests extends TestCase {

    @Test
    public void testWhenIdIsCorrect_ShouldReturnQuiz() {
        QuizByIdQuery query = new QuizByIdQuery(1);
        IQuizRepository repo = new MockQuizRepo() {
            @Override
            public main.domain.Quiz getQuizById(int id) {
                return new main.domain.Quiz(1, "title", "description", LocalDateTime.MIN,"creator");
            }
        };

        QuizByIdQueryHandler handler = new QuizByIdQueryHandler(repo);

        QuizByIdResponse response = handler.handle(query);
        assertEquals(1, response.getId());
        assertEquals("title", response.getTitle());
        assertEquals("description", response.getDescription());
        assertEquals(LocalDateTime.MIN, response.getCreatedAt());
        assertEquals("creator", response.getCreatorId());
    }
}
