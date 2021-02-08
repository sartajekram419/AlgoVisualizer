package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


/**
 * This class controls the source code page for searching
 *
 * @author Nifty_180041219
 */
public class SearchSourceCodeController {

    @FXML
    private Button OkButton;
    @FXML
    private Label SortNameLabel;
    @FXML
    private TextArea CodeArea;


    @FXML
    void OkButtonPressed(ActionEvent event) {
        OkButton.getScene().getWindow().hide();
    }
}
