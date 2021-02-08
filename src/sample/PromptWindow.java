package sample;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class is there for a prompt window
 *
 * @author Nifty_180041219
 * @author Sajid_180041203
 *
 */
public class PromptWindow {
    private Label textLabel;
    private TextField textField;
    private Button button;
    private Stage windowStage;

    public PromptWindow(String lblTxt, String btnTxt){
        textLabel = new Label(lblTxt);
        button = new Button(btnTxt);
        textField = new TextField();
        textField.setPromptText("Enter " + lblTxt);
    }

    public Label getTextLabel() {
        return textLabel;
    }

    public void setTextLabel(Label textLabel) {
        this.textLabel = textLabel;
    }

    public TextField getTextField() {
        return textField;
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Stage getWindowStage() {
        return windowStage;
    }

    public void setWindowStage(Stage windowStage) {
        this.windowStage = windowStage;
    }

    public void run(){
        windowStage = new Stage();

        button.setAlignment(Pos.CENTER);

        VBox vpane = new VBox(textLabel, textField, button);
        vpane.setAlignment(Pos.CENTER);
        vpane.setPadding(new Insets(10));
        vpane.setSpacing(10);

        Pane windowPane = new Pane(vpane);

        windowStage.setScene(new Scene(windowPane));
        windowStage.setResizable(false);

        windowStage.show();
    }
}
