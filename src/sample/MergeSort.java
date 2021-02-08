package sample;


import javafx.animation.ParallelTransition;
import javafx.animation.Transition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This class does merge sort
 *
 * @author Nifty_180041219
 *
 */
public class MergeSort extends AbstractSort {

    private ArrayList<Transition> transitions;

    /**
     * This constructor sets the transition arraylist
     *
     * @author Nifty_180041219
     *
     */
    public MergeSort() {
        this.transitions = new ArrayList<>();
    }

    /**
     * This method sets the width of a node
     *
     * @author Nifty_180041219
     *
     */
    public void setX() {
        X = SortingPageController.WINDOW_WIDTH / SortingPageController.NO_OF_NODES;
    }

    private Node[] tmp;

    private ArrayList<Transition> merge(Node[] arr, int p, int q, int r) {
        ArrayList<Transition> transitions = new ArrayList<>();

        List<Node> tmpList = new ArrayList<>();

        for (int i = p; i <= r; i++) {
            tmp[i] = arr[i];
            tmpList.add(tmp[i]);
        }

        int i = p;
        int j = q + 1;
        int k = p;

        while (i <= q && j <= r) {
            if (tmp[i].getValue() <= tmp[j].getValue()) {
                arr[k++] = tmp[i++];
            } else {
                arr[k++] = tmp[j++];
            }
        }

        while (i <= q) {
            arr[k++] = tmp[i++];
        }

        while (j <= r) {
            arr[k++] = tmp[j++];
        }

        transitions.add(colorNode(tmpList, SELECT_COLOR));

        ParallelTransition pt = new ParallelTransition();

        for (int x = p; x <= r; x++) {
            for (int y = p; y <= r; y++) {
                if (tmp[x].equals(arr[y])) {
                    pt.getChildren().add(tmp[x].moveX(X * (y - x)));
                }
            }
        }

        transitions.add(pt);
        transitions.add(colorNode(tmpList, SORTED_COLOR));

        return transitions;
    }

    private ArrayList<Transition> mergeSort(Node[] arr, int p, int r) {
        ArrayList<Transition> transitions = new ArrayList<>();

        if (p < r) {
            int q = (p + r) / 2;
            transitions.addAll(mergeSort(arr, p, q));
            transitions.addAll(mergeSort(arr, q + 1, r));
            transitions.addAll(merge(arr, p, q, r));
        }

        return transitions;
    }

    /**
     * This method starts the merge sort
     *
     * @author Nifty_180041219
     *
     * @param arr Array of nodes
     *
     */
    @Override
    public ArrayList<Transition> startSort(Node[] arr) {
        // ArrayList<Transition> transitions = new ArrayList<>();

        this.tmp = new Node[arr.length];

        transitions.addAll(mergeSort(arr, 0, arr.length - 1));

        transitions.add(colorNode(Arrays.asList(arr), FINAL_COLOR));

        return transitions;
    }

}