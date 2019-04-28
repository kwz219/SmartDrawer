package commandAnalyse;

import java.util.ArrayList;

import com.sun.corba.se.impl.protocol.giopmsgheaders.LocateReplyMessage_1_0;
import com.sun.org.apache.xalan.internal.xsltc.cmdline.getopt.GetOpt;

public class Command {
	private CommandExecute ce=new CommandExecute();
	private String type;
	private int number;
	//type包括删除，新建，交于，垂直，平行，位于，平分，等于，高，中线，平分线，特定角度，直角三角形，等腰直角三角形，等边三角形，等腰三角形，菱形，正方形，平行四边形，矩形，切于
	//除了新建+0.5值，除了新建命令，别的命令都是+1权重，在这里每个命令都会自检
	private ArrayList<CommandGeo> Geolist=new ArrayList<CommandGeo>();
	private ArrayList<CommandPoint> Pointlist=new ArrayList<CommandPoint>();
	private CommandList commandList=CommandList.getInstance();
	public String gettype() {
		return type;
	}
	public void settype(String type) {
		this.type = type;
	}
	public ArrayList<CommandGeo> getGeolist() {
		return Geolist;
	}
	public void setGeolist(ArrayList<CommandGeo> geolist) {
		Geolist = geolist;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public void addGeo(CommandGeo Geo) {
		this.Geolist.add(Geo);
		boolean hasit;
		for(int i=0;i<this.getGeolist().size();i++) {
			switch (this.getGeolist().get(i).getType()) {
			case"点":{
				CommandPoint p=(CommandPoint)this.getGeolist().get(i) ;
				hasit=this.hasThisPoint(p);
				if(hasit==false) {
					Pointlist.add(p);
				}
				break;
			}
			case"直线":{
				CommandLine l=(CommandLine)this.getGeolist().get(i) ;
				CommandPoint startpoint=l.getStartpoint();
				CommandPoint endpoint=l.getEndpoint();
				hasit=this.hasThisPoint(startpoint);
				if(hasit==false) {
					Pointlist.add(startpoint);
				}
				hasit=this.hasThisPoint(endpoint);
				if(hasit==false) {
					Pointlist.add(endpoint);
				}
				break;
			}
			case"三角形":{
				CommandTriangle t=(CommandTriangle)this.getGeolist().get(i);
				CommandPoint p1=t.getVertex1();
				hasit=this.hasThisPoint(p1);
				if(hasit==false) {
					Pointlist.add(p1);
				}
				CommandPoint p2=t.getVertex2();
				hasit=this.hasThisPoint(p2);
				if(hasit==false) {
					Pointlist.add(p2);
				}
				CommandPoint p3=t.getVertex3();
				hasit=this.hasThisPoint(p3);
				if(hasit==false) {
					Pointlist.add(p3);
				}

				break;
			}
			case"圆":{
				CommandCircle c=(CommandCircle)this.getGeolist().get(i) ;
				CommandPoint p=c.getCenter();
				hasit=this.hasThisPoint(p);
				if(hasit==false) {
					Pointlist.add(p);
				}
				break;
			}
			}
		}

	}
	public void print() {
		for(int i=0;i<Geolist.size();i++) {
			System.out.print(Geolist.get(i).getType());
			System.out.println(Geolist.get(i).getName());
		}
		System.out.println(this.type);
	}
	public void addweight(double weight) {
		CommandList cl=CommandList.getInstance();
		System.out.println("need add weight"+Pointlist.size());
		for(int i=0;i<this.Pointlist.size();i++) {
			cl.addWeight(Pointlist.get(i).getName(), weight);
		}
	}

	public void execute() {
		System.out.println(this.type);
		switch (this.type) {
		case "删除":{
			commandList.reverseCommand(this.getGeolist().get(0));
			ce.deleteCommand(this.getGeolist().get(0));
			break;
		}
		case "新建": {
			this.addweight(0.5);
			CommandGeo geo=this.getGeolist().get(0);
			System.out.println("select geo");
			System.out.println(geo.getType());
				switch (geo.getType()) {
				case"点":{
					CommandPoint point=new CommandPoint(geo.getName());
					ce.newPoint(point);break;
				}
				case"直线":{
					CommandLine line=new CommandLine(geo.getName());
					ce.newLine(line);break;
				}
				case"圆":{
					CommandCircle circle=new CommandCircle(geo.getName());
					ce.newCircle(circle);break;
				}
				case"三角形":{
					CommandTriangle triangle=new CommandTriangle(geo.getName());
					ce.newTriangle(triangle);break;
				}
			}
				break;
			}
		case"交于":{
			//线与线有一个焦点
			if(this.Geolist.size()==3) {	
				CommandLine l1=new CommandLine(this.Geolist.get(0).getName());
				CommandLine l2=new CommandLine(this.Geolist.get(1).getName());
				CommandPoint p=new CommandPoint(this.Geolist.get(2).getName());
				l1.getStartpoint().addRelateList(this);
				l1.getEndpoint().addRelateList(this);
				l2.getStartpoint().addRelateList(this);
				l2.getEndpoint().addRelateList(this);
				ce.lineIntersect(l1, l2, p);
			}
			else if(this.Geolist.size()==4) {
				CommandCircle c=new CommandCircle(this.Geolist.get(0).getName());
				CommandLine l1=new CommandLine(this.Geolist.get(1).getName());
				CommandPoint p1=new CommandPoint(this.Geolist.get(2).getName());
				CommandPoint p2=new CommandPoint(this.Geolist.get(3).getName());
				l1.getStartpoint().addRelateList(this);
				l1.getEndpoint().addRelateList(this);
				ce.CircleIntersectLineAt2Points(c, l1, p1, p2);
			}	

			break;
		}
		case"垂直":{
			this.addweight(1);
			CommandLine l1=new CommandLine(this.Geolist.get(0).getName());
			CommandLine l2=new CommandLine(this.Geolist.get(1).getName());
			CommandPoint p=new CommandPoint(this.Geolist.get(2).getName());
			l1.addPoint(p);
			l2.addPoint(p);
			ce.lineVertical(l1, l2, p);
			break;
		}
		case"等于":{
			this.addweight(1);
			CommandLine l1=new CommandLine(this.Geolist.get(0).getName());
			CommandLine l2=new CommandLine(this.Geolist.get(1).getName());
			ce.lineEqual(l1, l2);
			for(CommandPoint commandPoint:this.Pointlist) {
				commandPoint.rerun();
			}
			break;
		}
		case"平行":{
			this.addweight(1);
			CommandLine l1=new CommandLine(this.Geolist.get(0).getName());
			CommandLine l2=new CommandLine(this.Geolist.get(1).getName());
			ce.lineParallel(l1, l2);
			commandList.reRun(l1.getStartpoint().getName());
			commandList.reRun(l2.getStartpoint().getName());
			commandList.reRun(l1.getEndpoint().getName());
			commandList.reRun(l2.getEndpoint().getName());

			break;
			
	
		}
		case"切于":{
			this.addweight(1);
			System.out.println(Geolist);
			CommandCircle c=new CommandCircle(this.Geolist.get(0).getName());
			CommandLine l=new CommandLine(this.Geolist.get(1).getName());
			CommandPoint p=new CommandPoint(this.Geolist.get(2).getName());
			ce.tangent(c, l, p);
			commandList.reRun(l.getStartpoint().getName());
			commandList.reRun(l.getEndpoint().getName());
			break;
		}
		}

	}
	public boolean hasThisPoint(CommandPoint p) {
		boolean hasit=false;
		for (int i=0;i<Pointlist.size();i++) {
			if(p.getName().equals(Pointlist.get(i).getName())) {
				hasit=true;
			}
		}
		return hasit;
	}
}

