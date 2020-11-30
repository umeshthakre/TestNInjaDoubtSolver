package com.example.testninjademo;

public class Doubt {
    private String title,description,subject,answer;


    public Doubt(String title, String description, String subject,String answer) {
        this.title = title;
        this.description = description;
        this.subject = subject;
        this.answer = answer;
    }

    public Doubt() {
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
