package sample;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import sample.AbstractSort;


public class QuickSort extends AbstractSort {
    private static final Color PIVOT_COLOR = Color.CRIMSON;
    private ArrayList<Transition> transitions;

    public QuickSort() {
        this.transitions = new ArrayList<>();
    }

    public void setX() {
        X = SortingPageController.WINDOW_WIDTH / SortingPageController.NO_OF_NODES;
    }


    private int partition(Node[] nodes, int low, int high) {
        int i = low;
        transitions.add(colorNode(nodes, PIVOT_COLOR, high));
        for (int j = low; j < high; j++) {
            transitions.add(colorNode(nodes, SELECT_COLOR, j));
            if (nodes[j].getValue() < nodes[high].getValue()) {
                transitions.add(swap(nodes, i, j));
                transitions.add(colorNode(nodes, Color.LIGHTGREEN, i));
                i++;
            }
            else transitions.add(colorNode(nodes, Color.AQUAMARINE, j));
        }
        transitions.add(swap(nodes, i, high));
        transitions.add(colorNode(nodes, SORTED_COLOR, i));
        return i;
    }

    private void quickSort(Node[] nodes, int low, int high) {
        if (low < high) {
            int pivot = partition(nodes, low, high);
            quickSort(nodes, low, pivot-1);
            quickSort(nodes, pivot+1, high);
        }
    }

    @Override
    public ArrayList<Transition> startSort(Node[] nodes) {
        quickSort(nodes, 0, nodes.length-1);
        transitions.add(colorNode(Arrays.asList(nodes), FINAL_COLOR));
        return transitions;
    }
}