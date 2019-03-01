package Logic;

import java.awt.Dimension;
import java.util.ArrayList;

import Logic.PointsFittingHelper.Pointtype;
import Model.Circle;
import Model.Point;
/**
 * Last Modification 2019/03/01
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

		//get three vertexes of a triangle from a similar graph
		public ArrayList<Point> getTriangleVertexes() {
			ArrayList<Point> list=new ArrayList<Point>();
			if(Math.abs(Leftmost_point.height-Rightmost_point.height)<10) {
				if(Math.abs(Highest_point.height-Leftmost_point.height)>25) {
					list.add(new Point(Highest_point,Pointtype.Triangleend));
					list.add(new Point(Leftmost_point,Pointtype.Triangleend));
					list.add(new Point(Rightmost_point,Pointtype.Triangleend));
				}else {
					list.add(new Point(Leftmost_point,Pointtype.Triangleend));
					list.add(new Point(Rightmost_point,Pointtype.Triangleend));
					list.add(new Point(Lowest_point,Pointtype.Triangleend));
					
				}
			}else if((Highest_point.width-Lowest_point.width)<10) {
				if((Rightmost_point.width-Highest_point.width)>25) {
					list.add(new Point(Highest_point,Pointtype.Triangleend));
					list.add(new Point(Rightmost_point,Pointtype.Triangleend));
					list.add(new Point(Lowest_point,Pointtype.Triangleend));
				}else {
					list.add(new Point(Highest_point,Pointtype.Triangleend));
					list.add(new Point(Lowest_point,Pointtype.Triangleend));
					list.add(new Point(Leftmost_point,Pointtype.Triangleend));
				}
			}else {
				if((Math.abs(Highest_point.width-Leftmost_point.width)<5)||Math.abs(Lowest_point.width-Leftmost_point.width)<5) {
					list.add(new Point(Highest_point,Pointtype.Triangleend));
					list.add(new Point(Rightmost_point,Pointtype.Triangleend));
					list.add(new Point(Lowest_point,Pointtype.Triangleend));
				}else if(Math.abs(Rightmost_point.width-Highest_point.width)<5||Math.abs(Rightmost_point.width-Lowest_point.width)<5){
					list.add(new Point(Highest_point,Pointtype.Triangleend));
					list.add(new Point(Lowest_point,Pointtype.Triangleend));
					list.add(new Point(Leftmost_point,Pointtype.Triangleend));
				}
			}
			return list;
		}
		
		// get a circle from a series of nofit points
		public Circle getSimilarCircle() {
			int length=Math.abs(Rightmost_point.width-Leftmost_point.width);
			int height=Math.abs(Highest_point.height-Lowest_point.height);
			int radius=(length+height)/2;
			Point center=new Point(new Dimension(Leftmost_point.width+length/2,Highest_point.height+height/2));
			return new Circle(center,radius);
		}
         
}
