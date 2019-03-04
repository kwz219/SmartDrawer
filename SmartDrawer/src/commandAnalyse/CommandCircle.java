package commandAnalyse;

import Model.Circle;

/**
 * 
 * @author zwk
 * model of graph circle;
 */
public class CommandCircle extends CommandGeo{
        private CommandPoint Center;
        private int Radius;
        public CommandCircle(CommandPoint center,int radius) {
        	 Center=center;
        	 Radius=radius;
        	 this.setType("圆");
        	 this.setName(center.getName());
        }
        public CommandCircle(String name) {
        	this.Center=new CommandPoint(0,0,name);
        	this.Radius=0;
       	 	this.setType("圆");
        	this.setName(name);
        }
        public void loadCirlce(Circle c) {
        	CommandPoint center=new CommandPoint();
        	center.loadPoint(c.getCenter());
        	this.Center=center;
        	this.Radius=c.getRadius();
        }
		public void setCenter(CommandPoint center) {
			Center = center;
		}
		public void setRadius(int radius) {
			Radius = radius;
		}
		public CommandPoint getCenter() {
			return Center;
		}
		public int getRadius() {
			return Radius;
		}
        public Circle changeToCircle() {
        	Circle c=new Circle(this.Center.changeToPoint(),this.Radius,null);
        	return c;
        }
        
}
