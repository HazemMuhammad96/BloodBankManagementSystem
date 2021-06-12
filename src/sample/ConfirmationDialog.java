package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

import java.util.Optional;

public class ConfirmationDialog {

    private static Alert alert;
    private static DialogPane dialogPane;

    public static ButtonType showDialog(){
        if(alert == null){
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add("AlertBox.css");
            dialogPane.getScene().setFill(Color.TRANSPARENT);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.initStyle(StageStyle.TRANSPARENT);
        }

        alert.setHeaderText("Confirm Purchase?");

        ImageView icon = new ImageView("images/AlertLogo.png");
        icon.setFitHeight(48);
        icon.setFitWidth(48);
        alert.getDialogPane().setGraphic(icon);
        Optional<ButtonType> result = alert.showAndWait();

        return result.get();
    }

}
