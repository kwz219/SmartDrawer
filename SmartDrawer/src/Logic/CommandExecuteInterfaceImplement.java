package Logic;

import Model.Circle;
import Model.Line;
import Model.Point;
import Model.Triangle;
import commandAnalyse.CommandExucuteInterface;
/**
 * Last modification time 2019/03/01
 * @author zwk
 *
 */
public class CommandExecuteInterfaceImplement implements CommandExucuteInterface{

	@Override
	public void createCircle(Point o, int radius) {
		// TODO Auto-generated method stub
		DrawCommand.createCircle(o, radius);
	}

	@Override
	public void createTriangle(Point A, Point B, Point C) {
		// TODO Auto-generated method stub
		DrawCommand.createTriangle(A, B, C);
	}

	@Override
	public void creadPoint(Point A) {
		// TODO Auto-generated method stub
		DrawCommand.createPoint(A);
	}

	@Override
	public void createLine(Line l) {
		// TODO Auto-generated method stub
		DrawCommand.createLine(l);
	}

	@Override
	public void changeLine(Line l) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		String cname=o.getCenter().getName();
		if(FindCommand.CircleExist(cname)) {
			AjustCommand.Del_circle(cname);
		}else {
			System.out.println("Circle "+cname+" doesn't exist");
		}
		DrawCommand.createCircle(o.getCenter(),o.getRadius());
	}

	@Override
	public void changePoint(Point p) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		
		if(FindCommand.PointExist(name)) {
			return FindCommand.getPoint(name);
		}
		return null;
	}

	@Override
	public Circle getCirlce(String name) {
		// TODO Auto-generated method stub
		if(FindCommand.CircleExist(name)) {
			return FindCommand.getCircle(name);
		}
		return null;
	}

	@Override
	public Triangle getTriangle(String name) {
		// TODO Auto-generated method stub
		if(FindCommand.TriangleExist(name)) {
			return FindCommand.getTriangle(name);
		}
		return null;
	}

}
