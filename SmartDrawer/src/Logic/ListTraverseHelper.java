package Logic;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import Logic.PointsFittingHelper.Pointtype;
import Model.Line;
import Model.Point;
import Model.PointIndex;
/**
 * Last modification time 2019/01/13
 * @author zwk
 * 该类作用是遍历PointMap的各个方法，可以和底层的PointMapDelear类合并
 */
public class ListTraverseHelper {
	
	
	//judge which point is closely clicked
     public static PointIndex FindnearestPointIndex(Dimension clickpoint,HashMap<Point,PointIndex> map) {
    	    for(Point p:map.keySet()) {
    	    	    if(p.Roughlyequal(clickpoint)) {
    	    	    	return map.get(p);
    	    	    }
    	    }
    	    
		return null;
    	 
     }
     public static Point FindnearestPoint(Dimension clickpoint,HashMap<Point,PointIndex> map) {
 	    for(Point p:map.keySet()) {
 	    	    if(p.Roughlyequal(clickpoint)) {
 	    	    	return p;
 	    	    }
 	    }
 	    
		return null;
 	 
  }
     
     //find all points by the same name
     public static ArrayList<Point> Findallpoints(String name,HashMap<Point,PointIndex> map){
    	     ArrayList<Point> pointsList=new ArrayList<Point>();
    	     for(Point p:map.keySet()) {
    	    	   if(p.getName()!=null)
    	    		   if(p.getName().equals(name)) {
    	    		   pointsList.add(p);
    	    		   }
    	     }
    	     return pointsList;
     }
     
     //find a point by name
     public static Point Findapoint(String name,HashMap<Point,PointIndex> map){
    	      for(Point p:map.keySet()) {
    	    	    if(name.equals(p.getName())) {
    	    	    	   return p;
    	    	    }
    	      }
    	      return null;
     }
     //检查该名字的点是否存在
     public static boolean Pointexist(String name,HashMap<Point,PointIndex> map) {
    	      for(Point p:map.keySet()) {
    	    	     if(p.getName().equals(name)) {
    	    	    	    return true;
    	    	     }
    	      }
    	      return false;
     }
     
     //find a point by name,type
     public static Point Findapoint_bynametype(String name,HashMap<Point,PointIndex> map,Pointtype type) {
    	     for(Point p:map.keySet()) {
    	    	    if(name.equals(p.getName())&&p.getType()==type) {
    	    	    	   return p;
    	    	    }
    	     }
    	     return null;
     }
     
     //打印所有点的信息
     public static void printall(HashMap<Point,PointIndex> map) {
    	     for(Point p:map.keySet()) {
    	    	    System.out.println("Point "+p.getName()+" "+p.getCoordinate()+" Index "+map.get(p).Type+" "+map.get(p).BelongedGraph+map.get(p).Innerindex);
    	     }
     }
     
     //根据坐标和点类型来删除点
     public static void delete_byCorType(HashMap<Point,PointIndex> map,Pointtype type,Dimension dm){
    	     for(Point p:map.keySet()) {
    	    	     if(p.getCoordinate().equals(dm)&&map.get(p).Type==type) {
    	    	    	    map.remove(p);
    	    	    	    break;
    	    	     }
    	     }
     }
     
     //得到所有同名同类型的点
     public static ArrayList<PointIndex> get_ByNameType(HashMap<Point,PointIndex> map,String name,Pointtype pt) {
    	       ArrayList<PointIndex> pilist=new ArrayList<PointIndex>();
    	       for(Point p:map.keySet()) {
    	    	       if(p.getName().equals(name)&&p.getType().equals(pt)) {
    	    	    	      pilist.add(map.get(p));
    	    	       }
    	       }
    	       return pilist;
     }
     
     //更新PointMap，将所有同名点的坐标信息给更新
     public static void UpdatePointMap(String name,HashMap<Point,PointIndex> map,Point newp) {
    	        for(Point p:map.keySet()) {
    	        	   if(p.getName().equals(name)) {
    	        		   Point np=p;
    	        		   np.setCoordinate(newp.getCoordinate());
    	        		   PointIndex pi=map.get(p);
    	        		   map.remove(p);
    	        		   map.put(np, pi);
    	        		   
    	        	   }
    	        }
     }
     
    
     //获得所有点的PointIndex
     public static ArrayList<PointIndex> getALLPI(Dimension d,HashMap<Point,PointIndex> map){
    	        ArrayList<PointIndex> pilist=new ArrayList<PointIndex>();
    	        for(Point p:map.keySet()) {
    	        	   if(p.Roughlyequal(d)) {
    	        		   pilist.add(map.get(p));
    	        	   }
    	        }
    	        return pilist;
     }
     //把近似点的坐标都更新为一致坐标
     public static void UpdateCoordinate(Dimension oldd,Dimension newd,HashMap<Point,PointIndex> map) {
    	        Iterator it=map.keySet().iterator();
    	        ArrayList<Point> plist=new ArrayList<Point>();
    	        ArrayList<PointIndex> pilist=new ArrayList<PointIndex>();
    	        while(it.hasNext()) {
    	        	    Point p=(Point)it.next();
    	        	    if(p.Roughlyequal(oldd)) {
    	        	    	   plist.add(p);
    	        	    	   pilist.add(map.get(p));
    	        	    }
    	        	    it.remove();
    	        }
    	        for(int i=0;i<plist.size();i++) {
    	        	    Point point=plist.get(i);
    	        	    point.setCoordinate(newd);
    	        	    map.put(point, pilist.get(i));
    	        }
     }
     
     public static Line FindLine_byname(String sname,String ename,HashMap<Point,PointIndex> map) {
    	       Line line=new Line(new Point(new Dimension(0,0),sname),new Point(new Dimension(0,0),ename));
    	       for(Point p:map.keySet()) {
    	    	       if(p.getName().equals(sname)) {
    	    	    	      line.getStartpoint().setCoordinate(p.getCoordinate());
    	    	       }
    	    	       if(p.getName().equals(ename)) {
    	    	    	      line.getEndpoint().setCoordinate(p.getCoordinate());
    	    	       }
    	       }
    	       return line;
     }
     
}
