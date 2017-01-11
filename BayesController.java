package application;

import javax.swing.SwingUtilities;

import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import norsys.netica.Net;
import norsys.netica.Streamer;
import norsys.netica.gui.NetPanel;
import norsys.netica.gui.NodePanel;



public class BayesController {

	@FXML
	public void initialize() {
		try{
		
	      final SwingNode swingNode = new SwingNode();

	        createSwingContent(swingNode);

	        StackPane pane = new StackPane();
	        pane.getChildren().add(swingNode);
		}catch(Exception e){}
	      
		
	}

	private void createSwingContent(SwingNode swingNode) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	try{
            	Net net = new Net( new Streamer( "medical.dne" ) );
      	      net.compile();  //optional
      	      NetPanel netPanel = new NetPanel( net, NodePanel.NODE_STYLE_BELIEF_BARS );
      	      netPanel.setLocation(0, 0);
      	      netPanel.setSize(900, 600);
      	      netPanel.setLinkPolicy( NetPanel.LINK_POLICY_BELOW);
      	    swingNode.setContent(netPanel);
            	}catch(Exception e){}
            }
                
	});
	
}
}
