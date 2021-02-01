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

import java.io.IOException;

public class MenuPageController {

    @FXML private AnchorPane MenuPage;
    @FXML private Button InsertionSortButton;
    @FXML private Button GraphTraversal;
    @FXML private Button SearchButton;
    @FXML private Button PathFinderButton;

    @FXML
    void PathFinderButtonPressed(ActionEvent event) {

    }

    @FXML
    void SearchButtonPressed(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SearchPage.fxml"));
        primaryStage.setTitle("AlgoVisualizer");
        primaryStage.setScene(new Scene(root));
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.show();

        InsertionSortButton.getScene().getWindow().hide();
    }



    @FXML
    void InsertionSortButtonPressed(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SortingPage.fxml"));
        primaryStage.setTitle("AlgoVisualizer");
        primaryStage.setScene(new Scene(root));
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.show();

        InsertionSortButton.getScene().getWindow().hide();
    }




    private Graph graph;
    private Pane displayPane;

    private String savedFile = null;

    private int selectedNode = -1;

    DoubleProperty nodeRadius;

    @FXML
    void GraphTraversalButtonPressed(ActionEvent event) throws IOException{

        Stage primaryStage = new Stage();
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);

        GraphTraversal.getScene().getWindow().hide();

        Rectangle board = new Rectangle(0,0, 500,400);
        board.setFill(Color.web("0x170538",1.0));
        board.setOnMouseClicked(e->addNode(e.getX(), e.getY()));

        displayPane = new Pane(board);
        graph = new Graph(displayPane);

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

        Label radiusSliderLbl = new Label("Radius");
        Slider radiusSlider = new Slider(10, 30, 20);
        radiusSlider.setBlockIncrement(5);
        radiusSlider.setShowTickMarks(true);
        radiusSlider.setShowTickLabels(true);
        radiusSlider.setMajorTickUnit(5);
        nodeRadius = new SimpleDoubleProperty(15);
        nodeRadius.bind(radiusSlider.valueProperty());


        VBox optionPane = new VBox(runDFSBtn, runBFSBtn, radiusSliderLbl, radiusSlider, clearBtn);
        optionPane.setSpacing(25);
        //optionPane.setAlignment(Pos.CENTER);
        optionPane.setPadding(new Insets(25));
        optionPane.setMinWidth(220);

        HBox mainPane = new HBox(optionPane, displayPane);
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setSpacing(10);

        Scene visualBoard = new Scene(mainPane, 630,400);
        board.widthProperty().bind(visualBoard.widthProperty());
        board.heightProperty().bind(visualBoard.heightProperty());


        primaryStage.setScene(visualBoard);
        primaryStage.setTitle("Graph Visualizer");
        primaryStage.show();
    }

    void addNode(double x, double y){
        addNode(x, y, nodeRadius.doubleValue());
        //System.out.println(graph);
    }

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
                graph.getNode(selectedNode).getNode().setFill(Color.RED);
                selectedNode = -1;
            }
        });
    }




    void addEdge(int u, int v){
        graph.addUndirectedEdge(u,v);
    }
}
