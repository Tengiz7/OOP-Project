package main.application.quiz.questions;

import java.util.*;

public class BlankQuestion extends main.application.quiz.QuestionBase {

    private List<String> question;
    private List<Set<String>> probableAnswers;

    public BlankQuestion(int id, String question, String answer, String image) {
        super(id, question, answer, image);
    }

    /**
     * @param question the first character must be the delimiter,
     *                 where the blank must later be. there must be a
     *                 character at the end of the sentence if a blank
     *                 is to be the last word in the sentence
     * @param answer the first character in 'answer' should be the delimiter,
     *               seperating answers for different blanks
     */
    @Override
    protected void initialize(String question, String answer) {
        StringTokenizer tokenizer = new StringTokenizer(answer.substring(1), answer.charAt(0) + "");
        probableAnswers = new ArrayList<>(tokenizer.countTokens());
        while (tokenizer.hasMoreTokens()) {
            StringTokenizer answerTokenizer = new StringTokenizer(tokenizer.nextToken(), ",");
            probableAnswers.add(new HashSet<String>(answerTokenizer.countTokens()));
            while (answerTokenizer.hasMoreTokens()) {
                probableAnswers.get(probableAnswers.size() - 1).add(answerTokenizer.nextToken());
            }
        }
        StringTokenizer questionTokenizer = new StringTokenizer(question.substring(1), question.charAt(0) + "");
        this.question = new ArrayList<>(questionTokenizer.countTokens());
        while (questionTokenizer.hasMoreTokens()) {
            this.question.add(questionTokenizer.nextToken());
        }
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        sb.append("<p>");
        if (imageSource != null && !imageSource.isBlank()) {
            sb.append("<a href=\"" + imageSource + "\">");
        }
        for(int i = 0; i < question.size() - 1; i++) {
            sb.append(question.get(i));
            sb.append("<input type=\"text\" name=\"" + id + "\" required>");
        }
        sb.append(question.get(question.size() - 1));
        sb.append("</p>\n");
        return sb.toString();
    }

    @Override
    public int getPoints(List<String> answers) {
        int score = 0;
        for(int i = 0; i < probableAnswers.size(); i++) {
            if(probableAnswers.get(i).contains(answers.get(i))) {
                score++;
            }
        }
        return score;
    }

}
