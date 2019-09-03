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
        public Circle(Point center,int radius) {
        	 Center=center;
        	 Radius=radius;
        	 upperleft=new Dimension(center.getX()-radius,center.getY()-radius);
        }
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
		public void setRadius(int radius) {
			Radius=radius;
			upperleft=new Dimension(Center.getX()-radius,Center.getY()-radius);
			
		}
		public Dimension getUpperleft() {
			return upperleft;
		}
		public void setUpperleft(Dimension upperleft) {
			this.upperleft = upperleft;
		}
		
        public void moveCenter(Dimension d) {
        	    upperleft.setSize(upperleft.width+d.width-Center.getX(),upperleft.height+d.height-Center.getY());
        	    Center.setCoordinate(d);
        	   
        	   
        }
        
        public void setCenter(Point p) {
        	    upperleft.setSize(upperleft.width+p.getX()-Center.getX(),upperleft.height+p.getY()-Center.getY());
        	    Center=p;
        	    
        }
        
}
