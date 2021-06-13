package sample;

import Blood.Blood;
import Data.DataFiles;
import Lists.DonationsList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.util.*;

import Users.LoginRegisterUtils;
import Users.Recipient;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BloodDataController implements sentHandler {


    @FXML
    private JFXTextField searchText, HospitalNameTxt;

    @FXML
    private JFXButton logoutButton, myProfileButton;

    @FXML
    private JFXListView<Blood> bloodList;

    @FXML
    private JFXComboBox<String> BloodTypeSearch;

    @FXML
    private VBox HospitalPopUP;


    DataFiles Data = new DataFiles();
    Recipient user = (Recipient) LoginRegisterUtils.loggedInUser;
    Blood currentBlood;

    @FXML
    void ClosebuttonClicked(MouseEvent event) {
        HospitalPopUP.setVisible(false);
    }

    @FXML
    void sentButtonClicked(ActionEvent event) {
        rawBlood.remove(currentBlood);
        bloodListView(rawBlood);
        Data.insertRecipientRequest(user.getID(), currentBlood, System.currentTimeMillis());
        HospitalPopUP.setVisible(false);
    }

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

    ObservableList<String> SearchOptions = FXCollections.observableArrayList("All", "A+", "A-", "B+", "B-", "AB+", "AB-", "O-", "O+");
    ArrayList<Blood> rawBlood = new ArrayList<Blood>(Data.getBloodData());
    List<Blood> FilterBlood = new ArrayList<Blood>(rawBlood);


    @FXML
    void initialize() {

        assert searchText != null : "fx:id=\"searchText\" was not injected: check your FXML file 'RecipientHome.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'RecipientHome.fxml'.";
        assert myProfileButton != null : "fx:id=\"myProfileButton\" was not injected: check your FXML file 'RecipientHome.fxml'.";
        assert bloodList != null : "fx:id=\"bloodList\" was not injected: check your FXML file 'RecipientHome.fxml'.";

        bloodListView(FilterBlood);
        initSearchView();
        initSearchQuntity();


    }

    private void initSearchView() {
        BloodTypeSearch.setItems(SearchOptions);
        BloodTypeSearch.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                FilterBlood.clear();
                FilterBlood = new ArrayList<Blood>(rawBlood);
                if (newValue.equalsIgnoreCase("All")) {
                    bloodListView(rawBlood);
                } else {
                    FilterBlood.removeIf(Blood -> !Blood.getType().equalsIgnoreCase(newValue));
                    bloodListView(FilterBlood);
                }

            }
        });
    }

    @FXML
    void logoutButtonClicked(ActionEvent event) {
        LoginRegisterUtils.loggedInUser = null;
        ChangeScene(event, "sample.fxml");
    }

    private void initSearchQuntity() {
        searchText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                FilterBlood.clear();
                FilterBlood = new ArrayList<Blood>(rawBlood);
                if (newValue.length() == 0) {
                    bloodListView(rawBlood);
                } else {
                    FilterBlood.removeIf(Blood -> !(Blood.getQuantity() == Integer.parseInt(newValue)));
                    bloodListView(FilterBlood);
                }

            }
        });
    }

    private void bloodListView(List<Blood> blood) {
        ObservableList<Blood> items = FXCollections.observableArrayList(blood);
        bloodList.getItems().clear();
        bloodList.setItems(items);
        bloodList.setCellFactory(param -> new DonationsList(this));

        System.out.println(blood);


    }


    @Override
    public void sentRequest(Blood blood) {
        currentBlood = blood;
        HospitalPopUP.setVisible(true);
        HospitalNameTxt.setText(user.getHospital());

    }
}
