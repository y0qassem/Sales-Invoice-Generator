/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salesInvoice.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Youssef Qassem
 */
public class Header {
      private int invoiceNumber;
      private String customerName;
      private Date invoiceDate;
      private ArrayList<InvoiceItem> items;
    public Header() {
    }

    public Header(int invoiceNumber, String customerName, Date invoiceDate) {
        this.invoiceNumber = invoiceNumber;
        this.customerName = customerName;
        this.invoiceDate = invoiceDate;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public ArrayList<InvoiceItem> getItems() {

        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }
    

    public void setItems(ArrayList<InvoiceItem> items) {
        this.items = items;
    }


  public double getInvoiceTotal(){
    double total=0.0;

    for (int i=0; i< items.size(); i++){
        total+=items.get(i).getLineTotal();
        }
    return total;
    }
}
