package application;

import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.ArrayList;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.ModelFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Controller {
	@FXML
	private WebView browse;

	@FXML
	private URL location;
	@FXML
	private TextArea querybox;
	
	
	/////////checkboxes start////////
	@FXML
    CheckBox cb1;
    @FXML
    CheckBox cb2;
    @FXML
    CheckBox cb3;
    @FXML
    CheckBox cb4;
    @FXML
    CheckBox cb5;
    @FXML
    CheckBox cb6;
    @FXML
    CheckBox cb7;
    @FXML
    CheckBox cb8;
    @FXML
    CheckBox cb9;
    @FXML
    CheckBox cb10;
    @FXML
    CheckBox cb11;
    @FXML
    CheckBox cb12;
    @FXML
    CheckBox cb13;
    @FXML
    CheckBox cb14;
    @FXML
    CheckBox cb15;
    @FXML
    CheckBox cb16;
    @FXML
    CheckBox cb17;
    @FXML
    CheckBox cb18;
    @FXML
    CheckBox cb19;
    @FXML
    CheckBox cb20;
    @FXML
    CheckBox cb21;
    @FXML
    CheckBox cb22;
    @FXML
    CheckBox cb23;
    @FXML
    CheckBox cb24;
    @FXML
    CheckBox cb25;
    @FXML
    CheckBox cb27;
    @FXML
    CheckBox cb28;
    @FXML
    CheckBox cb29;
    @FXML
    CheckBox cb30;
    @FXML
    CheckBox cb31;
    @FXML
    CheckBox cb32;
    @FXML
    CheckBox cb33;
    @FXML
    CheckBox cb34;
    @FXML
    CheckBox cb35;
    @FXML
    CheckBox cb36;
    @FXML
    CheckBox cb37;
    @FXML
    CheckBox cb38;
    @FXML
    CheckBox cb39;
    @FXML
    CheckBox cb40;
    @FXML
    CheckBox cb41;
    @FXML
    CheckBox cb42;
    @FXML
    CheckBox cb43;
    @FXML
    CheckBox cb44;
    @FXML
    CheckBox cb45;
    @FXML
    Label lab;
    
    
private Stage dialogStage;
private boolean buttonClicked = false;

public static  ArrayList<String> signs = new ArrayList<String>();
public static  ArrayList<String> hab = new ArrayList<String>();
public static  ArrayList<String> his = new ArrayList<String>();
public static  ArrayList<String> phis = new ArrayList<String>();

public void setDialogStage(Stage dialogStage) {
    this.dialogStage = dialogStage;
}

public boolean isbuttonClicked() {
    return buttonClicked;
}

	
///////method for checkbox ///////
@FXML
private void handleButtonAction(ActionEvent e) {
  
    String choices="",symp ="";
    if(cb1.isSelected()){
        
        symp = cb1.getText();
        signs.add(symp);
     }
    if(cb2.isSelected()){
        
        symp = cb2.getText();
        signs.add(symp);
     }
    if(cb3.isSelected()){
        
        symp = cb3.getText();
        signs.add(symp);
     }
    if(cb4.isSelected()){
       
        symp = cb4.getText();
        signs.add(symp);
     }
    if(cb5.isSelected()){
       
        symp = cb5.getText();
        signs.add(symp);
     }
    if(cb6.isSelected()){
        
        symp = cb6.getText();
        signs.add(symp);
     } 
    if(cb7.isSelected()){
       
        symp = cb7.getText();
        //System.out.println("sym"+symp);
        signs.add(symp);
     }
    if(cb8.isSelected()){
        
        symp = cb8.getText();
        signs.add(symp);
     }
    if(cb9.isSelected()){
       
        symp = cb9.getText();
        signs.add(symp);
     }
    if(cb10.isSelected()){
        
        symp = cb10.getText();
       // System.out.println("sym"+symp);
        signs.add(symp);
     }
    if(cb11.isSelected()){
        
        symp = cb11.getText();
        signs.add(symp);
     }
    if(cb12.isSelected()){
       
        symp = cb12.getText();
        signs.add(symp);
     }
    if(cb13.isSelected()){
        
        symp = cb13.getText();
        //System.out.println("sym"+symp);
        signs.add(symp);
     }
    if(cb14.isSelected()){
        
        symp = cb14.getText();
        signs.add(symp);
     }
    if(cb15.isSelected()){
       
        symp = cb15.getText();
        signs.add(symp);
     }
    if(cb16.isSelected()){
        
        symp = cb16.getText();
       // System.out.println("sym"+symp);
        signs.add(symp);
     }
    if(cb17.isSelected()){
        
        symp = cb17.getText();
        signs.add(symp);
     }
    if(cb18.isSelected()){
        
        symp = cb18.getText();
        signs.add(symp);
     }
    if(cb19.isSelected()){
        
        symp = cb19.getText();
       // System.out.println("sym"+symp);
        signs.add(symp);
     }
    if(cb20.isSelected()){
        
        symp = cb20.getText();
        signs.add(symp);
     }
    if(cb21.isSelected()){
        
        symp = cb21.getText();
        signs.add(symp);
     }
    if(cb22.isSelected()){
       
        symp = cb22.getText();
        //System.out.println("sym"+symp);
        signs.add(symp);
     }
    if(cb23.isSelected()){
        
       // choices+=cb23.getText() + "\n";count++;
        symp = cb23.getText();
        signs.add(symp);
     }
    if(cb24.isSelected()){
        
        symp = cb24.getText();
        signs.add(symp);
     }
    if(cb25.isSelected()){
       
        symp = cb25.getText();
        //System.out.println("sym"+symp);
        signs.add(symp);
     }
    
    if(cb27.isSelected()){
       
        symp = cb27.getText();
        signs.add(symp);
     }
    if(cb28.isSelected()){
        
        symp = cb28.getText();
        hab.add(symp);
     }
    if(cb29.isSelected()){
        
        symp = cb29.getText();
        hab.add(symp);
     }
    if(cb30.isSelected()){
        
        symp = cb30.getText();
        hab.add(symp);
     }
    if(cb31.isSelected()){
        
        symp = cb31.getText();
       // System.out.println("sym"+symp);
        hab.add(symp);
     }
    if(cb32.isSelected()){
        
        symp = cb32.getText();
        hab.add(symp);
     }
    if(cb33.isSelected()){
        
        symp = cb33.getText();
        hab.add(symp);
     }
    if(cb34.isSelected()){
        
        symp = cb34.getText();
       // System.out.println("sym"+symp);
        hab.add(symp);
     }
    if(cb35.isSelected()){
       
        
        symp = cb35.getText();
        hab.add(symp);
     }
    if(cb36.isSelected()){
        
        symp = cb36.getText();
        hab.add(symp);
     }
    if(cb37.isSelected()){
        
        symp = cb37.getText();
        hab.add(symp);
     }
    if(cb38.isSelected()){
        //count++;
        
        symp = cb38.getText();
        his.add(symp);
     }
    if(cb39.isSelected()){
        
        symp = cb39.getText();
        phis.add(symp);
     }
    if(cb40.isSelected()){
        
        symp = cb40.getText();
        phis.add(symp);
     }
    if(cb41.isSelected()){
       // count++;
        
        symp = cb41.getText();
        phis.add(symp);
     }
    if(cb42.isSelected()){
       // count++;
        
        symp = cb42.getText();
        phis.add(symp);
     }
    if(cb43.isSelected()){
       // count++;
       
        symp = cb43.getText();
        phis.add(symp);
     }
    if(cb44.isSelected()){
        // count++;
        
         symp = cb44.getText();
         signs.add(symp);
      }
    if(cb45.isSelected()){
        // count++;
        
         symp = cb45.getText();
         signs.add(symp);
      }
  
   System.out.println(signs);
   
 }


	public void showPage(){
		try{
		System.out.println("here");
		browse.getEngine().load("file:///Users/mukundverma/Downloads/vis/examples/network/basicUsage1.html");}catch(Exception e){System.out.println(e);}
	}
	public void onSubmit(){
		try{ /*String symp="";
			if(cb1.isSelected()){
		        
		        symp = cb1.getText();
		        signs.add(symp);
		     }
		    if(cb2.isSelected()){
		        
		        symp = cb2.getText();
		        signs.add(symp);
		     }
		    if(cb3.isSelected()){
		        
		        symp = cb3.getText();
		        signs.add(symp);
		     }
		    if(cb4.isSelected()){
		       
		        symp = cb4.getText();
		        signs.add(symp);
		     }
		    if(cb5.isSelected()){
		       
		        symp = cb5.getText();
		        signs.add(symp);
		     }
		    if(cb6.isSelected()){
		        
		        symp = cb6.getText();
		        signs.add(symp);
		     } */
			System.out.println("calling netica");
			SparqlQuery spar = new SparqlQuery();
			spar.neticaa();
			//Parent root = FXMLLoader.load(window.fxml);
			FXMLLoader loader = new FXMLLoader(Controller.class.getResource("/application/window.fxml"));
			 AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root, 1400, 920);
			Stage stage = new Stage();
			File f = new File("/Users/mukundverma/screen2.css");
			scene.getStylesheets().clear();
			scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
			
			stage.setScene(scene);
			stage.show();

		                
		                
		}catch(Exception e){}
	}
	
	public void onWebclick(){
		
		try{
		FXMLLoader loader = new FXMLLoader(Controller.class.getResource("/application/webwin.fxml"));
		 AnchorPane root = (AnchorPane) loader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root, 1400, 920));
		stage.show();
		}catch(Exception e){}
		
	}
	
	public void handleQuery()
	{
		try{
			String SOURCE = "/Users/mukundverma/Desktop/disont.owl";
		    String NS = SOURCE + "#";
		String queryString="";
		String res="";
		 OntModel model2 = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM);
		 model2.read( SOURCE, "RDF/XML" );
		String prefix="PREFIX : <http://www.w3.org/2002/07/owl#> "+
"PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
"PREFIX xml: <http://www.w3.org/XML/1998/namespace> " +
"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " +
"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
"PREFIX untitled-ontology-7: <http://www.semanticweb.org/prerna/ontologies/2015/9/untitled-ontology-7#> " +
"BASE <http://www.semanticweb.org/prerna/ontologies/2015/9/untitled-ontology-7>";
		queryString+=prefix+querybox.getText();
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, model2);

		org.apache.jena.query.ResultSet results =  qe.execSelect();
		List<String> l=results.getResultVars();
		Iterator<String> i=l.iterator();
		while(i.hasNext())
		{
			res+=i.next()+"\t";
		}
		res+="\n";
		while(results.hasNext()){
			QuerySolution dislist2 = results.nextSolution();
			i=l.iterator();
			while(i.hasNext())
			{
				res+=dislist2.get(i.next())+"\t";
			}
			res+="\n";
		}
		querybox.setText(res);
           buttonClicked = true;
           dialogStage.close();
           }catch(Exception e){
        	   
           }
            
		
	}
	 @FXML 
	    public void initialize() {
	        //showPage();
		 browse.getEngine().load("file:///Users/mukundverma/Downloads/vis/examples/network/nodeStyles/colors1.html");
	    }    
}
