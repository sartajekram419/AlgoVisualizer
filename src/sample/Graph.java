package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;

import java.lang.Math;


/**
 * This class holds all the nodes
 *
 * @author Sajid_180041203
 * @author Nifty_180041219
 * @author Sartaj_180041204
 *
 */
public class Graph {
    private Pane displayPane;
    private ArrayList<GraphNode> graphNodeList;
    private ArrayList<ArrayList<Integer>> edgeList;

    /**
     * This constructor is called from the default constructor
     *
     * @author Sajid_180041203
     *
     * @param displayPane Pane that holds the whole graph
     * @param n denoting number of nodes
     */
    public Graph(Pane displayPane, int n){
        graphNodeList = new ArrayList<>();
        this.displayPane = displayPane;
        for(int i=0;i<n;i++){
            graphNodeList.add(new GraphNode(i));
        }
        edgeList = new ArrayList<>();
    }

    /**
     * This default constructor is called from the Menu Page Controller class
     *
     * @author Sajid_180041203
     *
     * @param displayPane Pane that holds the whole graph
     */
    public Graph(Pane displayPane){
        this(displayPane, 0);
    }

    /**
     * This method returns the number of nodes in graph
     *
     * @author Nifty_180041219
     *
     * @return number of nodes
     */
    public int size(){
        return graphNodeList.size();
    }

    /**
     * This method returns the i-th node in graph
     *
     * @author Nifty_180041219
     *
     * @return i-th node
     */
    public GraphNode getNode(int i){
        return graphNodeList.get(i);
    }

    /**
     * This method returns the whole pane that holds the graph
     *
     * @author Nifty_180041219
     *
     * @return pane that holds the graph
     */
    public Pane getDisplayPane() {
        return displayPane;
    }

    /**
     * This method sets the displayPane
     *
     * @author Nifty_180041219
     *
     * @param displayPane Pane that holds the whole graph
     */
    public void setDisplayPane(Pane displayPane) {
        this.displayPane = displayPane;
    }

    /**
     * This method returns all the node in the graph
     *
     * @author Nifty_180041219
     *
     */
    public ArrayList<GraphNode> getGraphNodeList() {
        return graphNodeList;
    }


    /**
     * This method sets all the graph nodes in a list
     *
     * @author Nifty_180041219
     *
     * @param graphNodeList all the nodes in the graph
     */
    public void setGraphNodeList(ArrayList<GraphNode> graphNodeList) {
        this.graphNodeList = graphNodeList;
    }


    /**
     * This method calls add_edge method
     *
     * @author Sartaj_180041204
     *
     * @param u denotes the first node
     * @param v denotes the second node
     */
    void addUndirectedEdge(int u, int v){
        Line edge = createEdge(getNode(u), getNode(v));
        getNode(u).addAdjacentNode(getNode(v), displayPane);
        getNode(v).addAdjacentNode(getNode(u), edge);
        addEdge(u,v);
    }


    /**
     * This method adds undirected edge between two nodes
     *
     * @author Sartaj_180041204
     *
     * @param u denotes the first node
     * @param v denotes the second node
     */
    void addEdge(int u, int v){
        ArrayList<Integer> e = new ArrayList<>();
        e.add(u);
        e.add(v);
        edgeList.add(e);
    }

    void addDirectedEdge(int u, int v){
        getNode(u).addAdjacentNode(getNode(v), displayPane);
    }

    /**
     * This method draws undirected edge between two nodes
     *
     * @author Sartaj_180041204
     *
     * @param u denotes the first node
     * @param v denotes the second node
     */
    void drawEdge(int u, int v){
        GraphNode fromGraphNode = getNode(u);
        GraphNode toGraphNode = getNode(v);
        fromGraphNode.addAdjacentNode(toGraphNode, displayPane);
    }

    /**
     * This method creates an undirected edge between two nodes
     *
     * @author Sartaj_180041204
     *
     * @param fromGraphNode denotes the first node
     * @param toGraphNode denotes the second node
     *
     * @return Line between two nodes
     */
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
        fromGraphNode.getNode().setFill(Color.WHITE);
        toGraphNode.getNode().setFill(Color.WHITE);

        return createEdge(xl1, yl1, xl2, yl2);
    }


    /**
     * This method creates an undirected edge between two points
     *
     * @author Sartaj_180041204
     *
     * @param fromX denotes the first node X-coordinate
     * @param fromY denotes the second node Y-coordinate
     * @param toX denotes the first node X-coordinate
     * @param toY denotes the second node Y-coordinate
     *
     * @return Line between two point
     */
    static Line createEdge(double fromX, double fromY, double toX, double toY){
        Line edge = new Line(fromX, fromY, toX, toY);
        edge.setStyle("-fx-stroke: #ffffff;");
        //edge.setOpacity(2);
        return edge;
    }

    /**
     * This method adds new node
     *
     * @author Sartaj_180041204
     *
     * @param x denotes the node X-coordinate
     * @param y denotes the node Y-coordinate
     * @param r denotes the node radius
     */
    void addNode(double x, double y, double r){
        graphNodeList.add(new GraphNode(size(), x,y,r, displayPane));
    }

    /**
     * This method resets node color
     *
     * @author Sajid_180041203
     */
    void resetNodesColor(){
        for(GraphNode graphNode : graphNodeList){
            graphNode.getNode().setFill(Color.WHITE);
        }
    }

    /**
     * This method return adjacent node list of a specific node
     *
     * @author Sajid_180041203
     *
     * @param i denotes node number
     * @return the adjacent node list
     */
    ArrayList<AdjacentNode> getAdjacentNodes(int i){
        return graphNodeList.get(i).getAdjacentNodes();
    }

   // @Override
  /*  public String toString(){
        String str = "";
        for(int i = 0; i< graphNodeList.size(); i++){
            str += i + ": [";
            for(AdjacentNode node: graphNodeList.get(i).getAdjacentNodes()){
                str += node + "  ";
            }
            str += "]\n";
        }
        return str;
    }*/


    /**
     * This method calls the dfs method
     *
     * @author Sajid_180041203
     *
     * @param s denotes the source
     * @return the dfs traversed list
     */
    ArrayList<Integer> runDFS(int s){
        boolean []visited = new boolean[size()];
        ArrayList<Integer> dfsTraversal = new ArrayList<>();
        dfs(dfsTraversal, s, visited);
        return dfsTraversal;
    }

    /**
     * This method does dfs
     *
     * @author Sajid_180041203
     *
     */
    void dfs(ArrayList<Integer> dfsTraversal, int i, boolean[] visited){
        dfsTraversal.add(i);
        visited[i] = true;
        for(AdjacentNode adjnode: getAdjacentNodes(i)){
            if(!visited[adjnode.getAdjGraphNode().getNodeID()]){
                dfs(dfsTraversal, adjnode.getAdjGraphNode().getNodeID(), visited);
            }
        }
    }

    /**
     * This method does dfs
     *
     * @author Nifty_180041219
     *
     */
    ArrayList<ArrayList<Integer>> getEdgeList(){
        return edgeList;
    }
}
