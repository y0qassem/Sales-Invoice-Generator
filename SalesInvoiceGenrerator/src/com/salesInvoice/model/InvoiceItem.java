/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salesInvoice.model;

/**
 *
 * @author Youssef Qassem
 */
public class InvoiceItem {
private String name;
private double price;
private int count;
private Header header;   

    public InvoiceItem(String name, double price, int count, Header header) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.header = header;
    }

    public InvoiceItem() {
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }
    public double getLineTotal(){
     return price*count ;
    }
    @Override
    public String toString() {
        return "InvoiceLine{" + "item=" + name + ", price=" + price + ", count=" + count + ", header=" + header + '}';
    }
}
