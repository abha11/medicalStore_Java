/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalshop;

import ECUtils.GUIValidator;
import ECUtils.MyUtils;
import MS.bean.medicines;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Abha Suntwal
 */
public class UpdateMedicineController implements Initializable {

    public static String id = null;

    GUIValidator g1 = new GUIValidator();

    @FXML
    private Button btnBack;
    @FXML
    private Button btnUpdate;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtQuantity;
    @FXML
    private ComboBox<?> cmbCategory;
    @FXML
    private ComboBox<?> cmbCompany;
    @FXML
    private TextField txtMedName;

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
        populateOldData();
    }

    @FXML
    private void he(ActionEvent event) {
        if (event.getSource() == btnUpdate) {
            try {
                if (!g1.validateAll()) {
                    return;
                }
                medicines m1 = new medicines();
                m1.setMedName(txtMedName.getText());
                m1.setMedCompany(cmbCompany.getValue().toString());
                m1.setmedCategory(cmbCategory.getValue().toString());
                m1.setPrice(MyUtils.getDouble(txtPrice.getText()));
                m1.setQuantity(MyUtils.getInt(txtQuantity.getText()));
                m1.setId(id);
                medicineDAO.update(m1);

                JOptionPane.showMessageDialog(null, "" + txtMedName.getText() + " Updated!");

                Parent root = FXMLLoader.load(getClass().getResource(FxmlConstant.MEDICINE_LIST_FXML));
                Scene scene = new Scene(root);
                Stage stage = (Stage) btnBack.getScene().getWindow();
                stage.setMaximized(true);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == btnBack) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource(FxmlConstant.MEDICINE_LIST_FXML));
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
        medicines m1 = medicineDAO.searchById(id);
        txtMedName.setText(m1.getMedName());
        MyUtils.selectComboBoxValue(cmbCompany, m1.getMedCompany());
        MyUtils.selectComboBoxValue(cmbCategory, m1.getmedCategory());
        txtPrice.setText(Double.toString(m1.getPrice()));
        txtQuantity.setText(Integer.toString(m1.getQuantity()));
    }

}
