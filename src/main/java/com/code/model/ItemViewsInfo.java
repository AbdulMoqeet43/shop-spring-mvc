package com.code.model;

import java.security.Timestamp;

public class ItemViewsInfo {
    private int id;
    private int userid;
    private int itemid;
    private Timestamp viewAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public Timestamp getViewAt() {
        return viewAt;
    }

    public void setViewAt(Timestamp viewAt) {
        this.viewAt = viewAt;
    }

    public ItemViewsInfo(int id, int userid, int itemid, Timestamp viewAt) {
        this.id = id;
        this.userid = userid;
        this.itemid = itemid;
        this.viewAt = viewAt;
    }

    @Override
    public String toString() {
        return "ItemViewsInfo{" +

                ", userid=" + userid +
                ", itemid=" + itemid +
                ", viewAt=" + viewAt +
                '}';
    }
}
