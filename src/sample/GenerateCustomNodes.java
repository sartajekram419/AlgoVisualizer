package sample;


import javafx.scene.paint.Color;



public class GenerateCustomNodes {
    public GenerateCustomNodes(){};

    public static Node[] GenerateCustomNodes(int len, int[] arr) {

        Node nodes[] = new Node[len];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(arr[i]);
            nodes[i].setX(i * (SortingPageController.WINDOW_WIDTH / nodes.length));
            nodes[i].setFill(Color.LIGHTGRAY);
            setNodeDim(nodes[i], nodes.length);
            //System.out.println(nodes[i]);
        }
        return nodes;
    }

    public static void setNodeDim(Node nodes, int len) {
        nodes.setWidth(SortingPageController.WINDOW_WIDTH / len - SortingPageController.X_GAP);
        nodes.setHeight(((SortingPageController.WINDOW_HEIGHT - SortingPageController.BUTTON_BOUNDARY) / len) * nodes.getValue());
    }
}