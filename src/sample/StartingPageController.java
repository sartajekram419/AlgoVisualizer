package sample;

import animatefx.animation.BounceIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class StartingPageController implements Initializable {

    @FXML
    private AnchorPane StartingPage;

    @FXML
    private Label TitleLabel;

    @FXML
    private Button button;

    @FXML
    private Label EnterKeyLabel;


    @FXML
    void buttonPressed(ActionEvent event) throws IOException {

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MenuPage.fxml"));
        primaryStage.setTitle("AlgoVisualizer");
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        button.getScene().getWindow().hide();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new BounceIn(TitleLabel).play();
    }

}
