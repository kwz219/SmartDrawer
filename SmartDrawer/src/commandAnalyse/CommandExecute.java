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
		Point p=point.changeToPoint();
		cei.creadPoint(p);
		return true;
	}
	boolean newLine(CommandLine line) {
		Line l=line.changeToLine();
		cei.createLine(l);
		return true;
	}
	boolean newCircle(CommandCircle circle) {
		
		Circle c=cei.getCircle_fromDrawing();
		System.out.println("do have a circle");
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
		l1.LoadLine(cei.getLine(c.getName()));

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
				c.setRadius((int)(minDistance+verticalDistance)/2);
				cei.changeCircle(c.changeToCircle());
			}
		}else if(delta==0) {
			
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
		int X=(int) ((l2.getB()-l1.getB())/(l1.getK()-l2.getK()));
		int Y=(int)(l1.getK()*X+l1.getB());
		p.setX(X);
		p.setY(Y);
		Point po=p.changeToPoint();
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
		double k=(-1)/l2.getK();
		double r=Math.atan(k);
		CommandPoint sp=l1.getStartpoint();
		CommandPoint ep=l1.getEndpoint();
		double letfLength=p.getLength(sp);
		double rightLength=p.getLength(ep);
		sp.setX((int)(p.getX()-letfLength*Math.cos(r)));
		sp.setY((int)(p.getY()-letfLength*Math.sin(r)));
		ep.setX((int)(p.getX()+letfLength*Math.cos(r)));
		ep.setY((int)(p.getY()+letfLength*Math.sin(r)));
		l1.setStartpoint(sp);
		l1.setEndpoint(ep);
		cei.changeLine(l1.changeToLine());
		return true;
	}
	/**
	 * 
	 * @param l1 改变这条直线的斜率
	 * @param l2
	 * @return
	 */
	boolean lineParallel (CommandLine l1,CommandLine l2) {
		return false;
	}
	
	
	
}
