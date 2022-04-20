/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salesInvoice.controller;

import com.salesInvoice.model.Header;
import com.salesInvoice.model.InvoiceItem;
import com.salesInvoice.model.InvoiceLineTable;
import com.salesInvoice.view.SalesInvoiceGeneratorView;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Youssef Qassem
 */
public class TableSelectionListener  implements ListSelectionListener {
     private SalesInvoiceGeneratorView frame;

    public TableSelectionListener(SalesInvoiceGeneratorView frame) {
        this.frame = frame;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedInvIndex = frame.getInvoiceHeaderTbl().getSelectedRow();
        System.out.println("Invoice selected: " + selectedInvIndex);
        if (selectedInvIndex != -1) {
            Header selectedInv = frame.getInvoicesArray().get(selectedInvIndex);
            ArrayList<InvoiceItem> lines = selectedInv.getItems();
            InvoiceLineTable lineTableModel = new InvoiceLineTable(lines);
            frame.setLinesArray(lines);
            frame.getInvoiceItemsTbl().setModel(lineTableModel);
            frame.getCustNameLbl().setText(selectedInv.getCustomerName());
            frame.getInvNumLbl().setText("" + selectedInv.getInvoiceNumber());
            frame.getInvTotalIbl().setText("" + selectedInv.getInvoiceTotal());
            frame.getInvDateLbl().setText(SalesInvoiceGeneratorView.dateFormat.format(selectedInv.getInvoiceDate()));
        }
    }
}
