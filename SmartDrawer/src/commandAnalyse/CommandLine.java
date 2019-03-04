package commandAnalyse;
import java.awt.Color;

import java.awt.Dimension;
import java.util.ArrayList;

import Model.Line;
import Model.Point;

public class CommandLine extends CommandGeo{
	   private  CommandPoint startpoint;
	   private CommandPoint endpoint;
	   private String spname;
	   private String epname;
	   private double k;
	   private double b;
	   private double length;

	   public String getSpname() {
		return spname;
	}
	public void setSpname(String spname) {
		this.spname = spname;
	}
	public String getEpname() {
		return epname;
	}
	public void setEpname(String epname) {
		this.epname = epname;
	}
    public CommandLine(CommandPoint spoint,CommandPoint epoint) {
    	startpoint=spoint;
    	endpoint=epoint;
    	this.setLength(spoint.getLength(epoint));
    	this.k=((double)(spoint.getY()-epoint.getY())/(double)(spoint.getX()-epoint.getX()));
    	this.b=((double)spoint.getY()-((double)spoint.getX())*k);
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
    	this.k=((double)(startpoint.getY()-endpoint.getY())/(double)(startpoint.getX()-endpoint.getX()));
    	this.b=((double)startpoint.getY()-((double)endpoint.getX())*k);
    }  
    public void setEndpoint(CommandPoint endpoint) {
    	this.endpoint=endpoint;
    	this.k=((double)(startpoint.getY()-endpoint.getY())/(double)(startpoint.getX()-endpoint.getX()));
    	this.b=((double)startpoint.getY()-((double)endpoint.getX())*k);

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
	public double CalculateY(double d) {
		return (d*this.k+b);
	}
}
