package sample;

import Users.Donor;
import Users.LoginRegisterUtils;
import Users.Recipient;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToolbar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    LoginRegisterUtils log = new LoginRegisterUtils();
    @FXML
    private JFXTextField emailTextBox;

    @FXML
    private JFXPasswordField passwordTextBox;


    @FXML
    void loginButtonPressed(ActionEvent event) {

        boolean isExist = log.validateUserLogin(emailTextBox.getText(), passwordTextBox.getText());
        if (isExist) {
            if (LoginRegisterUtils.loggedInUser instanceof Donor) {
                ChangeScene(event, "DonorHome.fxml");
            } else if (LoginRegisterUtils.loggedInUser instanceof Recipient) {
                ChangeScene(event, "RecipientHome.fxml");
            }
        } else
            System.out.println("Wrong Email or Password");
    }

    @FXML
    void registerButtonPressed(ActionEvent event) {
        ChangeScene(event, "RegisterPage.fxml");

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
}