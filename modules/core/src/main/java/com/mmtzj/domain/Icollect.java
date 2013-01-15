package com.mmtzj.domain;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-1-15
 * Time: 下午8:42
 * To change this template use File | Settings | File Templates.
 */
public class Icollect {

    private int id;

    private String ip;

    private Date creationTime;

    private int itemId;

    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
