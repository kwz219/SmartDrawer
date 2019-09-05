package View.DrawerPanel;
/**
 * 
 * @author zwk
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import Logic.ListTraverseHelper;
import Model.Point;
import Model.PointIndex;

public class PointMapDealer {
       public static  HashMap<Point,PointIndex> getPointMap(){
    	          return DrawerPanel.getDrawer().PointMap;
       }
       
       //在PointMap中找到一个同名点
       public static Point fixPoints_byname(String name) {
      	 Point p=ListTraverseHelper.Findapoint(name,DrawerPanel.getDrawer().PointMap);
      	 if(p!=null) {
      		 return p;
      	 }
      	   return null;
      }
       
       //更新Pointmap中点的信息所有同名点的信息
       public static void  updatePointmap(String name,Point newp) {
  	     Iterator it=DrawerPanel.getDrawer().PointMap.keySet().iterator();
  	     ArrayList<Point> plist=new ArrayList<Point>();
  	     ArrayList<PointIndex> pilist=new ArrayList<PointIndex>();
  	     while(it.hasNext()) {
  	    	       Point p=(Point)it.next();
  	    	       if(p.getName().equals(name)) {
  	         		   System.out.println("update p "+p.getName());
  	         		   Point np=p;
  	         		   np.setCoordinate(newp.getCoordinate());
  	         		   PointIndex pi=DrawerPanel.getDrawer().PointMap.get(p);
  	         		   plist.add(p);
  	         		   pilist.add(pi);
  	         		   it.remove();
  	         	   }
  	     }
  	     for(int i=0;i<plist.size();i++) {
  	    	DrawerPanel.getDrawer().PointMap.put(plist.get(i),pilist.get(i));
  	     }
  	   DrawerPanel.getDrawer().repaint();
  	     
  }
} 
