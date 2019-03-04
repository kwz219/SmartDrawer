package Logic;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;

import Model.Point;
import Model.PointIndex;
/**
 * Last modification time 2019/01/13
 * @author zwk
 *
 */
public class ListTraverseHelper {
	
	
	//judge which point is closely clicked
     public static PointIndex FindnearestPoint(Dimension clickpoint,HashMap<Point,PointIndex> map) {
    	    for(Point p:map.keySet()) {
    	    	    if(p.Roughlyequal(clickpoint)) {
    	    	    	return map.get(p);
    	    	    }
    	    }
    	    
		return null;
    	 
     }
     
     //find all points by the same name
     public static ArrayList<Point> Findallpoints(String name,HashMap<Point,PointIndex> map){
    	     ArrayList<Point> pointsList=new ArrayList<Point>();
    	     for(Point p:map.keySet()) {
    	    	   if(p.getName()!=null)
    	    		   if(p.getName().equals(name)) {
    	    		   pointsList.add(p);
    	    		   }
    	     }
    	     return pointsList;
     }
     
     //find a point by name
     public static Point Findapoint(String name,HashMap<Point,PointIndex> map){
    	      for(Point p:map.keySet()) {
    	    	    if(name.equals(p.getName())) {
    	    	    	   return p;
    	    	    }
    	      }
    	      return null;
     }
}
