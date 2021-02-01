package sample;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.SequentialTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import javafx.beans.value.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class SortingPageController implements Initializable {

    @FXML private Button pauseButton;
    @FXML private Button sortButton;
    @FXML private Button randomButton;
    @FXML private ChoiceBox<AbstractSort> chooseBox;
    @FXML private ChoiceBox<Integer> nodeBox;
    @FXML private ChoiceBox<Integer> speedBox;
    @FXML private Pane SortingPagePane;
    @FXML private AnchorPane SortingPage;
    @FXML private TextField ElementCountButton;
    @FXML private AnchorPane LowerPane;
    @FXML private Button CustomButton;



    Connection connection;
    SequentialTransition st;
    boolean running = false;

    // Sartaj WINDOW_WIDTH = 1530 WINDOW_HEIGHT = 600

   /* public static final int WINDOW_WIDTH = 1530;
    public static final int WINDOW_HEIGHT = 600;*/

    // Sajid WINDOW_WIDTH = 1366 WINDOW_HEIGHT = 500

    public static final int WINDOW_WIDTH = 1366;
    public static final int WINDOW_HEIGHT = 500;

    // Nifty WINDOW_WIDTH = dummy WINDOW_HEIGHT = dummy

    public static final int BUTTON_BOUNDARY = 100;
    public static final int X_GAP = 10;
    public static int NO_OF_NODES = 50;
    public static int SPEED = 1;

    private Node nodes[];
    public static AbstractSort abstractSort;



    @FXML
    void CustomButtonAction(ActionEvent event) throws IOException {

        int[] arr = new int[NO_OF_NODES];
        Parent root = FXMLLoader.load(getClass().getResource("CustomInputPage.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("AlgoVisualizer");
        primaryStage.setScene(new Scene(root,600,550));
        primaryStage.showAndWait();
        arr = CustomInputPageController.arr;

        /////System.out.println(CustomInputPageController.a);
        /////System.out.println(' ');

        /////for(int i=0;i<NO_OF_NODES;i++)System.out.println(arr[i]);

        CustomGenerator(NO_OF_NODES,arr);
    }



    @FXML
    void ElementCountButton(ActionEvent event) {

        String s = ElementCountButton.getText();
        NO_OF_NODES = Integer.parseInt(s);
        abstractSort.X = WINDOW_WIDTH / NO_OF_NODES;
        //this.abstractSort.setX();
        randomGenerator(NO_OF_NODES);;

        //System.out.println(NO_OF_NODES);
    }

    @FXML
    void randomButtonAction(ActionEvent event) {
        randomGenerator(NO_OF_NODES);
    }

    @FXML
    void pauseButtonAction(ActionEvent event) {
        if (running) {
            st.pause();
            pauseButton.setText("Continue");
            running = false;
        }
        else {
            st.play();
            pauseButton.setText("Pause");
            running = true;
        }
    }

    @FXML
    void sortButtonAction(ActionEvent event) {
        running = true;
        sortButton.setDisable(true);
        randomButton.setDisable(true);
        pauseButton.setDisable(false);

        abstractSort = chooseBox.getSelectionModel().getSelectedItem();
        st = new SequentialTransition();
        st.getChildren().addAll(abstractSort.startSort(nodes));
        st.setOnFinished(e -> {
            randomButton.setDisable(false);
            pauseButton.setDisable(true);
            running = false;
        });
        st.play();
    }


    public void randomGenerator(int val) {
        sortButton.setDisable(false);
        SortingPagePane.getChildren().clear();
        this.nodes = GenerateRandomNodes.GenerateRandomNodes(val);
        SortingPagePane.getChildren().addAll(Arrays.asList(nodes));
    }

    public void CustomGenerator(int val, int[] arr) {
        sortButton.setDisable(false);
        SortingPagePane.getChildren().clear();
        this.nodes = GenerateCustomNodes.GenerateCustomNodes(val,arr);
        SortingPagePane.getChildren().addAll(Arrays.asList(nodes));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //try { connection = DriverManager.getConnection("jdbc:derby://localhost:1527/myDatabase", "app", "app"); }
        //catch(SQLException e) { }

        pauseButton.setDisable(true);

        this.nodes = GenerateRandomNodes.GenerateRandomNodes(NO_OF_NODES);
        SortingPagePane.getChildren().addAll(Arrays.asList(nodes));
        //randomGenerator(NO_OF_NODES);
        //for (int i = 0; i < NO_OF_NODES; i++) System.out.println(nodes[i].getValue());

        Integer speed[] = {1, 5, 10, 50, 100, 250, 500};
        //speedBox.setValue(50);
        Tooltip tt1 = new Tooltip();
        tt1.setStyle("-fx-base: #AE3522; " + "-fx-text-fill: BLACK;" + "-fx-background-color : WHITE;");
        tt1.setText("Choose Speed");
        speedBox.setTooltip(tt1);
        speedBox.setItems(FXCollections.observableArrayList(speed));

        speedBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                Integer n = new Integer(newValue);
                SPEED = n.intValue();
            }
        });

        List <AbstractSort> sortList = new ArrayList<>();
        sortList.add(new InsertionSort());
        sortList.add(new SelectionSort());
        sortList.add(new QuickSort());
        sortList.add(new BubbleSort());


        chooseBox.setValue(new InsertionSort());
        Tooltip tt2 = new Tooltip();
        tt2.setStyle("-fx-base: #AE3522; " + "-fx-text-fill: BLACK;" + "-fx-background-color : WHITE;");
        tt2.setText("Choose Sorting Algorithm");
        chooseBox.setTooltip(tt2);
        chooseBox.setItems(FXCollections.observableArrayList(sortList));
        chooseBox.getSelectionModel().select(4);

        chooseBox.setConverter(new StringConverter<AbstractSort>() {
            @Override
            public String toString(AbstractSort absSort) {
                if (absSort == null) return "";
                else return absSort.getClass().getSimpleName();
            }

            @Override
            public AbstractSort fromString(String s) {
                return null;
            }
        });
    }

}
