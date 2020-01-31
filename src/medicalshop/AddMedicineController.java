
package medicalshop;

import ECUtils.GUIValidator;
import ECUtils.MyUtils;
import MS.bean.medicines;
import MS.dao.medicineDAO;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
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

public class AddMedicineController extends CompanyListController implements Initializable {

    GUIValidator g1 = new GUIValidator();
    
    @FXML
    private TextField txtMedName;
    private TextField txtMedQuan;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtQuantity;
    @FXML
    private ComboBox<?> cmbCompany;
    @FXML
    private ComboBox<?> cmbCategory;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LinkedList cate = new LinkedList();
        cate.add("Tablet");
        cate.add("Syrup");
        cate.add("Gel");
        cate.add("Inhaler");
        cate.add("Injection");
        cate.add("Drops");
        cmbCategory.getItems().addAll(cate);
        
        LinkedList r = medicineDAO.getCompanyNames();
        cmbCompany.getItems().addAll(r);
        g1.addRequiredValidator(txtMedName);
        g1.addRequiredValidator(txtPrice);
        g1.addRequiredValidator(txtQuantity);
        g1.addRequiredValidator(cmbCompany);
        g1.addRequiredValidator(cmbCategory);
    }    

    @FXML
    private void he(ActionEvent event) {
        if(event.getSource() == btnAdd){
            try {
                if(!g1.validateAll()){
                    return;
                }
                medicines m1 = new medicines();
                m1.setMedName(txtMedName.getText());
                m1.setMedCompany(cmbCompany.getValue().toString());
                m1.setmedCategory(cmbCategory.getValue().toString());
                m1.setPrice(MyUtils.getDouble(txtPrice.getText()));
                m1.setQuantity(MyUtils.getInt(txtQuantity.getText()));
                medicineDAO.insert(m1);
                
                JOptionPane.showMessageDialog(null, "" + txtMedName.getText() + " added!");
                
                Parent root = FXMLLoader.load(getClass().getResource(FxmlConstant.MEDICINE_LIST_FXML));
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
                 Parent root = FXMLLoader.load(getClass().getResource(FxmlConstant.MEDICINE_LIST_FXML));
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
