/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalshop;

import ECUtils.MyUtils;
import MS.bean.medicines;
import MS.bean.myUser;
import MS.dao.MyuserDAO;
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
import javax.swing.JOptionPane;
import static medicalshop.MedicineListController.sc;
import static medicalshop.MedicineListController.si;

/**
 * FXML Controller class
 *
 * @author Abha Suntwal
 */
public class UserListController implements Initializable {

    public static String sc = "id";
    public static String si = "";
    
    @FXML
    private ComboBox<?> cmbBox;
    @FXML
    private Button btnShow;
    @FXML
    private TextField txtSi;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnAdd;
    @FXML
    private TableView<?> tabelList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MyUtils.populateColumnNames(cmbBox, "app_users");
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
    private void he(ActionEvent event) {
         if(event.getSource() == btnAdd){
            try {
            Parent root = FXMLLoader.load(getClass().getResource(FxmlConstant.ADD_USERS_FXML));
            Scene scene = new Scene(root);
            Stage stage = (Stage)btnBack.getScene().getWindow();
            stage.setMaximized(true);
            stage.setScene(scene);
            stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        else if(event.getSource() == btnUpdate){
            try {
                String id = MyUtils.getSelColValue("id", tabelList);
                if(id == null){
                    JOptionPane.showMessageDialog(null, "Please select a row!");
                    return;
                }
                UpdateUserController.id = id;
                Parent root = FXMLLoader.load(getClass().getResource(FxmlConstant.UPDATE_USERS_FXML));
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
                String id = MyUtils.getSelColValue("id", tabelList);
                if(id == null){
                    JOptionPane.showMessageDialog(null, "Please select a row!");
                    return;
                }
                MyuserDAO.delete(id);
                Parent root = FXMLLoader.load(getClass().getResource(FxmlConstant.USERS_FXML));
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
                Parent root = FXMLLoader.load(getClass().getResource(FxmlConstant.USERS_FXML));
                Scene scene = new Scene(root);
                Stage stage = (Stage)btnBack.getScene().getWindow();
                stage.setMaximized(true);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        else{
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
        LinkedList<myUser> res = MyuserDAO.search(cmbBox.getValue().toString(),txtSi.getText());
        MyUtils.populateTable(tabelList, res, myUser.class);
    }

    
}
