package commandAnalyse;
import java.util.ArrayList;

public class CommandList {
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
			switch (com.getGeolist().get(i).getType()) {
			case"point":{
				CommandPoint p=(CommandPoint)com.getGeolist().get(i) ;
				hasit=this.hasThisPoint(p);
				if(hasit==false) {
					Pointlist.add(p);
				}
				break;
			}
			case"line":{
				CommandLine l=(CommandLine)com.getGeolist().get(i) ;
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
			case"triangle":{
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
			case"circle":{
				CommandCircle c=(CommandCircle)com.getGeolist().get(i) ;
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
	public void printAll() {
		for(int i=0;i<list.size();i++) {
			list.get(i).print();
		}
	}
	public CommandPoint returnBiggestWeight() {
		CommandPoint cp=Pointlist.get(0);
		for(int i=1;i<Pointlist.size();i++) {
			if(cp.getChangeWeight()<Pointlist.get(i).getChangeWeight()) {
				cp=Pointlist.get(i);
			}
		}
		return cp;//返回最大的权重的点
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
