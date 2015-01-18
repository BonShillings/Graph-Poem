import java.io.File;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;


public class PoemGraph{
	Graph<Node, WeightedEdge> graph;
    int nodeCount, edgeCount;
    Factory <Node> vertexFactory;
    Factory<WeightedEdge> edgeFactory;
   
    /** Creates a new instance of SimpleGraphView */
    public PoemGraph() {
        // Graph<V, E> where V is the type of the vertices and E is the type of the edges
        graph = new SparseMultigraph<Node, WeightedEdge>();
        nodeCount = 0; edgeCount = 0;
        vertexFactory = new Factory<Node>() { // My vertex factory
            public Node create() {
                nodeCount = nodeCount + 1;
                Node n = new Node((Poem)null);
                return n;
            }
        };
        edgeFactory = new Factory<WeightedEdge>() { // My edge factory
            public WeightedEdge create() {
            	edgeCount = edgeCount + 1;
            	WeightedEdge w = new WeightedEdge(0,1);
                return w;
            }
        };
    }

}
