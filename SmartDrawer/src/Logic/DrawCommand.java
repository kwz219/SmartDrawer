package Logic;

import Model.Circle;
import Model.Line;
import Model.Point;
import Model.Triangle;
import View.DrawerPanel;
/**
 * Last Modification Time 2019/03/01
 * @author zwk
 *
 */
public class DrawCommand {
	public static void createTriangle(Point A,Point B,Point C) {	
		Triangle tri=new Triangle(A,B,C);
		DrawerPanel.getDrawer().addTriangle(tri);
	}

	public static void createCircle(Point o,int radius) {	
		Circle cir=new Circle(o,radius);
		DrawerPanel.getDrawer().addCircle(cir);
	}
	
	public static void createPoint(Point A) {
		DrawerPanel.getDrawer().addPoint(A);
	}
	
	public static void createLine(Line l) {
		DrawerPanel.getDrawer().addLine(l);
	}
}
