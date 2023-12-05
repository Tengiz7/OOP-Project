package main.application.quiz.questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import main.application.quiz.QuestionBase;

public class MultipleChoiceQuestion extends QuestionBase {

    private String question;
    private List<String> answers;
    private Set<String> correctAnswers;

    public MultipleChoiceQuestion(int id, String question, String answer, String image) {
        super(id, question, answer, image);
    }



    /**
     *
     * @param question
     * @param answer in the string 'answer', the first character
     *               should be the delimiter
     *               the first character in the answers category should be
     *               + for a correct answer, and - for incorrect ones
     */
    @Override
    protected void initialize(String question, String answer) {
        this.question = question;
        StringTokenizer tokenizer = new StringTokenizer(answer.substring(1), answer.charAt(0) + "");
        answers = new ArrayList<>(tokenizer.countTokens());
        correctAnswers = new java.util.HashSet<>();
        int count = 0;
        while(tokenizer.hasMoreTokens()) {
            String str = tokenizer.nextToken();
            if(str.charAt(0) == '+') {
                correctAnswers.add(str.substring(1));
            }
            answers.add(str.substring(1));
            count++;
        }
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        sb.append("<p>" + question + "</p>\n");
        if (imageSource != null && !imageSource.isBlank()) {
            sb.append("<a href=\"" + imageSource + "\">");
        }
        for(int i = 0; i < answers.size(); i++) {
            sb.append("<input type=\"checkbox\" name=\"" + id + "\"id=\"" + id + "\" value=\"" + answers.get(i) + "\">\n");
            sb.append("<label for=\"" + id + "\">" + answers.get(i) + "</label><br>\n");
        }
        return sb.toString();
    }

    @Override
    public int getPoints(List<String> answers) {
        int points = 0;
        for(int i = 0; i < answers.size(); i++) {
            if (correctAnswers.contains(answers.get(i))) {
                points++;
            }
        }
        return points;
    }

}
