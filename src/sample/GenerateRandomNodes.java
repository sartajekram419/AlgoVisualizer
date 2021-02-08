package sample;


import javafx.scene.paint.Color;

import java.util.Random;


/**
 * This class generates randomized nodes
 *
 * @author Nifty_180041219
 *
 */
public class GenerateRandomNodes {

    /**
     * Default constructor
     *
     * @author Nifty_180041219
     *
     */
    public GenerateRandomNodes(){};

    /**
     * This method generates random nodes
     *
     * @author Nifty_180041219
     *
     * @param len the length of the array
     */
    public static Node[] GenerateRandomNodes(int len) {
        Node nodes[] = new Node[len];
        Random r = new Random();
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(1 + r.nextInt(nodes.length));
            nodes[i].setX(i * (SortingPageController.WINDOW_WIDTH / nodes.length));
            nodes[i].setFill(Color.LIGHTGRAY);
            setNodeDim(nodes[i], nodes.length);
            //System.out.println(nodes[i]);
        }
        return nodes;
    }

    /**
     * This method sets the dimension of a node
     *
     * @author Nifty_180041219
     *
     * @param len Length of the array
     * @param nodes The node
     */
    public static void setNodeDim(Node nodes, int len) {
        nodes.setWidth(SortingPageController.WINDOW_WIDTH / len - SortingPageController.X_GAP);
        nodes.setHeight(((SortingPageController.WINDOW_HEIGHT - SortingPageController.BUTTON_BOUNDARY) / len) * nodes.getValue());
    }
}