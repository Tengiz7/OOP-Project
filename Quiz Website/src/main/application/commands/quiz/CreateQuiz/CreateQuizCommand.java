package main.application.commands.quiz.CreateQuiz;

import main.mediator.Nully;
import main.mediator.abstractions.IRequest;

import java.time.LocalDateTime;
import java.util.List;

public class CreateQuizCommand implements IRequest<CreateQuizCommandResponse> {
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

    public List<CreateQuestionCommand> getQuestions() {
        return questions;
    }

    public void setQuestions(List<CreateQuestionCommand> questions) {
        this.questions = questions;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    private String title;
    private String description;
    private List<CreateQuestionCommand> questions;
    private LocalDateTime createdAt;
    private String creator;

}
