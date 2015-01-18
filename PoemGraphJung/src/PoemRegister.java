import java.io.*;


public class PoemRegister {
	public static Poem[] poems;
	
	public static void main(String[] args) throws FileNotFoundException{
		final File folder = new File ("Formatted Poems") ; //defines directory
        //File here = new File("/University of Ottawa/Third Year/UROP PROJECT/Graph_Poetry/Formatted Poems/Nguyen Quoc Chanh - A Legend.txt");
		//Poem test;
	    // test = new Poem("Nguyen Quoc Chanh", "A Legend", "test", here);
		//System.out.println(test.getTitle());
		//test = new Poem(here);
		//test.printTextLines();
		poemRead(folder);
		//poemsListTheory();
		//poemsListMargento();
	}
	
		
		 public static void poemRead(final File folder) throws FileNotFoundException {
			 File file;
			 poems = new Poem[25];
			 int x = 0;
			 for (final File fileEntry : folder.listFiles()) {
			        if (fileEntry.isDirectory()) {
			          poemRead(fileEntry);
			        } else {
			           file = fileEntry;
			           poems[x] = new Poem(file);
			           //poems[x].printTextLines();
			           x = x + 1;
			        }
			        
			 }
 }
}
