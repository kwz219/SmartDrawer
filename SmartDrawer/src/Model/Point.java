package Model;

import java.awt.Dimension;

/**
 * Last modification time  2019/01/10
 * @author zwk
 * model of graph point
 */
public class Point {
      private String Name;
      private Dimension Coordinate;
      
    public Point(Dimension coordinate) {
    	   Coordinate=coordinate;
    }
    public Point(Dimension coordinate,String name) {
 	   Coordinate=coordinate;
 	   Name=name;
 }
	public String getName() {
		return Name;
	}
	public Dimension getCoordinate() {
		return Coordinate;
	}
      
}
