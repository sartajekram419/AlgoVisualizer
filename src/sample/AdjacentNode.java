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

    /**
     * Constructor that sets the adjacent graphnode
     *
     * @author Sartaj_180041204
     *
     */
    public AdjacentNode(GraphNode adjGraphNode){
        this.adjGraphNode = adjGraphNode;
    }

    /**
     * Constructor that sets the adjacent graphnode and edge
     *
     * @author Sartaj_180041204
     *
     */
    public AdjacentNode(GraphNode adjGraphNode, Line edge){
        this.adjGraphNode = adjGraphNode;
        this.edge = edge;
    }

    /**
     * Constructor that sets the adjacent graphnode, edge and displays that in the pane
     *
     * @author Sartaj_180041204
     *
     */
    public AdjacentNode(GraphNode adjGraphNode, Line edge, Pane displayPane){
        this(adjGraphNode, edge);
        drawEdge(displayPane);
    }

    /**
     * Method used to draw an edge
     *
     * @author Sartaj_180041204
     *
     */
    public void drawEdge(Pane displayPane){
        displayPane.getChildren().add(edge);
    }

    /**
     * Returns node value as string
     *
     * @author Sartaj_180041204
     *
     * @return String a string
     */
    @Override
    public String toString() {
        return adjGraphNode.toString();
    }

    /**
     * Returns adjacent graph node
     *
     * @author Sartaj_180041204
     *
     */
    public GraphNode getAdjGraphNode() {
        return adjGraphNode;
    }

    /**
     * This method sets the adjacent graphnode
     *
     * @author Sartaj_180041204
     *
     * @param adjGraphNode It denotes adjacent graph node
     */
    public void setAdjGraphNode(GraphNode adjGraphNode) {
        this.adjGraphNode = adjGraphNode;
    }

    /**
     * Returns the edge
     *
     * @author Sartaj_180041204
     *
     */
    public Line getEdge() {
        return edge;
    }

    /**
     * sets the edge
     *
     * @author Sartaj_180041204
     *
     * @param edge An edge
     */
    public void setEdge(Line edge) {
        this.edge = edge;
    }

}
