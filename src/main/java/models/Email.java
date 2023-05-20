package models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
public class Email {
    private String from;
    private String to;
    private String subject;
    private String body;
    private List<String> imgs;

    public Email(String from, String to, String subject, String body, ArrayList<String> imgs) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.imgs = imgs;
    }
}
