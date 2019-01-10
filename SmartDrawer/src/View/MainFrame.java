package View;


import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
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
	    	  this.add(DrawerPanel.getDrawer(),BorderLayout.CENTER);//add DrawerPanel
	      }
          public static void main(String[] args) {
        	        new MainFrame();
          }
}
