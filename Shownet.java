package application;

import norsys.netica.*;
import norsys.netica.gui.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.*;
  
  class Shownet extends JFrame{
  
      public Shownet( String netName ) throws Exception {
      Net net = new Net( new Streamer( netName ) );
      net.compile();  //optional
      NetPanel netPanel = new NetPanel( net, NodePanel.NODE_STYLE_BELIEF_BARS );
      netPanel.setLocation(0, 0);
      netPanel.setSize(900, 600);
      //setLayout(new BorderLayout());  
      JPanel panel = new JPanel();
      panel.setLayout(new GridBagLayout());
      panel.setSize(900, 600);
      panel.add(netPanel);
      JScrollPane scroll = new JScrollPane();
      setPreferredSize(new Dimension(750, 400));
      scroll.getVerticalScrollBar().setUnitIncrement(10);
      scroll.setViewportView(panel);  
      getContentPane().add(scroll); //adds the NetPanel, within a JScrollPane, to ourself
      netPanel.setLinkPolicy( NetPanel.LINK_POLICY_BELOW);
      setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      setSize( 1400, 900 );// or you may prefer: 
      //setSize( getPreferredSize() );
      //setLayout(null);
      setVisible(true);
      }
  
  }