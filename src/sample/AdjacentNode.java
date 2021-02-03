package sample;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 * This class sets the adjacent nodes
 *
 * @author Sartaj_180041204
 *
 */
public class AdjacentNode {
    private GraphNode adjGraphNode;
    private Line edge;


    public AdjacentNode(GraphNode adjGraphNode){
        this.adjGraphNode = adjGraphNode;
    }

    public AdjacentNode(GraphNode adjGraphNode, Line edge){
        this.adjGraphNode = adjGraphNode;
        this.edge = edge;
    }

    public AdjacentNode(GraphNode adjGraphNode, Line edge, Pane displayPane){
        this(adjGraphNode, edge);
        drawEdge(displayPane);
    }

    public void drawEdge(Pane displayPane){
        displayPane.getChildren().add(edge);
    }

    @Override
    public String toString() {
        return adjGraphNode.toString();
    }

    public GraphNode getAdjGraphNode() {
        return adjGraphNode;
    }

    public void setAdjGraphNode(GraphNode adjGraphNode) {
        this.adjGraphNode = adjGraphNode;
    }

    public Line getEdge() {
        return edge;
    }

    public void setEdge(Line edge) {
        this.edge = edge;
    }

}
