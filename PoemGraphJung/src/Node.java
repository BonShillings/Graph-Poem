import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;

import org.apache.commons.collections15.Factory;


public class Node {
  private Poem element;
  private Node next;
  Node() { this(null, null); }
  private int identifier;
  private String name;  
  public Node(Poem e, Node n, String stringName) {element = e;next = n;} 
  public Node(Poem e){ element = e;next = null;}
  public Node(Poem e, String n){element = e;name = n;}
  public void setIdentifier(int i){this.identifier = i; name ="p" +"o" +"e"+ "m" + Integer.toString(identifier);}
  public void setElement(Poem newElem) { element = newElem; }
  public void setNext(Node newNext) { next = newNext; }
  public Poem getElement() { return element; }
  public Node getNext() { return next; }
  public String getName() {return (name);}
  // public String toString(){return( "V"+ identifier);}

  //MyMouse methods
  private boolean packetSwitchCapable;
  private boolean tdmSwitchCapable;
  public Node(String name) {this.name = name;}
  public void setName(String name) {this.name = name;}
  public boolean isPacketSwitchCapable() {return packetSwitchCapable;}
  public void setPacketSwitchCapable(boolean packetSwitchCapable) {this.packetSwitchCapable = packetSwitchCapable;}
  public boolean isTdmSwitchCapable() {return tdmSwitchCapable;}
  public void setTdmSwitchCapable(boolean tdmSwitchCapable) {this.tdmSwitchCapable = tdmSwitchCapable;}
  public String toString() {return name;}
  public int getIdentifier() {return identifier;}
  
  public static class MyVertexFactory implements Factory<Node> {
      private static int nodeCount = 20;
      private static boolean defaultPSC = false;
      private static boolean defaultTDM = true;
      private static MyVertexFactory instance = new MyVertexFactory();
      
      private MyVertexFactory() {            
      }
      
      public static MyVertexFactory getInstance() {
          return instance;
      }
      
      public Node create()
      {
    	  Node v = null;
          
          JFileChooser fc = new JFileChooser();
          int returnValue = fc.showOpenDialog( null );
          if( returnValue == JFileChooser.APPROVE_OPTION )
          {
        	  File file = fc.getSelectedFile();
              String name = file.getName() + "Poem" + nodeCount++;
              
              try
              {
            	//CreateGraph.updateGraph(file, v);
				v = new Node(new Poem(file), name);
				v.setIdentifier(nodeCount);
				new CreateSimilarityEdges(CreateGraph.n, v, file);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              
          }
          return v;
      }        

      public static boolean isDefaultPSC() {
          return defaultPSC;
      }

      public static void setDefaultPSC(boolean aDefaultPSC) {
          defaultPSC = aDefaultPSC;
      }

      public static boolean isDefaultTDM() {
          return defaultTDM;
      }

      public static void setDefaultTDM(boolean aDefaultTDM) {
          defaultTDM = aDefaultTDM;
      }
  }
}
  