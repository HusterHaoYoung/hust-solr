package com.hust.hustsearch.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ResearchResult {
    private int id;
    private String title;
    private String link;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ResearchResult(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public ResearchResult() {
    }

    @Override
    public String toString() {
        return "ResearchResult{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
