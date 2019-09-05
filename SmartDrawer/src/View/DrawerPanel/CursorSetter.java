package View.DrawerPanel;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
/**
 * 
 * @author zwk
 * 一个无聊的类，用于设置鼠标样式
 */
public class CursorSetter {
	public static Cursor getCursor(String type) {
	       Toolkit tk = Toolkit.getDefaultToolkit(); 
	       String root=System.getProperty("user.dir");
	       if(type.equals("chalk")) {
	    	   String url=root+"/Resource/ChalkCursor.png";
	    	  
	    	   Image image = new ImageIcon(url).getImage(); 
	    	   Cursor cursor = tk.createCustomCursor(image,new java.awt.Point(1,1), "chalk");
	    
	    	   return cursor;
	       }
	       return null;
}
}
