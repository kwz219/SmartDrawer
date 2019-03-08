package Logic;

import java.util.ArrayList;

import Logic.PointsFittingHelper.Pointtype;
import Model.Circle;
import Model.Line;
import Model.Point;
import Model.Triangle;
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
		Line ol=FindCommand.getLine(l.getStartpoint().getName()+l.getEndpoint().getName());
		String lname=l.getStartpoint().getName()+l.getEndpoint().getName();
		if(FindCommand.LineExist(lname)) {
			System.out.println("line "+lname+" exists");
			AjustCommand.Del_line(lname);
			DrawCommand.createLine(l);
			if(!ol.getStartpoint().getCoordinate().equals(l.getStartpoint().getCoordinate())) {
			changeALlpoints(l.getStartpoint().getName(),l.getStartpoint());
			}
			if(!ol.getEndpoint().getCoordinate().equals(l.getStartpoint().getCoordinate())) {
			changeALlpoints(l.getEndpoint().getName(), l.getEndpoint());
			}
		}else {
			//System.out.println("Line "+lname+" doesn't exist");
		}
		
	}

	@Override
	public void changeCircle(Circle o) {
		
		String cname=o.getCenter().getName();
		if(FindCommand.CircleExist(cname)) {
			AjustCommand.Del_circle(cname);
			DrawCommand.createCircle(o);
			changeALlpoints(o.getCenter().getName(),o.getCenter());
		}else {
			System.out.println("Circle "+cname+" doesn't exist");
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
		if(FindCommand.TriangleExist(tname)) {
			AjustCommand.Del_triangle(tname);
			DrawCommand.createTriangle(tri);
			this.changeALlpoints(tri.getVertex1().getName(),tri.getVertex1());
			this.changeALlpoints(tri.getVertex2().getName(),tri.getVertex2());
			this.changeALlpoints(tri.getVertex3().getName(),tri.getVertex3());
		}else {
			System.out.println("Triangle "+tname+" doesn't exist");
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

	public void changeALlpoints(String name,Point point) {
		ArrayList<Point> plist=AjustCommand.getAllpoints(name);
		if(plist.size()>1) {
			for(int i=0;i<plist.size();i++) {
				Point p=plist.get(i);
				if(p.getType()==Pointtype.Lineend) {
					AjustCommand.moveLineend(p, point);
				}else if(p.getType()==Pointtype.Triangleend) {
					AjustCommand.moveTriangleend(p, point);
				}else if(p.getType()==Pointtype.Circlecenter) {
					AjustCommand.moveCirclecenteer(p, point);
				}
			}
		}else {
			
		}
	}

	@Override
	public void changeTrangleVertex(Point p) {
		// TODO Auto-generated method stub
		AjustCommand.changeTriangleVertex(p);
	}
	
	public Point fix_samenamePoints(String name,Pointtype type) {
	   Point exp=FindCommand.findpoint_byName(name);
	   if(exp!=null) {
		   exp.setType(type);
		   return exp;
	   }
	   return null;
	   
	}
}
