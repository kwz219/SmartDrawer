package commandAnalyse;

import java.awt.Dimension;

/**
 * Last modification time  2019/01/10
 * @author zwk
 * model of graph point
 */
public class CommandPoint extends CommandGeo{
      private Dimension Coordinate;
      
    public CommandPoint(Dimension coordinate) {
   	 	this.setType("point");
    	   Coordinate=coordinate;
    }
    public CommandPoint(Dimension coordinate,String name) {
 	   Coordinate=coordinate;
 	   this.setName(name);
 	   this.setType("point");
 }
	public Dimension getCoordinate() {
		return Coordinate;
	}
	public void setCoordinate(Dimension coordinate) {
		Coordinate = coordinate;
	}
      
}
