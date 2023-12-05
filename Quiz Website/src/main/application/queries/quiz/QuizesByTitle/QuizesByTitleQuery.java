package main.application.queries.quiz.QuizesByTitle;

import main.application.queries.quiz.QuizesResponse;
import main.mediator.abstractions.IRequest;

import java.util.List;

public class QuizesByTitleQuery implements IRequest<List<QuizesResponse>> {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
}
