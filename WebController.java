package application;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class WebController {

	@FXML
	private WebView graph;
	public void initialize(){
		try{
		System.out.println("here");
		graph.getEngine().load("file:///Users/mukundverma/Downloads/vis/examples/network/nodeStyles/colors1.html");}catch(Exception e){System.out.println(e);}
	}
}
