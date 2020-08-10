package com.example.trivia.Model;

public class Question {
    private String answer;
    private boolean value;

    public Question() {

    }

    public Question(String answer, boolean value) {
        this.answer = answer;
        this.value = value;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Question{" +
                "answer='" + answer + '\'' +
                ", value=" + value +
                '}';
    }
}
