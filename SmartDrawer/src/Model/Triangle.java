package Model;

import java.awt.Dimension;
/**
 * 
 * @author zwk
 * model of graph triangle
 */
public class Triangle {
       private Point Vertex1;
       private Point Vertex2;
       private Point Vertex3;
       private double Angel1;
       private double Angle2;
       private double Angle3;
       
       public Triangle(Point vertex1,Point vertex2,Point vertex3) {
    	       Vertex1=vertex1;
    	       Vertex2=vertex2;
    	       Vertex3=vertex3;
    	   
       }

	public Point getVertex1() {
		return Vertex1;
	}

	public void setVertex1(Point vertex1) {
		Vertex1 = vertex1;
	}

	public Point getVertex2() {
		return Vertex2;
	}

	public void setVertex2(Point vertex2) {
		Vertex2 = vertex2;
	}

	public Point getVertex3() {
		return Vertex3;
	}

	public void setVertex3(Point vertex3) {
		Vertex3 = vertex3;
	}

	public double getAngel1() {
		return Angel1;
	}

	public void setAngel1(double angel1) {
		Angel1 = angel1;
	}

	public double getAngle2() {
		return Angle2;
	}

	public void setAngle2(double angle2) {
		Angle2 = angle2;
	}

	public double getAngle3() {
		return Angle3;
	}

	public void setAngle3(double angle3) {
		Angle3 = angle3;
	}
	
	public int[] getXpoints() {
		int []xPoints=new int[3];
		xPoints[0]=Vertex1.getX();
		xPoints[1]=Vertex2.getX();
		xPoints[2]=Vertex3.getX();
		return xPoints;
	}
	
	public int[] getYpoints() {
		int []yPoints=new int[3];
		yPoints[0]=Vertex1.getY();
		yPoints[1]=Vertex2.getY();
		yPoints[2]=Vertex3.getY();
		return yPoints;
	}
	
	public Point getPoint_byindex(int index) {
		if(index==1) {
			return Vertex1;
		}else if(index==2) {
			return Vertex2;
		}else if(index==3) {
			return Vertex3;
		}
		return null;
	}
	
	public void setPoint_byindex(int index,Point p) {
		if(index==1) {
			Vertex1=p;
		}else if(index==2) {
			Vertex2=p;
		}else if(index==3) {
			Vertex3=p;
		}
	}
       
}
