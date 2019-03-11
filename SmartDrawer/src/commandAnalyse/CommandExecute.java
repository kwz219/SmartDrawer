package commandAnalyse;

import java.util.ArrayList;

import Logic.CommandExecuteInterfaceImplement;
import Logic.DrawCommand;
import Model.Circle;
import Model.Line;
import Model.Point;
import Model.Triangle;

//这个类用来执行两个图形之间的交互命令
public class CommandExecute {
	private CommandExucuteInterface cei= new CommandExecuteInterfaceImplement();
	boolean newPoint (CommandPoint point) {
		Point p=cei.getPoint_fromDrawing();
		p.setName(point.getName());
		cei.creadPoint(p);
		return true;
	}
	boolean newLine(CommandLine line) {
		Line l=cei.getLine_fromDrawing();
		l.getStartpoint().setName(line.getStartpoint().getName());
		l.getEndpoint().setName(line.getEndpoint().getName());
		cei.createLine(l);
		System.out.println(line.getName());
		System.out.println(cei.getLine(line.getName()));
		return true;
	}
	boolean newCircle(CommandCircle circle) {
		
		Circle c=cei.getCircle_fromDrawing();
		c.getCenter().setName(circle.getName());
		cei.createCircle(c);
		return true;
	}
	boolean newTriangle(CommandTriangle tri) {
		ArrayList<Point> points=cei.getTriangleVertexes_fromDrawing();	
		System.out.println(points.size());
		points.get(0).setName(tri.getVertex1().getName());
		points.get(1).setName(tri.getVertex2().getName());
		points.get(2).setName(tri.getVertex3().getName());
		Triangle triangle=new Triangle(points.get(0),points.get(1),points.get(2));
		cei.createTriangle(triangle);
		return true;
	}
	/**
	 * 
	 * @param c 圆
	 * @param l1 直线
	 * @param p1 第一个交点，命令输入应该已经给这个点名字了
	 * @param p2 第二个交点
	 * @return 返回这两个交点现在的坐标
	 */
	ArrayList<CommandPoint> CircleIntersectLineAt2Points(CommandCircle c,CommandLine l1,CommandPoint p1,CommandPoint p2) {
		c.loadCirlce(cei.getCirlce(c.getName()));
		l1.LoadLine(cei.getLine(l1.getName()));
		System.out.println("come to circle");
		ArrayList<CommandPoint> points=new ArrayList<CommandPoint>();
		double k=l1.getK();//直线斜率
		double b=l1.getB();//直线截距
		double x0=c.getCenter().getX();//圆心x
		double y0=c.getCenter().getY();//圆心y
		double r=c.getRadius();//圆半径
		double verticalDistance=c.getCenter().getDistance(l1);//圆心到这个直线的距离
		double startDistance=c.getCenter().getLength(l1.getStartpoint());
		double endDistance=c.getCenter().getLength(l1.getEndpoint());
		double minDistance=startDistance;
		if(startDistance>endDistance) {
			minDistance=endDistance;
		}
		double a0=Math.pow(k, 2)+1;
		double b0=2*(b*k-y0*k-x0);
		double c0=x0*x0+(b-y0)*(b-y0)-r*r;
		double delta=b0*b0-4*a0*c0;
		if(delta>0) {
			double x1=(((Math.pow(delta, 0.5))-b0)/(2*a0));
			double x2=((-(Math.pow(delta, 0.5))-b0)/(2*a0));
			if(l1.getStartpoint().getX()<x1&&x1<l1.getEndpoint().getX()&&l1.getStartpoint().getX()<x2&&x2<l1.getEndpoint().getX()) {
				//这里是图上的圆和直线已经有两个交点了，这里只是设置名字
				System.out.println("只是增加了新的点哦");
				p1.setX((int)(x1));
				p1.setY(l1.CalculateY(p1.getX()));
				p2.setX((int)(x2));
				p2.setY(l1.CalculateY(p2.getX()));
				cei.creadPoint(p1.changeToPoint());
				cei.creadPoint(p2.changeToPoint());
				points.add(p1);
				points.add(p2);
				return points;
			}
			else {
				System.out.println("delta>0 and changed");
				r=(minDistance+verticalDistance)/2;
				c.setRadius((int)(minDistance+verticalDistance)/2);
				cei.changeCircle(c.changeToCircle());
				a0=Math.pow(k, 2)+1;
				b0=2*(b*k-y0*k-x0);
				c0=x0*x0+(b-y0)*(b-y0)-r*r;
				delta=b0*b0-4*a0*c0;
				x1=(((Math.pow(delta, 0.5))-b0)/(2*a0));
				x2=((-(Math.pow(delta, 0.5))-b0)/(2*a0));
				p1.setX((int)(x1));
				p1.setY(l1.CalculateY(p1.getX()));
				p2.setX((int)(x2));
				p2.setY(l1.CalculateY(p2.getX()));
				p1.PrintSelf();
				p1.PrintSelf();
				cei.creadPoint(p1.changeToPoint());
				cei.creadPoint(p2.changeToPoint());
				points.add(p1);
				points.add(p2);
			}
		}else if(delta==0) {
			
		}
		else if(delta<0) {
			System.out.println("delta<0");
			c.setRadius((int)(minDistance+verticalDistance)/2);
			r=(minDistance+verticalDistance)/2;
			cei.changeCircle(c.changeToCircle());
			a0=Math.pow(k, 2)+1;
			b0=2*(b*k-y0*k-x0);
			c0=x0*x0+(b-y0)*(b-y0)-r*r;
			delta=b0*b0-4*a0*c0;
			double x1=(((Math.pow(delta, 0.5))-b0)/(2*a0));
			double x2=((-(Math.pow(delta, 0.5))-b0)/(2*a0));
			System.out.println("x1坐标是"+x1);
			System.out.println("x2坐标是"+x2);

			p1.setX((int)(x1));
			p1.setY(l1.CalculateY(p1.getX()));
			p2.setX((int)(x2));
			p2.setY(l1.CalculateY(p2.getX()));
			p1.PrintSelf();
			p2.PrintSelf();
			cei.creadPoint(p1.changeToPoint());
			cei.creadPoint(p2.changeToPoint());
			points.add(p1);
			points.add(p2);
		}
		
		return null;
	}
	/**
	 * 这里要求这个交点并没有在界面上标注，是第一次出现的点，这里也需要一个创建新的直线的方法
	 * @param l1相交的第一条直线
	 * @param l2相交的第二条直线
	 * @param p相交的交点，这个交点应该已经起好了名字
	 * @return 返回相交的点p
	 */
	CommandPoint lineIntersect(CommandLine l1,CommandLine l2,CommandPoint p) {
		//应该要检查这个点是否出现过，这里先默认没有这个点还没有，所以在代码的最后创建这个点
		l1.LoadLine(cei.getLine(l1.getName()));
		l2.LoadLine(cei.getLine(l2.getName()));
		System.out.println(l1.getK());
		System.out.println(l1.getB());
		System.out.println(l2.getK());
		System.out.println(l2.getB());
		double X= ((l2.getB()-l1.getB())/(l1.getK()-l2.getK()));
		double Y= (l1.getK()*X+l1.getB());
		p.setX(X);
		p.setY(Y);
		Point po=p.changeToPoint();
		System.out.println(po.getX());
		System.out.println(po.getY());
		cei.creadPoint(po);
		return p;
	}
	/**
	 * 这个方法可以扩大化为两条直线的夹角度数，现在垂直为特殊的九十度
	 * @param l1垂直中会被调整的直线,
	 * @param l2垂直中不会动的直线
	 * @param p他们的交点，如果没有就标注出来，垂直结束后应该保持这个交点不变，l1的长度不变
	 * @return
	 */
	boolean lineVertical(CommandLine l1,CommandLine l2,CommandPoint p) {
		p=this.lineIntersect(l1,l2,p);//先相交一下
		System.out.println("come to vertical");
		l1.LoadLine(cei.getLine(l1.getName()));
		l2.LoadLine(cei.getLine(l2.getName()));
		double k=(-1)/l2.getK();
		double r=Math.atan(k);
		CommandPoint sp=l1.getStartpoint();
		CommandPoint ep=l1.getEndpoint();
		double letfLength=p.getLength(sp);
		double rightLength=p.getLength(ep);
		sp.setX((int)(p.getX()-letfLength*Math.cos(r)));
		sp.setY((int)(p.getY()-letfLength*Math.sin(r)));
		ep.setX((int)(p.getX()+rightLength*Math.cos(r)));
		ep.setY((int)(p.getY()+rightLength*Math.sin(r)));
		l1.setStartpoint(sp);
		l1.setEndpoint(ep);
		System.out.println("end reading");
		cei.changeLine(l1.changeToLine());
//		cei.changeAllNamedPoint(sp.changeToPoint());
//		cei.changeAllNamedPoint(ep.changeToPoint());
		System.out.println("change finish");

		return true;
	}
	/**
	 * 
	 * @param l1 改变这条直线的斜率
	 * @param l2 第二条直线不变化
	 * @return
	 */
	boolean lineParallel (CommandLine l1,CommandLine l2) {
		double k=l2.getK();
		
		return false;
	}
	/**
	 * 
	 * @param l1 改变这条直线的长度，且如果命令输入为AB,那么即改变B点位置而不改变A点位置，且AB的斜率和截距不变
	 * @param l2
	 * @return
	 */
	boolean lineEqual(CommandLine l1,CommandLine l2) {
		l1.LoadLine(cei.getLine(l1.getName()));
		l2.LoadLine(cei.getLine(l2.getName()));
		CommandPoint sp=l1.getStartpoint();
		CommandPoint ep=l1.getEndpoint();
		double length=l2.getLength();
		double k=l1.getK();
		double r=Math.atan(k);
		System.out.println(k);
		System.out.println(Math.cos(r));
		System.out.println(Math.sin(r));
		if(sp.getX()<=ep.getX()) {
			System.out.println("点A在左边");
			System.out.println(sp.getX());
				ep.setX(sp.getX()+length*Math.cos(r));
				ep.setY(sp.getY()+length*Math.sin(r));
				sp.PrintSelf();
				ep.PrintSelf();
				l1.setEndpoint(ep);
		}
		else {
			System.out.println("点B在左边");
			sp.setX(ep.getX()+length*Math.cos(r));
			sp.setY(ep.getY()+length*Math.sin(r));
			sp.PrintSelf();
			ep.PrintSelf();
			l1.setStartpoint(sp);
		}
		Line l=l1.changeToLine();
		System.out.println(l1.getLength());
		System.out.println(l2.getLength());
		System.out.println("equal");
		cei.changeLine(l);
		return false;
		
	}
	boolean tangent(CommandCircle c,CommandLine l,CommandPoint p) {
		System.out.println("开始相切");
		c.loadCirlce(cei.getCirlce(c.getName()));
		l.LoadLine(cei.getLine(l.getName()));
		CommandPoint changePoint;
		CommandLine standardline;
		if(l.getStartpoint().getChangeWeight()>=l.getEndpoint().getChangeWeight()) {
			changePoint=l.getEndpoint();
			standardline=new CommandLine(l.getStartpoint(),c.getCenter());
		}
		else{
			changePoint=l.getStartpoint();
			standardline=new CommandLine(l.getEndpoint(),c.getCenter());
		}
		double length=standardline.getLength();
		double radius=c.getRadius();
		double k=standardline.getK();
		double standr=Math.atan(k);
		double addedR=Math.asin(radius/length);
		double liner=Math.atan(l.getK());
		double finalr=0;
		double linelength=l.getLength();
		if(liner>standr) {
			finalr=standr+addedR;

		}
		else{
			finalr=standr-addedR;
		}
		double tangentPointLength=Math.pow(length*length-radius*radius, 0.5);
		if(standardline.getStartpoint().getX()>c.getCenter().getX()) {
			p.setX(standardline.getStartpoint().getX()-tangentPointLength*Math.cos(finalr));
			p.setY(standardline.getStartpoint().getY()-tangentPointLength*Math.sin(finalr));
			changePoint.setX(standardline.getStartpoint().getX()-linelength*Math.cos(finalr));
			changePoint.setY(standardline.getStartpoint().getY()-linelength*Math.sin(finalr));
		}
		else {
			p.setX(standardline.getStartpoint().getX()+tangentPointLength*Math.cos(finalr));
			p.setY(standardline.getStartpoint().getY()+tangentPointLength*Math.sin(finalr));
			changePoint.setX(standardline.getStartpoint().getX()+linelength*Math.cos(finalr));
			changePoint.setY(standardline.getStartpoint().getY()+linelength*Math.sin(finalr));
		}

		if(l.getStartpoint().getChangeWeight()>=l.getEndpoint().getChangeWeight()) {
			l.setEndpoint(changePoint);
		}
		else{
			l.setStartpoint(changePoint);
		}
		c.getCenter().PrintSelf();
		System.out.println(c.getRadius());
		p.PrintSelf();
		System.out.println("这是直线"+l.getName());
		l.getStartpoint().PrintSelf();
		l.getEndpoint().PrintSelf();
		cei.creadPoint(p.changeToPoint());
		cei.changeAllNamedPoint(changePoint.changeToPoint());
	return true;	
	}
	
	
}
