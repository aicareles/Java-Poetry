package com.jerry.poetry.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Game implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String nick_name;

    private String game_info;

    private String game_score;

    //文件上传日期  详细
    private long date;

    //文件上传日期   天
    private String day;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getGame_info() {
        return game_info;
    }

    public void setGame_info(String game_info) {
        this.game_info = game_info;
    }

    public String getGame_score() {
        return game_score;
    }

    public void setGame_score(String game_score) {
        this.game_score = game_score;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
