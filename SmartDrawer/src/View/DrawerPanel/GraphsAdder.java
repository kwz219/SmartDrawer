package View.DrawerPanel;

import Logic.ListTraverseHelper;
import Logic.PointsFittingHelper.Pointtype;
import Model.Circle;
import Model.Line;
import Model.Point;
import Model.PointIndex;
import Model.Triangle;
/**
 * 
 * @author zwk
 *  该类作用是在底层各图形列表中添加图形并绘制
 */
public class GraphsAdder {
	public static void addTriangle(Triangle triangle) {
		triangle.printPoints();
		//先通过遍历PointMap检查是否已经有同名的点
		Point v1=PointMapDealer.fixPoints_byname(triangle.getVertex1().getName());
		Point v2=PointMapDealer.fixPoints_byname(triangle.getVertex2().getName());
		Point v3=PointMapDealer.fixPoints_byname(triangle.getVertex3().getName());
		
		//若有同名点，直接将坐标设置为之前同名点的坐标
		if(v1!=null) {
			
			triangle.getVertex1().setCoordinate(v1.getCoordinate());
			triangle.getVertex1().setType(Pointtype.Triangleend);
		}
		if(v2!=null) {
			
			triangle.getVertex2().setCoordinate(v2.getCoordinate());
			triangle.getVertex2().setType(Pointtype.Triangleend);
		}
		if(v3!=null) {
			
			triangle.getVertex3().setCoordinate(v3.getCoordinate());
			triangle.getVertex3().setType(Pointtype.Triangleend);
		}
		DrawerPanel.getDrawer().triangleList.add(triangle);
		PointIndex pi1=new PointIndex(1,Pointtype.Triangleend,triangle.getTriname());
		pi1.setListIndex(DrawerPanel.getDrawer().triangleList.size()-1);
		PointIndex pi2=new PointIndex(2,Pointtype.Triangleend,triangle.getTriname());
		pi2.setListIndex(DrawerPanel.getDrawer().triangleList.size()-1);
		PointIndex pi3=new PointIndex(3,Pointtype.Triangleend,triangle.getTriname());
		pi3.setListIndex(DrawerPanel.getDrawer().triangleList.size()-1);
		PointMapDealer.getPointMap().put(triangle.getVertex1(),pi1 );
		PointMapDealer.getPointMap().put(triangle.getVertex2(),pi2 );
		PointMapDealer.getPointMap().put(triangle.getVertex3(),pi3 );
		DrawerPanel.getDrawer().nofitpointList.removeAll(DrawerPanel.getDrawer().nofitpointList.subList(DrawerPanel.getDrawer().pointLptr, DrawerPanel.getDrawer().nofitpointList.size()));
		DrawerPanel.getDrawer().repaint();
	}
	
	public static void addCircle(Circle circle) {
		Point center=PointMapDealer.fixPoints_byname(circle.getCenter().getName());
		if(center!=null) {
			center.setType(Pointtype.Circlecenter);
			circle.setCenter(center);
		}
		
		DrawerPanel.getDrawer().circleList.add(circle);
		PointIndex pio=new PointIndex(0,Pointtype.Circlecenter,circle.getCenter().getName());
		pio.setListIndex(DrawerPanel.getDrawer().circleList.size()-1);
		PointMapDealer.getPointMap().put(circle.getCenter(),pio);
		
		DrawerPanel.getDrawer().nofitpointList.removeAll(DrawerPanel.getDrawer().nofitpointList.subList(DrawerPanel.getDrawer().pointLptr, DrawerPanel.getDrawer().nofitpointList.size()));
		DrawerPanel.getDrawer().repaint();
	}

