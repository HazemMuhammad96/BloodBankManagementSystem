package sample;

import Data.DataFiles;
import Users.Donor;
import Users.LoginRegisterUtils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import Users.DonationRequest;
import javafx.stage.Stage;

public class DonorHome {


    @FXML
    private JFXTextField nameText, emailText, passwordText, ageText;

    @FXML
    private JFXTextField dateText;

    @FXML
    private Text nameDonationText, requestDonationText, appointmentDonationText, bloodTypeDonationText, genderText, bloodText;

    @FXML
    private JFXButton logoutButton, sendRequestButton, saveButton, deleteButton;

    @FXML
    private AnchorPane donationCard;


    Donor user;
    DonationRequest date;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


    @FXML
    void sendRequestButton(ActionEvent event) {
        ButtonType confirmation = ConfirmationDialog.showDialog();
        if (confirmation == ButtonType.OK) {
            date = new DonationRequest(user.getID(), user.getBloodType());
            nameDonationText.setText(user.getName());
            bloodTypeDonationText.setText(user.getBloodType());

            requestDonationText.setText(formatter.format(date.getRequestDate()));
            appointmentDonationText.setText(formatter.format(date.getAppointmentDate()));
            user.setDateOfLastDonation(date.getRequestDate());
            dateText.setText(formatter.format(date.getRequestDate()));
            files.insertDonation(date);
            donationCard.setVisible(true);
            sendRequestButton.setDisable(true);
        }

    }


    @FXML
    void logoutButtonClicked(ActionEvent event) {
        LoginRegisterUtils.loggedInUser = null;
        ChangeScene(event, "sample.fxml");
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
        user = (Donor) LoginRegisterUtils.loggedInUser;
        nameText.setText(user.getName());
        emailText.setText(user.getMail());
        passwordText.setText(user.getPassword());
        ageText.setText(user.getAge() + "");
        genderText.setText(user.getGender());
        bloodText.setText(user.getBloodType());
        dateText.setText(formatter.format(user.getDateOfLastDonation()));

        System.out.println(user.getDonorDisease());
        assert nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'DonorHome.fxml'.";
        assert emailText != null : "fx:id=\"emailText\" was not injected: check your FXML file 'DonorHome.fxml'.";
        assert passwordText != null : "fx:id=\"passwordText\" was not injected: check your FXML file 'DonorHome.fxml'.";
        assert ageText != null : "fx:id=\"ageText\" was not injected: check your FXML file 'DonorHome.fxml'.";
        assert genderText != null : "fx:id=\"genderText\" was not injected: check your FXML file 'DonorHome.fxml'.";
        assert bloodText != null : "fx:id=\"bloodText\" was not injected: check your FXML file 'DonorHome.fxml'.";
        assert dateText != null : "fx:id=\"dateText\" was not injected: check your FXML file 'DonorHome.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'DonorHome.fxml'.";
        assert deleteButton != null : "fx:id=\"deleteButton\" was not injected: check your FXML file 'DonorHome.fxml'.";
        assert nameDonationText != null : "fx:id=\"nameDonationText\" was not injected: check your FXML file 'DonorHome.fxml'.";
        assert requestDonationText != null : "fx:id=\"requestDonationText\" was not injected: check your FXML file 'DonorHome.fxml'.";
        assert appointmentDonationText != null : "fx:id=\"appointmentDonationText\" was not injected: check your FXML file 'DonorHome.fxml'.";
        assert bloodTypeDonationText != null : "fx:id=\"bloodTypeDonationText\" was not injected: check your FXML file 'DonorHome.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'DonorHome.fxml'.";
        assert sendRequestButton != null : "fx:id=\"sendRequestButton\" was not injected: check your FXML file 'DonorHome.fxml'.";

        DonationRequest request = files.getDonation(user.getID());
        if (request != null) {
            sendRequestButton.setDisable(true);
            donationCard.setVisible(true);
            nameDonationText.setText(user.getName());
            bloodTypeDonationText.setText(user.getBloodType());

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            requestDonationText.setText(formatter.format(request.getRequestDate()));
            appointmentDonationText.setText(formatter.format(request.getAppointmentDate()));
        }
    }

    DataFiles files = new DataFiles();

    @FXML
    void saveButtonClicked(ActionEvent event) {
        user.setName(nameText.getText());
        user.setMail(emailText.getText());
        user.setPassword(passwordText.getText());
        user.setAge(Integer.parseInt(ageText.getText()));
        try {
            Date lastDonationDate = formatter.parse(dateText.getText());
            user.setDateOfLastDonation(lastDonationDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        files.updateUser(user);
    }

    @FXML
    void deleteButtonClicked(ActionEvent event) {
        files.deleteUser(user);
        Platform.exit();
    }
}
