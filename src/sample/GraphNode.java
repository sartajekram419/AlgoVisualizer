package sample;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;


/**
 * This class creates node
 *
 * @author Sajid_180041203
 * @author Nifty_180041219
 * @author Sartaj_180041204
 *
 */
public class GraphNode {
    private int nodeID;
    private Circle node;
    private ArrayList<AdjacentNode> adjacentNodes;

    /**
     * This method returns the adjacent node list of a specific node
     *
     * @author Nifty_180041219
     *
     */
    public ArrayList<AdjacentNode> getAdjacentNodes() {
        return adjacentNodes;
    }

    /**
     * This method sets the adjacent node list of a specific node
     *
     * @author Nifty_180041219
     *
     * @param adjacentNodes adjacentNodes list
     *
     */
    public void setAdjacentNodes(ArrayList<AdjacentNode> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    /**
     * This default constructor initializes the nodes
     *
     * @author Nifty_180041219
     *
     */
    public GraphNode(int n){
        this(n, null);
    }

    /**
     * This constructor initializes the nodes
     *
     * @author Nifty_180041219
     *
     */
    public GraphNode(int n, double nodeX, double nodeY, double nodeR, Pane displayNode){
        this(n, createNode(nodeX, nodeY, nodeR));
        drawNode(displayNode);
    }

    /**
     * This constructor sets the node id, adjacent node lists and the circle node
     *
     * @author Nifty_180041219
     *
     */
    public GraphNode(int nid, Circle node){
        nodeID = nid;
        this.node = node;
        adjacentNodes = new ArrayList<>();
    }

    /**
     * This method displays the node in the display pane
     *
     * @author Nifty_180041219
     *
     */
    public GraphNode(int nid, Circle node, Pane displayPane){
        this(nid, node);
        drawNode(displayPane);
    }

    /**
     * This method draws the node in display pane
     *
     * @author Sartaj_180041204
     *
     */
    public void drawNode(Pane displayPane) {
        Text textNodeID = new Text(String.valueOf(nodeID));
        textNodeID.setFill(Color.BLACK);
        textNodeID.setFont(Font.font(node.getRadius()));
        StackPane nodePane = new StackPane(node, textNodeID);
        nodePane.setLayoutX(node.getCenterX() - node.getRadius());
        nodePane.setLayoutY(node.getCenterY() - node.getRadius());
        displayPane.getChildren().add(nodePane);
    }

    /**
     * This method creates circle for nodes
     *
     * @author Nifty_180041219
     *
     * @return Circle
     */
    public static Circle createNode(double x, double y, double r){
        Circle gnode = new Circle(x,y, r);
        gnode.setFill(Color.WHITE);
        return gnode;
    }

    /**
     * This method sets the node
     *
     * @author Nifty_180041219
     *
     */
    void setNode(Pane displayPane, Circle gnode){
        node = gnode;
        drawNode(displayPane);
    }

    /**
     * This method returns the circle
     *
     * @author Nifty_180041219
     *
     */
    public Circle getNode() {
        return node;
    }

    /**
     * This method sets the circle into the node
     *
     * @author Sartaj_180041204
     *
     * @param node a circle
     */
    public void setNode(Circle node) {
        this.node = node;
    }

    int getNodeID(){
        return nodeID;
    }
    void setNodeID(int n){
        nodeID = n;
    }

    /**
     * This method returns the nodeID as string
     *
     * @author Sartaj_180041204
     *
     * @return String returns nodeID
     */
    @Override
    public String toString() {
        return String.valueOf(nodeID);
    }


    /**
     * This method sets adjacent nodes
     *
     * @author Sartaj_180041204
     *
     * @param adjGraphNode Adjacent graph node
     * @param edge An edge
     */
    public void addAdjacentNode(GraphNode adjGraphNode, Line edge){
        adjacentNodes.add(new AdjacentNode(adjGraphNode, edge));
    }

    /**
     * This method sets adjacent nodes
     *
     * @author Sartaj_180041204
     *
     * @param adjGraphNode Adjacent graph node
     * @param edge An edge
     * @param displayPane Pane that displays the edge
     */
    public void addAdjacentNode(GraphNode adjGraphNode, Line edge, Pane displayPane) {
        adjacentNodes.add(new AdjacentNode(adjGraphNode, edge, displayPane));
    }

    /**
     * This method sets adjacent nodes
     *
     * @author Sartaj_180041204
     *
     * @param adjGraphNode Adjacent graph node
     * @param displayPane Pane that displays the edge
     */
    public void addAdjacentNode(GraphNode adjGraphNode, Pane displayPane) {
        adjacentNodes.add(new AdjacentNode(adjGraphNode, Graph.createEdge(this, adjGraphNode), displayPane));
    }

}
