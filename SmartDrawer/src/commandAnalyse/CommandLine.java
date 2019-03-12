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
	   private ArrayList<CommandPoint> pointlist=new ArrayList<CommandPoint>();
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
    	this.length=(spoint.getLength(epoint));
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
    }  
    public void setEndpoint(CommandPoint endpoint) {
    	this.endpoint=endpoint;

    }
	public CommandPoint getStartpoint() {
		return startpoint;
	}


	public CommandPoint getEndpoint() {
		return endpoint;
	}

	public double getB() {
    	this.b=((double)startpoint.getY()-((double)startpoint.getX())*k);
		return b;
	}

	public double getK() {
    	this.k=((double)(startpoint.getY()-endpoint.getY())/(double)(startpoint.getX()-endpoint.getX()));
		return k;
	}
	public double getLength() {
		this.length=startpoint.getLength(endpoint);
		return length;
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
		CommandList cl=CommandList.getInstance();
		startpoint.setChangeWeight(cl.ChangeWeight(startpoint.getName()));
		endpoint.setChangeWeight(cl.ChangeWeight(endpoint.getName()));
		this.setEndpoint(endpoint);
		this.setStartpoint(startpoint);

	}
	public double CalculateY(double d) {
		return (d*this.k+b);
	}
	public void addPoint(CommandPoint p) {
		pointlist.add(p);
	}
	public ArrayList<CommandPoint> getPointlist() {
		return pointlist;
	}
	public void setPointlist(ArrayList<CommandPoint> pointlist) {
		this.pointlist = pointlist;
	}
}
