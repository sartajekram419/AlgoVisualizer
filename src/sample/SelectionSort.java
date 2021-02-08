package sample;

import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * This class does selection sort
 *
 * @author Sartaj_180041204
 *
 */
public class SelectionSort extends AbstractSort {

    private ArrayList<Transition> transitions;

    private static final Color MIN_COLOR = Color.web("0xf4511e",1.0);;
    private static final Color NEWMIN_COLOR = Color.web("0xf4511e",1.0);

    private ParallelTransition ColorNode(Node nodes[], int x, int y, Color A, Color B) {
        ParallelTransition p = new ParallelTransition();
        p.getChildren().addAll(colorNode(nodes, A, x), colorNode(nodes, B, y));
        return p;
    }

    /**
     * This method sets the width of a node
     *
     * @author Sartaj_180041204
     *
     */
    public void setX(){
        X = SortingPageController.WINDOW_WIDTH / SortingPageController.NO_OF_NODES;
    }

    /**
     * This method starts the selection sort
     *
     * @author Sartaj_180041204
     *
     * @param nodes Array of nodes
     *
     */
    @Override
    public ArrayList <Transition> startSort(Node[] nodes) {
        transitions = new ArrayList<>();
        int minIdx;
        for (int i = 0; i < nodes.length - 1; i++) {
            minIdx = i;
            transitions.add(colorNode(nodes, SELECT_COLOR, minIdx));
            for (int j = i+1; j < nodes.length; j++) {
                transitions.add(colorNode(nodes, START_COLOR, j));
                if (nodes[j].getValue() < nodes[minIdx].getValue()) {
                    if (minIdx == i) transitions.add(ColorNode(nodes, minIdx, j, SELECT_COLOR, START_COLOR));
                    else transitions.add(ColorNode(nodes, minIdx, j, START_COLOR, SELECT_COLOR));
                    minIdx = j;
                }
                else transitions.add(colorNode(nodes, START_COLOR, j));
            }
            if (minIdx != i) transitions.add(swap(nodes, i, minIdx));
            transitions.add(colorNode(nodes, SORTED_COLOR, i, minIdx));
        }
        transitions.add(colorNode(Arrays.asList(nodes), FINAL_COLOR));
        return transitions;
    }
}