package MockRepositories;

import main.application.quiz.IQuestionRepository;
import main.domain.Question;

public class MockQuestionRepository implements IQuestionRepository {
    @Override
    public int addQuestion(Question question) {
        return 0;
    }
}
