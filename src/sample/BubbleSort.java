package sample;

import javafx.animation.Transition;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * This class does bubble sort
 *
 * @author Sajid_180041203
 *
 */
public class BubbleSort extends AbstractSort {
    private ArrayList<Transition> transitions;
    private boolean f;

    /**
     * This constructor sets the transition arraylist
     *
     * @author Sajid_180041203
     *
     */
    public BubbleSort() {
        transitions = new ArrayList<>();
    }

    private ArrayList<Transition> compare(Node nodes[], int i, int j) {
        ArrayList<Transition> transitions = new ArrayList<>();
        transitions.add(colorNode(nodes, SELECT_COLOR, i, j));
        if (nodes[i].getValue() > nodes[j].getValue()) {
            transitions.add(swap(nodes, i, j));
            f = true;
        }
        transitions.add(colorNode(nodes, START_COLOR, i, j));
        return transitions;
    }

    private void bubbleSort(Node[] nodes) {
        int len = nodes.length-1;
        for (int i = 0; i < nodes.length; i++) {
            f = false;
            for (int j = 0; j < nodes.length-1-i; j++) {
                X = SortingPageController.WINDOW_WIDTH / SortingPageController.NO_OF_NODES;  transitions.addAll(compare(nodes, j, j+1));
            }
            transitions.add(colorNode(nodes, SORTED_COLOR, len--));
            if (!f) break;
        }
    }

    /**
     * This method starts the bubble sort
     *
     * @author Sajid_180041203
     *
     * @param nodes Array of nodes
     */
    @Override
    public ArrayList<Transition> startSort(Node[] nodes) {
        bubbleSort(nodes);
        transitions.add(colorNode(Arrays.asList(nodes), FINAL_COLOR));
        return transitions;
    }

    /**
     * This method sets the width of a node
     *
     * @author Sajid_180041203
     *
     */
    @Override
    public void setX() {
        X = SortingPageController.WINDOW_WIDTH / SortingPageController.NO_OF_NODES;
    }
}