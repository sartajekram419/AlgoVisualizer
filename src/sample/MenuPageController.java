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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuPageController {

    @FXML private AnchorPane MenuPage;
    @FXML private Button InsertionSortButton;
    @FXML private Button GraphTraversal;

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
        Rectangle board = new Rectangle(0,0, 500,400);
        board.setFill(Color.CYAN);
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

        Button saveBtn = new Button("Save Graph");
        saveBtn.setOnAction(e-> {
            if(graph.size()>0)
                promptToSave();
        });

        Button loadBtn = new Button("Load Graph");
        loadBtn.setOnAction(e->promptToLoad());

        Button clearBtn = new Button("Clear All");
        clearBtn.setOnAction(e->{
            displayPane.getChildren().clear();
            displayPane.getChildren().add(board);
            graph = new Graph(displayPane);
        });

        Label radiusSliderLbl = new Label("Radius");
        Slider radiusSlider = new Slider(15, 25, 5);
        radiusSlider.setBlockIncrement(5);
        radiusSlider.setShowTickMarks(true);
        radiusSlider.setShowTickLabels(true);
        radiusSlider.setMajorTickUnit(5);
        nodeRadius = new SimpleDoubleProperty(15);
        nodeRadius.bind(radiusSlider.valueProperty());


        VBox optionPane = new VBox(saveBtn, loadBtn, runDFSBtn, runBFSBtn, radiusSliderLbl, radiusSlider, clearBtn);
        optionPane.setSpacing(10);
        //optionPane.setAlignment(Pos.CENTER);
        optionPane.setPadding(new Insets(10));
        optionPane.setMinWidth(120);

        HBox mainPane = new HBox(optionPane, displayPane);
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setSpacing(10);

        Scene visualBoard = new Scene(mainPane, 630,400);
        board.widthProperty().bind(visualBoard.widthProperty());
        board.heightProperty().bind(visualBoard.heightProperty());


        visualBoard.getAccelerators().put(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN), ()->{
            if(graph.size() > 0){
                if(savedFile != null) {
                    try {
                        saveGraph(savedFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                    promptToSave();
            }
        });


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

    void saveGraph(String filename) throws IOException {
        File savedFile = new File(filename);
        FileWriter fout = new FileWriter(savedFile);
        fout.append(graph.size() + "\n");
        for(GraphNode graphNode :graph.getGraphNodeList()){
            fout.append(graphNode.getNode().getCenterX() + " " + graphNode.getNode().getCenterY() + " " + graphNode.getNode().getRadius() + "\n");
        }
        fout.append(graph.getEdgeList().size() + "\n");
        for(ArrayList<Integer> edge:graph.getEdgeList()){
            fout.append(edge.get(0) + " " + edge.get(1) +"\n");
        }
        this.savedFile = filename;
        fout.close();
    }

    void loadGraph(String filename) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(filename));
        int n = scan.nextInt();

        graph= new Graph(displayPane);

        //displayPane.getChildren().clear();
        for(int i=0;i<n;i++){
            double x = scan.nextDouble();
            double y = scan.nextDouble();
            double r = scan.nextDouble();
            addNode(x, y, r);
        }
        int m = scan.nextInt();
        for(int i=0;i<m;i++){
            int u = scan.nextInt();
            int v = scan.nextInt();
            addEdge(u, v);
        }
        savedFile = filename;
    }

    void promptToSave(){
        PromptWindow saveWindow = new PromptWindow("Name", "Save");
        saveWindow.getButton().setOnAction(e->{
            if(!saveWindow.getTextField().getText().isEmpty()){
                try {
                    saveGraph(saveWindow.getTextField().getText() + ".gph");
                    saveWindow.getWindowStage().close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        saveWindow.run();
    }

    void promptToLoad(){
        PromptWindow loadWindow = new PromptWindow("Name", "Load");
        loadWindow.getButton().setOnAction(e->{
            if(!loadWindow.getTextField().getText().isEmpty()){
                try{
                    loadGraph(loadWindow.getTextField().getText() + ".gph");
                    loadWindow.getWindowStage().close();
                }
                catch (FileNotFoundException fileNotFoundException){
                    //fileNotFoundException.printStackTrace();
                    loadWindow.getTextField().setText("**Invalid name!");
                }
            }
        });
        loadWindow.getTextField().setOnMouseClicked(e->loadWindow.getTextField().clear());
        loadWindow.run();
    }

    void addEdge(int u, int v){
        graph.addUndirectedEdge(u,v);
    }
}
