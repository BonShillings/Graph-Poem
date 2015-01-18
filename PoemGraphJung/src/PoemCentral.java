import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;


public class PoemCentral {
	static File folder;
	private static PoemGraphIndex chrisGraph;
	private static CreateGraph graphPoem;
	
@SuppressWarnings("static-access")
public static void main(String[] args){
	folder = new File("Formatted Poems");
	
	//Potential "from file" code
	
	//JFileChooser fc = null;
    //int returnValue; 
    //String name;
    //System.out.println( "enter file");
    //fc = new JFileChooser();
    //returnValue = fc.showOpenDialog( null );
    //if (returnValue == JFileChooser.APPROVE_OPTION) {
	//file = fc.getSelectedFile();
	//name = file.toString();
	//File folder = new File(name);}
	
 chrisGraph = new PoemGraphIndex(folder);
 graphPoem = new CreateGraph(folder);
 System.out.println("Do you want to load Margento Edges? (y = yes)");
 BufferedReader br = new BufferedReader(
         new InputStreamReader(System.in));
 String s;
try {
	s = br.readLine();
	if(s.equalsIgnoreCase("y")){
		 CreateGraph.margentoEdges();
	 }
	s = "";
 System.out.println("Do you want to load Lucene Edges( y = yes)?");
 br = new BufferedReader(
         new InputStreamReader(System.in));
} catch (IOException e) {
	e.printStackTrace();
}
try {
	s = br.readLine();
	if(s.equalsIgnoreCase("y")){
		 createLuceneEdges();
	 }
} catch (IOException e) {
	e.printStackTrace();
}

}

public static void createLuceneEdges(){
	int x;
	 for (x = 0; x < 21; x++){
	 	new CreateSimilarityEdges(graphPoem.n, graphPoem.n[x], graphPoem.n[x].getElement().getFile());
	 }
}
}

