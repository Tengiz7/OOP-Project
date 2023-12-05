package main.domain;

import main.domain.enums.QuestionType;

public class Question {
    public Question(int id, int quizId, String question, String answer, String imageSource, QuestionType type) {
        this.id = id;
        this.quizId = quizId;
        this.question = question;
        this.answer = answer;
        this.imageSource = imageSource;
        this.type = type;
    }

    public Question(int quizId, String question, String answer, String imageSource, QuestionType type) {
        this.quizId = quizId;
        this.question = question;
        this.answer = answer;
        this.imageSource = imageSource;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getImageSource() {
        return imageSource;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    private int id;
    private int quizId;
    private String question;
    private String answer;
    private String imageSource;

    public QuestionType getType() {
        return type;
    }

    private QuestionType type;
}
