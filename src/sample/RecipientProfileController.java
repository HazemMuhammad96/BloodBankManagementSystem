package sample;

import Data.DataFiles;
import Users.LoginRegisterUtils;
import Users.Recipient;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RecipientProfileController {

    @FXML
    private JFXTextField nameText, emailText, passwordText, ageText, hospitalText, doctorText;

    @FXML
    private Text genderText, bloodText;

    @FXML
    private JFXButton saveButton, deleteButton, logoutButton, backButton;

    Recipient user;

    @FXML
    void initialize() {
        user = (Recipient) LoginRegisterUtils.loggedInUser;
        nameText.setText(user.getName());
        emailText.setText(user.getMail());
        passwordText.setText(user.getPassword());
        ageText.setText(user.getAge() + "");
        genderText.setText(user.getGender());
        bloodText.setText(user.getBloodType());
        hospitalText.setText(user.getHospital());
        doctorText.setText(user.getDoctorOfCase());

        assert nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'RecipientProfile.fxml'.";
        assert emailText != null : "fx:id=\"emailText\" was not injected: check your FXML file 'RecipientProfile.fxml'.";
        assert passwordText != null : "fx:id=\"passwordText\" was not injected: check your FXML file 'RecipientProfile.fxml'.";
        assert ageText != null : "fx:id=\"ageText\" was not injected: check your FXML file 'RecipientProfile.fxml'.";
        assert genderText != null : "fx:id=\"genderText\" was not injected: check your FXML file 'RecipientProfile.fxml'.";
        assert bloodText != null : "fx:id=\"bloodText\" was not injected: check your FXML file 'RecipientProfile.fxml'.";
        assert hospitalText != null : "fx:id=\"hospitalText\" was not injected: check your FXML file 'RecipientProfile.fxml'.";
        assert doctorText != null : "fx:id=\"doctorText\" was not injected: check your FXML file 'RecipientProfile.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'RecipientProfile.fxml'.";
        assert deleteButton != null : "fx:id=\"deleteButton\" was not injected: check your FXML file 'RecipientProfile.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'RecipientProfile.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'RecipientProfile.fxml'.";

    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        ChangeScene(event, "RecipientHome.fxml");
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

    DataFiles files = new DataFiles();

    @FXML
    void saveButtonClicked(ActionEvent event) {
        user.setName(nameText.getText());
        user.setMail(emailText.getText());
        user.setPassword(passwordText.getText());
        user.setAge(Integer.parseInt(ageText.getText()));
        user.setHospital(hospitalText.getText());
        user.setDoctorOfCase(doctorText.getText());
        files.updateUser(user);
    }

    @FXML
    void deleteButtonClicked(ActionEvent event) {
        files.deleteUser(user);
        Platform.exit();
    }
}
