package com.mmtzj.domain;

/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-1-6
 * Time: 下午10:56
 * To change this template use File | Settings | File Templates.
 */
public class Category {

    private int id;

    private String name;

    private String title;

    private int rank;

    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
