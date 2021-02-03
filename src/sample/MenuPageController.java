package sample;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


/**
 * This class controls the Menu Page
 *
 * @author Sajid_180041203
 * @author Nifty_180041219
 * @author Sartaj_180041204
 *
 */
public class MenuPageController {

    @FXML private AnchorPane MenuPage;
    @FXML private Button InsertionSortButton;
    @FXML private Button GraphTraversal;
    @FXML private Button SearchButton;


    /**
     * This method launches the Search Page when Searching Button is clicked
     *
     * @author Sajid_180041203
     *
     * @param event Indicates if a component defined action occurred
     */
    @FXML
    void SearchButtonPressed(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SearchPage.fxml"));
        primaryStage.setTitle("AlgoVisualizer");
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.show();

        InsertionSortButton.getScene().getWindow().hide();
    }

    /**
     * This method launches the Sorting Page when Sorting Button is clicked
     *
     * @author Nifty_180041219
     *
     * @param event Indicates if a component defined action occurred
     */
    @FXML
    void InsertionSortButtonPressed(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SortingPage.fxml"));
        primaryStage.setTitle("AlgoVisualizer");
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.show();

        InsertionSortButton.getScene().getWindow().hide();
    }




    private Graph graph;
    private Pane displayPane;


    private int selectedNode = -1;

    DoubleProperty nodeRadius;


    /**
     * This method launches the Graph Traversal Page when Graph Traversal Button is clicked
     *
     * @author Sajid_180041203
     * @author Nifty_180041219
     * @author Sartaj_180041204
     *
     * @param event Indicates if a component defined action occurred
     */
    @FXML
    void GraphTraversalButtonPressed(ActionEvent event) throws IOException{

        Stage primaryStage = new Stage();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);



        Rectangle board = new Rectangle(0,0, 500,400);
        board.setFill(Color.web("0x151019",1.0));
        board.setOnMouseClicked(e->addNode(e.getX(), e.getY()));

        displayPane = new Pane(board);
        graph = new Graph(displayPane);


        Button sourceButton = new Button("Source Code");
        sourceButton.setOnAction(e->{
            Stage stage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("GraphSourceCodeWindow.fxml"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            stage.setTitle("AlgoVisualizer");
            //primaryStage.initStyle(StageStyle.UNDECORATED);
            //stage.setMaximized(true);
            //stage.setResizable(false);
            stage.setScene(new Scene(root,600,600));
            stage.showAndWait();

        });

        Button backButton = new Button("<< Back");
        backButton.setOnAction(e->{
            Stage stage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("MenuPage.fxml"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            stage.setTitle("AlgoVisualizer");
            //primaryStage.initStyle(StageStyle.UNDECORATED);
            stage.setMaximized(true);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();

            backButton.getScene().getWindow().hide();
        });

        Button runDFSBtn = new Button("Run DFS");
        runDFSBtn.setOnAction(e->{
            if(selectedNode != -1) {
                AlgoVisualizer dfsVisualizer = new AlgoVisualizer(graph);
                dfsVisualizer.runDFS(selectedNode);
                selectedNode = -1;
            }
        });

        Button runBFSBtn = new Button("Run BFS");
        runBFSBtn.setOnAction(e->{
            if(selectedNode != -1) {
                AlgoVisualizer dfsVisualizer = new AlgoVisualizer(graph);
                dfsVisualizer.runBFS(selectedNode);
                selectedNode = -1;
            }
        });


        Button clearBtn = new Button("Clear All");
        clearBtn.setOnAction(e->{
            displayPane.getChildren().clear();
            displayPane.getChildren().add(board);
            graph = new Graph(displayPane);
        });

        backButton.setStyle("-fx-background-color: #151019; -fx-text-fill: white;-fx-font-size: 24px; -fx-pref-width: 175px;");
        runBFSBtn.setStyle("-fx-background-color: #151019; -fx-text-fill: white;-fx-font-size: 24px; -fx-pref-width: 175px;");
        runDFSBtn.setStyle("-fx-background-color: #151019; -fx-text-fill: white;-fx-font-size: 24px; -fx-pref-width: 175px;");
        clearBtn.setStyle("-fx-background-color: #151019; -fx-text-fill: white;-fx-font-size: 24px; -fx-pref-width: 175px;");
        sourceButton.setStyle("-fx-background-color: #151019; -fx-text-fill: white;-fx-font-size: 24px; -fx-pref-width: 175px;");

        Label radiusSliderLbl = new Label("Radius");
        radiusSliderLbl.setStyle("-fx-font-size: 24px; -fx-text-alignment: center");
        Slider radiusSlider = new Slider(10, 30, 20);
        radiusSlider.setBlockIncrement(5);
        radiusSlider.setShowTickMarks(true);
        radiusSlider.setShowTickLabels(true);
        radiusSlider.setMajorTickUnit(5);
        nodeRadius = new SimpleDoubleProperty(15);
        nodeRadius.bind(radiusSlider.valueProperty());


        VBox optionPane = new VBox(backButton, runDFSBtn, runBFSBtn, radiusSliderLbl, radiusSlider, clearBtn, sourceButton);
        optionPane.setSpacing(35);
        //optionPane.setAlignment(Pos.CENTER);
        optionPane.setPadding(new Insets(25));
        optionPane.setMinWidth(320);

        HBox mainPane = new HBox(optionPane, displayPane);
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setSpacing(10);

        Scene visualBoard = new Scene(mainPane, 630,400);
        board.widthProperty().bind(visualBoard.widthProperty());
        board.heightProperty().bind(visualBoard.heightProperty());


        primaryStage.setScene(visualBoard);
        primaryStage.setTitle("Graph Visualizer");
        primaryStage.show();

        GraphTraversal.getScene().getWindow().hide();
    }

    /**
     * This method takes the coordinates of the node and calls a different add_node method
     *
     * @author Sajid_180041203
     *
     * @param x defines the X-coordinate of the node
     * @param y defines the Y-coordinate of the node
     */
    void addNode(double x, double y){
        addNode(x, y, nodeRadius.doubleValue());
        //System.out.println(graph);
    }


    /**
     * This method calls the add_edge method or selects a node depending on specific conditions
     *
     * @author Sajid_180041203
     * @author Nifty_180041219
     * @author Sartaj_180041204
     *
     * @param x defines the X-coordinate of the node
     * @param y defines the Y-coordinate of the node
     * @param radius defines the radius of the node
     */
    void addNode(double x, double y, double radius){
        graph.addNode(x,y,radius);
        GraphNode graphNode = graph.getNode(graph.size()-1);
        displayPane.getChildren().get(displayPane.getChildren().size()-1).setOnMouseClicked(e->{
            if(selectedNode == -1){
                selectedNode = graphNode.getNodeID();
                graphNode.getNode().setFill(Color.DARKRED);
            }
            else if(selectedNode != graphNode.getNodeID()){
                addEdge(selectedNode, graphNode.getNodeID());
                graph.getNode(selectedNode).getNode().setFill(Color.WHITE);
                selectedNode = -1;
            }
        });
    }



    /**
     * This method calls a method to add unidirectional edge between two nodes

     * @author Sartaj_180041204
     *
     * @param u first node
     * @param v second node
     */
    void addEdge(int u, int v){
        graph.addUndirectedEdge(u,v);
    }
}
