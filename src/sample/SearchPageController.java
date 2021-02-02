package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SearchPageController {

    private ArrayList<SearchNode> searchNodeList;
    private Timeline visualizer;

    public static int arraySize = 5;
    public int[] array;
    public int searchElement = 4;

    @FXML
    private Button InputButton;

    @FXML
    private Button ClearButton;

    @FXML
    private AnchorPane MenuPage;

    @FXML
    private AnchorPane SideBar;

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
    private Button BackButton;

    @FXML
    private Button CodeButton;

    @FXML
    void InputButtonPressed(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SearchCustomInput.fxml"));
        primaryStage.setTitle("AlgoVisualizer");
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        //primaryStage.setMaximized(true);
        //primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root,600,600));
        primaryStage.showAndWait();

        //System.out.println(SearchCustomInputController.arr[0]);
        array = new int[arraySize];
        array = SearchCustomInputController.arr;

        searchNodeList = new ArrayList<>();

        for(int i=0;i<arraySize;i++){
            searchNodeList.add(new SearchNode(ArrayHolder,array[i],i));
        }
    }

    @FXML
    void ClearButtonPressed(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SearchPage.fxml"));
        primaryStage.setTitle("AlgoVisualizer");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        ClearButton.getScene().getWindow().hide();
    }

    @FXML
    void BackButtonPressed(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MenuPage.fxml"));
        primaryStage.setTitle("AlgoVisualizer");
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        BackButton.getScene().getWindow().hide();
    }

    @FXML
    void CodeButtonPressed(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SearchSourceCode.fxml"));
        primaryStage.setTitle("AlgoVisualizer");
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        /*primaryStage.setMaximized(true);
        primaryStage.setResizable(false);*/
        primaryStage.setScene(new Scene(root,600,600));
        primaryStage.showAndWait();
    }


    @FXML
    void OkButtonPressed(ActionEvent event) {

        msg.setText("");
        array = new int[arraySize];
        Random rand = new Random();

        for(int i=0; i<arraySize; i++){
            array[i] = rand.nextInt(100);
        }

        searchNodeList = new ArrayList<>();

        for(int i=0;i<arraySize;i++){
            searchNodeList.add(new SearchNode(ArrayHolder,array[i],i));
        }

    }

    @FXML
    void BinarySearchButtonPressed(ActionEvent event) {

        for(int i=1; i<arraySize; i++){
            if(array[i]<array[i-1]){
                msg.setText("Array is not Sorted");
                return;
            }
        }
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



}
