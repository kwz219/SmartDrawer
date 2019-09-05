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
/**
 * 
 * @author zwk
 * what does PointMap do？
 *   它是DrawerPanel的一个成员变量，键值对为<Point,PointIndex>。
 *   存储的是所有“点”的信息，这里的点是指端点，包括三角形端点、直线端点、圆心等
 * 为什么要存储这些信息？
 *   一开始的构想是随着图形类型和数量的增多，对各个图形的查找往往需要遍历各个图形列表，效率不高，
 *   而各个图形最终要的信息是端点信息，因此将所有的这些端点和端点对应的图形列表中的位置存储到这个
 *   HashMap中提高效率，对于负责图形情况例如同名端点(端点A既是直线的端点又是某个三角形的端点)
 *   的查找效率会大幅提高
 * 缺点：
 *   要频繁更新该PointMap，所以某些方法实际上未应用该类
 */
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
