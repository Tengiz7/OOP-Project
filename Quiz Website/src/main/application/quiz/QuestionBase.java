package main.application.quiz;

import java.util.List;

public abstract class QuestionBase {

    protected int id;
    protected String imageSource;

    public QuestionBase(int id, String question, String answer, String image) {
        this.id = id;
        imageSource = image;
        initialize(question, answer);
    }

    protected abstract void initialize(String question, String answer);

    /**
     * Returns an html representation of the qeustion and answers
     * @return
     */
    public abstract String render();

    /**
     * Given a list of strings answers, calculates appropriate points
     * @param answers
     * @return
     */
    public abstract int getPoints(List<String> answers);

    public int getId() {
        return id;
    }
  
    public String getImageSource() {
        return imageSource;
    }
}
