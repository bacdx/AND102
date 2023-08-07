package com.example.and102;

import java.util.HashMap;
import java.util.Objects;

public class Todo {
    private String id,title,content,date,status;

    public Todo(String id, String title, String content, String date, String status) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.status = status;
    }

    public Todo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public HashMap<String, Object> converHashMap(){
        HashMap<String,Object> work=new HashMap<>();
        work.put("id",id);
        work.put("title",title);
        work.put("content",content);
        work.put("date",date);
        work.put("status",status);
        return work;
    }
}
