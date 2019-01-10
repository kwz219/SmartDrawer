package Logic;
import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import Model.Xoy_model;
import View.DrawerPanel;

public class Analytic {
	private int width;
	private int height;
	private Xoy_model xoymod;
	private int UnitLength=10;//���ļ��
	private DrawerPanel d;
	private Graphics2D g;
	public Analytic (DrawerPanel d){
		this.d=d;
		this.width=d.getWidth();
		this.height=d.getHeight();
		this.g=(Graphics2D)d.getGraphics();
		this.g.setStroke(new BasicStroke(2.0f));
		this.g.setColor(Color.BLACK);
	}
	public void DrawXoy(Xoy_model xoymod) {

		g.draw(new Line2D.Double(xoymod.getXstart(), xoymod.getXend()));
		g.draw(new Line2D.Double(xoymod.getYstart(), xoymod.getYend()));
		g.drawString("0",(int)xoymod.getOriginX() + 2,(int)xoymod.getOriginY() +12); //��ԭ������
		for(int i = 1 ;(int)xoymod.getOriginX()+i*UnitLength<xoymod.getXend().getX();i++)
		{
//			g.drawLine((int)xoymod.getOriginX()+i*UnitLength,(int)xoymod.getOriginY()-1,(int)xoymod.getOriginX()+i*UnitLength,(int)xoymod.getOriginY()-6);//��X�������С����
//			g.drawLine((int)xoymod.getOriginX()-i*UnitLength, (int)xoymod.getOriginY()-1, (int)xoymod.getOriginX() - i*UnitLength, (int)xoymod.getOriginY()-6);//��X�Ḻ���С����
////			g.drawString(String.valueOf(i), originX + i*UnitLength-3, originY + 12);  // x����������
////			g.drawString(String.valueOf(i*-1),originX - i*UnitLength-3, originY + 12);  // x�Ḻ������
//			//��Y��
//			g.drawLine((int)xoymod.getOriginX()+1,(int)xoymod.getOriginY()+i*UnitLength,(int)xoymod.getOriginX()+6,(int)xoymod.getOriginY()+i*UnitLength);
//			g.drawLine((int)xoymod.getOriginX()+1,(int)xoymod.getOriginY()-i*UnitLength,(int)xoymod.getOriginX()+6,(int)xoymod.getOriginY()-i*UnitLength);
////			g.drawString(String.valueOf(i), originX-12, originY - i*UnitLength-3);
////			g.drawString(String.valueOf(i*-1), originX-12, originY + i*UnitLength-3);
		}
	}
	public void DrawParabola(Xoy_model xoymod,double a,double b,double c){
		Point2D temp1,temp2;

		double x,y;//���ǿ���������ֵ
		double originX=xoymod.getOriginX();
		double originY=xoymod.getOriginY();
		g.setColor(Color.BLACK);
		x = (xoymod.getXstart().getX()-xoymod.getOriginX())/UnitLength;
		y = a*x*x+b*x+c;
		for(int i = (int)xoymod.getXstart().getX(); i < xoymod.getXend().getX(); i++){
			if ( this.alterY(y ,xoymod)< xoymod.getYend().getY()&&this.alterY(y ,xoymod)> xoymod.getYstart().getY()){
				temp1 = new Point2D.Double(this.alterX(x ,xoymod), this.alterY(y ,xoymod));
				x =x + 1.0/UnitLength;//ǰ��һ������
				y = a*x*x+b*x+c;
				if ( this.alterY(y ,xoymod)< xoymod.getYend().getY()&&this.alterY(y ,xoymod)> xoymod.getYstart().getY()){
					temp2 = new Point2D.Double(this.alterX(x ,xoymod), this.alterY(y  ,xoymod));
					g.draw(new Line2D.Double(temp1, temp2));
				}
			}
			else {
				x =x + 1.0/UnitLength;//ǰ��һ������
				y = a*x*x+b*x+c;
			}
		}
	}
	public void  DrawInverse(Xoy_model xoymod,double k) {
		Point2D temp1,temp2;

		double x,y;//���ǿ���������ֵ
		double originX=xoymod.getOriginX();
		double originY=xoymod.getOriginY();
		g.setColor(Color.BLACK);
		x = (xoymod.getXstart().getX()-xoymod.getOriginX())/UnitLength;
		y = k/x;
		for(int i = (int)xoymod.getXstart().getX(); i < xoymod.getXend().getX(); i++){
			if ( this.alterY(y ,xoymod)< xoymod.getYend().getY()&&this.alterY(y ,xoymod)> xoymod.getYstart().getY()){
				temp1 = new Point2D.Double(this.alterX(x ,xoymod), this.alterY(y ,xoymod));
				x =x + 1.0/UnitLength;//ǰ��һ������
				y = k/x;
				if ( this.alterY(y ,xoymod)< xoymod.getYend().getY()&&this.alterY(y ,xoymod)> xoymod.getYstart().getY()){
					temp2 = new Point2D.Double(this.alterX(x ,xoymod), this.alterY(y  ,xoymod));
					g.draw(new Line2D.Double(temp1, temp2));
				}
			}
			else {
				x =x + 1.0/UnitLength;//ǰ��һ������
				y = k/x;
			}
		}
	}
	public void  Drawlinear(Xoy_model xoymod,double k,double b) {

		Point2D temp1,temp2;
		double x,y;//���ǿ���������ֵ
		double originX=xoymod.getOriginX();
		double originY=xoymod.getOriginY();
		g.setColor(Color.BLACK);
		x = (xoymod.getXstart().getX()-xoymod.getOriginX())/UnitLength;
		y = k*x+b;
		for(int i = (int)xoymod.getXstart().getX(); i < xoymod.getXend().getX(); i++){
			if ( this.alterY(y ,xoymod)< xoymod.getYend().getY()&&this.alterY(y ,xoymod)> xoymod.getYstart().getY()){
				temp1 = new Point2D.Double(this.alterX(x ,xoymod), this.alterY(y ,xoymod));
				x =x + 1.0/UnitLength;//ǰ��һ������
				y = k*x+b;
				if ( this.alterY(y ,xoymod)< xoymod.getYend().getY()&&this.alterY(y ,xoymod)> xoymod.getYstart().getY()){
					temp2 = new Point2D.Double(this.alterX(x ,xoymod), this.alterY(y  ,xoymod));
					g.draw(new Line2D.Double(temp1, temp2));
				}
			}
			else {
				x =x + 1.0/UnitLength;//ǰ��һ������
				y = k*x+b;
			}
		}
	}
	private double alterX(double x,Xoy_model xoymod){
		double originX=xoymod.getOriginX();
		x=x*UnitLength;
		return  x + originX;
	}
	private double alterY(double y,Xoy_model xoymod){
		double originY=xoymod.getOriginY();
		y=y*UnitLength;
		return originY-y;
	}
}
