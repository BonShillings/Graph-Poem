import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.picking.PickedState;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * A class to implement the deletion of a vertex from within a 
 * PopupVertexEdgeMenuMousePlugin.
 * @author Dr. Greg M. Bernstein
 */
public class DisplayPoemMenuItem extends JFrame implements ActionListener{
    public Node vertex;
    private java.util.List<String> list;
    
    /** Creates a new instance of DeleteVertexMenuItem */
    public DisplayPoemMenuItem(Node node) {
    	int x;
        JPanel p = new JPanel();
        JButton show = new JButton("Display" + node.getName() +"?");
        show.addActionListener(this);
        p.add(show);
        list = new ArrayList<>();
        for(x = 0; x < node.getElement().numLines; x++){
        list.add(node.getElement().textLines[x]);
        }
        
        add(p);
        setLocationRelativeTo(null);
        pack();
    }
    /**
     * Implements the VertexMenuListener interface.
     * @param v 
     * @param visComp 
     */
        
	@Override
	public void actionPerformed(ActionEvent e){
		String s = "";
		for(String a: list){
			s += a + "\n";
		}
		JOptionPane.showMessageDialog(this, s);
			
		}
		
	}
    
    
