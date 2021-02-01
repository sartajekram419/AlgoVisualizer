package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

public class SearchPageController {

    private ArrayList<SearchNode> searchNodeList;
    private Timeline visualizer;

    @FXML
    private Label msg;

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

        searchNodeList = new ArrayList<>();

        for(int i=0;i<arraySize;i++){
            searchNodeList.add(new SearchNode(ArrayHolder,array[i],i));
        }

    }

    @FXML
    void BinarySearchButtonPressed(ActionEvent event) {

        binaryRecursion(0,arraySize);

    }


    @FXML
    void LinearSearchButton(ActionEvent event) throws InterruptedException {


        linearRecursion(0, arraySize);

    }

    void binaryRecursion(int l, int r){
        KeyFrame bfsKeyFrame = new KeyFrame(Duration.seconds(1), e->{

            int m=(l+r)/2;
            if(m<arraySize && array[m]==searchElement){
                searchNodeList.get(m).getNode().setFill(Color.BLUE);
                visualizer.stop();
                msg.setText("Element Found");
            }
            else if(l<r && array[m]>searchElement){
                searchNodeList.get(m).getNode().setFill(Color.BLUE);
                binaryRecursion(l,m-1);
            }
            else if(l<r && array[m]<searchElement){
                searchNodeList.get(m).getNode().setFill(Color.BLUE);
                binaryRecursion(m+1,r);
            }
            else if(l<r){
                searchNodeList.get(m).getNode().setFill(Color.BLUE);
                visualizer.stop();
                msg.setText("Element Found");
            }
            else{
                visualizer.stop();
                msg.setText("Element Not Found");
            }
        });
        visualizer = new Timeline(bfsKeyFrame);
        visualizer.setCycleCount(Animation.INDEFINITE);
        visualizer.play();
    }

    void linearRecursion(int i, int n){
        KeyFrame bfsKeyFrame = new KeyFrame(Duration.seconds(1), e->{
            if(i<arraySize && array[i]!=searchElement){
                searchNodeList.get(i).getNode().setFill(Color.BLUE);
                linearRecursion(i+1,n);
            }
            else if(i<arraySize){
                searchNodeList.get(i).getNode().setFill(Color.BLUE);
                visualizer.stop();
                msg.setText("Element Found");
            }
            else{
                visualizer.stop();
                msg.setText("Element Not Found");
            }
        });
        visualizer = new Timeline(bfsKeyFrame);
        visualizer.setCycleCount(Animation.INDEFINITE);
        visualizer.play();
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
    public int searchElement = 4;

}
