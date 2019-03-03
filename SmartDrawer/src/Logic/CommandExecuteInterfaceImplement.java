package Logic;

import java.util.ArrayList;

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
		
		DrawCommand.createLine(l);
	}

	@Override
	public void changeLine(Line l) {
		
		String lname=l.getStartpoint().getName()+l.getEndpoint().getName();
		if(FindCommand.LineExist(lname)) {
			AjustCommand.Del_line(lname);
		}else {
			System.out.println("Line "+lname+" doesn't exist");
		}
		DrawCommand.createLine(l);
	}

	@Override
	public void changeCircle(Circle o) {
		
		String cname=o.getCenter().getName();
		if(FindCommand.CircleExist(cname)) {
			AjustCommand.Del_circle(cname);
		}else {
			System.out.println("Circle "+cname+" doesn't exist");
		}
		DrawCommand.createCircle(o);
	}

	@Override
	public void changePoint(Point p) {
		
		String pname=p.getName();
		if(FindCommand.PointExist(pname)) {
			AjustCommand.Del_point(pname);
		}else {
			System.out.println("Point "+pname+" doesn't exist");
		}
		DrawCommand.createPoint(p);
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

}
