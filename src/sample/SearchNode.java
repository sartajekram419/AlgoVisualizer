package sample;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * This class controls the customized input for searching
 *
 * @author Sajid_180041203
 * @author Nifty_180041219
 */
public class SearchNode {

    private int nodeID;
    private Circle node;
    private int nodeValue;

    /**
     * This constructor initializes the nodes
     *
     * @author Nifty_180041219
     *
     */
    public SearchNode(Pane displayNode, int n, int i){
            this(createNode(i*50+i*15, 25, 25), i, n);
            drawNode(displayNode, n);
    }

    /**
     * This constructor initializes the nodes
     *
     * @author Nifty_180041219
     *
     */
    public SearchNode(Circle node, int nid, int nval){
        nodeID = nid;
        this.node = node;
        nodeValue = nval;
    }

    /**
     * This method draws a node in the diplayPane
     *
     * @author Nifty_180041219
     *
     */
    public void drawNode(Pane displayPane, int n) {
        Text textNodeID = new Text(String.valueOf(n));
        textNodeID.setFill(Color.BLACK);
        textNodeID.setFont(Font.font(node.getRadius()));
        StackPane nodePane = new StackPane(node, textNodeID);
        nodePane.setLayoutX(node.getCenterX() - node.getRadius());
        nodePane.setLayoutY(node.getCenterY() - node.getRadius());
        displayPane.getChildren().add(nodePane);
    }

    /**
     * This method creates a node
     *
     * @author Sajid_180041203
     *
     */
    public static Circle createNode(double x, double y, double r){
        Circle gnode = new Circle(x,y, r);
        gnode.setFill(Color.LIGHTBLUE);
        return gnode;
    }

    /**
     * This method returns the circle
     *
     * @author Sajid_180041203
     *
     */
    public Circle getNode() {
        return node;
    }

    /**
     * This method sets the circle in the node
     *
     * @author Sajid_180041203
     *
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
     * This method returns the nodeID
     *
     * @author Sajid_180041203
     *
     * @return The nodeID
     */
    @Override
    public String toString() {
        return String.valueOf(nodeID);
    }

}
