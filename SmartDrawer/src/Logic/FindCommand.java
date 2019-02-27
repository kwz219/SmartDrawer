package Logic;

import View.DrawerPanel;

/**
 * Last modification time 2019/02/27
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
}
