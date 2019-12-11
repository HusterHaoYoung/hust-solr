package com.hust.hustsearch.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author :younghao
 * @ClassName: Webnews
 * @Description: TODO
 * @date : 12/10/19  5:23 PM
 */
@Entity
public class Webnews {
    private int id;
    private String title;
    private String link;
    private int wbid;
    private String ownerName;
    private String editor;
    private int showtimes;
    private String summary;
    private String content;
    private Date date;
    private String wid;

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

    public int getWbid() {
        return wbid;
    }

    public void setWbid(int wbid) {
        this.wbid = wbid;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public int getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(int showtimes) {
        this.showtimes = showtimes;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public Webnews() {
    }
}
