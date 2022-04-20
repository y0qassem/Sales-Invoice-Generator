/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salesInvoice.controller;

import com.salesInvoice.model.Header;
import com.salesInvoice.model.InvoiceHeaderLineTable;
import com.salesInvoice.model.InvoiceItem;
import com.salesInvoice.model.InvoiceLineTable;
import com.salesInvoice.view.SalesInvoiceGeneratorView;
import com.salesInvoice.view.InvoiceHeaderDialog;
import com.salesInvoice.view.InvoiceLineDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Youssef Qassem
 */
public class Listener implements ActionListener {
    private SalesInvoiceGeneratorView frame;
     private InvoiceHeaderDialog headerDialog;
    private InvoiceLineDialog lineDialog;

    public Listener(SalesInvoiceGeneratorView frame) {
        this.frame = frame;
    }
   


    @Override
    public void actionPerformed(ActionEvent e){
    switch(e.getActionCommand()){
        case "Load File":
                JOptionPane.showMessageDialog(null,"Please, import invoices header file and invoices lines file .csv","Dialog",JOptionPane.PLAIN_MESSAGE);
                loadFilesInvoices();
                
                break;

        case"saveFile":
           
            break;
        case"Create New Invoice":
            createNewInvoice();
            break;
        case"Delete Invoice":
             deleteInvoice();
            break;
       
        case "New Line":
                createNewLine();
                break;

        case "Delete Line":
                deleteLine();
                break;

        case "newInvoiceOK":
                newInvoiceDialogOK();
                break;

        case "newInvoiceCancel":
                newInvoiceDialogCancel();
                break;

        case "newLineCancel":
                newLineDialogCancel();
                break;

        case "newLineOK":
                newLineDialogOK();
                break;

        }
    }
    

    private void loadFilesInvoices() {
     
       JFileChooser fileChooser = new JFileChooser();
        try {
            System.out.println("Reading files...");
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fileChooser.getSelectedFile();
                Path headerPath = Paths.get(headerFile.getAbsolutePath());
                List<String> headerLines = Files.readAllLines(headerPath);
                ArrayList<Header> invoiceHeaders = new ArrayList<>();
                System.out.println("Invoice header lines");
                for (String headerLine : headerLines) {
                    String[] arr = headerLine.split(",");
                    String str1 = arr[0];
                    String str2 = arr[1];
                    String str3 = arr[2];
                    int code = Integer.parseInt(str1);
                    Date invoiceDate = SalesInvoiceGeneratorView.dateFormat.parse(str2);
                    Header header = new Header(code, str3, invoiceDate);
                    System.out.println(header);
                    invoiceHeaders.add(header);
                }
                frame.setInvoicesArray(invoiceHeaders);

                result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fileChooser.getSelectedFile();
                    Path linePath = Paths.get(lineFile.getAbsolutePath());
                    List<String> lineLines = Files.readAllLines(linePath);
                    ArrayList<InvoiceItem> invoiceLines = new ArrayList<>();
                    System.out.println("Invoice lines");
                    for (String lineLine : lineLines) {
                        String[] arr = lineLine.split(",");
                        String str1 = arr[0];  
                        String str2 = arr[1];    
                        String str3 = arr[2];    
                        String str4 = arr[3];    
                        int invCode = Integer.parseInt(str1);
                        double price = Double.parseDouble(str3);
                        int count = Integer.parseInt(str4);
                        Header inv = frame.getInvObject(invCode);
                        InvoiceItem line = new InvoiceItem(str4, price, count, inv);
                        System.out.println(line);
                        inv.getItems().add(line);
                    }
                }
                InvoiceHeaderLineTable headerTableModel = new InvoiceHeaderLineTable(invoiceHeaders);
                frame.setHeaderTableModel(headerTableModel);
                frame.getInvoiceHeaderTbl().setModel(headerTableModel);
                System.out.println("files read");
            }
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void createNewInvoice() {
        headerDialog = new InvoiceHeaderDialog(frame);
        headerDialog.setVisible(true);
    }

