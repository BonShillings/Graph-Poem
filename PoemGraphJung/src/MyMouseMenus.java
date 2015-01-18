/* MyMouseMenus.java
 *
 * Created on March 21, 2007, 3:34 PM; Updated May 29, 2007
 *
 * Copyright March 21, 2007 Grotto Networking
 *
 */

 //package Samples.MouseMenu;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

/**
 * A collection of classes used to assemble popup mouse menus for the custom
 * edges and vertices developed in this example.
 * @author Dr. Greg M. Bernstein
 */
public class MyMouseMenus {
    
    public static class EdgeMenu extends JPopupMenu {        
        // private JFrame frame; 
        public EdgeMenu(final JFrame frame) {
            super("Edge Menu");
            // this.frame = frame;
            this.add(new DeleteEdgeMenuItem<WeightedEdge>());
            this.addSeparator();
            this.add(new WeightDisplay());
            this.add(new CapacityDisplay());
            this.addSeparator();
            //this.add(new EdgePropItem(frame));           
        }
        
    }
    
    public static class EdgePropItem extends JMenuItem implements EdgeMenuListener<WeightedEdge>,
            MenuPointListener {
        WeightedEdge edge;
        VisualizationViewer visComp;
        Point2D point;
        
        public void setEdgeAndView(WeightedEdge edge, VisualizationViewer visComp) {
            this.edge = edge;
            this.visComp = visComp;
        }

        public void setPoint(Point2D point) {
            this.point = point;
        }
        
        /*public  EdgePropItem(final JFrame frame) {            
            super("Edit Edge Properties...");
            this.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    EdgePropertyDialog dialog = new EdgePropertyDialog(frame, edge);
                    dialog.setLocation((int)point.getX()+ frame.getX(), (int)point.getY()+ frame.getY());
                    dialog.setVisible(true);
                }
                
            });
        }*/
        
    }
    public static class WeightDisplay extends JMenuItem implements EdgeMenuListener<WeightedEdge> {
        public void setEdgeAndView(WeightedEdge e, VisualizationViewer visComp) {
            this.setText("Weight " + e + " = " + e.getWeight());
        }
    }
    
    public static class CapacityDisplay extends JMenuItem implements EdgeMenuListener<WeightedEdge> {
        public void setEdgeAndView(WeightedEdge e, VisualizationViewer visComp) {
            this.setText("Capacity " + e + " = " + e.getCapacity());
        }
    }
    
    public static class VertexMenu extends JPopupMenu {
        public VertexMenu() {
            super("Vertex Menu");
            this.add(new DeleteVertexMenuItem<Node>());
            this.addSeparator();
            //this.add(new pscCheckBox());
            //this.add(new tdmCheckBox());
            this.add(new DisplayPoem());
            this.add(new GenerateEdges());
        }
    }
    public static class GenerateEdges extends JMenuItem implements VertexMenuListener<Node>{
    Node v;
    VisualizationViewer visView;
    
    public GenerateEdges(){
    super("Generate Edges");
    this.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			visView.getPickedVertexState().pick(v, false);
			SwingUtilities.invokeLater(new Runnable() {

	            @Override
	            public void run() {
	            new CreateSimilarityEdges(CreateGraph.n, v , v.getElement().getFile());
	            }
	        });
				}
	});


}

	@Override
	public void setVertexAndView(Node v, VisualizationViewer visView) {
		this.v = v;
		this.visView = visView;
		this.setText("Generate Edges");
	}
    }
    public static class DisplayPoem extends JMenuItem implements VertexMenuListener<Node>{
    	Node v;
    	VisualizationViewer visView;
    	public DisplayPoem(){
    		super("Display Poem");
    		this.addActionListener(new ActionListener(){
    			public void actionPerformed(ActionEvent e){
    				visView.getPickedVertexState().pick(v, false);
    				SwingUtilities.invokeLater(new Runnable() {

    		            @Override
    		            public void run() {
    		              new DisplayPoemMenuItem(v).setVisible(true);
    		            }
    		        });
    					}
    		});
    	
    
    	}
		@Override
		public void setVertexAndView(Node v, VisualizationViewer visView) {
			// TODO Auto-generated method stub
			this.v = v;
			this.visView = visView;
			this.setText("Display Poem");
			
		}
    
    }
   /* public static class pscCheckBox extends JCheckBoxMenuItem implements VertexMenuListener<Node> {
        Node v;
        
        public pscCheckBox() {
            super("PSC Capable");
            this.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    v.setPacketSwitchCapable(isSelected());
                }
                
            });
        }
        public void setVertexAndView(Node v, VisualizationViewer visComp) {
            this.v = v;
            this.setSelected(v.isPacketSwitchCapable());
        }
        
    }
    
        public static class tdmCheckBox extends JCheckBoxMenuItem implements VertexMenuListener<Node> {
        Node v;
        
        public tdmCheckBox() {
            super("TDM Capable");
            this.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    v.setTdmSwitchCapable(isSelected());
                }
                
            });
        }
        public void setVertexAndView(Node v, VisualizationViewer visComp) {
            this.v = v;
            this.setSelected(v.isTdmSwitchCapable());
        }
        
    }*/
    
}