package Lists;

import com.jfoenix.controls.JFXButton;

import Blood.Blood;
import Users.DonationRequest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import sample.ListCardController;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class DonationsList extends ListCell<Blood> {

    @Override
    protected void updateItem(Blood item, boolean empty) {
        super.updateItem(item, empty);
        Parent cardFrame = null;

        if (item != null) {


            FXMLLoader load = new FXMLLoader(getClass().getResource("../sample/ListCard.fxml"));

            try {
                cardFrame = load.load();
                ListCardController controller = load.getController();
                controller.setItem(item);
            } catch (IOException e) {
                e.printStackTrace();
            }


            setGraphic(cardFrame);
        }
        else{
            setGraphic(null);
        }
    }
}
