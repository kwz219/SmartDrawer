package commandAnalyse;
import java.util.ArrayList;

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
			switch (com.getGeolist().get(i).getType()) {
			case"点":{
				CommandPoint p=(CommandPoint)com.getGeolist().get(i) ;
				hasit=this.hasThisPoint(p);
				if(hasit==false) {
					Pointlist.add(p);
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
				}
				hasit=this.hasThisPoint(endpoint);
				if(hasit==false) {
					Pointlist.add(endpoint);
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
	public void addWeight(String pointname,double weight) {
		for(int i=0;i<Pointlist.size();i++) {
			if(Pointlist.get(i).getName().equals(pointname)) {
				Pointlist.get(i).addChangeWeight(0.5);
				System.out.println(Pointlist.get(i).getName()+"点现在的权重是"+"");
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
