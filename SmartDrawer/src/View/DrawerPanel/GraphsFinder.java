package View.DrawerPanel;

import java.util.ArrayList;

import Model.Circle;
import Model.Line;
import Model.Point;
import Model.Triangle;
/**
 * 
 * @author zwk
 * 本类的作用是通过图形名字找到该图形在对应图形列表中的index位置
 */
public class GraphsFinder {
	
	//找到单独的点图形在单独点列表中的位置
	public static int findPointIndex_byname(String name,ArrayList<Point> mpointList) {
	     int index=-1;
	     for(int i=0;i<mpointList.size();i++) {
	    	     
	    	     if(name.equals(mpointList.get(i).getName())) {
	    	    	    return i;
	    	     }
	     }
	     return index;
}
	//找到圆图形在圆列表中的位置
	public static int findCircleIndex_byname(String name,ArrayList<Circle> circleList) {
	     int index=-1;
    for(int i=0;i<circleList.size();i++) {
   	     
   	     if(name.equals(circleList.get(i).getCenter().getName())) {
   	    	    return i;
   	     }
    }
    return index;
}
	//找到直线图形在直线列表中的位置
	public static int findLineIndex_byname(String name,ArrayList<Line> lineList) {
	     int index=-1;
    for(int i=0;i<lineList.size();i++) {
   	     Point startpoint=lineList.get(i).getStartpoint();
   	     Point endpoint=lineList.get(i).getEndpoint();
   	     String name1=startpoint.getName()+endpoint.getName();
   	     String name2=endpoint.getName()+startpoint.getName();
   	     //System.out.println("name "+name+" name1 "+name1+" name2 "+name2);
   	     if(name.equals(name1)||name.equals(name2)) {
   	    	 //System.out.println("equals");
   	    	 return i;
   	     }
   	     
    }
    return index;
}
	//找到三角形图形在三角形列表中的位置
	public static int findTriangleIndex_byname(String name,ArrayList<Triangle> triangleList) {
	      int index=-1;
	      for(int i=0;i<triangleList.size();i++) {
	    	    Point v1=triangleList.get(i).getVertex1();
	    	    Point v2=triangleList.get(i).getVertex2();
	    	    Point v3=triangleList.get(i).getVertex3();
	    	    String name123=v1.getName()+v2.getName()+v3.getName();
	    	    String name132=v1.getName()+v3.getName()+v2.getName();
	    	    String name213=v2.getName()+v1.getName()+v3.getName();
	    	    String name231=v2.getName()+v3.getName()+v1.getName();
	    	    String name312=v3.getName()+v1.getName()+v3.getName();
	    	    String name321=v3.getName()+v2.getName()+v1.getName();
	    	    if(name.equals(name123)||name.equals(name132)||name.equals(name213)||name.equals(name231)||name.equals(name312)||name.equals(name321)) {
	    	    	   return i;
	    	    }
	      }
	      return index;
}
	//根据图形在列表中的位置得到对应的图形对象
	public static Point getpoint_byindex(int index) {
	      return DrawerPanel.getDrawer().mpointList.get(index);
}

public static Line getline_byindex(int index) {
	      return DrawerPanel.getDrawer().lineList.get(index);
}

public static Triangle gettriangle_byindex(int index) {
	     return DrawerPanel.getDrawer().triangleList.get(index);
}

public static Circle getcircle_byindex(int index) {
	     return DrawerPanel.getDrawer().circleList.get(index);
}
}
