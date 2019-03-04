package commandAnalyse;

import java.util.ArrayList;

public class Command {
	private CommandExecute ce=new CommandExecute();
	private String type;
	private int number;
	//type包括新建，交于，垂直，平行，位于，平分，等于，高，中线，平分线，特定角度，直角三角形，等腰直角三角形，等边三角形，等腰三角形，菱形，正方形，平行四边形，矩形
	private ArrayList<CommandGeo> Geolist=new ArrayList<CommandGeo>();
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
	}
	public void print() {
		for(int i=0;i<Geolist.size();i++) {
			System.out.print(Geolist.get(i).getType());
			System.out.println(Geolist.get(i).getName());
		}
		System.out.println(this.type);
	}
	public void execute() {
		System.out.println("do come to here1");
		System.out.println(this.type);
		switch (this.type) {
		case "新建": {
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
			CommandLine l1=new CommandLine(this.Geolist.get(0).getName());
			CommandLine l2=new CommandLine(this.Geolist.get(1).getName());
			CommandPoint p=new CommandPoint(this.Geolist.get(2).getName());
			ce.lineIntersect(l1, l2, p);
			break;
		}
		case"垂直":{
			CommandLine l1=new CommandLine(this.Geolist.get(0).getName());
			CommandLine l2=new CommandLine(this.Geolist.get(1).getName());
			CommandPoint p=new CommandPoint(this.Geolist.get(2).getName());
			ce.lineVertical(l1, l2, p);
			break;
		}
		}
	}
}
