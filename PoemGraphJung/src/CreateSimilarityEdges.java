import java.io.File;
import java.io.IOException;


public class CreateSimilarityEdges {
	public CreateSimilarityEdges(Node[] nodes, Node newNode, File file2){
	// method to be exported to creation phase
		double value;
		double maxvalue = 0;
		int y = 13;
	   for (int x = 0; x < CreateGraph.arraylength;x++) {
		    
		try {
		
			value = CosineDocumentSimilarity.getCosineSimilarity(nodes[x].getElement().getFileName(), file2.toString());
			
			if(!(nodes[x].getIdentifier() == newNode.getIdentifier())){
				if(maxvalue < value){
					maxvalue = value;
					y = x;
				}
			}
			if (value > 0.7){
				if(x==13){}
			
				else{
					if(!(nodes[x].getIdentifier() == newNode.getIdentifier())){
						if(maxvalue < value){
							maxvalue = value;
							y = x;
						}
				  CreateGraph.g.graph.addEdge(new WeightedEdge(3.0 , 20), nodes[x], newNode);
				  
					}
				}
				  }
			
			 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		 } 
	   
	   CreateGraph.g.graph.addEdge(new WeightedEdge(3.0 , 20), nodes[y], newNode);
	
}
}
