package Model;

import java.awt.Dimension;
/**
 * 
 * @author zwk
 * model of graph circle;
 */
public class Circle {
        private Point Center;
        private int Radius;
        public Circle(Point center,int radius) {
        	 Center=center;
        	 Radius=radius;
        }
		public Point getCenter() {
			return Center;
		}
		public int getRadius() {
			return Radius;
		}
        
        
}
