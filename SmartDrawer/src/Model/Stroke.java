package Model;

import java.awt.Dimension;
import java.util.ArrayList;
/**
 * 
 * @author zwk
 * 笔画的抽象模型，用于保持绘制的持续性
 */
public class Stroke {
         private ArrayList<Dimension> plist=new ArrayList<Dimension>();

		public ArrayList<Dimension> getPlist() {
			return plist;
		}

		public void setPlist(ArrayList<Dimension> plist) {
			this.plist = plist;
		}
        
		public void addPoint(int x,int y) {
			this.plist.add(new Dimension(x,y));
		}
}
