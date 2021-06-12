package sample;

import Users.LoginRegisterUtils;
import Users.Recipient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class RecipientRegisterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField nameText;

    @FXML
    private JFXTextField emailText;

    @FXML
    private JFXTextField passwordText;

    @FXML
    private JFXTextField ageText;

    @FXML
    private JFXComboBox<String> genderText;

    @FXML
    private JFXComboBox<String> bloodTypeText;

    @FXML
    private JFXTextField doctorCaseText;

    @FXML
    private JFXTextField hospitalName;

    @FXML
    private JFXButton RegisterButton;

    @FXML
    void RegisterButtonClick(ActionEvent event) {
        LoginRegisterUtils Utils = new LoginRegisterUtils();
        DataFiles Files = new DataFiles();

        String name = nameText.getText();
        String email = emailText.getText();
        String password = passwordText.getText();
        int age = Integer.parseInt(ageText.getText());
        String doctorCase = doctorCaseText.getText();
        String hospital = hospitalName.getText();
        String gender = genderText.getValue().toString();
        String blood = bloodTypeText.getValue().toString();

        if (Utils.validateUserRegistration(email , password))
        {
            Recipient rec = new Recipient( 1 , name , email, password , age , gender , blood , hospital , doctorCase );

            Files.insertUser(rec);
        }
        else
        {
            System.err.println("Somk");
        }

    }

    @FXML
    void initialize() {
        assert nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'RecipientReg.fxml'.";
        assert emailText != null : "fx:id=\"emailText\" was not injected: check your FXML file 'RecipientReg.fxml'.";
        assert passwordText != null : "fx:id=\"passwordText\" was not injected: check your FXML file 'RecipientReg.fxml'.";
        assert ageText != null : "fx:id=\"ageText\" was not injected: check your FXML file 'RecipientReg.fxml'.";
        assert genderText != null : "fx:id=\"genderText\" was not injected: check your FXML file 'RecipientReg.fxml'.";
        assert bloodTypeText != null : "fx:id=\"bloodTypeText\" was not injected: check your FXML file 'RecipientReg.fxml'.";
        assert doctorCaseText != null : "fx:id=\"doctorCaseText\" was not injected: check your FXML file 'RecipientReg.fxml'.";
        assert hospitalName != null : "fx:id=\"hospitalName\" was not injected: check your FXML file 'RecipientReg.fxml'.";
        assert RegisterButton != null : "fx:id=\"RegisterButton\" was not injected: check your FXML file 'RecipientReg.fxml'.";
        genderText.setItems(FXCollections.observableArrayList("M","F"));
        bloodTypeText.setItems((FXCollections.observableArrayList("A+","A-","B+","B-","AB+","AB-","O-","O+")));
    }

}