package Lists;

import com.jfoenix.controls.JFXButton;

import Blood.Blood;
import Users.DonationRequest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import sample.ListCardController;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class DonationsList extends ListCell<Blood> {

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

    public DonationsList() {

    }

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


    @Override
    protected void updateItem(Blood item, boolean empty) {
        super.updateItem(item, empty);
        HBox cardFrame = null;

        if (item != null) {


            FXMLLoader load = new FXMLLoader();

            load.setLocation(getClass().getResource("../sample/ListCard.fxml"));
            try {
                cardFrame = load.load();
                load.setController(this);
//                ListCardController controller = load.getController();
//                controller.setItem(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
            BloodType.setText(item.getType());
            QuantityText.setText(item.getQuantity()+"");
            ReceivedDate.setText(formatter.format(item.getReceivedDate()));
            ExpiryDate.setText(formatter.format(item.getExpiredDate()));

            setGraphic(cardFrame);
        }
    }
}