	public static void addPoint(Point A) {
		int index=DrawerPanel.getDrawer().mpointList.indexOf(A);
		if(index==-1) {
			DrawerPanel.getDrawer().mpointList.add(A);
		     A.setType(Pointtype.Singlepoint);
		     ListTraverseHelper.delete_byCorType(PointMapDealer.getPointMap(),Pointtype.Singlepoint,A.getCoordinate());
		     PointIndex pi=new PointIndex(0,Pointtype.Singlepoint,A.getName());
		     pi.setListIndex(DrawerPanel.getDrawer().mpointList.size()-1);
		     PointMapDealer.getPointMap().put(A, pi);
		     DrawerPanel.getDrawer().nofitpointList.removeAll(DrawerPanel.getDrawer().nofitpointList.subList(DrawerPanel.getDrawer().pointLptr,DrawerPanel.getDrawer(). nofitpointList.size()));
		}else {
			DrawerPanel.getDrawer().mpointList.get(index).setName(A.getName());
			PointIndex pi= new PointIndex(0,Pointtype.Singlepoint,A.getName());
			pi.setListIndex(index);
			PointMapDealer.getPointMap().put(A, pi);
		}
		DrawerPanel.getDrawer().repaint();
	}
	
	//注：添加直线的情况较为复杂，因为直线图形是需要自动规整的
	public static void addLine(Line l) {
		Point ps=new Point(l.getStartpoint().getCoordinate(),Pointtype.Lineend);
		Point pe=new Point(l.getEndpoint().getCoordinate(),Pointtype.Lineend);
		
		
		Line prel=new Line(ps,pe);
		int index=DrawerPanel.getDrawer().lineList.indexOf(prel);
		Point snps=PointMapDealer.fixPoints_byname(l.getStartpoint().getName());//康康初始端点是否有重名的
		Point snpe=PointMapDealer.fixPoints_byname(l.getEndpoint().getName());//康康结束端点是否有重名的
		ps.setName(l.getStartpoint().getName());
		pe.setName(l.getEndpoint().getName());
		//检查直线列表中是否已经有相同坐标的两个直线端点
		if(index!=-1) {
			ListTraverseHelper.delete_byCorType(PointMapDealer.getPointMap(), Pointtype.Lineend,ps.getCoordinate());
			ListTraverseHelper.delete_byCorType(PointMapDealer.getPointMap(), Pointtype.Lineend,pe.getCoordinate());
			
			DrawerPanel.getDrawer().lineList.get(index).getStartpoint().setName(l.getStartpoint().getName());
			DrawerPanel.getDrawer().lineList.get(index).getEndpoint().setName(l.getEndpoint().getName());
			if(snps!=null) {
				DrawerPanel.getDrawer().lineList.get(index).getStartpoint().setCoordinate(snps.getCoordinate());
				ps.setCoordinate(snps.getCoordinate());
				
			}
			if(snpe!=null) {
				DrawerPanel.getDrawer().lineList.get(index).getEndpoint().setCoordinate(snpe.getCoordinate());
				pe.setCoordinate(snpe.getCoordinate());
			}
			    PointIndex pi1=new PointIndex(1,Pointtype.Lineend,l.getLinename());
			    pi1.setListIndex(index);
			    PointIndex pi2=new PointIndex(2,Pointtype.Lineend,l.getLinename());
			    pi2.setListIndex(index);
			    PointMapDealer.getPointMap().put(pe, pi2);
			    PointMapDealer.getPointMap().put(ps, pi1);
				
			
			
			
		}else {
		    if(snps!=null) {
		    		   snps.setType(Pointtype.Lineend);
			    	   l.setPointbyindex(1, snps);
		    	    	  
		    }
		    if(snpe!=null) {
		    	   snpe.setType(Pointtype.Lineend);
		    	   l.setPointbyindex(2, snpe);
		    }
		    DrawerPanel.getDrawer().lineList.add(l);
		    
		    DrawerPanel.getDrawer().nofitpointList.removeAll(DrawerPanel.getDrawer().nofitpointList.subList(DrawerPanel.getDrawer().pointLptr,DrawerPanel.getDrawer(). nofitpointList.size()));
		    PointIndex pi1=new PointIndex(1,Pointtype.Lineend,l.getLinename());
		    pi1.setListIndex(DrawerPanel.getDrawer().lineList.size()-1);
		    PointIndex pi2=new PointIndex(1,Pointtype.Lineend,l.getLinename());
		    pi2.setListIndex(DrawerPanel.getDrawer().lineList.size()-1);
		    PointMapDealer.getPointMap().put(ps, pi1);
		    PointMapDealer.getPointMap().put(pe, pi2);
		}
		   
		DrawerPanel.getDrawer().repaint();
	}
}
