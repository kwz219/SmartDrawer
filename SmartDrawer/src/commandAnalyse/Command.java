package commandAnalyse;

import java.util.ArrayList;

public class Command {
	private String tpye;
	private int number;
	//type�����½������ڣ���ֱ��ƽ�У�λ�ڣ�ƽ�֣����ڣ��ߣ����ߣ�ƽ���ߣ��ض��Ƕȣ�ֱ�������Σ�����ֱ�������Σ��ȱ������Σ����������Σ����Σ������Σ�ƽ���ı��Σ�����
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
