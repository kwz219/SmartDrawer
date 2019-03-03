package commandAnalyse;
import java.awt.Color;

import java.awt.Dimension;
import java.util.ArrayList;

import Model.Line;
import Model.Point;

public class CommandLine extends CommandGeo{
	   private  CommandPoint startpoint;
	   private CommandPoint endpoint;
	   private double k;
	   private double b;
	   private double length;
    public CommandLine(CommandPoint spoint,CommandPoint epoint) {
    	startpoint=spoint;
    	endpoint=epoint;
    	this.setLength(spoint.getLength(epoint));
    	this.setK((spoint.getY()-epoint.getY())/(spoint.getX()-epoint.getX()));
    	this.setB(spoint.getY()-spoint.getX()*k);
    	this.setType("直线");
   	 	this.setName(spoint.getName()+epoint.getName());
    }
    public CommandLine(String name) {
    	startpoint=new CommandPoint(0,0,name.substring(0, 1));
    	endpoint =new CommandPoint(0,0,name.substring(1,2));
    	this.setName(name);
    	this.setType("直线");
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
    //根据两个点不断转换斜率
    public void setStartpoint(CommandPoint startpoint) {
    	this.startpoint=startpoint;
    	this.setK((this.startpoint.getY()-this.endpoint.getY())/(this.startpoint.getX()-this.endpoint.getX()));
    	this.setB(this.startpoint.getY()-this.startpoint.getX()*k);
    }  
    public void setEndpoint(CommandPoint endpoint) {
    	this.endpoint=endpoint;
    	this.setK((this.startpoint.getY()-this.endpoint.getY())/(this.startpoint.getX()-this.endpoint.getX()));
    	this.setB(this.startpoint.getY()-this.startpoint.getX()*k);
    }
	public CommandPoint getStartpoint() {
		return startpoint;
	}


	public CommandPoint getEndpoint() {
		return endpoint;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}
	public double getK() {
		return k;
	}

	public void setK(double k) {
		this.k = k;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}
	public Line changeToLine() {
		Line l=new Line(this.startpoint.changeToPoint(),this.endpoint.changeToPoint());
		return l;
	}
}
