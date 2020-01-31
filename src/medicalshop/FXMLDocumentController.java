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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Abha Suntwal
 */
public class FXMLDocumentController implements Initializable {
    GUIValidator g1 = new GUIValidator();
    
    private Label label;
    @FXML
    private TextField txtName;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnReset;
    @FXML
    private ImageView img;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        g1.addRequiredValidator(txtName);
        g1.addRequiredValidator(txtPass);
    }    

    @FXML
    private void he(ActionEvent event) {
        if(event.getSource() == btnLogin){
            try {
                if(!g1.validateAll()){
                return;
            }
            myUser m1 = MyuserDAO.validate_login(txtName.getText(), txtPass.getText());
            if(m1 == null){
                JOptionPane.showMessageDialog(null, "Incorrect UserName or Password!");
                return;
            }
            //JOptionPane.showMessageDialog(null, "Succesfully Logged In!");
            HomeController.welName = txtName.getText();
            Stage stage = (Stage)btnLogin.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(FxmlConstant.HOME_FXML));
            Scene scene = new Scene(root);
            stage.setMaximized(true);
            stage.setScene(scene);
            stage.show();
            
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        else{
            try {
                Parent root = FXMLLoader.load(getClass().getResource(FxmlConstant.LOGIN_FXML));
                Scene scene = new Scene(root);
                Stage stage = (Stage)btnLogin.getScene().getWindow();
                stage.setMaximized(true);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }
    
}
