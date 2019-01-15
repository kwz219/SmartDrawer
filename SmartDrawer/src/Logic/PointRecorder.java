package Logic;

import java.awt.Dimension;
import java.util.ArrayList;
/**
 * Last Modification 2019/01/15
 * @author zwk
 * this class is used to record points of a drawing(not all points)
 * you can get leftmostpoint,rightmostpoint,highestpoint,lowestpoint of a Drawing
 */
public class PointRecorder {
         public int Count=1;
         public ArrayList<Dimension> Plist=new ArrayList<Dimension>();//point list
         private Dimension Leftmost_point=new Dimension(10000,0);
         private Dimension Rightmost_point=new Dimension(-1,0);
         private Dimension Highest_point=new Dimension(0,-1);
         private Dimension Lowest_point=new Dimension(0,10000);
         
         //add a point into the recorder
         public void Add(int x,int y) {
        	    Count++;
        	    if(Count%2==0) {
        	    Plist.add(new Dimension(x,y));
        	    this.Ifhigher(x, y);
        	    this.Iflower(x, y);
        	    this.Ifmoreleft(x, y);
        	    this.Ifmoreright(x, y);
        	    }
         }
         
         //empty the recorder
         public void Empty() {
        	    Plist.clear();
        	    Leftmost_point.setSize(10000,0);
        	    Rightmost_point.setSize(-1, 0);
        	    Highest_point.setSize(0, 10000);
        	    Lowest_point.setSize(0, -1);
        	    Count=0;
         }
         
        
         //judge if current point is more left,if so, change leftmost point
         public void Ifmoreleft(int x,int y) {
        	   if(Leftmost_point.width>x) {
        		   Leftmost_point.setSize(x, y);
        	   }
         }
         
        //judge if current point is more right,if so, change rightmost point
         public void Ifmoreright(int x,int y) {
        	   if(Rightmost_point.width<x) {
        		   Rightmost_point.setSize(x,y);
        	   }
        	 
         }
        //judge if current point is higher,if so, change highest point
         public void Ifhigher(int x,int y) {
        	   if(Highest_point.height<y) {
        		   Highest_point.setSize(x, y);
        	   }
         }
         
         //judge if current point is lower,if so, change lowest point
         public void Iflower(int x,int y) {
        	   if(Lowest_point.height>y) {
        		   Lowest_point.setSize(x, y);
        	   }
         }

        //get point list
         public ArrayList<Dimension> getPlist(){
        	 return Plist;
         }
        //get left-most point
		public Dimension getLeftmost_point() {
			return Leftmost_point;
		}

        //get rightmost point
		public Dimension getRightmost_point() {
			return Rightmost_point;
		}

        //get highest point
		public Dimension getHighest_point() {
			return Highest_point;
		}

        //get lowest point
		public Dimension getLowest_point() {
			return Lowest_point;
		}

		
         
}
