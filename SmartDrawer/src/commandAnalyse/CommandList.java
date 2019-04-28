package commandAnalyse;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

public class CommandList {
	 private final static CommandList INSTANCE = new CommandList();
	 private CommandList(){}
	 public static CommandList getInstance(){
	      return INSTANCE;
	 }
	private ArrayList<Command> list=new ArrayList<Command>();
	private ArrayList<CommandPoint> Pointlist=new ArrayList<CommandPoint>();
	public ArrayList<Command> getList() {
		return list;
	}

	public void setList(ArrayList<Command> list) {
		this.list = list;
	}
	public void add(Command com) {
		boolean hasit;
		list.add(com);
		for(int i=0;i<com.getGeolist().size();i++) {
			if(com.gettype().equals("删除")==false) {
			switch (com.getGeolist().get(i).getType()) {
			case"点":{
				CommandPoint p=(CommandPoint)com.getGeolist().get(i) ;
				hasit=this.hasThisPoint(p);
				if(hasit==false) {
					Pointlist.add(p);
				}
				if(com.gettype().equals("交于")) {
					for(CommandPoint commandPoint:Pointlist) {
						if(commandPoint.getName().equals(p.getName())) {
							commandPoint.addRelateList(com);
						}
					}
				}
				break;
			}
			case"直线":{
				CommandLine l=(CommandLine)com.getGeolist().get(i) ;
				CommandPoint startpoint=l.getStartpoint();
				CommandPoint endpoint=l.getEndpoint();
				hasit=this.hasThisPoint(startpoint);
				if(hasit==false) {
					Pointlist.add(startpoint);
					startpoint.addRelateList(com);
				}
				hasit=this.hasThisPoint(endpoint);
				if(com.gettype().equals("交于")) {
					for(CommandPoint commandPoint:Pointlist) {
						if(commandPoint.getName().equals(startpoint.getName())) {
							commandPoint.addRelateList(com);
						}
					}
				}
				if(hasit==false) {
					Pointlist.add(endpoint);
					endpoint.addRelateList(com);
				}
				if(com.gettype().equals("交于")) {
					for(CommandPoint commandPoint:Pointlist) {
						if(commandPoint.getName().equals(endpoint.getName())) {
							commandPoint.addRelateList(com);
						}
					}
				}
				break;
			}
			case"三角形":{
				CommandTriangle t=(CommandTriangle)com.getGeolist().get(i);
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
				CommandCircle c=(CommandCircle)com.getGeolist().get(i) ;
				CommandPoint p=c.getCenter();
				hasit=this.hasThisPoint(p);
				if(hasit==false) {
					Pointlist.add(p);
					p.addRelateList(com);
				}
				if(com.gettype().equals("交于")) {
					for(CommandPoint commandPoint:Pointlist) {
						if(commandPoint.getName().equals(p.getName())) {
							commandPoint.addRelateList(com);
						}
					}
				}
				break;
			}
			}
			}
		}
	}
	public void printAll() {
		for(int i=0;i<list.size();i++) {
			list.get(i).print();
		}
	}
	public void addWeight(String pointname,double weight) {
		for(int i=0;i<Pointlist.size();i++) {
			if(Pointlist.get(i).getName().equals(pointname)) {
				Pointlist.get(i).addChangeWeight(0.5);
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
	public double ChangeWeight(String Pointname) {
		double weight=999;
		for(int i=0;i<Pointlist.size();i++) {
			if(Pointlist.get(i).getName().equals(Pointname)) {
				weight=Pointlist.get(i).getChangeWeight();
			}
		}
		return weight;
	}
	public void reRun(String pointName) {
		System.out.println(Pointlist.size());
		for(CommandPoint commandPoint:Pointlist) {
			if(commandPoint.getName().equals(pointName)) {
				commandPoint.rerun();
			}
		}
	}
	public void reverseCommand(CommandGeo geo) {

		String name=geo.getName();
		System.out.println("要删除的名字是"+name);
		for(int t=0;t<Pointlist.size();t++) {
			String pname=Pointlist.get(t).getName();
			System.out.println(pname);
			for (int i=0;i<name.length();i++) {
				String pointname=name.substring(i, i+1);
				if(pname.equals(pointname)) {
					Pointlist.get(t).addChangeWeight(-0.5);
					if(Pointlist.get(t).getChangeWeight()==0) {
						Pointlist.remove(t);
						t=t-1;
					}
				}
			}
		}	
		System.out.println("现在还有的点数是"+this.Pointlist.size());

	}
}
