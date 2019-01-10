package Model;

import java.awt.Dimension;
/**
 * 
 * @author zwk
 * model of graph circle;
 */
public class Circle {
        private Dimension Center;
        private int Radius;
        public Circle(Dimension center,int radius) {
        	 Center=center;
        	 Radius=radius;
        }
		public Dimension getCenter() {
			return Center;
		}
		public int getRadius() {
			return Radius;
		}
        
        
}
