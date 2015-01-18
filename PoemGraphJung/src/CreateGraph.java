import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;

import org.apache.commons.collections15.Transformer;




import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.RadialTreeLayout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;


public class CreateGraph { 
static PoemGraph g = new PoemGraph();
public static int arraylength = -1;
final int LIMIT = 100;
public static Node[] n; 


public CreateGraph(File file) {
	JFrame frame = new JFrame("Poem Graph");
	
	for (final File fileEntry : file.listFiles()) {
		arraylength = arraylength + 1;
	}
	n = new Node[LIMIT];
	createGraph(file); // turn into object
	
    //Adapted from tutorial
  
    // The Layout<V, E> is parameterized by the vertex and edge types
    Layout<Node, WeightedEdge> layout = new SpringLayout(g.graph);
    layout.setSize(new Dimension(300,300));
    VisualizationViewer<Node,WeightedEdge> vv = 
            new VisualizationViewer< Node,WeightedEdge>(layout);
    vv.setPreferredSize(new Dimension(350,350));
    // Show vertex and edge labels
    vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
    vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
    // Create a graph mouse and add it to the visualization viewer
    EditingModalGraphMouse gm = new EditingModalGraphMouse(vv.getRenderContext(), 
             Node.MyVertexFactory.getInstance(),
             WeightedEdge.MyEdgeFactory.getInstance()); 
    // Set some defaults for the Edges...
    WeightedEdge.MyEdgeFactory.setDefaultCapacity(192.0);
    WeightedEdge.MyEdgeFactory.setDefaultWeight(5.0);
    // Trying out our new popup menu mouse plugin...
    PopupVertexEdgeMenuMousePlugin myPlugin = new PopupVertexEdgeMenuMousePlugin();
    // Add some popup menus for the edges and vertices to our mouse plugin.
    JPopupMenu edgeMenu = new MyMouseMenus.EdgeMenu(frame);
    JPopupMenu vertexMenu = new MyMouseMenus.VertexMenu();
    myPlugin.setEdgePopup(edgeMenu);
    myPlugin.setVertexPopup(vertexMenu);
    gm.remove(gm.getPopupEditingPlugin());  // Removes the existing popup editing plugin
    
    gm.add(myPlugin);   // Add our new plugin to the mouse
    
    vv.setGraphMouse(gm);

    
    //JFrame frame = new JFrame("Editing and Mouse Menu Demo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(vv);
    
    // Let's add a menu for changing mouse modes
    JMenuBar menuBar = new JMenuBar();
    JMenu modeMenu = gm.getModeMenu();
    modeMenu.setText("Modes");
    modeMenu.setIcon(null); // I'm using this in a main menu
    modeMenu.setPreferredSize(new Dimension(80,20)); // Change the size so I can see the text
    
    menuBar.add(modeMenu);
    frame.setJMenuBar(menuBar);
    gm.setMode(ModalGraphMouse.Mode.TRANSFORMING); // Start off in Transforming mode
    frame.pack();
    frame.setVisible(true);
    }

	public static void createGraph(File file){
		int x;
		final File folder = new File (file.toString()) ;
		try {
			PoemRegister.poemRead(folder);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(x = 0; x < arraylength; x++){
	    n[x] = new Node(PoemRegister.poems[x+1]);
	    n[x].setIdentifier(x);
		System.out.println(n[x].getElement().getFileName());
	    }
		
			
  }
	
	public static Node updateGraph(File file, Node v){
		arraylength = arraylength + 1;
	    n[arraylength] = v;
	    return v;
	
	}
	public static void margentoEdges(){

		// Theory based connections
		g.graph.addEdge(new WeightedEdge(1.0 , 20), n[7], n[5]);
		g.graph.addEdge(new WeightedEdge(1.0 , 20), n[5], n[6]);
		g.graph.addEdge(new WeightedEdge(1.0 , 20), n[6], n[12]);
		
		// Margento based connections
		g.graph.addEdge(new WeightedEdge(2.0 , 20), n[5], n[0]);
		g.graph.addEdge(new WeightedEdge(2.0 , 20), n[0], n[10]);
		g.graph.addEdge(new WeightedEdge(2.0 , 20), n[10], n[15]);
		g.graph.addEdge(new WeightedEdge(2.0 , 20), n[15], n[16]);
		g.graph.addEdge(new WeightedEdge(2.0 , 20), n[16], n[17]);
		g.graph.addEdge(new WeightedEdge(2.0 , 20), n[17], n[20]);
		g.graph.addEdge(new WeightedEdge(2.0 , 20), n[20], n[2]);
		g.graph.addEdge(new WeightedEdge(2.0 , 20), n[2], n[1]);
		g.graph.addEdge(new WeightedEdge(2.0 , 20), n[1], n[19]);
		g.graph.addEdge(new WeightedEdge(2.0 , 20), n[19], n[11]);
		g.graph.addEdge(new WeightedEdge(2.0 , 20), n[11], n[4]);
		g.graph.addEdge(new WeightedEdge(2.0 , 20), n[4], n[9]);
		g.graph.addEdge(new WeightedEdge(2.0 , 20), n[9], n[3]);
		g.graph.addEdge(new WeightedEdge(2.0 , 20), n[3], n[14]);
		g.graph.addEdge(new WeightedEdge(2.0 , 20), n[14], n[18]);
		g.graph.addEdge(new WeightedEdge(2.0 , 20), n[18], n[8]);
			
	
	}
}
