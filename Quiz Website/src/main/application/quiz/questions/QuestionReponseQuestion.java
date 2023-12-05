package main.application.quiz.questions;

import main.application.quiz.QuestionBase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class QuestionReponseQuestion extends QuestionBase {
    private String question;
    private Set<String> answer;

    public QuestionReponseQuestion(int id, String question, String answer, String image) {
        super(id, question, answer, image);
    }

    /**
     *
     * @param question
     * @param answer if there are multiple correct answers, they should be seperated by commas
     */
    @Override
    protected void initialize(String question, String answer) {
        this.question = question;
        StringTokenizer tokenizer = new StringTokenizer(answer, ",");
        this.answer = new HashSet<>(tokenizer.countTokens());
        while (tokenizer.hasMoreTokens()) {
            this.answer.add(tokenizer.nextToken());
        }
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        sb.append("<p>" + question + "</p>\n");
        if (imageSource != null && !imageSource.isBlank()) {
            sb.append("<a href=\"" + imageSource + "\">");
        }
        sb.append("<input type=\"text\" name=\"" + id + "\" required>\n");
        return sb.toString();
    }

    @Override
    public int getPoints(List<String> answers) {
        return answers.containsAll(answer) ? 1 : 0;
    }
}