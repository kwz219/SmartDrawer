package commandAnalyse;

import java.util.ArrayList;

public class Command {
	private String tpye;
	private int number;
	//type包括新建，交于，垂直，平行，位于，平分，等于，高，中线，平分线，特定角度，直角三角形，等腰直角三角形，等边三角形，等腰三角形，菱形，正方形，平行四边形，矩形
	private ArrayList<CommandGeo> Geolist=new ArrayList<CommandGeo>();
	public String getTpye() {
		return tpye;
	}
	public void setTpye(String tpye) {
		this.tpye = tpye;
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
		System.out.println(this.tpye);
	}
}
