package Infrastructure;

import junit.framework.TestCase;
import main.domain.Quiz;
import main.domain.User;
import main.infrastructure.ChallengeRepository;
import main.infrastructure.QuizRepository;
import main.infrastructure.UserRepository;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ChallengeRepositoryTests extends TestCase {

    private int quizId;
    private ChallengeRepository challengeRepository;
    private UserRepository userRepository;
    private QuizRepository quizRepository;
    private BasicDataSource dataSource;

    public ChallengeRepositoryTests() {
        this.dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/oop_db_test");
        dataSource.setUsername("test");
        dataSource.setPassword("password");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        try {
            this.challengeRepository = new ChallengeRepository(dataSource.getConnection());
            this.userRepository = new UserRepository(dataSource.getConnection());
            this.quizRepository = new QuizRepository(dataSource.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @BeforeEach
    public void setUp() throws SQLException {
        dataSource.getConnection().prepareStatement("DELETE FROM challenges").execute();
    }

    @BeforeAll
    public void setUpAll() throws SQLException {
        Quiz quiz = new Quiz();
        quiz.setTitle("quiz");
        quiz.setCreatorId("sender");
        quiz.setDescription("description");
        quiz.setCreatedAt(LocalDateTime.now());
        userRepository.addUser(new User("sender", "pas"));
        userRepository.addUser(new User("receiver", "pas"));
        quizId = quizRepository.addQuiz(quiz);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.prepareStatement("DELETE FROM challenges").execute();
        connection.prepareStatement("DELETE FROM users").execute();
        connection.prepareStatement("DELETE FROM quizzes").execute();
    }

    public void testAddChallenge() throws SQLException {
        setUpAll();
        challengeRepository.addChallenge("sender", "receiver", quizId, "message", LocalDateTime.now());
        assertEquals(1, challengeRepository.getReceivedChallenges("receiver").size());
        tearDown();
    }

    public void testGetReceivedChallenges() throws SQLException {
        setUpAll();
        challengeRepository.addChallenge("sender", "receiver", quizId, "message", LocalDateTime.now());
        assertEquals(1, challengeRepository.getReceivedChallenges("receiver").size());
        tearDown();
    }
}
