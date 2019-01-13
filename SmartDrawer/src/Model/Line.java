package Model;

import java.awt.Color;

import java.awt.Dimension;
import java.util.ArrayList;
/**
 * Last modification time 2019/01/13
 * @author zwk
 * model of graph line
 */
public class Line {
	   Color color;
	   Point startpoint;
	   Point endpoint;
	
    public Line(Point spoint,Point epoint) {
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
    public Point getPointbyindex(int index) {
    	    if(index==1) {
    	    	  return startpoint;
    	    }
    	      return endpoint;
    }
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Point getStartpoint() {
		return startpoint;
	}

	public Point getEndpoint() {
		return endpoint;
	}

	

}
