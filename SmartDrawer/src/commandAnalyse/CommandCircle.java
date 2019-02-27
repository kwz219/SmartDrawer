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
        	 this.setType("circle");
        	 this.setName(center.getName());
        }
		public CommandPoint getCenter() {
			return Center;
		}
		public int getRadius() {
			return Radius;
		}
        
        
}
