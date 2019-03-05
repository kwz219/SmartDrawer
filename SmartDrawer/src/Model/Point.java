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
    	   Name="noname";
    	   Type=Pointtype.Undefined;
    }
    public Point(Dimension coordinate,Pointtype type) {
 	   Coordinate=coordinate;
 	   X=coordinate.width;
	   Y=coordinate.height;
 	   Type=type;
 	   Name="noname";
 }
    public Point(Dimension coordinate,String name) {
 	   Coordinate=coordinate;
 	   X=coordinate.width;
	   Y=coordinate.height;
 	   Name=name;
 	   Type=Pointtype.Undefined;
 }
    public boolean Roughlyequal(Dimension point) {
    	      boolean result=false;
    	      if(Math.abs(point.width-Coordinate.width)<5&&Math.abs(point.height-Coordinate.height)<5) {
    	    	     result=true;
    	      }
    	      return result;
    	      
    }
    
    public Pointtype getType() {
    	        return Type;
    }
    
    public void setType(Pointtype type) {
    	       Type=type;
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
    public void print() {
    	   System.out.println("Cor "+Coordinate+" X "+X+" Y "+Y+" Type "+Type+" name "+Name);
    }
    
    @Override
    public boolean equals(Object obj) {
    	    if(obj instanceof Point) {
    	    	   if(this.getX()==((Point)obj).getX()&&this.getY()==((Point)obj).getY()&&this.getType()==((Point)obj).getType()&&this.getName().equals(((Point)obj).getName())) {
    	    		   return true;
    	    	   }
    	    }
    	    	return false;
    }
}
