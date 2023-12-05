package Application.Queries;

import MockRepositories.MockQuizAttemptRepo;
import MockRepositories.MockQuizRepo;
import junit.framework.TestCase;
import main.application.queries.quiz.GetQuizStatistics.QuizStatisticsQuery;
import main.application.queries.quiz.GetQuizStatistics.QuizStatisticsQueryHandler;
import main.application.queries.quiz.GetQuizStatistics.QuizStatisticsQueryResponse;
import main.application.quiz.IQuizAttemptRepository;
import main.application.quiz.IQuizRepository;
import main.domain.Quiz;
import org.junit.jupiter.api.Test;

public class QuizStatisticsQueryHandlerTests extends TestCase {

    @Test
    public void testWhenEverythingIsFine_ShouldReturnResult() {
        QuizStatisticsQuery query = new QuizStatisticsQuery(1);

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
            public int getNumberOfTimesTaken(int quizId) {
                if (quizId == query.getQuizId()) {
                    return 1;
                }
                throw new RuntimeException("Quiz with id " + quizId + " does not exist");
            }

            @Override
            public int getNumberOfUniqueTimesTaken(int quizId) {
                if (quizId == query.getQuizId()) {
                    return 1;
                }
                throw new RuntimeException("Quiz with id " + quizId + " does not exist");
            }

            @Override
            public int getMaxScore(int quizId) {
                if (quizId == query.getQuizId()) {
                    return 1;
                }
                throw new RuntimeException("Quiz with id " + quizId + " does not exist");
            }

            @Override
            public double getAverageScore(int quizId) {
                if (quizId == query.getQuizId()) {
                    return 1;
                }
                throw new RuntimeException("Quiz with id " + quizId + " does not exist");
            }
        };

        QuizStatisticsQueryHandler handler = new QuizStatisticsQueryHandler(quizRepo, quizAttemptRepo);
        QuizStatisticsQueryResponse response = handler.handle(query);

        assertEquals(1, response.getNumberOfTimesTaken());
        assertEquals(1, response.getNumberOfUniqueTimesTaken());
        assertEquals(1, response.getMaxScore());
        assertEquals(1.0,0, response.getAverageScore());
    }

    @Test
    public void testWhenQuizDoesntExist_ShouldThrowException() {
        QuizStatisticsQuery query = new QuizStatisticsQuery(1);

        IQuizRepository quizRepo = new MockQuizRepo() {
            @Override
            public Quiz getQuizById(int id) {
                if (id == query.getQuizId()) {
                    return null;
                }
                throw new RuntimeException("Quiz with id " + id + " does not exist");
            }
        };

        IQuizAttemptRepository quizAttemptRepo = new MockQuizAttemptRepo() {
            @Override
            public int getNumberOfTimesTaken(int quizId) {
                throw new RuntimeException("Quiz with id " + quizId + " does not exist");
            }

            @Override
            public int getNumberOfUniqueTimesTaken(int quizId) {
                throw new RuntimeException("Quiz with id " + quizId + " does not exist");
            }

            @Override
            public int getMaxScore(int quizId) {
                throw new RuntimeException("Quiz with id " + quizId + " does not exist");
            }

            @Override
            public double getAverageScore(int quizId) {
                throw new RuntimeException("Quiz with id " + quizId + " does not exist");
            }
        };

        QuizStatisticsQueryHandler handler = new QuizStatisticsQueryHandler(quizRepo, quizAttemptRepo);
        try {
            handler.handle(query);
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals("Quiz with id " + query.getQuizId() + " does not exist", e.getMessage());
        }
    }
}
