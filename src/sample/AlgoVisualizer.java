package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * This method controls the animation for BFS and DFS
 *
 * @author Sartaj_180041204
 *
 */
public class AlgoVisualizer {
    private Graph graph;
    boolean visited[];
    Timeline visualizer;
    Stack<Integer> stack;
    Queue<Integer> queue;

    /**
     * This constructor sets the graph
     *
     * @author Sartaj_180041204
     *
     * @param g source node
     */
    public AlgoVisualizer(Graph g){
        graph = g;
    }

    /**
     * This method runs BFS and controls animation
     *
     * @author Sartaj_180041204
     *
     * @param startNode source node
     */
    public void runBFS(int startNode){
        queue = new LinkedList<>();
        visited = new boolean[graph.size()];

        queue.add(startNode);
        queue.add(startNode);
        visited[startNode] = true;
        graph.getNode(startNode).getNode().setFill(Color.ORANGE);

        KeyFrame bfsKeyFrame = new KeyFrame(Duration.seconds(1.5), e->{
            if(queue.size()>1){
                graph.getNode(queue.poll()).getNode().setStrokeWidth(0);
                int currentNode = queue.peek();
                graph.getNode(currentNode).getNode().setStroke(Color.DEEPPINK);
                graph.getNode(currentNode).getNode().setStrokeWidth(5);

                for(AdjacentNode anode: graph.getAdjacentNodes(currentNode)){
                    if(!visited[anode.getAdjGraphNode().getNodeID()]){
                        visited[anode.getAdjGraphNode().getNodeID()] = true;
                        queue.add(anode.getAdjGraphNode().getNodeID());
                        anode.getAdjGraphNode().getNode().setFill(Color.ORANGE);
                    }
                }
            }
            else{
                graph.getNode(queue.poll()).getNode().setStrokeWidth(0);
                graph.resetNodesColor();
                visualizer.stop();
            }
        });
        visualizer = new Timeline(bfsKeyFrame);
        visualizer.setCycleCount(Animation.INDEFINITE);
        visualizer.play();
    }

    /**
     * This method runs DFS and controls animation
     *
     * @author Sartaj_180041204
     *
     * @param startNode source node
     */
    public void runDFS(int startNode){
        stack = new Stack<>();
        visited = new boolean[graph.size()];

        stack.push(startNode);

        KeyFrame dfsKeyFrame = new KeyFrame(Duration.seconds(1), e->{
            if(!stack.isEmpty()){
                int currentNode = stack.peek();
                visited[currentNode] = true;

                graph.getNode(currentNode).getNode().setStrokeWidth(5);
                graph.getNode(currentNode).getNode().setStroke(Color.DEEPPINK);

                if(stack.size() > 1){
                    graph.getNode(stack.get(stack.size()-2)).getNode().setStrokeWidth(0);
                }

                int i;
                for(i=0;i < graph.getAdjacentNodes(currentNode).size();i++){
                    GraphNode graphNode = graph.getAdjacentNodes(currentNode).get(i).getAdjGraphNode();
                    if(!visited[graphNode.getNodeID()]){
                        stack.push(graphNode.getNodeID());
                        graph.getNode(currentNode).getNode().setFill(Color.DARKORANGE);
                        break;
                    }
                }
                if(i == graph.getAdjacentNodes(currentNode).size()){
                    int node = stack.pop();
                    graph.getNode(node).getNode().setFill(Color.GREY);
                    graph.getNode(node).getNode().setStrokeWidth(0);
                }
            }
            else{
                visualizer.stop();
                graph.resetNodesColor();
            }
        });
        visualizer = new Timeline(dfsKeyFrame);
        visualizer.setCycleCount(Animation.INDEFINITE);
        visualizer.play();
    }
}
