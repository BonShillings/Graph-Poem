import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.search.similarities.DefaultSimilarity;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;


public class LuceneMethods {
	
	final File folder = new File ("Formatted Poems");
	
	
	public void main(String[] args) throws IOException, ParseException{
		Similarity vector = new DefaultSimilarity() ;
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
		Directory index = new RAMDirectory();
		
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, analyzer);
		IndexWriter writer = new IndexWriter(index, config);
	
		indexDocs(writer, folder);
		writer.close();
		
		String querystr = args.length > 0 ? args[0] : "Lucene";
		Query q = new QueryParser(Version.LUCENE_40, "title", analyzer).parse(querystr);
		
		int hitsPerPage = 10;
		IndexReader reader = IndexReader.open(index);
		IndexSearcher searcher = new IndexSearcher(reader);
		TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
		searcher.search(q, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;
		
		System.out.println("Found " + hits.length + " hits.");
		for(int i=0;i<hits.length;++i) {
		    int docId = hits[i].doc;
		    Document d = searcher.doc(docId);
		    System.out.println((i + 1) + ". " + d.get("isbn") + "\t" + d.get("title"));
		}
	
	
		
	}
	private static void addDoc(IndexWriter w, String title, String isbn) throws IOException {
		  Document doc = new Document();
		 doc.add(new TextField("title", title, Field.Store.YES));
		 doc.add(new StringField("isbn", isbn, Field.Store.YES));
		  w.addDocument(doc);
		}
	
	/**
	   * Indexes the given file using the given writer, or if a directory is given,
	   * recurses over files and directories found under the given directory.
	   * 
	   * NOTE: This method indexes one document per input file.  This is slow.  For good
	   * throughput, put multiple documents into your input file(s).  An example of this is
	   * in the benchmark module, which can create "line doc" files, one document per line,
	   * using the
	   * <a href="../../../../../contrib-benchmark/org/apache/lucene/benchmark/byTask/tasks/WriteLineDocTask.html"
	   * >WriteLineDocTask</a>.
	   *  
	   * @param writer Writer to the index where the given file/dir info will be stored
	   * @param file The file to index, or the directory to recurse into to find files to index
	   * @throws IOException If there is a low-level I/O error
	   */
	  static void indexDocs(IndexWriter writer, File file)
	    throws IOException {
	    // do not try to index files that cannot be read
	    if (file.canRead()) {
	      if (file.isDirectory()) {
	        String[] files = file.list();
	        // an IO error could occur
	        if (files != null) {
	          for (int i = 0; i < files.length; i++) {
	            indexDocs(writer, new File(file, files[i]));
	          }
	        }
	      } else {

	        FileInputStream fis;
	        try {
	          fis = new FileInputStream(file);
	        } catch (FileNotFoundException fnfe) {
	          // at least on windows, some temporary files raise this exception with an "access denied" message
	          // checking if the file can be read doesn't help
	          return;
	        }

	        try {

	          // make a new, empty document
	          Document doc = new Document();

	          // Add the path of the file as a field named "path".  Use a
	          // field that is indexed (i.e. searchable), but don't tokenize 
	          // the field into separate words and don't index term frequency
	          // or positional information:
	          Field pathField = new StringField("path", file.getPath(), Field.Store.YES);
	          doc.add(pathField);

	          // Add the last modified date of the file a field named "modified".
	          // Use a LongField that is indexed (i.e. efficiently filterable with
	          // NumericRangeFilter).  This indexes to milli-second resolution, which
	          // is often too fine.  You could instead create a number based on
	          // year/month/day/hour/minutes/seconds, down the resolution you require.
	          // For example the long value 2011021714 would mean
	          // February 17, 2011, 2-3 PM.
	          doc.add(new LongField("modified", file.lastModified(), Field.Store.NO));

	          // Add the contents of the file to a field named "contents".  Specify a Reader,
	          // so that the text of the file is tokenized and indexed, but not stored.
	          // Note that FileReader expects the file to be in UTF-8 encoding.
	          // If that's not the case searching for special characters will fail.
	          doc.add(new TextField("contents", new BufferedReader(new InputStreamReader(fis, "UTF-8"))));

	          if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
	            // New index, so we just add the document (no old document can be there):
	            System.out.println("adding " + file);
	            writer.addDocument(doc);
	          } else {
	            // Existing index (an old copy of this document may have been indexed) so 
	            // we use updateDocument instead to replace the old one matching the exact 
	            // path, if present:
	            System.out.println("updating " + file);
	            writer.updateDocument(new Term("path", file.getPath()), doc);
	  
	            
	          }
	          
	        } finally {
	          fis.close();
	        }
	      }
	    }   
	  }
}



