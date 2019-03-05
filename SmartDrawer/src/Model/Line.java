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
	  
	   Point startpoint;
	   Point endpoint;
	
    public Line(Point spoint,Point epoint) {
    	startpoint=spoint;
    	endpoint=epoint;
    }
    
    public void print() {
    	  System.out.print("end1 ");
    	  startpoint.print();
    	  System.out.print("end2 ");
    	  endpoint.print();
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
    
    public void setPointbyindex(int index,Point p) {
    	if(index==1) {
	    	   startpoint=p;
	    }else {
	       endpoint=p;
	    }
    }
	

	public Point getStartpoint() {
		return startpoint;
	}

	public Point getEndpoint() {
		return endpoint;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Line) {
			if(this.getStartpoint().equals(((Line) obj).getStartpoint())&&this.getEndpoint().equals(((Line) obj).getEndpoint())){
				return true;
			}
		}
		return false;
	}

}
