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
	
       
}
