package commandAnalyse;

import java.util.ArrayList;

import Model.*;

public interface CommandExucuteInterface {
	public void createCircle(Circle o);
	public void createTriangle(Triangle T);
	public void creadPoint(Point A);
	public void createLine(Line l);
	public void changeLine(Line l);
	public void changeCircle(Circle o);
	public void changePoint(Point p);
	public Point getPoint(String name);
	public Circle getCirlce(String name);
	public Triangle getTriangle(String name);
	public ArrayList<Point> getTriangleVertexes_fromDrawing();// zwk added，get 3 points after drawing a triangle
	public Circle getCircle_fromDrawing();//zwk added,find a circle after drawing a circle
}
