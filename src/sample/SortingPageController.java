package sample;

import javafx.animation.SequentialTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


/**
 * This class controls the sort page
 *
 * @author Nifty_180041219
 * @author Sajid_180041203
 * @author Sartaj_180041204
 */
public class SortingPageController implements Initializable {

    @FXML private Button pauseButton;
    @FXML private Button sortButton;
    @FXML private Button randomButton;
    @FXML private ChoiceBox<AbstractSort> chooseBox;
    @FXML private ChoiceBox<Integer> speedBox;
    @FXML private Pane SortingPagePane;
    @FXML private AnchorPane SortingPage;
    @FXML private TextField ElementCountButton;
    @FXML private AnchorPane LowerPane;
    @FXML private Button CustomButton;
    @FXML private Button BackButton;
    @FXML private Button SourceButton;



    /**
     * This method launches the source code window for sorting
     *
     * @author Sajid_180041203
     *
     * @param event Indicates if a component defined action occurred
     */
    @FXML
    void SourceButtonPressed(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SourceCodeWindow.fxml"));
        primaryStage.setTitle("AlgoVisualizer");
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        /*primaryStage.setMaximized(true);
        primaryStage.setResizable(false);*/
        primaryStage.setScene(new Scene(root,600,600));
        primaryStage.showAndWait();
    }

    SequentialTransition st;
    boolean running = false;

    // Sartaj WINDOW_WIDTH = 1530 WINDOW_HEIGHT = 600

    public static final int WINDOW_WIDTH = 1530;
    public static final int WINDOW_HEIGHT = 600;

    // Sajid WINDOW_WIDTH = 1366 WINDOW_HEIGHT = 500

    /*public static final int WINDOW_WIDTH = 1366;
    public static final int WINDOW_HEIGHT = 500;*/

    // Nifty WINDOW_WIDTH = dummy WINDOW_HEIGHT = dummy

    public static final int BUTTON_BOUNDARY = 0;
    public static final int X_GAP = 10;
    public static int NO_OF_NODES = 50;
    public static int SPEED = 20;

    private Node nodes[];
    public static AbstractSort abstractSort;


    /**
     * This method sends back to Menu Page
     *
     * @author Sajid_180041203
     *
     * @param event Indicates if a component defined action occurred
     */
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


    /**
     * This method launches Window for customized input
     *
     * @author Sajid_180041203
     *
     * @param event Indicates if a component defined action occurred
     */
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


    /**
     * This method sets the value for # of nodes
     *
     * @author Sajid_180041203
     *
     * @param event Indicates if a component defined action occurred
     */
    @FXML
    void ElementCountButton(ActionEvent event) {

        String s = ElementCountButton.getText();
        NO_OF_NODES = Integer.parseInt(s);
        abstractSort.X = WINDOW_WIDTH / NO_OF_NODES;
        //this.abstractSort.setX();
        randomGenerator(NO_OF_NODES);;

        //System.out.println(NO_OF_NODES);
    }

    /**
     * This method creates randomized array
     *
     * @author Nifty_180041219
     *
     * @param event Indicates if a component defined action occurred
     */
    @FXML
    void randomButtonAction(ActionEvent event) {
        randomGenerator(NO_OF_NODES);
    }

    /**
     * This method pauses the sorting
     *
     * @author Nifty_180041219
     *
     * @param event Indicates if a component defined action occurred
     */
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

    /**
     * This method starts the sorting
     *
     * @author Nifty_180041219
     *
     * @param event Indicates if a component defined action occurred
     */
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

    /**
     * This method generates random array and displays the nodes
     *
     * @author Nifty_180041219
     *
     * @param val # of nodes
     */
    public void randomGenerator(int val) {
        sortButton.setDisable(false);
        SortingPagePane.getChildren().clear();
        this.nodes = GenerateRandomNodes.GenerateRandomNodes(val);
        SortingPagePane.getChildren().addAll(Arrays.asList(nodes));
    }

    /**
     * This method displays the customized nodes
     *
     * @author Nifty_180041219
     *
     * @param val # of nodes
     * @param arr customized array
     */
    public void CustomGenerator(int val, int[] arr) {
        sortButton.setDisable(false);
        SortingPagePane.getChildren().clear();
        this.nodes = GenerateCustomNodes.GenerateCustomNodes(val,arr);
        SortingPagePane.getChildren().addAll(Arrays.asList(nodes));
    }

    /**
     * This initalizes when the class is created
     *
     * @author Sartaj_180041204
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        pauseButton.setDisable(true);

        this.nodes = GenerateRandomNodes.GenerateRandomNodes(NO_OF_NODES);
        SortingPagePane.getChildren().addAll(Arrays.asList(nodes));
        //randomGenerator(NO_OF_NODES);
        //for (int i = 0; i < NO_OF_NODES; i++) System.out.println(nodes[i].getValue());

        Integer speed[] = {1, 5, 10, 20, 50, 100, 250, 500};
        //speedBox.setValue(50);
        Tooltip tt1 = new Tooltip();
        tt1.setStyle("-fx-base: #AE3522; " + "-fx-text-fill: BLACK;" + "-fx-background-color : WHITE;");
        tt1.setText("Choose Speed");
        speedBox.setTooltip(tt1);
        speedBox.setValue(20);
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
        sortList.add(new MergeSort());


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
