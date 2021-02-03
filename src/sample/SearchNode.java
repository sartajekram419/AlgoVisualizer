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

    public SearchNode(Pane displayNode, int n, int i){
            this(createNode(i*50+i*15, 25, 25), i, n);
            drawNode(displayNode, n);
    }

    public SearchNode(Circle node, int nid, int nval){
        nodeID = nid;
        this.node = node;
        nodeValue = nval;
    }

    /*public GraphNode(int nid, Circle node, Pane displayPane){
        this(nid, node);
        drawNode(displayPane);
    }*/

    public void drawNode(Pane displayPane, int n) {
        Text textNodeID = new Text(String.valueOf(n));
        textNodeID.setFill(Color.BLACK);
        textNodeID.setFont(Font.font(node.getRadius()));
        StackPane nodePane = new StackPane(node, textNodeID);
        nodePane.setLayoutX(node.getCenterX() - node.getRadius());
        nodePane.setLayoutY(node.getCenterY() - node.getRadius());
        displayPane.getChildren().add(nodePane);
    }

    public static Circle createNode(double x, double y, double r){
        Circle gnode = new Circle(x,y, r);
        gnode.setFill(Color.LIGHTBLUE);
        return gnode;
    }

    /*void setNode(Pane displayPane, Circle gnode){
        node = gnode;
        drawNode(displayPane);
    }*/

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

}
