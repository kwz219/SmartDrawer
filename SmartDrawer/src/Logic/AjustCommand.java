package Logic;

import java.util.ArrayList;

import Model.Point;
import View.DrawerPanel.DrawerPanel;
import View.DrawerPanel.GraphsFinder;
import View.DrawerPanel.GraphsSweeper;
/**
 * Last modification time 2019/02/28
 * @author zwk
 *
 */
public class AjustCommand {
	
       public static void Del_point(String name) {
    	         int index=GraphsFinder.findPointIndex_byname(name,DrawerPanel.getDrawer().mpointList);
    	         if(index!=-1) {
    	        	    GraphsSweeper.delpoint_byindex(index);
    	         }
       }
       
       public static void Del_line(String name) {
    	         int index=GraphsFinder.findLineIndex_byname(name, DrawerPanel.getDrawer().lineList);
    	         if(index!=-1) {
    	        	 GraphsSweeper.delline_byindex(index);
    	         }
       }
       
       public static void Del_triangle(String name) {
    	         int index=GraphsFinder.findTriangleIndex_byname(name, DrawerPanel.getDrawer().triangleList);
    	         if(index!=-1) {
    	        	 GraphsSweeper.deltriangle_byindex(index);
    	         }
       }
       
       public static void Del_circle(String name) {
    	         int index=GraphsFinder.findCircleIndex_byname(name, DrawerPanel.getDrawer().circleList);
    	         if(index!=-1) {
    	        	 GraphsSweeper.delcircle_byindex(index);
    	         }
       }
       
       public static ArrayList<Point> getAllpoints(String name) {
    	         ArrayList<Point> plist=DrawerPanel.getDrawer().getAllpoint_byname(name);
    	         return plist;
       }
       
       public static void changeCircleRadius(int index,int radius) {
    	         DrawerPanel.getDrawer().changeCircleRadius(index, radius);
       }
}

