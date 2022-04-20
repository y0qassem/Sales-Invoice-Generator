/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salesInvoice.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Youssef Qassem
 */
public class InvoiceLineTable extends AbstractTableModel {

     private ArrayList<InvoiceItem>linesArray;
     private String[] columns ={"Item Name","Price","Count" ,"Line Total"};

    public InvoiceLineTable(ArrayList<InvoiceItem> linesArray) {
        this.linesArray = linesArray;
    }
    @Override
    public int getRowCount() {
        return linesArray == null ? 0 : linesArray.size();
    }
     @Override
    public int getColumnCount() {
        return columns.length;
    } @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (linesArray == null) {
            return "";
        } else {
            InvoiceItem line = linesArray.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return line.getName();
                case 1:
                    return line.getPrice();
                case 2:
                    return line.getCount();
                case 3:
                    return line.getLineTotal();
                default:
                    return "";
            }
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

}
