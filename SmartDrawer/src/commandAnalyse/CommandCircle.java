package commandAnalyse;

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
		public CommandPoint getCenter() {
			return Center;
		}
		public int getRadius() {
			return Radius;
		}
        
        
}
