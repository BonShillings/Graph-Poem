import java.io.*;

public class Poem {
private String author;
private String title;
public String[] textLines;
private String text;
// private String footnote;
private File filename;
//private Scanner textReader;
private BufferedReader reader; 
public int numLines;

public Poem(String authorName, String titleName, String textRead, File filenamePassed){
  this.filename = filenamePassed;
  this.author = authorName;
  this.title = titleName;
  this.text = textRead;
  // this.footnote = footnoteRead;
}

public Poem(File filenamePassed) throws FileNotFoundException{
 this.filename = filenamePassed;  
 try {
	 this.numLines = getNumLines(filenamePassed);
	 this.textLines = new String[numLines];
	 this.text = "";
     reader = new BufferedReader(new FileReader(filename.toString()));
     String line = null;
  

     for(this.numLines = 0;this.numLines < this.textLines.length; numLines++)
     {
         line = reader.readLine();
         if(line == null)
             break;
         textLines[numLines] = line;
         this.text = text.concat(line);
         
     }
 }catch(Exception e) {
     e.printStackTrace();
 }finally {
     if(reader != null)
     {
         try {
             reader.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 }

}

public int getNumLines(File filenamePassed) throws FileNotFoundException{
	 this.filename = filenamePassed;  
	 try {
	     reader = new BufferedReader(new FileReader(filename.toString()));
	     String line = null;
	     while(true)
	     {
	         line = reader.readLine();
	         if(line == null)
	             break;
	         this.numLines = this.numLines + 1;    
	     }
	 }catch(Exception e) {
	     e.printStackTrace();
	 }finally {
	     if(reader != null)
	     {
	         try {
	             reader.close();
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
	     }
	 }
return (numLines);
	}


public String getAuthor(){
	return this.author;
}



public String getTitle(){
	return this.title;
}


public String getText(){
	return this.text;
}


public void printTextLines(){
	for(this.numLines = 0; numLines < textLines.length; numLines++){
		System.out.println(textLines[numLines]);
	}
}
public String getFileName(){
return (this.filename.toString());

}
public File getFile(){
	return (this.filename);
}

}
