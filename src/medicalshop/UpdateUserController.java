/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalshop;

import ECUtils.GUIValidator;
import ECUtils.MyUtils;
import MS.bean.medicines;
import MS.bean.myUser;
import MS.dao.MyuserDAO;
import MS.dao.medicineDAO;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static medicalshop.UpdateMedicineController.id;

/**
 * FXML Controller class
 *
 * @author Abha Suntwal
 */
public class UpdateUserController implements Initializable {

    GUIValidator g1 = new GUIValidator();
    
    public static String id = null;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnBack;
    @FXML
    private ComboBox<?> cmbBox;
    @FXML
    private TextField txtPN;
    @FXML
    private TextField txtConPass;
    @FXML
    private TextField txtPass;
    @FXML
    private TextField txtName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LinkedList cate = new LinkedList();
        cate.add("admin");
        cate.add("user");
        cmbBox.getItems().addAll(cate);
        populateOldData();
        g1.addRequiredValidator(txtName);
        g1.addRequiredValidator(txtPN);
        g1.addPNValidator(txtPN);
        g1.addRequiredValidator(txtPass);
        g1.addRequiredValidator(txtConPass);
        g1.addPassValidator(txtPass);
        g1.addPassValidator(txtConPass);
        
        txtConPass.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    //System.out.println("Textfield on focus");
                } else {
                    if(!(txtPass.getText().equals(txtConPass.getText()))){
                        JOptionPane.showMessageDialog(null, "Password don't match!");
                        btnUpdate.setDisable(true);
                        return;
                    }
                else{
                        btnUpdate.setDisable(false);
                    }
                    
                }
            }
        });
        
    }    

    @FXML
    private void he(ActionEvent event) {
        if(event.getSource() == btnUpdate){
            try {
                if(!g1.validateAll()){
                    return;
                }
                myUser m1 = new myUser();
                m1.setUserName(txtName.getText());
                m1.setPassWord(txtPass.getText());
                m1.setPhoneNo(txtPN.getText());
                m1.setUserCategory(cmbBox.getValue().toString());
                m1.setId(id);
                MyuserDAO.update(m1);
                JOptionPane.showMessageDialog(null, "Done!");
                Parent root = FXMLLoader.load(getClass().getResource(FxmlConstant.USERS_FXML));
                Scene scene = new Scene(root);
                Stage stage = (Stage)btnBack.getScene().getWindow();
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.show();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (event.getSource() == btnBack) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource(FxmlConstant.USERS_FXML));
                Scene scene = new Scene(root);
                Stage stage = (Stage) btnBack.getScene().getWindow();
                stage.setMaximized(true);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void populateOldData() {
        myUser m1 = MyuserDAO.searchById(id);
        txtName.setText(m1.getUserName());
        MyUtils.selectComboBoxValue(cmbBox, m1.getUserCategory());
        txtPass.setText(m1.getPassWord());
        txtConPass.setText(m1.getPassWord());
        txtPN.setText(m1.getPhoneNo());
    }
}
    

