package com.jerry.girl.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Poetry implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    private String yunlv_rule;

    private Integer author_id;

    private String content;

    private String dynasty;

    private String author;

    public Poetry() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYunlv_rule() {
        return yunlv_rule;
    }

    public void setYunlv_rule(String yunlv_rule) {
        this.yunlv_rule = yunlv_rule;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Poetry{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", yunlv_rule='" + yunlv_rule + '\'' +
                ", author_id=" + author_id +
                ", content='" + content + '\'' +
                ", dynasty='" + dynasty + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
