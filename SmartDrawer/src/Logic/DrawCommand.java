package Logic;

import Model.Circle;
import Model.Line;
import Model.Point;
import Model.Triangle;
import View.DrawerPanel.DrawerPanel;
import View.DrawerPanel.GraphsAdder;
import View.DrawerPanel.PointMapDealer;
/**
 * Last Modification Time 2019/03/03
 * @author zwk
 *
 */
public class DrawCommand {
	public static void createTriangle(Triangle T) {	
		
		GraphsAdder.addTriangle(T);
	}

	public static void createCircle(Circle cir) {	
		
		GraphsAdder.addCircle(cir);
	}
	
	public static void createPoint(Point A) {
		System.out.println("A: "+A.getCoordinate());
		GraphsAdder.addPoint(A);
	}
	
	public static void createLine(Line l) {
		GraphsAdder.addLine(l);
	}
}
