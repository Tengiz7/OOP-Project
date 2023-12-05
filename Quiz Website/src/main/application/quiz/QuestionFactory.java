package main.application.quiz;

import main.application.quiz.questions.BlankQuestion;
import main.application.quiz.questions.MultipleChoiceQuestion;
import main.application.quiz.questions.QuestionReponseQuestion;
import main.domain.enums.QuestionType;

import java.util.HashMap;
import java.util.Map;

public class QuestionFactory {

    private static Map<QuestionType, Class<? extends QuestionBase>> map = new HashMap<>();

    static {
        map.put(QuestionType.MULTIPLE_CHOICE, MultipleChoiceQuestion.class);
        map.put(QuestionType.BLANK, BlankQuestion.class);
        map.put(QuestionType.QUESTION_RESPONSE, QuestionReponseQuestion.class);
    }

    public static QuestionBase getQuestionForType(QuestionType type, int id, String question, String answer, String image) {
        Class<? extends QuestionBase> aClass = map.get(type);
        if(aClass == null) {
            throw new RuntimeException("No given class for type " + type.toString());
        }
        try {
            QuestionBase result = aClass.getDeclaredConstructor(int.class, String.class, String.class, String.class).newInstance(id, question, answer, image);
            return result;
        } catch (Exception exception) {
            throw new RuntimeException("Something went wrong in QuestionFactory.getQuestionForType", exception);
        }
    }
}
