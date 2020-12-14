package sample;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.paint.Color;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import sample.AbstractSort;

/**
 * @author shiningflash
 */

public class InsertionSort extends AbstractSort {

    private ArrayList<Transition> transitions;

    public InsertionSort() {
        this.transitions = new ArrayList<>();
    }

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
            transitions.add(colorNode(nodes, Color.ALICEBLUE, j+1));
        }
        transitions.add(colorNode(Arrays.asList(nodes), FINAL_COLOR));
        return transitions;
    }
}