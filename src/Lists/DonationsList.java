package Lists;

import Users.DonationRequest;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class DonationsList extends ListCell<DonationRequest> {
    HBox cardFrame;
    public DonationsList(){
super();
FXMLLoader load = new FXMLLoader();
        try {
            load.setLocation(getClass().getResource("../sample/ListCard.fxml"));
            cardFrame = load.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(DonationRequest item, boolean empty) {
        super.updateItem(item, empty);
        if(item != null)
        {
         setGraphic(cardFrame);   
        }
    }
}
