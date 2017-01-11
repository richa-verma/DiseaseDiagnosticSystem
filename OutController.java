package application;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class OutController {
	@FXML
	TextArea textArea;
	@FXML
	TextArea testArea;
	
	
	
	
	@FXML
	public void initialize() {
		
		SparqlQuery.tests.replaceAll("null", "");
		//textArea.setPrefRowCount(2); 
		textArea.setText(SparqlQuery.output);
		//testArea.setPrefRowCount(4);
		testArea.setText(SparqlQuery.tests);
		
	}

	public void onSubmitClicked(){
		try{
			//System.out.println("onSubmitClicked");
				
			String pythonScriptPath = "test1.py";
			String[] cmd = new String[4];
			cmd[0] = "python"; // check version of installed python: python -V
			cmd[1] = pythonScriptPath;
				 
			// create runtime to execute external command
			//System.out.println(cmd[1]);
			Runtime rt = Runtime.getRuntime();
			Process pr = rt.exec("/Library/Frameworks/Python.framework/Versions/2.7/bin/python tumhariMeetikaJ.py");
			pr.waitFor();
			// retrieve output from python script
			BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line = "";
			while((line = bfr.readLine()) != null) {
				// display each output line form python script
				System.out.println(line);
				pr = rt.exec("/opt/local/bin/dot -Tpng belief.dot -o outfile.png");
				pr.waitFor();
			}
			//System.out.println("img created1");
			Stage primaryStage = new Stage();
			//System.out.println("img created2");
			primaryStage.setTitle("Bayesian Belief Network");
			//System.out.println("img created3");
		    StackPane sp = new StackPane();
		    //System.out.println("img created4");
			Image img = new Image("file:outfile.png");
			//System.out.println("img created5");
			ImageView imgView = new ImageView(img);
			//System.out.println("img created6");
			sp.getChildren().add(imgView);
			//System.out.println("img created7");
			Scene scene = new Scene(sp);
			//System.out.println("img created8");
			primaryStage.setScene(scene);
			//System.out.println("img created9");
	        primaryStage.show();
	        //System.out.println("here");

		                
		                
		}catch(Exception e){e.printStackTrace();}
	}
	
	
}
