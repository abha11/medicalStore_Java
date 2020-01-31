/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalshop;

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
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Abha Suntwal
 */
public class HomeController implements Initializable,FxmlConstant {

    public static String welName = null;
    
    @FXML
    private Button btnMed;
    @FXML
    private Button btnComp;
    @FXML
    private Button btnAcc;
    @FXML
    private Button btnLog;
    @FXML
    private Label labelWel;
    @FXML
    private Button btnCust;
    @FXML
    private Button btnUsers;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelWel.setText("Welcome " + welName);
    }    

    @FXML
    private void he(ActionEvent event) {
        if(event.getSource() == btnMed){
            try {
            Parent root = FXMLLoader.load(getClass().getResource(MEDICINE_LIST_FXML));
            Scene scene = new Scene(root);
            Stage stage = (Stage)btnLog.getScene().getWindow();
            stage.setMaximized(true);
            stage.setScene(scene);
            stage.show();
            
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        else if(event.getSource() == btnComp){
            try {
            Parent root = FXMLLoader.load(getClass().getResource(COMPANY_LIST_FXML));
            Scene scene = new Scene(root);
            Stage stage = (Stage)btnLog.getScene().getWindow();
            stage.setMaximized(true);
            stage.setScene(scene);
            stage.show();
            
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        else if(event.getSource() == btnAcc){
            try {
            Parent root = FXMLLoader.load(getClass().getResource(SALE_MEDICINE_FXML));
            Scene scene = new Scene(root);
            Stage stage = (Stage)btnLog.getScene().getWindow();
            stage.setMaximized(true);
            stage.setScene(scene);
            stage.show();
            
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        else if(event.getSource() == btnLog){
            try {
                JOptionPane.showMessageDialog(null, "Logged Out!");
                Parent root = FXMLLoader.load(getClass().getResource(LOGIN_FXML));
                Scene scene = new Scene(root);
                Stage stage = (Stage)btnLog.getScene().getWindow();
                stage.setMaximized(true);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
         else if(event.getSource() == btnCust){
            try {
                Parent root = FXMLLoader.load(getClass().getResource(CUSTOMER_FXML));
                Scene scene = new Scene(root);
                Stage stage = (Stage)btnLog.getScene().getWindow();
                stage.setMaximized(true);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        else if(event.getSource() == btnUsers){
            try {
                Parent root = FXMLLoader.load(getClass().getResource(USERS_FXML));
                Scene scene = new Scene(root);
                Stage stage = (Stage)btnLog.getScene().getWindow();
                stage.setMaximized(true);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }
    
}
