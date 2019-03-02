package commandAnalyse;

import Model.*;

public interface CommandExucuteInterface {
	public void createCircle(Circle o);
	public void createTriangle(Point A,Point B,Point C);
	public void creadPoint(Point A);
	public void createLine(Line l);
	public void changeLine(Line l);
	public void changeCircle(Circle o);
	public void changePoint(Point p);
	public Point getPoint(String name);
	public Circle getCirlce(String name);
	public Triangle getTriangle(String name);
}
