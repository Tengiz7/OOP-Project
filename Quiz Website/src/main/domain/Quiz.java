package main.domain;

import main.domain.enums.QuestionType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import main.domain.Question;

public class Quiz {

    public Quiz(int id, String title, String description, List<Question> questions, LocalDateTime createdAt, String creatorId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.questions = questions;
        this.createdAt = createdAt;
        this.creatorId = creatorId;
    }

    public Quiz(int id, String title, String description, LocalDateTime createdAt, String creatorId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.questions = new ArrayList<>();
        this.createdAt = createdAt;
        this.creatorId = creatorId;
    }

    public Quiz() {

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    private int id;
    private String title;
    private String description;
    private List<Question> questions;
    private LocalDateTime createdAt;
    private String creatorId;

}
