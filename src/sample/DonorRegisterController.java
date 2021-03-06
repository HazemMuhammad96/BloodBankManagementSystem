package sample;

import Data.DataFiles;
import Users.Donor;
import Users.LoginRegisterUtils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DonorRegisterController {


    @FXML
    private JFXTextField nameText, emailText, ageText;

    @FXML
    private JFXPasswordField passwordText;

    @FXML
    private JFXComboBox<String> genderText, bloodTypeText;

    @FXML
    private JFXCheckBox bloodPressureTick, thyroidDiseaseTick, heardDisordersTick, hepatitsTick, diabetesTick, cancerTick;

    @FXML
    private JFXTextField LastDate;

    @FXML
    private JFXButton RegisterButton;

    @FXML
    private JFXTextArea othersText;

    @FXML
    private JFXDatePicker LastDatePicker;

    ArrayList<String> DiseaseList = new ArrayList<String>();

    public void isTicked(JFXCheckBox name) {
        if (name.isSelected()) {
            DiseaseList.add(name.getText());
        } else {
            System.err.println("ERROR");
        }

    }

    @FXML
    void BloodPressureCheck(ActionEvent event) {
        isTicked(bloodPressureTick);
    }

    @FXML
    void CancerCheck(ActionEvent event) {
        isTicked(cancerTick);
    }

    @FXML
    void DiabetesCheck(ActionEvent event) {
        isTicked(diabetesTick);
    }

    @FXML
    void HeartDisordersCheck(ActionEvent event) {
        isTicked(heardDisordersTick);
    }

    @FXML
    void HepatitsCheck(ActionEvent event) {
        isTicked(hepatitsTick);
    }

    @FXML
    void ThyroidDiseaseCheck(ActionEvent event) {
        isTicked(thyroidDiseaseTick);
    }

    @FXML
    public void RegisterButtonClick(ActionEvent event) {
        LoginRegisterUtils Utils = new LoginRegisterUtils();
        DataFiles Files = new DataFiles();
        String name = nameText.getText();
        String email = emailText.getText();
        String password = passwordText.getText();
        int age = Integer.parseInt(ageText.getText());
        String gender = genderText.getValue().toString();
        String blood = bloodTypeText.getValue().toString();
        String lDate = LastDate.getText();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, LastDatePicker.getValue().getDayOfMonth());
        cal.set(Calendar.MONTH, LastDatePicker.getValue().getMonthValue() - 1);
        cal.set(Calendar.YEAR, LastDatePicker.getValue().getYear());
        long date = cal.getTimeInMillis();

        //ArrayList of possible disease missing !!

        if (Utils.validateUserRegistration(email, password)) {
            Donor don = new Donor(1, name, email, password, age, gender, blood, date);
            DiseaseList.add(othersText.getText());
            don.setDonorDisease(DiseaseList);
            Files.insertUser(don);
            LoginRegisterUtils utils = new LoginRegisterUtils();
            utils.validateUserLogin(email, password);

            ChangeScene(event, "DonorHome.fxml");

        } else {
            System.err.println("Wrong Email or password");
        }

    }

    private void ChangeScene(ActionEvent event, String path) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(path));
            Parent rootRegister = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Blood Bank Management System");
            stage.setScene(new Scene(rootRegister));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'DonorReg.fxml'.";
        assert emailText != null : "fx:id=\"emailText\" was not injected: check your FXML file 'DonorReg.fxml'.";
        assert passwordText != null : "fx:id=\"passwordText\" was not injected: check your FXML file 'DonorReg.fxml'.";
        assert ageText != null : "fx:id=\"ageText\" was not injected: check your FXML file 'DonorReg.fxml'.";
        assert genderText != null : "fx:id=\"genderText\" was not injected: check your FXML file 'DonorReg.fxml'.";
        assert bloodTypeText != null : "fx:id=\"bloodTypeText\" was not injected: check your FXML file 'DonorReg.fxml'.";
        assert bloodPressureTick != null : "fx:id=\"bloodPressureTick\" was not injected: check your FXML file 'DonorReg.fxml'.";
        assert thyroidDiseaseTick != null : "fx:id=\"thyroidDiseaseTick\" was not injected: check your FXML file 'DonorReg.fxml'.";
        assert heardDisordersTick != null : "fx:id=\"heardDisordersTick\" was not injected: check your FXML file 'DonorReg.fxml'.";
        assert hepatitsTick != null : "fx:id=\"hepatitsTick\" was not injected: check your FXML file 'DonorReg.fxml'.";
        assert diabetesTick != null : "fx:id=\"diabetesTick\" was not injected: check your FXML file 'DonorReg.fxml'.";
        assert cancerTick != null : "fx:id=\"cancerTick\" was not injected: check your FXML file 'DonorReg.fxml'.";
        assert LastDate != null : "fx:id=\"LastDate\" was not injected: check your FXML file 'DonorReg.fxml'.";
        assert RegisterButton != null : "fx:id=\"RegisterButton\" was not injected: check your FXML file 'DonorReg.fxml'.";
        genderText.setItems(FXCollections.observableArrayList("M", "F"));
        bloodTypeText.setItems((FXCollections.observableArrayList("A+", "A-", "B+", "B-", "AB+", "AB-", "O-", "O+")));
    }
}
