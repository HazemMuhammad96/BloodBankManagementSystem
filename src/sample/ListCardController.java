package sample;

import com.jfoenix.controls.JFXButton;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import Blood.Blood;
import Users.DonationRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ListCardController implements Initializable {

    public sentHandler Info;

    @FXML
    private Text BloodType, QuantityText, ReceivedDateText, ExpiryDateText;


    Blood blood;

    public void setInfo(sentHandler info) {
        Info = info;
    }

    @FXML
    void RequestButtonClicked(ActionEvent event) {
        Info.sentRequest(blood);
    }

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public void setItem(Blood blood) {
        this.blood = blood;
        BloodType.setText(blood.getType());
        QuantityText.setText(blood.getQuantity() + "");
        ReceivedDateText.setText(formatter.format(blood.getReceivedDate()));
        ExpiryDateText.setText(formatter.format(blood.getExpiredDate()));

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