    private void deleteInvoice() {
        int selectedInvoiceIndex = frame. getInvoiceHeaderTbl().getSelectedRow();
        if (selectedInvoiceIndex != -1) {
            frame.getInvoicesArray().remove(selectedInvoiceIndex);
            frame.getHeaderLineTable().fireTableDataChanged();

            frame.getInvoiceItemsTbl().setModel(new InvoiceLineTable(null));
            frame.setLinesArray(null);
            frame.getCustNameLbl().setText("");
            frame.getInvNumLbl().setText("");
            frame.getInvTotalIbl().setText("");
            frame.getInvDateLbl().setText("");
        }
    }

    private void createNewLine() {
        lineDialog = new InvoiceLineDialog(frame);
        lineDialog.setVisible(true);
    }

    private void deleteLine() {
        int selectedLineIndex = frame.getInvoiceItemsTbl().getSelectedRow();
        int selectedInvoiceIndex = frame. getInvoiceHeaderTbl().getSelectedRow();
        if (selectedLineIndex != -1) {
            frame.getLinesArray().remove(selectedLineIndex);
            
            InvoiceLineTable lineTableModel = (InvoiceLineTable) frame.getInvoiceItemsTbl().getModel();
            lineTableModel.fireTableDataChanged();
            frame.getInvTotalIbl().setText(""+frame.getInvoicesArray().get(selectedInvoiceIndex).getInvoiceTotal());
            frame.getHeaderLineTable().fireTableDataChanged();
            frame. getInvoiceHeaderTbl().setRowSelectionInterval(selectedInvoiceIndex, selectedInvoiceIndex);
        }
    }

    private void saveFiles() {
    }

    private void newInvoiceDialogCancel() {
        headerDialog.setVisible(false);
        headerDialog.dispose();
        headerDialog = null;
    }

    private void newInvoiceDialogOK() {
        headerDialog.setVisible(false);
        String custName = headerDialog.getCustNameField().getText();
        String str = headerDialog.getInvDateField().getText();
        Date d = new Date();
        try {
            d = SalesInvoiceGeneratorView.dateFormat.parse(str);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(frame, "Cannot parse date, resetting to today.", "Invalid date format", JOptionPane.ERROR_MESSAGE);
        }

        int invNum = 0;
        for (Header inv : frame.getInvoicesArray()) {
            if (inv.getInvoiceNumber()> invNum) {
                invNum = inv.getInvoiceNumber();
            }
        }
        invNum++;
        Header newInv = new Header(invNum, custName, d);
        frame.getInvoicesArray().add(newInv);
        frame.getHeaderLineTable().fireTableDataChanged();
        headerDialog.dispose();
        headerDialog = null;
    }

    private void newLineDialogCancel() {
        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;
    }

    private void newLineDialogOK() {
        lineDialog.setVisible(false);
        
        String name = lineDialog.getItemNameField().getText();
        String str1 = lineDialog.getItemCountField().getText();
        String str2 = lineDialog.getItemPriceField().getText();
        int count = 1;
        double price = 1;
        try {
            count = Integer.parseInt(str1);
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Cannot convert number", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }
        
        try {
            price = Double.parseDouble(str2);
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Cannot convert price", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }
        int selectedInvHeader = frame.getInvoiceHeaderTbl().getSelectedRow();
        if (selectedInvHeader != -1) {
            Header invHeader = frame.getInvoicesArray().get(selectedInvHeader);
            InvoiceItem line = new InvoiceItem(name, price, count, invHeader);
            //invHeader.getLines().add(line);
            frame.getLinesArray().add(line);
            InvoiceLineTable lineTableModel = (InvoiceLineTable) frame.getInvoiceItemsTbl().getModel();
            lineTableModel.fireTableDataChanged();
            frame.getHeaderLineTable().fireTableDataChanged();
        }
        frame.getInvoiceHeaderTbl().setRowSelectionInterval(selectedInvHeader, selectedInvHeader);
        lineDialog.dispose();
        lineDialog = null;
    }
}
