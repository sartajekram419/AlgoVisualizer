package sample;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SearchPageController {

    @FXML
    private Button LinearSearchButton;

    @FXML
    private Button BinarySearchButton;

    @FXML
    private TextField Size;

    @FXML
    private TextField SearchElement;

    @FXML
    private Pane ArrayHolder;

    @FXML
    private Button Ok;

    @FXML
    private Pane MovingElement;

    @FXML
    void OkButtonPressed(ActionEvent event) {

        for(int i=0; i<arraySize; i++) {
            Text textNodeID = new Text(String.valueOf(array[i]));
            textNodeID.setFill(Color.BLACK);
            Circle node = new Circle();
            node.setFill(Color.RED);
            node.setRadius(10);
            node.setCenterX(i*20+i*15);
            node.setCenterY(20);
            textNodeID.setFont(Font.font(node.getRadius()));
            StackPane nodePane = new StackPane(node, textNodeID);
            nodePane.setLayoutX(node.getCenterX() - node.getRadius());
            nodePane.setLayoutY(node.getCenterY() - node.getRadius());
            ArrayHolder.getChildren().add(nodePane);
        }
    }

    @FXML
    void BinarySearchButtonPressed(ActionEvent event) {

    }

    @FXML
    void LinearSearchButton(ActionEvent event) {


        for(int i=0; i<arraySize; i++) {

            Text textNodeID = new Text(String.valueOf(searchElement));
            textNodeID.setFill(Color.BLACK);
            Circle node = new Circle();
            node.setFill(Color.RED);
            node.setRadius(10);
            node.setCenterX(i*20+i*15);
            node.setCenterY(20);
            textNodeID.setFont(Font.font(node.getRadius()));
            StackPane nodePane = new StackPane(node, textNodeID);
            nodePane.setLayoutX(node.getCenterX() - node.getRadius());
            nodePane.setLayoutY(node.getCenterY() - node.getRadius());

            TranslateTransition trans1 = new TranslateTransition();
            trans1.setByY(300);
            trans1.setCycleCount(50);
            trans1.setDuration(Duration.millis(500));
            trans1.setAutoReverse(true);
            trans1.setNode(nodePane);
            trans1.play();

        }
    }

    @FXML
    void SearchElementPressed(ActionEvent event) {
        String s = SearchElement.getText();
        searchElement = Integer.parseInt(s);
    }

    @FXML
    void SizePressed(ActionEvent event) {
        String s = Size.getText();
        arraySize = Integer.parseInt(s);
    }

    public int arraySize = 5;
    public int array[] = {1, 2, 3, 4, 5};
    public int searchElement = 3;

}
