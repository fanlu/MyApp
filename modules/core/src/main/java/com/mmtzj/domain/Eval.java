package com.mmtzj.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-1-8
 * Time: 下午9:05
 * To change this template use File | Settings | File Templates.
 */
public class Eval implements Serializable {

    private int id;

    private String eval;

    private int itemId;

    private String picId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEval() {
        return eval;
    }

    public void setEval(String eval) {
        this.eval = eval;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }
}
