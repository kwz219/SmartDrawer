package Logic;

import Model.Circle;
import Model.Point;
import Model.Triangle;
import View.DrawerPanel;

public class DrawCommand {
	public static void createTriangle(Point A,Point B,Point C) {	
		Triangle tri=new Triangle(A,B,C);
		DrawerPanel.getDrawer().addTriangle(tri);
	}

	public static void createCircle(Point o,int radius) {	
		Circle cir=new Circle(o,radius);
		DrawerPanel.getDrawer().addCircle(cir);
	}
}
