package com.example.restservice;

public class Email {
    private String  email, body, subj, id;

    public Email() {
        this.subj = "Tema";
        this.email = "tarik@ukr.net";
        this.body = "aaaa";
        this.id = "";
    }

    public Email( String email, String body, String subj, String id) {
        this.email = email;
        this.body = body;
        this.subj = subj;
        this.id = id;;
    }

    public String getSubj() {
        return subj;
    }

    public void setSubj(String subj) {
        this.subj = subj;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(id=="") {
            this.id = java.util.UUID.randomUUID().toString();
        }
        else {
            this.id = id;
        }
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String toString() {
        return "{subj:" + subj + ";email:" + email + ";body:" + body + "}";
    }
}
