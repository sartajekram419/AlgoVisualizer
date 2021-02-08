package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


/**
 * This class extends the Rectangle class
 *
 * @author Nifty_180041219
 * @author Sajid_180041203
 *
 */
public class Node extends Rectangle {
    private int val;

    /**
     * This constructor set the value of val
     *
     * @author Sajid_180041203
     *
     * @param n the value of a node
     */
    public Node(int n) {
        val = n;
    }



    /**
     * This returns the value of a node
     *
     * @author Sajid_180041203
     *
     * @return the value of a node
     */
    public int getValue() {
        return val;
    }

    /**
     * This methodes moves the position of the node
     *
     * @author Nifty_1800412129
     *
     * @param x the value of new X-coordinate of the node
     */
    public TranslateTransition moveX(int x) {
        TranslateTransition translation = new TranslateTransition();
        translation.setNode(this);
        translation.setDuration(Duration.millis(SortingPageController.SPEED));
        translation.setByX(x);
        return translation;
    }
}