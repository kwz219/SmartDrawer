package commandAnalyse;

import java.awt.Dimension;

import Model.Point;

/**
 * Last modification time  2019/01/10
 * @author zwk
 * model of graph point
 */
public class CommandPoint extends CommandGeo{
    private int X;
    private int Y;
    public CommandPoint( ) {
   	 	this.setType("点");
    }
    public CommandPoint( int X,int Y,String name) {
    	this.setX(X);
    	this.setY(Y);
 	   this.setName(name);
 	   this.setType("点");
 }
	public int getX() {
		return X;
	}
	public void setX(int x) {
		X = x;
	}
	public int getY() {
		return Y;
	}
	public void setY(int y) {
		Y = y;
	}
	public Point changeToPoint() {
		Point p=new Point(new Dimension ());
		p.setName(this.getName());
		p.setX(this.getX());
		p.setY(this.getY());
		return p;
	}
     public double getLength(CommandPoint p) {
    	double length=((this.getX()-p.getX())^2+(this.getY()-p.getY())^2);
    	length=Math.pow(length, 0.5);
		return length;
    	 
     }
     public double getDistance(CommandLine l1) {
    	 double k=l1.getK();
    	 double x=this.getX();
    	 double y=this.getY();
    	 double b=l1.getB();
    	 return (Math.abs(k*x+b-y))/(Math.pow((k*k+1), 0.5));
     }
     public void loadPoint(Point p) {
    	 this.setX(p.getX());
    	 this.setY(p.getY());
     }
}
