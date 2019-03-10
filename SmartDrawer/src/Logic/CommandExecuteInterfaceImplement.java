package Logic;

import java.util.ArrayList;

import Logic.PointsFittingHelper.Pointtype;
import Model.Circle;
import Model.Line;
import Model.Point;
import Model.Triangle;
import View.DrawerPanel;
import commandAnalyse.CommandExucuteInterface;
/**
 * Last modification time 2019/03/03
 * @author zwk
 * the implement of commandAnalyse.CommandExecuteInterface
 */
public class CommandExecuteInterfaceImplement implements CommandExucuteInterface{

	@Override
	public void createCircle(Circle o) {
		
		DrawCommand.createCircle(o);
	}

	@Override
	public void createTriangle(Triangle T) {
		
		DrawCommand.createTriangle(T);
	}

	@Override
	public void creadPoint(Point A) {
		
		DrawCommand.createPoint(A);
	}

	@Override
	public void createLine(Line l) {
		/*Point sp=this.fix_samenamePoints(l.getStartpoint().getName(),Pointtype.Lineend);
		Point ep=this.fix_samenamePoints(l.getEndpoint().getName(),Pointtype.Lineend);
		if(sp!=null) {
			l.setPointbyindex(1,sp);
		}
		if(ep!=null) {
			l.setPointbyindex(2, ep);
		}*/
		DrawCommand.createLine(l);
	}

	@Override
	public void changeLine(Line l) {
		Line ol=FindCommand.getLine(l.getLinename());
		String lname=l.getLinename();
		String changep = null;
		if(!ol.getStartpoint().getCoordinate().equals(l.getStartpoint().getCoordinate())&&ol.getEndpoint().getCoordinate().equals(l.getEndpoint().getCoordinate())) {
			System.out.println("startpoint changed");
			this.changeAllpoints_byname(l.getStartpoint().getName(),l.getStartpoint());
			DrawerPanel.getDrawer().updatePointmap(l.getStartpoint().getName(),l.getStartpoint());
		}else if(ol.getStartpoint().getCoordinate().equals(l.getStartpoint().getCoordinate())&&!ol.getEndpoint().getCoordinate().equals(l.getEndpoint().getCoordinate())) {
			System.out.println("endpoint changed");
			this.changeAllpoints_byname(l.getEndpoint().getName(),l.getEndpoint());
			DrawerPanel.getDrawer().updatePointmap(l.getEndpoint().getName(),l.getEndpoint());
		}else if(!ol.getStartpoint().getCoordinate().equals(l.getStartpoint().getCoordinate())&&!ol.getEndpoint().getCoordinate().equals(l.getEndpoint().getCoordinate())) {
			System.out.println("all changed");
			this.changeAllpoints_byname(l.getStartpoint().getName(),l.getStartpoint());
			DrawerPanel.getDrawer().updatePointmap(l.getStartpoint().getName(),l.getStartpoint());
			this.changeAllpoints_byname(l.getEndpoint().getName(),l.getEndpoint());
			DrawerPanel.getDrawer().updatePointmap(l.getEndpoint().getName(),l.getEndpoint());
		}else {
			
		}
		/*if(FindCommand.LineExist(lname)) {
			System.out.println("line "+lname+" exists");
			AjustCommand.Del_line(lname);
			DrawCommand.createLine(l);
			
		}
		if(!ol.getStartpoint().getCoordinate().equals(l.getStartpoint().getCoordinate())) {
			System.out.println("startpoint changed");
			changep=l.getStartpoint().getName();
			//this.changeeffect_line(changep,l.getP_byname(changep));
			//this.changeALlpoints(changep, l.getP_byname(changep));
			
		}
		if(!ol.getEndpoint().getCoordinate().equals(l.getEndpoint().getCoordinate())) {
			System.out.println("endpoint changed");
			changep=l.getEndpoint().getName();
			//this.changeALlpoints(changep, l.getP_byname(changep));
		}*/
		
		
	}

	@Override
	public void changeCircle(Circle o) {
		
		String cname=o.getCenter().getName();
		int index=DrawerPanel.getDrawer().findCircleIndex_byname(cname);
		if(index!=-1) {
			Circle oldo=DrawerPanel.getDrawer().getcircle_byindex(index);
			if(!o.getCenter().getCoordinate().equals(oldo.getCenter().getCoordinate())) {
				System.out.println("center changed");
				this.changeAllNamedPoint(o.getCenter());
				
				
			}
			if(o.getRadius()!=oldo.getRadius()) {
				System.out.println("Radius changed");
				AjustCommand.changeCircleRadius(index, o.getRadius());
				
			}
			
			DrawerPanel.getDrawer().updatePointmap(cname, o.getCenter());
		}
		
		
	}

