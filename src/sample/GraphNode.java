package sample;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class GraphNode {
    private int nodeID;
    private Circle node;
    private ArrayList<AdjacentNode> adjacentNodes;

    public ArrayList<AdjacentNode> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(ArrayList<AdjacentNode> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public GraphNode(int n){
        this(n, null);
    }

    public GraphNode(int n, double nodeX, double nodeY, double nodeR, Pane displayNode){
        this(n, createNode(nodeX, nodeY, nodeR));
        drawNode(displayNode);
    }

    public GraphNode(int nid, Circle node){
        nodeID = nid;
        this.node = node;
        adjacentNodes = new ArrayList<>();
    }

    public GraphNode(int nid, Circle node, Pane displayPane){
        this(nid, node);
        drawNode(displayPane);
    }

    public void drawNode(Pane displayPane) {
        Text textNodeID = new Text(String.valueOf(nodeID));
        textNodeID.setFill(Color.YELLOW);
        textNodeID.setFont(Font.font(node.getRadius()));
        StackPane nodePane = new StackPane(node, textNodeID);
        nodePane.setLayoutX(node.getCenterX() - node.getRadius());
        nodePane.setLayoutY(node.getCenterY() - node.getRadius());
        displayPane.getChildren().add(nodePane);
    }

    public static Circle createNode(double x, double y, double r){
        Circle gnode = new Circle(x,y, r);
        gnode.setFill(Color.RED);
        return gnode;
    }

    void setNode(Pane displayPane, Circle gnode){
        node = gnode;
        drawNode(displayPane);
    }

    public Circle getNode() {
        return node;
    }

    public void setNode(Circle node) {
        this.node = node;
    }

    int getNodeID(){
        return nodeID;
    }
    void setNodeID(int n){
        nodeID = n;
    }

    @Override
    public String toString() {
        return String.valueOf(nodeID);
    }

    public void addAdjacentNode(GraphNode adjGraphNode, Line edge){
        adjacentNodes.add(new AdjacentNode(adjGraphNode, edge));
    }

    public void addAdjacentNode(GraphNode adjGraphNode, Line edge, Pane displayPane) {
        adjacentNodes.add(new AdjacentNode(adjGraphNode, edge, displayPane));
    }

    public void addAdjacentNode(GraphNode adjGraphNode, Pane displayPane) {
        adjacentNodes.add(new AdjacentNode(adjGraphNode, Graph.createEdge(this, adjGraphNode), displayPane));
    }

}
