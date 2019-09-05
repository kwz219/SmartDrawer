package View;


import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Label;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JFrame;

import View.DrawerPanel.DrawerPanel;
/**
 * 
 * @author zwk
 * frame of the application
 */
public class MainFrame extends JFrame{
	
	      MainFrame(){
	    	  Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();//get size of the screen
	    	  setSize(screenSize.width,screenSize.height);
	    	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	  setVisible(true);
	    	  Box hBox1=Box.createHorizontalBox();
	    	  hBox1.add(CommandField.getField());
	    	  hBox1.add(RecordButton.getRecordButton());
	    	  hBox1.add(StateLabel.getLabel());
	    	  this.add(hBox1,BorderLayout.BEFORE_FIRST_LINE);
	    	  
	    	  this.add(DrawerPanel.getDrawer(),BorderLayout.CENTER);//add DrawerPanel
	    	  
	    	  this.setTitle("SmartDrawer     ©2019");
	      }
          public static void main(String[] args) {
        	        new MainFrame();
          }
}
