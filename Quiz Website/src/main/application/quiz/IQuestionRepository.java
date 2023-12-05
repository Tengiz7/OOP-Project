package main.application.quiz;

import main.domain.Question;

public interface IQuestionRepository {
    public int addQuestion(Question question);
}
