package sample;

import Blood.Blood;
import Lists.DonationsList;
import Users.DonationRequest;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class BloodDataController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField searchText;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton myProfileButton;

    @FXML
    private JFXListView<Blood> bloodList;

    @FXML
    void profileButtonClicked(ActionEvent event) {
        ChangeScene(event, "RecipientProfile.fxml");
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

//        PlayerList.setCellFactory(param -> new PurchaseCell());
        assert searchText != null : "fx:id=\"searchText\" was not injected: check your FXML file 'RecipientHome.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'RecipientHome.fxml'.";
        assert myProfileButton != null : "fx:id=\"myProfileButton\" was not injected: check your FXML file 'RecipientHome.fxml'.";
        assert bloodList != null : "fx:id=\"bloodList\" was not injected: check your FXML file 'RecipientHome.fxml'.";

        ObservableList<Blood> items = FXCollections.observableArrayList(
                new Blood("A+", 2, System.currentTimeMillis(),System.currentTimeMillis()),
                new Blood("B+", 1, System.currentTimeMillis(),System.currentTimeMillis()),
                new Blood("O+", 3, System.currentTimeMillis(),System.currentTimeMillis()),
                new Blood("AB+", 4, System.currentTimeMillis(),System.currentTimeMillis()),
                new Blood("A+", 7, System.currentTimeMillis(),System.currentTimeMillis()),
                new Blood("B+", 8, System.currentTimeMillis(),System.currentTimeMillis()),
                new Blood("O+", 1, System.currentTimeMillis(),System.currentTimeMillis()),
                new Blood("AB+", 2, System.currentTimeMillis(),System.currentTimeMillis()));
        bloodList.setItems(items);
        bloodList.setCellFactory(param -> new DonationsList());
    }
}
