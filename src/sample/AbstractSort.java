package sample;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an abstract class for all the sorting class
 *
 * @author Sartaj_180041204
 *
 */
public abstract class AbstractSort {
    public final Color START_COLOR = Color.WHITE;
    public final Color SELECT_COLOR = Color.web("0xf4511e",1.0);
    public final Color FINAL_COLOR = Color.LIGHTGREEN;
    public final Color SORTED_COLOR = Color.VIOLET;

    static public int X;
    ParallelTransition p;
    FillTransition f;


    static {
        X = SortingPageController.WINDOW_WIDTH / SortingPageController.NO_OF_NODES;
    }


    /**
     * Changes the color of the nodes and prints those with animation
     *
     * @author Sartaj_180041204
     *
     */
    void fillTransion(Node c, Color color) {
        f = new FillTransition();
        f.setShape((Shape) c);
        f.setToValue(color);
        f.setDuration(Duration.millis(SortingPageController.SPEED));
        p.getChildren().add(f);
    }

    /**
     * Colors all the nodes
     *
     * @author Sartaj_180041204
     *
     */
    ParallelTransition colorNode(Node[] arr, Color color, int...a) {
        p = new ParallelTransition();
        for (int i = 0; i < a.length; i++) {
            fillTransion(arr[a[i]], color);
        }
        return p;
    }

    /**
     * Colors all the nodes
     *
     * @author Sartaj_180041204
     *
     */
    ParallelTransition colorNode(List<Node> list, Color color) {
        p = new ParallelTransition();
        for (Node c : list) {
            fillTransion(c, color);
        }
        return p;
    }

    /**
     * Swaps the nodes and displace them
     *
     * @author Sartaj_180041204
     *
     */
    ParallelTransition swap(Node a[], int i, int j) {
        p = new ParallelTransition();
        int dx = j-i;
        p.getChildren().addAll(a[i].moveX(X * dx), a[j].moveX(-X * dx));
        Node tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        return p;
    }

    public abstract ArrayList<Transition> startSort(Node[] a);

    public abstract void setX();
}