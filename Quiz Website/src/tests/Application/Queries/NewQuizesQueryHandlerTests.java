package Application.Queries;

import MockRepositories.MockQuizRepo;
import junit.framework.TestCase;
import main.application.queries.quiz.NewQuizes.NewQuizesQuery;
import main.application.queries.quiz.NewQuizes.NewQuizesQueryHandler;
import main.application.queries.quiz.NewQuizes.NewQuizesResponse;
import main.application.quiz.IQuizRepository;
import main.domain.Quiz;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewQuizesQueryHandlerTests extends TestCase {

    @Test
    public void testWhenEverythingFine_ShouldReturnCorrectResult() {
        IQuizRepository quizRepo = new MockQuizRepo() {
            @Override
            public List<Quiz> getNewQuizes() {
                List<Quiz> result = new ArrayList<>();
                result.add(new Quiz(1, "title1", "description1", LocalDateTime.MIN, "1"));
                result.add(new Quiz(2, "title2", "description2", LocalDateTime.MIN, "2"));
                result.add(new Quiz(3, "title3", "description3", LocalDateTime.MIN, "3"));
                return result;
            }
        };

        NewQuizesQueryHandler handler = new NewQuizesQueryHandler(quizRepo);

        List<NewQuizesResponse> result = handler.handle(new NewQuizesQuery());

        assertEquals(3, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("title1", result.get(0).getTitle());
        assertEquals("description1", result.get(0).getDescription());
        assertEquals(LocalDateTime.MIN, result.get(0).getCreatedAt());
        assertEquals("1", result.get(0).getCreatorId());
        assertEquals(2, result.get(1).getId());
        assertEquals("title2", result.get(1).getTitle());
        assertEquals("description2", result.get(1).getDescription());
        assertEquals(LocalDateTime.MIN, result.get(1).getCreatedAt());
        assertEquals("2", result.get(1).getCreatorId());
    }
}
