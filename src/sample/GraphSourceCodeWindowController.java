package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


/**
 * This class includes the source code for graph traversal
 *
 * @author Nifty_180041219
 */
public class GraphSourceCodeWindowController {

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
