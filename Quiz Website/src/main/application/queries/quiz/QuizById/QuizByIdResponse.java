package main.application.queries.quiz.QuizById;

import main.application.quiz.QuestionBase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuizByIdResponse {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<QuestionBase> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionBase> questions) {
        this.questions = questions;
    }

    public void addQuestion(QuestionBase question) {
        questions.add(question);
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    private int id;

    public QuizByIdResponse(int id, String title, String description, LocalDateTime createdAt, String creatorId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.creatorId = creatorId;
        questions = new ArrayList<>();
    }

    private String title;
    private String description;
    private List<QuestionBase> questions;
    private LocalDateTime createdAt;
    private String creatorId;
}
