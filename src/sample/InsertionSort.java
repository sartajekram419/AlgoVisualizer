package sample;

import javafx.animation.ParallelTransition;
import javafx.animation.Transition;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class does insertion sort
 *
 * @author Sajid_180041203
 *
 */
public class InsertionSort extends AbstractSort {

    private ArrayList<Transition> transitions;

    /**
     * This constructor sets the transition arraylist
     *
     * @author Sajid_180041203
     *
     */
    public InsertionSort() {
        this.transitions = new ArrayList<>();
    }

    /**
     * This method sets the width of a node
     *
     * @author Sajid_180041203
     *
     */
    public void setX(){
        X = SortingPageController.WINDOW_WIDTH / SortingPageController.NO_OF_NODES;
    }

    /**
     * This method starts the insertion sort
     *
     * @author Sajid_180041203
     *
     * @param nodes Array of nodes
     */
    @Override
    public ArrayList <Transition> startSort(Node[] nodes) {
        for (int i = 1; i < nodes.length; i++) {
            int j = i - 1, idx = i;
            Node number = nodes[i];

            ParallelTransition p = new ParallelTransition();
            transitions.add(colorNode(nodes, SELECT_COLOR, i));

            while (j >= 0 && nodes[j].getValue() > number.getValue()) {
                p.getChildren().add(nodes[j].moveX(X));
                nodes[j+1] = nodes[j];
                transitions.add(colorNode(nodes, SORTED_COLOR, j));
                j--;
            }
            nodes[j+1] = number;

            p.getChildren().add(number.moveX(X * (j+1-i)));
            transitions.add(p);
            transitions.add(colorNode(nodes, SORTED_COLOR, j+1));
        }
        transitions.add(colorNode(Arrays.asList(nodes), FINAL_COLOR));
        return transitions;
    }
}