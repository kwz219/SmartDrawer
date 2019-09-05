package Logic;

import java.util.ArrayList;

import Model.Circle;
import Model.Line;
import Model.Point;
import Model.Triangle;
import View.DrawerPanel.DrawerPanel;
import View.DrawerPanel.GraphsFinder;

/**
 * Last modification time 2019/03/03
 * @author zwk
 * to find a geo with name or check if it exists
 */
public class FindCommand {
	
	   //check if a single point exists
       public static boolean PointExist(String name) {
    	   boolean result =false;
    	   if(GraphsFinder.findPointIndex_byname(name,DrawerPanel.getDrawer().mpointList)!=-1) {
    		   result=true;
    	   }
    	   return result;
       }
       
       //check if the line exists
       public static boolean LineExist(String name) {
    	   boolean result =false;
    	   if(GraphsFinder.findLineIndex_byname(name,DrawerPanel.getDrawer().lineList)!=-1) {
    		   result= true;
    	   }else {
    		   System.out.println("Line "+name+" doesn't exist");
    	   }
    	   return result;
       }
       
       //check if the triangle exists
       public static boolean TriangleExist(String name) {
    	   boolean result =false;
    	   if(GraphsFinder.findTriangleIndex_byname(name,DrawerPanel.getDrawer().triangleList)!=-1) {
    		   result=true;
    	   }
    	   return result;
       }
       
       //check if the circle exists
       public static boolean CircleExist(String name) {
    	   boolean result =false;
    	   if(GraphsFinder.findCircleIndex_byname(name,DrawerPanel.getDrawer().circleList)!=-1) {
    		   result=true;
    	   }
    	   return result;
       }
       
       public static Point getPoint(String name) {
    	      int pindex=GraphsFinder.findPointIndex_byname(name,DrawerPanel.getDrawer().mpointList);
    	      return GraphsFinder.getpoint_byindex(pindex);
       }
       
       public static Line getLine(String name) {
    	      int lindex=GraphsFinder.findLineIndex_byname(name,DrawerPanel.getDrawer().lineList);
    	      return  GraphsFinder.getline_byindex(lindex);
       }
       
       public static Triangle getTriangle(String name) {
    	      int tindex=GraphsFinder.findTriangleIndex_byname(name,DrawerPanel.getDrawer().triangleList);
    	      return  GraphsFinder.gettriangle_byindex(tindex);
       }
       
       public static Circle getCircle(String name) {
    	      int cindex=GraphsFinder.findCircleIndex_byname(name,DrawerPanel.getDrawer().circleList);
    	      return  GraphsFinder.getcircle_byindex(cindex);
       }
       
       public static ArrayList<Point> getTriPoints_byDrawing(){
    	      return DrawerPanel.getDrawer().getTriPoints_byDrawing();
       }
       
       public static Circle getCircle_byDrawing() {
    	      return DrawerPanel.getDrawer().getCircle_byDrawing();
       }
       
       public static Line getLine_byDrawing() {
    	      return DrawerPanel.getDrawer().getLine_byDrawing();
       }
       
       public static Point getPoint_byDrawing() {
    	      return DrawerPanel.getDrawer().getPoint_byDrawing();
       }
       
       //check if the point(all types) named exists
      public static boolean Pointexists(String name) {
    	      return DrawerPanel.getDrawer().PointExists(name);
      }
       
       
}
