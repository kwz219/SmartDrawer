package Model;

import java.awt.Color;

import java.awt.Dimension;
import java.util.ArrayList;
/**
 * Last modification time 2019/01/10
 * @author zwk
 * model of graph line
 */
public class Line {
	   Color color;
	   Dimension startpoint;
	   Dimension endpoint;
	   String spname;
	   String epname;
	
    public Line(Dimension spoint,Dimension epoint) {
    	startpoint=spoint;
    	endpoint=epoint;
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

	public Dimension getStartpoint() {
		return startpoint;
	}

	public void setStartpoint(Dimension startpoint) {
		this.startpoint = startpoint;
	}

	public Dimension getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(Dimension endpoint) {
		this.endpoint = endpoint;
	}
}
