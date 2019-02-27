package commandAnalyse;
import java.awt.Color;

import java.awt.Dimension;
import java.util.ArrayList;
/**
 * Last modification time 2019/01/10
 * @author zwk
 * model of graph line
 */
public class CommandLine extends CommandGeo{
	   Color color;
	   CommandPoint startpoint;
	   CommandPoint endpoint;
	
    public CommandLine(CommandPoint spoint,CommandPoint epoint) {
    	startpoint=spoint;
    	endpoint=epoint;
   	 	this.setType("line");
   	 	this.setName(spoint.getName()+epoint.getName());
    }
    
    public static boolean Isvertical(Dimension Highestpoint,Dimension Lowestpoint) {
    	    if(Highestpoint.width==Lowestpoint.getWidth()) {
    	    	   return true;
    	    }
		return false;
    }
    
    public static boolean Ishorizontal(Dimension Leftpoint,Dimension Rightpoint) {
    	    if(Leftpoint.height==Rightpoint.height) {
    	    	  return true;
    	    }
		return false;
    	
    }
    
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public CommandPoint getStartpoint() {
		return startpoint;
	}


	public CommandPoint getEndpoint() {
		return endpoint;
	}

}
