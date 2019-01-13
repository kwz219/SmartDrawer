package Logic;
import java.awt.Dimension;
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
}
