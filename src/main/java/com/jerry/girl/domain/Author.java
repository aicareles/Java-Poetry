package com.jerry.girl.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Author implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer author_id;

    private String name;

    private String intro_l;

    private String intro_s;

    private String dynasty;

    public Author() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro_l() {
        return intro_l;
    }

    public void setIntro_l(String intro_l) {
        this.intro_l = intro_l;
    }

    public String getIntro_s() {
        return intro_s;
    }

    public void setIntro_s(String intro_s) {
        this.intro_s = intro_s;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", intro_l='" + intro_l + '\'' +
                ", intro_s='" + intro_s + '\'' +
                ", dynasty='" + dynasty + '\'' +
                '}';
    }
}
