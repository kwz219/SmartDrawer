package Logic;

import Model.Circle;
import Model.Line;
import Model.Point;
import Model.Triangle;
import View.DrawerPanel;

/**
 * Last modification time 2019/03/01
 * @author zwk
 * to find a geo with name or check if it exists
 */
public class FindCommand {
	
	   //check if the point exists
       public static boolean PointExist(String name) {
    	   boolean result =false;
    	   if(DrawerPanel.getDrawer().findPointIndex_byname(name)!=-1) {
    		   result=true;
    	   }
    	   return result;
       }
       
       //check if the line exists
       public static boolean LineExist(String name) {
    	   boolean result =false;
    	   if(DrawerPanel.getDrawer().findLineIndex_byname(name)!=-1) {
    		   result= true;
    	   }
    	   return result;
       }
       
       //check if the triangle exists
       public static boolean TriangleExist(String name) {
    	   boolean result =false;
    	   if(DrawerPanel.getDrawer().findTriangleIndex_byname(name)!=-1) {
    		   result=true;
    	   }
    	   return result;
       }
       
       //check if the circle exists
       public static boolean CircleExist(String name) {
    	   boolean result =false;
    	   if(DrawerPanel.getDrawer().findCircleIndex_byname(name)!=-1) {
    		   result=true;
    	   }
    	   return result;
       }
       
       public static Point getPoint(String name) {
    	      int pindex=DrawerPanel.getDrawer().findPointIndex_byname(name);
    	      return DrawerPanel.getDrawer().getpoint_byindex(pindex);
       }
       
       public static Line getLine(String name) {
    	      int lindex=DrawerPanel.getDrawer().findLineIndex_byname(name);
    	      return DrawerPanel.getDrawer().getline_byindex(lindex);
       }
       
       public static Triangle getTriangle(String name) {
    	      int tindex=DrawerPanel.getDrawer().findTriangleIndex_byname(name);
    	      return DrawerPanel.getDrawer().gettriangle_byindex(tindex);
       }
       
       public static Circle getCircle(String name) {
    	      int cindex=DrawerPanel.getDrawer().findCircleIndex_byname(name);
    	      return DrawerPanel.getDrawer().getcircle_byindex(cindex);
       }
}
