package Logic;

import View.DrawerPanel;
/**
 * Last modification time 2019/02/28
 * @author zwk
 *
 */
public class AjustCommand {
	
       public static void Del_point(String name) {
    	         int index=DrawerPanel.getDrawer().findPointIndex_byname(name);
    	         if(index!=-1) {
    	        	    DrawerPanel.getDrawer().delpoint_byindex(index);
    	         }
       }
       
       public static void Del_line(String name) {
    	         int index=DrawerPanel.getDrawer().findLineIndex_byname(name);
    	         if(index!=-1) {
    	        	    DrawerPanel.getDrawer().delline_byindex(index);
    	         }
       }
       
       public static void Del_triangle(String name) {
    	         int index=DrawerPanel.getDrawer().findTriangleIndex_byname(name);
    	         if(index!=-1) {
    	        	   DrawerPanel.getDrawer().deltriangle_byindex(index);
    	         }
       }
       
       public static void Del_circle(String name) {
    	         int index=DrawerPanel.getDrawer().findCircleIndex_byname(name);
    	         if(index!=-1) {
    	        	   DrawerPanel.getDrawer().delcircle_byindex(index);
    	         }
       }
       
       
}

