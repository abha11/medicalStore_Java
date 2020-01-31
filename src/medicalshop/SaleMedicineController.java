/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalshop;

import ECUtils.GUIValidator;
import ECUtils.MyUtils;
import MS.bean.Company;
import MS.bean.medicines;
import MS.bean.myCustomer;
import MS.bean.purchasedMedicine;
import MS.dao.companyDAO;
import MS.dao.customerDAO;
import MS.dao.medicineDAO;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static medicalshop.MedicineListController.si;

/**
 * FXML Controller class
 *
 * @author Abha Suntwal
 */
public class SaleMedicineController implements Initializable {

    GUIValidator g1 = new GUIValidator();
    public static String CID = "";
    
    public static String si = "";
    @FXML
    private TableView<?> tableMed;
    @FXML
    private TableView<?> tabelPur;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txtSi;
    @FXML
    private TextField txtCustName;
    @FXML
    private TextField txtPN;
    @FXML
    private TextField txtQuantity;
    @FXML
    private Label labelTotal;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnTotal;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnNew;
    @FXML
    private Label labelReturn;
    @FXML
    private TextField txtCash;
    @FXML
    private Button btnGo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        purMedicineDAO.deleteExistingData();
        labelTotal.setText("");
        g1.addRequiredValidator(txtCustName);
        g1.addRequiredValidator(txtPN);   
        g1.addPNValidator(txtPN);            
        txtSi.setText(si);
        populateSales();
        populate();
    }    

    @FXML
    private void he(ActionEvent event) {
        if(event.getSource() == btnAdd){
            try {
                int quantity = 0;
                int quan_left = 0;
                if(!g1.validateAll()){
                    return;
                }
                String pId = MyUtils.getSelColValue("id", tableMed);
                if(pId==null){
                    JOptionPane.showMessageDialog(null, "Please select a row!");
                    return;
                }
                purchasedMedicine m1 =  purMedicineDAO.searchByIdForSale(pId);
                medicines t1 = medicineDAO.searchById(pId);
                //This adds the remainig data to the object
                if(MyUtils.getInt(txtQuantity.getText()) == 0){
                      quantity = 1;
                }
                else{
                    quantity = MyUtils.getInt(txtQuantity.getText());
                }
                if((quantity > t1.getQuantity())){
                    JOptionPane.showMessageDialog(null, "Stock Insufficient!");
                    txtQuantity.setText(Integer.toString(t1.getQuantity()));
                    return;
                }
                m1.setQuantPurchased(quantity);
                
                quan_left = t1.getQuantity() - m1.getQuantPurchased();
                t1.setQuantity(quan_left);
                medicineDAO.update(t1);
                populate();
                
                double total = m1.getPricePerUnit();
                total = total * m1.getQuantPurchased();
                m1.setTotalPrice(total);
                purMedicineDAO.insert(m1);                
                populateSales();
                double d1 = purMedicineDAO.getTotalBill();
                labelTotal.setText(Double.toString(d1));   
              
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
       /* else if(event.getSource() == btnTotal){
            try {
              double d1 = purMedicineDAO.getTotalBill();
               labelTotal.setText(Double.toString(d1));                
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }*/
        if(event.getSource() == btnSave){
            try {
                 if(!g1.validateAll()){
                    return;
                }
                //Populating customer table
                myCustomer c1 = new myCustomer();
                c1.setCustomerName(txtCustName.getText());
                c1.setPhoneNumber(txtPN.getText());
                double total = purMedicineDAO.getTotalBill();
                c1.setTotalBill(total);
                c1.setPurchaseDate(MyUtils.getToday());
                int pId = customerDAO.insert(c1);
                InvoiceController.id = Integer.toString(pId);
                JOptionPane.showMessageDialog(null, "Done!");
                Parent root = FXMLLoader.load(getClass().getResource(FxmlConstant.INVOICE_FXML));
                 Scene scene = new Scene(root);
                 Stage stage = (Stage)btnBack.getScene().getWindow();
                 stage.setMaximized(true);
                 stage.setScene(scene);
                 stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
        else if(event.getSource() == btnDelete){
               try {
                 String id = MyUtils.getSelColValue("sNo", tabelPur);         
                 
                 if(id == null){
                     JOptionPane.showMessageDialog(null, "Please select a row!");
                     return;
                 }
                 int ch = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete! ");
                 if(ch != JOptionPane.YES_OPTION ){
                     return;
                 }
                 String q1 = MyUtils.getSelColValue("quantPurchased", tabelPur);
                 String mId = MyUtils.getSelColValue("mId", tabelPur);
                 int q = MyUtils.getInt(q1);
                 purMedicineDAO.delete(id,q,mId);
                 populateSales();
                 populate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(event.getSource() == btnGo){
            try{
                double d1 = purMedicineDAO.getTotalBill();
                double d2 = MyUtils.getDouble(txtCash.getText());
                labelReturn.setText(Double.toString(d2-d1));
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(event.getSource() == btnNew){
            try{
              
                purMedicineDAO.deleteExistingData();
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
        
        else if(event.getSource() == btnBack){
               try {
                 Parent root = FXMLLoader.load(getClass().getResource(FxmlConstant.HOME_FXML));
                 Scene scene = new Scene(root);
                 Stage stage = (Stage)btnBack.getScene().getWindow();
                 stage.setMaximized(true);
                 stage.setScene(scene);
                 stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void kr(KeyEvent event) {
        si = txtSi.getText();
        populate();
    }
    
    public void populate(){
        LinkedList<medicines> res = medicineDAO.search("m_name",txtSi.getText());
        MyUtils.populateTable(tableMed, res, medicines.class);
    }
     public void populateSales(){
        LinkedList<purchasedMedicine> res = purMedicineDAO.search();
        MyUtils.populateTable(tabelPur, res, purchasedMedicine.class);
    }
     
}
