package sample;

import java.io.IOException;
import java.nio.file.Files;

import javax.xml.crypto.Data;

import Users.Donor;
import Users.Recipient;
import Users.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Blood Bank");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 1300, 860));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
