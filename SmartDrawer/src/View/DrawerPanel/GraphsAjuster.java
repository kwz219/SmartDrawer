package View.DrawerPanel;

import Model.Circle;
import Model.Line;
import Model.Point;
import Model.Triangle;
/**
 * 
 * @author zwk
 * 该类用于调整图形
 */
public class GraphsAjuster {
	//changePoints——byname系列方法是更新所有同名点，因为有这种情况：移动直线AB，但还存在三角形AGF，因此三角形的A端点也需要移过去
	//注：目前该系列方法都采用简单粗暴的遍历，实际可以利用PointMap这个类来优化
	 public static void changeAllpointsofline_byname(String name,Point p) {
	     for(int i=0;i<DrawerPanel.getDrawer().lineList.size();i++) {
	    	     Line l=DrawerPanel.getDrawer().lineList.get(i);
	    	     Point ps=l.getStartpoint();
	    	     Point pe=l.getEndpoint();
	    	     if(ps.getName().equals(name)) {
	    	    	 DrawerPanel.getDrawer().lineList.get(i).getStartpoint().setCoordinate(p.getCoordinate());
	    	     }else if(pe.getName().equals(name)) {
	    	    	 DrawerPanel.getDrawer().lineList.get(i).getEndpoint().setCoordinate(p.getCoordinate());
	    	     }
	     }
}
public static void changeAllpointsoftriangle_byname(String name,Point p) {
     for(int i=0;i<DrawerPanel.getDrawer().triangleList.size();i++) {
    	     Triangle tri=DrawerPanel.getDrawer().triangleList.get(i);
    	     Point v1=tri.getVertex1();
    	     Point v2=tri.getVertex2();
    	     Point v3=tri.getVertex3();
    	     if(v1.getName().equals(name)) {
    	    	 DrawerPanel.getDrawer().triangleList.get(i).getVertex1().setCoordinate(p.getCoordinate());
    	     }else if(v2.getName().equals(name)) {
    	    	 DrawerPanel.getDrawer().triangleList.get(i).getVertex2().setCoordinate(p.getCoordinate());
    	     }else if(v3.getName().equals(name)) {
    	    	 DrawerPanel.getDrawer().triangleList.get(i).getVertex3().setCoordinate(p.getCoordinate());
    	     }
     }
}
public static void changeAllpointsofcircle_byname(String name,Point p) {
	    for(int i=0;i<DrawerPanel.getDrawer().circleList.size();i++) {
	    	   Circle cir=DrawerPanel.getDrawer().circleList.get(i);
	    	   Point center=cir.getCenter();
	    	   if(center.getName().equals(name)) {
	    		   DrawerPanel.getDrawer().circleList.get(i).moveCenter(p.getCoordinate());
	    	   }
	    }
}

//改变圆的半径
public static void changeCircleRadius(int index,int radius) {
    DrawerPanel.getDrawer().circleList.get(index).setRadius(radius);
}
}
