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
        private Dimension upperleft;
        public Circle(Point center,int radius,Dimension Upperleft) {
        	 Center=center;
        	 Radius=radius;
        	 upperleft=Upperleft;
        }
		public Point getCenter() {
			return Center;
		}
		public int getRadius() {
			return Radius;
		}
		public Dimension getUpperleft() {
			return upperleft;
		}
		public void setUpperleft(Dimension upperleft) {
			this.upperleft = upperleft;
		}
		
        
        
}
