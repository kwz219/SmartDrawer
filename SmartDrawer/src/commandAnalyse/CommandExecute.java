package commandAnalyse;

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
		Point  o=circle.getCenter().changeToPoint();
		Circle c=new Circle(o,0,null);
		System.out.println("do have a circle");
		cei.createCircle(c);
		return true;
	}
	boolean newTriangle(CommandTriangle tri) {
		Point A=tri.getVertex1().changeToPoint();
		Point B=tri.getVertex2().changeToPoint();
		Point C=tri.getVertex3().changeToPoint();
		cei.createTriangle(A, B, C);
		return true;
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
