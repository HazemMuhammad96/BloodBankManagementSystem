package sample;

import Users.Donor;
import Users.LoginRegisterUtils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import Users.DonationRequest;

public class DonorHome {

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
    private Text genderText;

    @FXML
    private Text bloodText;

    @FXML
    private JFXTextField dateText;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private Text nameDonationText;

    @FXML
    private Text requestDonationText;

    @FXML
    private Text appointmentDonationText;

    @FXML
    private Text bloodTypeDonationText;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton sendRequestButton;
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
    void initialize() {
        user = (Donor) LoginRegisterUtils.loggedInUser;
        nameText.setText(user.getName());
        emailText.setText(user.getMail());
        passwordText.setText(user.getPassword());
        ageText.setText(user.getAge() + "");
        genderText.setText(user.getGender());
        bloodText.setText(user.getBloodType());
        dateText.setText(formatter.format(user.getDateOfLastDonation()));

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
