/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalshop;

import ECUtils.GUIValidator;
import MS.bean.myUser;
import MS.dao.MyuserDAO;
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

/**
 * FXML Controller class
 *
 * @author Abha Suntwal
 */
public class AddUserController implements Initializable {

    GUIValidator g1 = new GUIValidator();

    @FXML
    private Button btnBack;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField txtPN;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPass;
    @FXML
    private TextField txtConPass;
    @FXML
    private ComboBox<?> cmbBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LinkedList cate = new LinkedList();
        cate.add("admin");
        cate.add("user");
        cmbBox.getItems().addAll(cate);
        g1.addRequiredValidator(txtName);
        g1.addRequiredValidator(txtPN);
        g1.addPNValidator(txtPN);
        g1.addRequiredValidator(txtPass);
        g1.addRequiredValidator(txtConPass);
        
        
        txtConPass.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    //System.out.println("Textfield on focus");
                } else {
                    if(!(txtPass.getText().equals(txtConPass.getText()))){
                        JOptionPane.showMessageDialog(null, "PassWord don't match!");
                        btnAdd.setDisable(true);
                        return;
                    }
                else{
                        btnAdd.setDisable(false);
                    }
                    
                }
            }
        });
        
    }    

    @FXML
    private void he(ActionEvent event) {
        if(event.getSource() == btnAdd){
            try {
                if(!g1.validateAll()){
                    return;
                }
                myUser m1 = new myUser();
                m1.setUserName(txtName.getText());
                m1.setPassWord(txtPass.getText());
                m1.setPhoneNo(txtPN.getText());
                m1.setUserCategory(cmbBox.getValue().toString());
                MyuserDAO.insert(m1);
                JOptionPane.showMessageDialog(null, "Done!");
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
    
}
