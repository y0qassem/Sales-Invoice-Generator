/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salesInvoice.model;
import com.salesInvoice.view.SalesInvoiceGeneratorView;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Youssef Qassem
 */
public class InvoiceHeaderLineTable extends AbstractTableModel  {
     private ArrayList<Header> invoicesArray;
    private String[] columns = {"Invoice Num", "Invoice Date", "Customer Name"};

    public InvoiceHeaderLineTable(ArrayList<Header> invoicesArray) {
        this.invoicesArray = invoicesArray;
    }

 @Override
    public int getRowCount() {
        return invoicesArray.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Header inv = invoicesArray.get(rowIndex);
        switch (columnIndex) {
            case 0: return inv.getInvoiceNumber();
            case 1: return SalesInvoiceGeneratorView.dateFormat.format(inv.getInvoiceDate());
            case 2: return inv.getCustomerName();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

}
