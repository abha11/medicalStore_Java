/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalshop;

import ECUtils.MyUtils;
import MS.bean.medicines;
import MS.bean.myCustomer;
import MS.dao.customerDAO;
import MS.dao.medicineDAO;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import static medicalshop.MedicineListController.sc;
import static medicalshop.MedicineListController.si;

/**
 * FXML Controller class
 *
 * @author Abha Suntwal
 */
public class CustomerListController implements Initializable {
    public static String sc = "id";
    public static String si = "";

    @FXML
    private ComboBox<?> cmbBox;
    @FXML
    private TableView<?> tableList;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txtSi;
    @FXML
    private Button btnShow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MyUtils.populateColumnNames(cmbBox, "customer");
        MyUtils.selectComboBoxValue(cmbBox, sc);
        txtSi.setText(si);
        populate();
    }    

    @FXML
    private void cc(ActionEvent event) {
        sc = cmbBox.getValue().toString();
        populate();
    }

    @FXML
    private void kr(KeyEvent event) {
        si = txtSi.getText();
        populate();
    }
    public void populate(){
        LinkedList<myCustomer> res = customerDAO.search(cmbBox.getValue().toString(),txtSi.getText());
        MyUtils.populateTable(tableList, res, myCustomer.class);
    }

    @FXML
    private void he(ActionEvent event) {
        if(event.getSource() == btnBack){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage)btnBack.getScene().getWindow();
                stage.setMaximized(true);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(event.getSource() == btnShow){
            try {
                sc = "id";
                si = "";
                Parent root = FXMLLoader.load(getClass().getResource(FxmlConstant.CUSTOMER_FXML));
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
    
}