	@Override
	public void changePoint(Point p) {
		
		String pname=p.getName();
		if(FindCommand.PointExist(pname)) {
			AjustCommand.Del_point(pname);
			DrawCommand.createPoint(p);
		}else {
			System.out.println("Point "+pname+" doesn't exist");
		}
		
	}

	@Override
	public Point getPoint(String name) {
		
		
		if(FindCommand.PointExist(name)) {
			return FindCommand.getPoint(name);
		}
		return null;
	}

	@Override
	public Circle getCirlce(String name) {
		
		if(FindCommand.CircleExist(name)) {
			return FindCommand.getCircle(name);
		}
		return null;
	}

	@Override
	public Triangle getTriangle(String name) {
		
		if(FindCommand.TriangleExist(name)) {
			return FindCommand.getTriangle(name);
		}
		return null;
	}

	@Override
	public ArrayList<Point> getTriangleVertexes_fromDrawing() {
		// TODO Auto-generated method stub
		return FindCommand.getTriPoints_byDrawing();
	}

	@Override
	public Circle getCircle_fromDrawing() {
		// TODO Auto-generated method stub
		return FindCommand.getCircle_byDrawing();
	}

	@Override
	public void changeTriangle(Triangle tri) {
		// TODO Auto-generated method stub
		String tname=tri.getVertex1().getName()+tri.getVertex2().getName()+tri.getVertex3().getName();
		int index=DrawerPanel.getDrawer().findTriangleIndex_byname(tname);
		if(index!=-1) {
			Triangle oldtri=DrawerPanel.getDrawer().gettriangle_byindex(index);
			if(!oldtri.getVertex1().getCoordinate().equals(tri.getVertex1().getCoordinate())) {
				this.changeAllNamedPoint(tri.getVertex1());
				DrawerPanel.getDrawer().updatePointmap(tname, tri.getVertex1());
			}
			if(!oldtri.getVertex2().getCoordinate().equals(tri.getVertex2().getCoordinate())) {
				this.changeAllNamedPoint(tri.getVertex2());
				DrawerPanel.getDrawer().updatePointmap(tname, tri.getVertex2());
			}
			if(!oldtri.getVertex3().getCoordinate().equals(tri.getVertex3().getCoordinate())) {
				this.changeAllNamedPoint(tri.getVertex3());
				DrawerPanel.getDrawer().updatePointmap(tname, tri.getVertex3());
			}
			
		}
	}

	@Override
	public Line getLine(String name) {
		// TODO Auto-generated method stub
		if(FindCommand.LineExist(name)) {
			return FindCommand.getLine(name);
		}
		return null;
	}

	@Override
	public Line getLine_fromDrawing() {
		// TODO Auto-generated method stub
		return FindCommand.getLine_byDrawing();
	}

	@Override
	public Point getPoint_fromDrawing() {
		// TODO Auto-generated method stub
		return FindCommand.getPoint_byDrawing();
	}

	
	
	public void changeeffect_line(String name,Point point) {
		ArrayList<Line> linelist=DrawerPanel.getDrawer().getLines_containspname(name);
		for(int i=0;i<linelist.size();i++) {
			   Line l=linelist.get(i);
			   if(!l.getStartpoint().getCoordinate().equals(point.getCoordinate())) {
				   l.getStartpoint().setCoordinate(point.getCoordinate());
			   }else if(!l.getEndpoint().getCoordinate().equals(point.getCoordinate())) {
				   l.getEndpoint().setCoordinate(point.getCoordinate());
			   }
			   AjustCommand.Del_line(l.getStartpoint().getName()+l.getEndpoint().getName());
			   System.out.println("del "+l.getStartpoint().getName()+l.getEndpoint().getName());
			   DrawCommand.createLine(l);
		}
	}

	@Override
	public void changeTrangleVertex(Point p) {
		// TODO Auto-generated method stub
		//
	}
	
	
	
	public void changeAllpoints_byname(String name,Point p) {
		DrawerPanel.getDrawer().changeAllpointsofline_byname(name, p);
		DrawerPanel.getDrawer().changeAllpointsoftriangle_byname(name, p);
		DrawerPanel.getDrawer().changeAllpointsofcircle_byname(name, p);
	}
	
	

	@Override
	public Point changeAllNamedPoint(Point p) {
		// TODO Auto-generated method stub
		if(FindCommand.Pointexists(p.getName())) {
			this.changeAllpoints_byname(p.getName(), p);
			return p;
		}
		return null;
	}
}
