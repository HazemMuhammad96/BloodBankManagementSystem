package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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

        //alert.setHeaderText("Are You Sure?");
        DialogPane dialogPane = alert.getDialogPane();
        GridPane grid = new GridPane();
        ColumnConstraints graphicColumn = new ColumnConstraints();
        graphicColumn.setFillWidth(false);
        graphicColumn.setHgrow(Priority.NEVER);
        ColumnConstraints textColumn = new ColumnConstraints();
        textColumn.setFillWidth(true);
        textColumn.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().setAll(graphicColumn, textColumn);
        grid.setPadding(new Insets(5));

        Image image1 = new Image("images/AlertLogo.png");
        ImageView imageView = new ImageView(image1);
        imageView.setFitWidth(48);
        imageView.setFitHeight(48);

        StackPane stackPane = new StackPane(imageView);
        stackPane.setAlignment(Pos.CENTER);
        grid.add(stackPane, 0, 0);
        GridPane.setMargin(stackPane, new Insets(10, 0, 0, 10));

        Label headerLabel = new Label("Are You Sure?");
        headerLabel.setFont(new Font("Montserrat Semibold", 30));
        headerLabel.setTextFill(Color.WHITE);
        headerLabel.setWrapText(true);
        headerLabel.setAlignment(Pos.CENTER);
        headerLabel.setMaxWidth(Double.MAX_VALUE);
        headerLabel.setMaxHeight(Double.MAX_VALUE);
        grid.add(headerLabel, 1, 0);

        dialogPane.setHeader(grid);
        dialogPane.setGraphic(null);

        Optional<ButtonType> result = alert.showAndWait();

        return result.get();
    }

}
