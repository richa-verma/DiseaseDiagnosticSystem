package application;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.impl.ResourceImpl;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
import norsys.netica.Environ;
import norsys.netica.Net;
import norsys.netica.NeticaException;
import norsys.netica.Streamer;
import norsys.neticaEx.aliases.Node;


	public class SparqlQuery2{
		 static Node[] nodes=new Node[200];
		 static Net net;
		 public static  ArrayList<String> rawsigns,symptoms,habits,history,phistory,diseases;
		 static ArrayList<Node> nodelist;
		 public static int getIndex(String s)
		 {
			 for(int i=0; i<nodelist.size();i++)
			 {
				 if(nodelist.get(i).toString().contains(s))
				 {
					 return(i);
				 }
				
			 }
			 return -1;
		 }
		 public static double getCpt(String d, String s)
		 {
			 String csvFile = "/Users/mukundverma/Desktop/cptsheet.csv";
				BufferedReader br = null;
				String line = "";
				String cvsSplitBy = ",";

				try {

					br = new BufferedReader(new FileReader(csvFile));
					while ((line = br.readLine()) != null) {

					        // use comma as separator
						String[] info = line.split(cvsSplitBy);
	                    if(info[0].equals(s) && info[3].equals(d))
	                    { 	
						   return Double.parseDouble(info[1]);

						   
	                    }
					}
					

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (br != null) {
						try {
							br.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				return 0;

		 }
	@SuppressWarnings("deprecation")
	public void neticaa () {
		try{
		Node.setConstructorClass("norsys.neticaEx.aliases.Node");
		Environ env=new Environ(null);
		net=new Net();
		net.setName("Ontology");
		}catch(Exception e){}
		symptoms = new ArrayList<String>();
		rawsigns = new ArrayList<String>();
		habits = new ArrayList<String>();
		history = new ArrayList<String>();
		phistory = new ArrayList<String>();
		nodelist = new ArrayList<Node>();
		diseases = new ArrayList<String>();
		
	    String SOURCE = "/Users/mukundverma/Desktop/disont.owl";
	    String NS = SOURCE + "#";
	   //create a model using reasoner
	   // OntModel model1 = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF);
	   //create a model which doesn't use a reasoner
	    OntModel model2 = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM);
	    
	    // read the RDF/XML file
	    //model1.read( SOURCE, "RDF/XML" );
	   model2.read( SOURCE, "RDF/XML" );
	    //prints out the RDF/XML structure
	  //  qe.close();
	    System.out.println(" ");
	    
	    
	    Controller con = new Controller();
	    rawsigns = con.signs;
	    for(int i=0;i<rawsigns.size();i++)
	    {
	    
	    	if(!symptoms.contains(rawsigns.get(i)))
	    		symptoms.add(rawsigns.get(i));
	    }

	// Create a new query
	String queryString =        
	"PREFIX : <http://www.w3.org/2002/07/owl#> "+
	"PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
	"PREFIX xml: <http://www.w3.org/XML/1998/namespace> " +
	"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " +
	"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
	"PREFIX untitled-ontology-7: <http://www.semanticweb.org/prerna/ontologies/2015/9/untitled-ontology-7#> " +
	"BASE <http://www.semanticweb.org/prerna/ontologies/2015/9/untitled-ontology-7> "+
	    "select ?disease ?symptom\n"+
	    "where { \n ?disease untitled-ontology-7:hasSymptom ?symptom.\n}";
	    
	Query query = QueryFactory.create(queryString);
	QueryExecution qe = QueryExecutionFactory.create(query, model2);

	org.apache.jena.query.ResultSet results =  qe.execSelect();
	while(results.hasNext()){
		QuerySolution dislist2 = results.nextSolution();
		for(int i=0; i<symptoms.size();i++) {
		if(dislist2.get("symptom").toString().contains(symptoms.get(i)))
		{ 
	    // System.out.println(dislist2.get("disease")+"\t"+dislist2.get("symptom"));
	     String dis = dislist2.get("disease").toString().substring(72);
	     String sym = dislist2.get("symptom").toString().substring(72);
	     if(!diseases.contains(dis))
	     {
	    	 diseases.add(dis);
	     }
	     //System.out.println(diseases);
//	     System.out.println(dis +" "+sym);
	     try {
	    	int index1,index2;
	    	index1 = getIndex(dis);
	    	index2 = getIndex(sym);
	        if(index1 == -1){
			nodelist.add(new Node(dis,"present,absent",net));
			index1 = getIndex(dis);
	        }
	        if(index2 == -1){
			nodelist.add(new Node(sym,"present"+sym+",absent"+sym,net));
			index2 = getIndex(sym);
	        }
		
			nodelist.get(index1).addLink(nodelist.get(index2));
		} catch (NeticaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	} 
		
	}

	try{
	int disindex;
	//System.out.println(diseases.size()+"\t"+symptoms.size());
	//System.out.println(diseases);
	for(int i=0;i<diseases.size();i++){
		String equation="";
		String st="";
		String dis = diseases.get(i);
		equation+=dis+"(";
		disindex=getIndex(dis);
		//System.out.println(disindex);
		for(int j=0;j<symptoms.size();j++){
			String sym = symptoms.get(j);
			int symindx = getIndex(sym);
			//System.out.println("ss"+symindx);
			double d;
			d = getCpt(dis,sym); 
			System.out.println(dis+"\t"+sym+"\t"+d+"\t"+symindx);
			if(d != 0) {
			//	System.out.println("probab"+d);
				nodelist.get(symindx).setCPTable(d,1-d);
				//System.out.println("probability");
					equation+=sym+",";
					st+=sym+"||";
				}
		}
		equation+=")="+st;
		int id=equation.indexOf(")");
		id--;
		String eq=equation.substring(0, id);
		eq+=")";
		id=equation.indexOf("=");
		int last=equation.length()-2;
		eq+=equation.substring(id, last);
		//System.out.println(eq);
		nodelist.get(disindex).setEquation(eq);
		nodelist.get(disindex).equationToTable(1, false, false);
		System.out.println(eq);
	}
	Streamer s=new Streamer("medical.dne");
	net.write(s);
	net.compile();
	/*for(int i=0;i<symptoms.size();i++)
	{
		String symp = symptoms.get(i);
		int symindx = getIndex(symp);
		nodelist.get(symindx).enterFinding("present"+symp);
		System.out.println("entered findings");
	}*/

	for(int i=0;i<diseases.size();i++){
		String dis = diseases.get(i);	
	int disindx = getIndex(dis);
	double belief=nodelist.get(disindx).getBelief("present");
	System.out.println("belief"+i+" "+belief); }
	net.finalize();
	}catch(Exception e){}
	qe.close();
	}
	}

