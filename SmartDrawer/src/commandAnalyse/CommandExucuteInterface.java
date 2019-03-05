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
	public void changeTriangle(Triangle tri);//3.3新增加的方法
	public void changeTrangleVertex(Point p);//传入改变后三角形顶点
	
	public Point getPoint(String name);
	public Circle getCirlce(String name);
	public Triangle getTriangle(String name);
	public Line getLine(String name);//3.3新增加的方法
	
	public ArrayList<Point> getTriangleVertexes_fromDrawing();// zwk added，get 3 points after drawing a triangle
	public Circle getCircle_fromDrawing();//zwk added,find a circle after drawing a circle
	public Line getLine_fromDrawing();//find a line after drawing a line;
	public Point getPoint_fromDrawing();//find a point after drawing a point;

}
