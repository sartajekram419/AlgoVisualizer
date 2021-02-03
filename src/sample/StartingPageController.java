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

/**
 * This is a class controlling the starting page
 *
 * @author Sartaj_180041204
 */
public class StartingPageController implements Initializable {

    @FXML private AnchorPane StartingPage;
    @FXML private Label TitleLabel;
    @FXML private Button button;
    @FXML private Label EnterKeyLabel;

    /**
     * This method launches the Menu Page when Enter Button is pressed
     *
     * @author Sartaj_180041204
     *
     * @param event Indicates if a component defined action occurred
     */
    @FXML
    void buttonPressed(ActionEvent event) throws IOException {

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MenuPage.fxml"));
        primaryStage.setTitle("AlgoVisualizer");
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        button.getScene().getWindow().hide();
    }

    /**
     * This method adds animation to the title
     *
     * @author Sartaj_180041204
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new BounceIn(TitleLabel).play();
    }
}
