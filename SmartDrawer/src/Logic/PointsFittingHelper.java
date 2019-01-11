package Logic;

import java.awt.Dimension;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * Last Modification 2019/01/11
 * @author zwk
 * use points coordinates to judge what type of graphs the drawing is
 */
public class PointsFittingHelper {
	public static enum Graphicstype{
		Point,Line,Triangle,Circle,Quadrangle
	}
	public static enum Pointtype{
	    Lineend,Circlecenter,Triangleend,Quadrangleend
	}
   
	public static Graphicstype PointsFit(ArrayList<Dimension> plist) {
		boolean tristub=true;
		boolean circlestub=true;
		if(Isapoint(plist)) {
			return Graphicstype.Point;
		}else if(Islinear(plist)) {
			return Graphicstype.Line;
		}else if(Istriangle(tristub)) {
			return Graphicstype.Triangle;
		}else if(Iscircle(circlestub)) {
			return Graphicstype.Circle;
		}
		return null;
	}
	
	//judge if the drawing is similar to a point
	public static  boolean Isapoint(ArrayList<Dimension> plist) {
		boolean result=false;
		double horizontal_error=5.00;
		double vertical_error=5.00;
		double maxnum=15;
		if(plist.size()!=0) {
		Dimension startpoint=plist.get(0);
		Dimension endpoint=plist.get(plist.size()-1);
		if(Math.abs(startpoint.getWidth()-endpoint.getWidth())<horizontal_error&&Math.abs(startpoint.getHeight()-endpoint.getHeight())<horizontal_error&&plist.size()<=maxnum) {
			result=true;
		}
		}
		return result;
	}
	//judge if the points fitting a line
	public static boolean Islinear(ArrayList<Dimension> plist) {
		boolean result=true;
		double horizontal_error=5.00;
		double vertical_error=5.00;
		double distance_error=5.00;
		double minaccuracy=0.55;
		//suppose the equation of the line is Ax+By+C=0;
		if(plist.size()!=0) {
		Dimension startpoint=plist.get(0);
		Dimension endpoint=plist.get(plist.size()-1);
		double A;
		double B;
		double C;
		
		if(Math.abs(startpoint.width-endpoint.width)<horizontal_error) {
			//if the line is vertical
			A=1.00;
			B=0;
			C=-startpoint.getWidth();
		}else if(Math.abs(startpoint.height-endpoint.height)<vertical_error) {
			//if the line is horizontal
			A=0;
			B=1.00;
			C=-endpoint.getHeight();
		}else {
			A=1.00;
			B=(endpoint.getWidth()-startpoint.getWidth())/(startpoint.getHeight()-endpoint.getHeight());
			C=-startpoint.getWidth()-B*startpoint.getHeight();
		}
		
		Iterator it=plist.iterator();
		int correctnum=0;//the points is within the error range
		while(it.hasNext()) {
			Dimension point=(Dimension)it.next();
			if(Math.abs(Getdistance(A,B,C,point.width,point.height))<distance_error) {
				correctnum++;
			}
		}
		double accuracy=(1.00*correctnum)/(plist.size()*1.00);
		System.out.println("accuracy: "+accuracy+"correctnum"+correctnum+" "+plist.size());
	
		if(accuracy<minaccuracy) {
			return false;
		}
		}
		
		return result;
	}
	   //calculate the distance of a point to a line
	   public static double Getdistance(double A,double B,double C,int x,int y) {
		   double result=0.00;
		   result=Math.abs(A*x+B*y+C)/Math.sqrt(A*A+B*B);
		   return result;
	   }
	  
	   //judge if the drawing is a triangle
	   public static  boolean Istriangle(boolean result) {
		   return result;
	   }
	   
	   //judge if the drawing is a circle
	   public static boolean Iscircle(boolean result) {
		   return result;
	   }
	  public static void main(String[] args) {
		  /*ArrayList<Dimension> plist=new ArrayList<Dimension>();
		  plist.add(new Dimension(1,1));
		  plist.add(new Dimension(2,1));
		  plist.add(new Dimension(3,1));
		  plist.add(new Dimension(3,2));
		  plist.add(new Dimension(10,21));
		  System.out.println(Islinear(plist));*/
	  }
}
