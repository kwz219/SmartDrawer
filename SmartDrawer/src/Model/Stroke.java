package Model;

import java.awt.Dimension;
import java.util.ArrayList;

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
