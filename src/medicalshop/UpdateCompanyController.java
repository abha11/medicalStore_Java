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
import MS.dao.companyDAO;
import MS.dao.medicineDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static medicalshop.UpdateMedicineController.id;

/**
 * FXML Controller class
 *
 * @author Abha Suntwal
 */
public class UpdateCompanyController implements Initializable {

     public static String id = null;
    
    GUIValidator g1 = new GUIValidator();
    
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtCountry;
    @FXML
    private TextField txtEmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        g1.addRequiredValidator(txtName);
        g1.addRequiredValidator(txtCountry);
        g1.addRequiredValidator(txtPhone);
        g1.addPNValidator(txtPhone);
        g1.addRequiredValidator(txtAddress);
        g1.addEmailValidator(txtEmail);
        g1.addRequiredValidator(txtEmail);
        populateOldData();

    }    

    @FXML
    private void he(ActionEvent event) {
         if(event.getSource() == btnUpdate){
            try {
                if(!g1.validateAll()){
                    return;
                }
                Company c1 = new Company();
                c1.setCompanyName(txtName.getText());
                c1.setCompanyCountry(txtCountry.getText());
                c1.setCompanyPhone(txtPhone.getText());
                c1.setCompanyEmail(txtEmail.getText());
                c1.setCompanyAddress(txtAddress.getText());
                c1.setId(id);
                companyDAO.update(c1);
                
                JOptionPane.showMessageDialog(null, "" + c1.getCompanyName() + " Updated!");
                
                Parent root = FXMLLoader.load(getClass().getResource(FxmlConstant.COMPANY_LIST_FXML));
                Scene scene = new Scene(root);
                Stage stage = (Stage)btnBack.getScene().getWindow();
                stage.setMaximized(true);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
        else if(event.getSource() == btnBack){
               try {
                 Parent root = FXMLLoader.load(getClass().getResource(FxmlConstant.COMPANY_LIST_FXML));
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
    
      public void populateOldData(){
          Company c1 = companyDAO.searchById(id);
          txtName.setText(c1.getCompanyName());
          txtCountry.setText(c1.getCompanyCountry());
          txtEmail.setText(c1.getCompanyEmail());
          txtPhone.setText(c1.getCompanyPhone());
          txtAddress.setText(c1.getCompanyAddress());
    }
    
}
