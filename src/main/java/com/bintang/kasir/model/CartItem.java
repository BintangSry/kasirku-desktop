package com.bintang.kasir.model;

public class CartItem {
    public int id;
    public String name;
    public int price;
    public int qty;
    public int maxStock;
    public String image;

    public CartItem(int id, String name, int price, int qty, int maxStock, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.maxStock = maxStock;
        this.image = image;
    }
}
