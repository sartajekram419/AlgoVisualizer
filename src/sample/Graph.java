package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;

import java.lang.Math;

public class Graph {
    private Pane displayPane;
    private ArrayList<GraphNode> graphNodeList;
    private ArrayList<ArrayList<Integer>> edgeList;

    //parameterized constructor to initialize graph
    public Graph(Pane displayPane, int n){
        graphNodeList = new ArrayList<>();
        this.displayPane = displayPane;
        for(int i=0;i<n;i++){
            graphNodeList.add(new GraphNode(i));
        }
        edgeList = new ArrayList<>();
    }

    //default constructor
    public Graph(Pane displayPane){
        this(displayPane, 0);
    }

    public int size(){
        return graphNodeList.size();
    }

    public GraphNode getNode(int i){
        return graphNodeList.get(i);
    }

    public Pane getDisplayPane() {
        return displayPane;
    }

    public void setDisplayPane(Pane displayPane) {
        this.displayPane = displayPane;
    }

    public ArrayList<GraphNode> getGraphNodeList() {
        return graphNodeList;
    }

    public void setGraphNodeList(ArrayList<GraphNode> graphNodeList) {
        this.graphNodeList = graphNodeList;
    }

    void addUndirectedEdge(int u, int v){
        Line edge = createEdge(getNode(u), getNode(v));
        getNode(u).addAdjacentNode(getNode(v), displayPane);
        getNode(v).addAdjacentNode(getNode(u), edge);
        addEdge(u,v);
    }

    void addEdge(int u, int v){
        ArrayList<Integer> e = new ArrayList<>();
        e.add(u);
        e.add(v);
        edgeList.add(e);
    }

    void addDirectedEdge(int u, int v){
        getNode(u).addAdjacentNode(getNode(v), displayPane);
    }

    void drawEdge(int u, int v){
        GraphNode fromGraphNode = getNode(u);
        GraphNode toGraphNode = getNode(v);
        fromGraphNode.addAdjacentNode(toGraphNode, displayPane);
    }


    static Line createEdge(GraphNode fromGraphNode, GraphNode toGraphNode){
        double x1 = fromGraphNode.getNode().getCenterX();
        double y1 = fromGraphNode.getNode().getCenterY();
        double x2 = toGraphNode.getNode().getCenterX();
        double y2 = toGraphNode.getNode().getCenterY();

        int s = 1;
        if(x1 > x2)
            s = -1;

        double theta = Math.atan((y2 - y1)/(x2 - x1));

        double r = fromGraphNode.getNode().getRadius();
        double xl1 = x1 + (s * r * Math.cos(theta));
        double yl1 = y1 + (s * r * Math.sin(theta));
        r = toGraphNode.getNode().getRadius();
        double xl2 = x2 - (s * r * Math.cos(theta));
        double yl2 = y2 - (s * r * Math.sin(theta));

        return createEdge(xl1, yl1, xl2, yl2);
    }

    static Line createEdge(double fromX, double fromY, double toX, double toY){
        Line edge = new Line(fromX, fromY, toX, toY);
        //edge.setOpacity(0.5);
        return edge;
    }

    void addNode(double x, double y, double r){
        graphNodeList.add(new GraphNode(size(), x,y,r, displayPane));
    }

    void resetNodesColor(){
        for(GraphNode graphNode : graphNodeList){
            graphNode.getNode().setFill(Color.RED);
        }
    }

    ArrayList<AdjacentNode> getAdjacentNodes(int i){
        return graphNodeList.get(i).getAdjacentNodes();
    }

    @Override
    public String toString(){
        String str = "";
        for(int i = 0; i< graphNodeList.size(); i++){
            str += i + ": [";
            for(AdjacentNode node: graphNodeList.get(i).getAdjacentNodes()){
                str += node + "  ";
            }
            str += "]\n";
        }
        return str;
    }

    ArrayList<Integer> runDFS(int s){
        boolean []visited = new boolean[size()];
        ArrayList<Integer> dfsTraversal = new ArrayList<>();
        dfs(dfsTraversal, s, visited);
        return dfsTraversal;
    }

    void dfs(ArrayList<Integer> dfsTraversal, int i, boolean[] visited){
        dfsTraversal.add(i);
        visited[i] = true;
        for(AdjacentNode adjnode: getAdjacentNodes(i)){
            if(!visited[adjnode.getAdjGraphNode().getNodeID()]){
                dfs(dfsTraversal, adjnode.getAdjGraphNode().getNodeID(), visited);
            }
        }
    }

    ArrayList<ArrayList<Integer>> getEdgeList(){
        return edgeList;
    }
}
