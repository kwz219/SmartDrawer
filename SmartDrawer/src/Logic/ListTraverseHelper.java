package Logic;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;

import Logic.PointsFittingHelper.Pointtype;
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
     
     //find a point by name,type
     public static Point Findapoint_bynametype(String name,HashMap<Point,PointIndex> map,Pointtype type) {
    	     for(Point p:map.keySet()) {
    	    	    if(name.equals(p.getName())&&p.getType()==type) {
    	    	    	   return p;
    	    	    }
    	     }
    	     return null;
     }
     
     public static void printall(HashMap<Point,PointIndex> map) {
    	     for(Point p:map.keySet()) {
    	    	    System.out.println("Point "+p.getName()+" "+p.getCoordinate()+" Index "+map.get(p).Type+" "+map.get(p).Listindex+" "+map.get(p).Innerindex);
    	     }
     }
     
     public static void delete_byCorType(HashMap<Point,PointIndex> map,Pointtype type,Dimension dm){
    	     for(Point p:map.keySet()) {
    	    	     if(p.getCoordinate().equals(dm)&&map.get(p).Type==type) {
    	    	    	    map.remove(p);
    	    	    	    break;
    	    	     }
    	     }
     }
     
     public static ArrayList<PointIndex> get_ByNameType(HashMap<Point,PointIndex> map,String name,Pointtype pt) {
    	       ArrayList<PointIndex> pilist=new ArrayList<PointIndex>();
    	       for(Point p:map.keySet()) {
    	    	       if(p.getName().equals(name)&&p.getType().equals(pt)) {
    	    	    	      pilist.add(map.get(p));
    	    	       }
    	       }
    	       return pilist;
     }
}
