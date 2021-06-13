package sample;

import com.jfoenix.controls.JFXButton;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import Blood.Blood;
import Users.DonationRequest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ListCardController implements Initializable {

    @FXML
    private HBox CardHbox;

    @FXML
    private AnchorPane AnchorImage;

    @FXML
    private Text BloodType;

    @FXML
    private VBox QuantityVbox;

    @FXML
    private Text Quantity;

    @FXML
    private Text QuantityText;

    @FXML
    private VBox ReceivedVbox;

    @FXML
    private Text ReceivedDate;

    @FXML
    private Text ReceivedDateText;

    @FXML
    private VBox ExpiryVbox;

    @FXML
    private Text ExpiryDate;

    @FXML
    private Text ExpiryDateText;

    @FXML
    private JFXButton RequestButton;
//    Blood blood;

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public void setItem(Blood blood) {
        BloodType.setText(blood.getType());
        QuantityText.setText(blood.getQuantity()+"");
        ReceivedDateText.setText(formatter.format(blood.getReceivedDate()));
        ExpiryDateText.setText(formatter.format(blood.getExpiredDate()));

        System.out.println(blood.getType());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
