package Logic;

import Model.Circle;
import Model.Line;
import Model.Point;
import Model.Triangle;
import View.DrawerPanel;
/**
 * Last Modification Time 2019/03/03
 * @author zwk
 *
 */
public class DrawCommand {
	public static void createTriangle(Triangle T) {	
		
		DrawerPanel.getDrawer().addTriangle(T);
	}

	public static void createCircle(Circle cir) {	
		
		DrawerPanel.getDrawer().addCircle(cir);
	}
	
	public static void createPoint(Point A) {
		System.out.println("A: "+A.getCoordinate());
		DrawerPanel.getDrawer().addPoint(A);
	}
	
	public static void createLine(Line l) {
		DrawerPanel.getDrawer().addLine(l);
	}
}
