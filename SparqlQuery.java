package application;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.JScrollPane;

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
import norsys.netica.gui.NetPanel;
import norsys.netica.gui.NodePanel;
import norsys.neticaEx.aliases.Node;


	public class SparqlQuery{
		 static Node[] nodes=new Node[200];
		 static Net net;
		 public static  ArrayList<String> rawsigns,symptoms,habits,history,phistory,diseases;
		 static ArrayList<Node> nodelist;
		 static String output="",tests="";
		 static FileWriter fw;
		 static FileWriter fwr;
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
		Environ env=new Environ("+VermaR/IITech/Ex15-12-31,120/42820");
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
	    rawsigns = con.hab;
	    for(int i=0;i<rawsigns.size();i++)
	    {
	    
	    	if(!habits.contains(rawsigns.get(i)))
	    		habits.add(rawsigns.get(i));
	    }
	    rawsigns = con.his;
	    for(int i=0;i<rawsigns.size();i++)
	    {
	    
	    	if(!history.contains(rawsigns.get(i)))
	    		history.add(rawsigns.get(i));
	    }
	    rawsigns = con.phis;
	    for(int i=0;i<rawsigns.size();i++)
	    {
	    
	    	if(!phistory.contains(rawsigns.get(i)))
	    		phistory.add(rawsigns.get(i));
	    }
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
	String queryString1 =        
			"PREFIX : <http://www.w3.org/2002/07/owl#> "+
			"PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			"PREFIX xml: <http://www.w3.org/XML/1998/namespace> " +
			"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " +
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
			"PREFIX untitled-ontology-7: <http://www.semanticweb.org/prerna/ontologies/2015/9/untitled-ontology-7#> " +
			"BASE <http://www.semanticweb.org/prerna/ontologies/2015/9/untitled-ontology-7> "+
			    "select ?disease ?habit\n"+
			    "where { \n ?disease untitled-ontology-7:causedByHabit ?habit.\n}";
			    
			Query query1 = QueryFactory.create(queryString1);
			QueryExecution qe1 = QueryExecutionFactory.create(query1, model2);

			org.apache.jena.query.ResultSet results1 =  qe1.execSelect();
			while(results1.hasNext()){
				QuerySolution dislist2 = results1.nextSolution();
				for(int i=0; i<habits.size();i++) {
				if(dislist2.get("habit").toString().contains(habits.get(i)))
				{ 
			    // System.out.println(dislist2.get("disease")+"\t"+dislist2.get("symptom"));
			     String dis = dislist2.get("disease").toString().substring(72);
			     String sym = dislist2.get("habit").toString().substring(72);
			     if(!diseases.contains(dis))
			     {
			    	 diseases.add(dis);
			    	 
			     }
			     //System.out.println(diseases);
//			     System.out.println(dis +" "+sym);
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
			String queryString2 =        
					"PREFIX : <http://www.w3.org/2002/07/owl#> "+
					"PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
					"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
					"PREFIX xml: <http://www.w3.org/XML/1998/namespace> " +
					"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " +
					"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
					"PREFIX untitled-ontology-7: <http://www.semanticweb.org/prerna/ontologies/2015/9/untitled-ontology-7#> " +
					"BASE <http://www.semanticweb.org/prerna/ontologies/2015/9/untitled-ontology-7> "+
					    "select ?disease ?history\n"+
					    "where { \n ?disease untitled-ontology-7:causedByHistory ?history.\n}";
					    
					Query query2 = QueryFactory.create(queryString2);
					QueryExecution qe2 = QueryExecutionFactory.create(query2, model2);

					org.apache.jena.query.ResultSet results2 =  qe2.execSelect();
					while(results2.hasNext()){
						QuerySolution dislist2 = results2.nextSolution();
						for(int i=0; i<history.size();i++) {
						if(dislist2.get("history").toString().contains(history.get(i)))
						{ 
					    // System.out.println(dislist2.get("disease")+"\t"+dislist2.get("symptom"));
					     String dis = dislist2.get("disease").toString().substring(72);
					     String sym = dislist2.get("history").toString().substring(72);
					     if(!diseases.contains(dis))
					     {
					    	 diseases.add(dis);
					    	 System.out.println("history ki waja se ayi ye disease"+dis);
					     }
					     //System.out.println(diseases);
//					     System.out.println(dis +" "+sym);
					     try {
					    	int index1,index2;
					    	index1 = getIndex(dis);
					    	index2 = getIndex("h"+sym);
					        if(index1 == -1){
							nodelist.add(new Node(dis,"present,absent",net));
							index1 = getIndex(dis);
					        }
					        if(index2 == -1){
							nodelist.add(new Node("h"+sym,"present"+sym+",absent"+sym,net));
							index2 = getIndex("h"+sym);
					        }
						
							nodelist.get(index1).addLink(nodelist.get(index2));
						} catch (NeticaException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}
						
					} 
						
					}
					String queryString3 =        
							"PREFIX : <http://www.w3.org/2002/07/owl#> "+
							"PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
							"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
							"PREFIX xml: <http://www.w3.org/XML/1998/namespace> " +
							"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " +
							"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
							"PREFIX untitled-ontology-7: <http://www.semanticweb.org/prerna/ontologies/2015/9/untitled-ontology-7#> " +
							"BASE <http://www.semanticweb.org/prerna/ontologies/2015/9/untitled-ontology-7> "+
							    "select ?disease ?history\n"+
							    "where { \n ?disease untitled-ontology-7:causedByParentHistory ?history.\n}";
							    
							Query query3 = QueryFactory.create(queryString3);
							QueryExecution qe3 = QueryExecutionFactory.create(query3, model2);

							org.apache.jena.query.ResultSet results3 =  qe3.execSelect();
							while(results3.hasNext()){
								QuerySolution dislist2 = results3.nextSolution();
								for(int i=0; i<phistory.size();i++) {
								if(dislist2.get("history").toString().contains(phistory.get(i)))
								{ 
							    // System.out.println(dislist2.get("disease")+"\t"+dislist2.get("symptom"));
							     String dis = dislist2.get("disease").toString().substring(72);
							     String sym = dislist2.get("history").toString().substring(72);
							     if(!diseases.contains(dis))
							     {
							    	 diseases.add(dis);
							    	 System.out.println("parent ki waja se ayi ye disease"+dis);
							     }
							     //System.out.println(diseases);
//							     System.out.println(dis +" "+sym);
							    try {
							    	int index1,index2;
							    	index1 = getIndex(dis);
							    	index2 = getIndex("p"+sym);
							        if(index1 == -1){
									nodelist.add(new Node(dis,"present,absent",net));
									index1 = getIndex(dis);
							        }
							        if(index2 == -1){
									nodelist.add(new Node("p"+sym,"present"+sym+",absent"+sym,net));
									index2 = getIndex("p"+sym);
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
	fw = new FileWriter("network.csv");
	fwr = new FileWriter("equation.csv");
	//System.out.println(diseases.size()+"\t"+symptoms.size());
	System.out.println(diseases);
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
			System.out.println(dis+"\t"+sym+"\t"+d);     //write this
			fw.write(dis+" "+sym+" "+d+"\n");
			if(d != 0) {
			//	System.out.println("probab"+d);
				nodelist.get(symindx).setCPTable(d,1-d);
				//System.out.println("probability");
					equation+=sym+",";
					st+=sym+"||";
				}
		}
		for(int j=0;j<habits.size();j++){
			String sym = habits.get(j);
			int symindx = getIndex(sym);
			//System.out.println("ss"+symindx);
			double d;
			d = getCpt(dis,sym); 
			System.out.println(dis+"\t"+sym+"\t"+d);                     //write this
			fw.write(dis+" "+sym+" "+d+"\n");
			if(d != 0) {
			//	System.out.println("probab"+d);
				nodelist.get(symindx).setCPTable(d,1-d);
				//System.out.println("probability");
					equation+=sym+",";
					st+=sym+"||";
				}
		}
		for(int j=0;j<history.size();j++){
			String sym = history.get(j);
			int symindx = getIndex("h"+sym);
			//System.out.println("ss"+symindx);
			double d;
			d = getCpt(dis,"h"+sym); 
			System.out.println(dis+"\t"+sym+"\t"+d);                              //write this
			fw.write(dis+" "+sym+" "+d+"\n");
			if(d != 0) {
			//	System.out.println("probab"+d);
				nodelist.get(symindx).setCPTable(d,1-d);
				//System.out.println("probability");
					equation+="h"+sym+",";
					st+="h"+sym+"||";
				}
		}
		for(int j=0;j<phistory.size();j++){
			String sym = phistory.get(j);
			int symindx = getIndex("p"+sym);
			//System.out.println("ss"+symindx);
			double d;
			d = getCpt(dis,"p"+sym); 
			System.out.println(dis+"\t"+sym+"\t"+d);                                    //write this 
			fw.write(dis+" "+sym+" "+d+"\n");
			if(d != 0) {
			//	System.out.println("probab"+d);
				nodelist.get(symindx).setCPTable(d,1-d);
				//System.out.println("probability");
					equation+="p"+sym+",";
					st+="p"+sym+"||";
				}
		}
		equation+=")="+st;
		//System.out.println(equation);
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
		System.out.println(eq);                                             //write this
		fwr.write(eq+"\n");
	}
	System.out.println("reaching end");
	Streamer s=new Streamer("medical.dne");
	net.write(s);
	net.compile();
	
	ArrayList<Double> beliefList= new ArrayList<Double>();
	ArrayList<Double> beliefs=new ArrayList<Double>();
	for(int i=0;i<diseases.size();i++){
		String dis = diseases.get(i);	
	int disindx = getIndex(dis);
	double belief=nodelist.get(disindx).getBelief("present");
	beliefList.add(belief);
	System.out.println("belief "+dis+" "+belief); 
	fw.write("belief "+dis+" "+belief+"\n");
	}                         // write this
	
	
	
	System.out.println("beliefs_original"+ beliefList);
	for(int i=0;i<beliefList.size();i++)
	{
		beliefs.add(beliefList.get(i)); 
	}
	Collections.sort(beliefs);
	System.out.println("beliefs_original"+ beliefList);
	System.out.println("beliefs_sorted"+ beliefs);
	System.out.println("diseases"+ diseases);
	fw.close();
	fwr.close();
	
	int index1=beliefList.indexOf(beliefs.get(beliefs.size()-1));
	double b1 = beliefList.get(index1);
	double b2 = 0;
	int index2=0;
	if(diseases.size()>1){
	index2=beliefList.indexOf(beliefs.get(beliefs.size()-2));
	b2 = beliefList.get(index2);
	}
	String d1=diseases.get(index1);
	String d2=diseases.get(index2);
	System.out.println(d1+d2+b1+b2);
	if(d1.equals(d2))
	output = d1+"\t"+b1;
	else 
	output = d1+" "+b1+"\n"+d2+" "+b2;
	String queryString4 =        
			"PREFIX : <http://www.w3.org/2002/07/owl#> "+
			"PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			"PREFIX xml: <http://www.w3.org/XML/1998/namespace> " +
			"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " +
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
			"PREFIX untitled-ontology-7: <http://www.semanticweb.org/prerna/ontologies/2015/9/untitled-ontology-7#> " +
			"BASE <http://www.semanticweb.org/prerna/ontologies/2015/9/untitled-ontology-7> "+
			    "select ?test\n"+
			    "where { \n {untitled-ontology-7:"+d1+" untitled-ontology-7:hasTest ?test}UNION{untitled-ontology-7:"+d2+" untitled-ontology-7:hasTest ?test}.\n}";
			    
			Query query4 = QueryFactory.create(queryString4);
			QueryExecution qe4 = QueryExecutionFactory.create(query4, model2);

			org.apache.jena.query.ResultSet results4 =  qe4.execSelect();
			while(results4.hasNext())
			{
				QuerySolution dislist2 = results4.nextSolution();
				//System.out.println("Here are the tests:"+dislist2.get("test").toString().substring(72));
				String test = dislist2.get("test").toString().substring(72);
				if(test!=null)
				tests += test+"\n";
			}
	net.finalize();
	//Shownet disp = new Shownet("medical.dne");
	}catch(Exception e){
		System.out.println(e);
	}
	qe.close();
	}
	
	}

