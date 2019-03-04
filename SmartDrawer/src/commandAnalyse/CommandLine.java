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
    	this.k=((spoint.getY()-epoint.getY())/(spoint.getX()-epoint.getX()));
    	this.b=(spoint.getY()-spoint.getX()*k);
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
    	this.k=((this.startpoint.getY()-this.endpoint.getY())/(this.startpoint.getX()-this.endpoint.getX()));
    	this.b=(this.startpoint.getY()-this.startpoint.getX()*k);
    }  
    public void setEndpoint(CommandPoint endpoint) {
    	this.endpoint=endpoint;
    	this.k=((this.startpoint.getY()-this.endpoint.getY())/(this.startpoint.getX()-this.endpoint.getX()));
    	this.b=(this.startpoint.getY()-this.startpoint.getX()*k);
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

	public double getK() {
		return k;
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
	public void LoadLine(Line l1) {
		CommandPoint startpoint=new CommandPoint();
		CommandPoint endpoint=new CommandPoint();
		startpoint.loadPoint(l1.getStartpoint());
		endpoint.loadPoint(l1.getEndpoint());
		this.setEndpoint(endpoint);
		this.setStartpoint(startpoint);
	}
}
