/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalshop;

import MS.bean.myCustomer;
import MS.bean.purchasedMedicine;
import MS.dao.customerDAO;
import MS.dao.purMedicineDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.transform.Scale;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abha Suntwal
 */
public class InvoiceController implements Initializable {

    public static String id = "";
    
    @FXML
    private WebView webView;
    @FXML
    private Button btnPrint;
    @FXML
    private Button btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//      we.load("c:;\\programdata\\invoice.html");
        getInvoice(id);
        
    }    
 
    private void getInvoice(String pId){
        try {
            myCustomer c1 = customerDAO.searchById(pId);
            LinkedList<purchasedMedicine> res = purMedicineDAO.search();
            File f = new File("c:\\programdata\\invoice.html");
            FileOutputStream out = new FileOutputStream(f);
            PrintWriter pr = new PrintWriter(out);
            pr.write("<html>"
                    + "<head><title>Your Invoice</title></head>"
                    + "<body>"
                    + "<center><table border = '4'>"
                    + "<tr><td colspan = 20 style='font-size:75px'>HealthCare Pharmacy</td></tr>"
                    + "<tr ><td colspan = 8>Customer Name :</td><td colspan = 12>"+c1.getCustomerName()+"</td></tr>"
                    + "<tr><td colspan = 8>Customer ID :</td><td colspan = 12>"+c1.getId()+"</td></tr>"
                    + "<tr><td colspan = 8>Phone No :</td><td colspan = 12>"+c1.getPhoneNumber()+"</td></tr>"
                    + "<tr><td colspan = 8>Date :</td><td colspan = 12>"+c1.getPurchaseDate()+"</td></tr>"
                    + "<tr style='border = 1px solid white;'><td colspan = 20></td>"
                    + "<tr style='border = 1px solid white;'><td colspan = 20></td>"
                    + "<tr style='font-size:20px;border:4px solid black;'><th colspan = 4>Sr. No.</th><th colspan = 4>Medicine Name</th><th colspan = 4>Quantity Purchased</th><th colspan = 4>Price Per unit</th><th colspan = 4>   Total   </th></tr>");
            for(purchasedMedicine p1 : res){
                pr.println("<tr>");
                pr.println("<td colspan = 4>"+ p1.getsNo() +"</td>");
                pr.println("<td colspan = 4>"+ p1.getMedName() +"</td>");
                pr.println("<td colspan = 4>"+ p1.getQuantPurchased() +"</td>");
                pr.println("<td colspan = 4>"+ p1.getPricePerUnit() +"</td>");
                pr.println("<td colspan = 4>"+ p1.getTotalPrice() +"</td>");
                pr.println("</tr>");
            }
            pr.print("<tr style='border:2px solid black; font-size:20px;'><th colspan = 8>Total Bill :  "
                    + "</th><th colspan = 12>"+c1.getTotalBill()+"</th></tr>");
            pr.println("</table></center><center><h2>THANK YOU</h2></center></body></html>");
            pr.close();
            WebEngine we = webView.getEngine();
            File fi = new File("c:\\programdata\\invoice.html");
            we.load(fi.toURI().toString());
           // Runtime.getRuntime().exec("explorer.exe \"c:\\programdata\\invoice.html\"");
        } catch (Exception ex) {
            ex.printStackTrace();
        }     
    }  
    
    public void print(final Node node){
        Printer printer = Printer.getDefaultPrinter();
        PrinterAttributes printerAttributes = printer.getPrinterAttributes();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
        double scaleX = 1;
        double scaleY = 1;
        node.getTransforms().add(new Scale(scaleX, scaleY));
        
        PrinterJob job = PrinterJob.createPrinterJob();
        if(job != null){
            System.out.println(job.getJobSettings().getPageLayout());
            boolean success = job.printPage(node);
            if(success){
                job.endJob();
            }
        }
        //pageLayout.getPrintableWidth()/node.getBoundsInParent().getMaxX()
        //pageLayout.getPrintableHeight()/ node.getBoundsInParent().getMaxY()
        ///node.getBoundsInParent().getWidth()
        ///node.getBoundsInParent().getHeight()
    }

    @FXML
    private void he(ActionEvent event) {
        if(event.getSource() == btnPrint){
            print(webView);
        }
        else{
            try{
                 Parent root = FXMLLoader.load(getClass().getResource(FxmlConstant.SALE_MEDICINE_FXML));
                 Scene scene = new Scene(root);
                 Stage stage = (Stage)btnBack.getScene().getWindow();
                 stage.setMaximized(true);
                 stage.setScene(scene);
                 stage.show();
            }
            catch(Exception e){
                e.printStackTrace();
            }
                
        }
    }
}
