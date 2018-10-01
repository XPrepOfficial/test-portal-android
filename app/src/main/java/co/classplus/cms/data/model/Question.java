package co.classplus.cms.data.model;

import java.util.ArrayList;

public class Question {

    private CmsNameId paragraph;
    private boolean hasMultipleAnswer;
    private int positiveMarks;
    private int negativeMarks;
    private ArrayList<CmsNameId> options;

    public CmsNameId getParagraph() {
        return paragraph;
    }

    public void setParagraph(CmsNameId paragraph) {
        this.paragraph = paragraph;
    }

    public boolean isHasMultipleAnswer() {
        return hasMultipleAnswer;
    }

    public void setHasMultipleAnswer(boolean hasMultipleAnswer) {
        this.hasMultipleAnswer = hasMultipleAnswer;
    }

    public int getPositiveMarks() {
        return positiveMarks;
    }

    public void setPositiveMarks(int positiveMarks) {
        this.positiveMarks = positiveMarks;
    }

    public int getNegativeMarks() {
        return negativeMarks;
    }

    public void setNegativeMarks(int negativeMarks) {
        this.negativeMarks = negativeMarks;
    }

    public ArrayList<CmsNameId> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<CmsNameId> options) {
        this.options = options;
    }
}
