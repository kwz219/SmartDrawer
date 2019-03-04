package commandAnalyse;

import java.awt.Dimension;

import Model.Point;

/**
 * Last modification time  2019/01/10
 * @author zwk
 * model of graph point
 */
public class CommandPoint extends CommandGeo{
    private double X;
    private double Y;
    public CommandPoint( ) {
   	 	this.setType("点");
    }
    public CommandPoint( int X,int Y,String name) {
    	this.setX(X);
    	this.setY(Y);
 	   this.setName(name);
 	   this.setType("点");
 }
	public CommandPoint(String name) {
		this.setType("点");
		this.setName(name);
	}
	public double getX() {
		return X;
	}
	public void setX(double x) {
		X = x;
	}
	public double getY() {
		return Y;
	}
	public void setY(double y) {
		Y = y;
	}
	public Point changeToPoint() {
		Point p=new Point(new Dimension ());
		p.setName(this.getName());
		p.setX((int)this.getX());
		p.setY(-(int)this.getY());
		return p;
	}
     public double getLength(CommandPoint p) {
    	double length=((this.getX()-p.getX())*(this.getX()-p.getX())+(this.getY()-p.getY())*(this.getY()-p.getY()));
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
    	 this.setName(p.getName());
    	 this.setX(p.getX());
    	 this.setY(-p.getY());
    	 System.out.println("点"+this.getName()+"坐标是"+this.getX()+","+this.getY());
    	 
     }
}
