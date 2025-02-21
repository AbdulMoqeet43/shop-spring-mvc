package com.code.model;

public class ShoppingCartInfo {
    private int id;
    private int userid;
    private int itemid;

    private int quantity;

    private  double price;
    private String name;


    public ShoppingCartInfo(int userid, int itemid, int quantity, String name, double price) {
        this.userid = userid;
        this.itemid = itemid;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ShoppingCartInfo{" +
                "id=" + id +
                ", userid=" + userid +
                ", itemid=" + itemid +
                ", quantity=" + quantity +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
