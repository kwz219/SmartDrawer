package Model;

import java.awt.Dimension;

import Logic.PointsFittingHelper.Graphicstype;
import Logic.PointsFittingHelper.Pointtype;

/**
 * Last modification time  2019/03/01
 * @author zwk
 * model of graph point
 */
public class Point {
      private String Name;
      private Dimension Coordinate;
      private int X;
      private int Y;
      private Pointtype Type;
    public Point(Dimension coordinate) {
    	   Coordinate=coordinate;
    	   X=coordinate.width;
    	   Y=coordinate.height;
    }
    public Point(Dimension coordinate,Pointtype type) {
 	   Coordinate=coordinate;
 	   X=coordinate.width;
	   Y=coordinate.height;
 	   Type=type;
 }
    public Point(Dimension coordinate,String name) {
 	   Coordinate=coordinate;
 	   X=coordinate.width;
	   Y=coordinate.height;
 	   Name=name;
 }
    public boolean Roughlyequal(Dimension point) {
    	      boolean result=false;
    	      if(Math.abs(point.width-Coordinate.width)<3&&Math.abs(point.height-Coordinate.height)<3) {
    	    	     result=true;
    	      }
    	      return result;
    	      
    }
    
    public Pointtype getType() {
    	        return Type;
    }
	public String getName() {
		return Name;
	}
	public Dimension getCoordinate() {
		return Coordinate;
	}
	public void setName(String name) {
		Name = name;
	}
	public void setCoordinate(Dimension coordinate) {
		Coordinate = coordinate;
		X=coordinate.width;
  	    Y=coordinate.height;
	}
	public int getX() {
		return X;
	}
	public int getY() {
		return Y;
	}
	
	public void setX(int x) {
		X=x;
	}
	
	public void setY(int y) {
		Y=y;
	}
      
}
