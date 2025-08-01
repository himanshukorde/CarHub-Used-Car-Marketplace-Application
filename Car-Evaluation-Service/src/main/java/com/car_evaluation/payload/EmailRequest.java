package com.car_evaluation.payload;

public class EmailRequest {

    private String to;
    private String subject;
    private String body;



    public EmailRequest(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }
    public EmailRequest(){
        super();
    }
    public String getTo() {
        return to;
    }
    public String getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return "EmailRequest{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public String getBody() {
        return body;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public void setBody(String body) {
        this.body = body;
    }


}