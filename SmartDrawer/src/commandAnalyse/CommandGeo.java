
package commandAnalyse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CommandGeo {
	private Set<Command> relatedList=new HashSet<Command>();//和这个图形相关的命令集，每次这个图形改变之后，都要重新运行这个命令集，暂时只加入相交的命令
	private String name;
	private String type;//circle,line,point,triangle;
	private double changeWeight;//利用这个来评价某个点是否应该改变
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getChangeWeight() {
		return changeWeight;
	}
	public void setChangeWeight(double changeWeight) {
		this.changeWeight = changeWeight;
	}
	public void addChangeWeight(double changeWeight) {
		this.changeWeight+=changeWeight;
		System.out.println(this.name+"点权重是"+this.changeWeight);
	}
	public void rerun() {
		System.out.println("the size is"+ relatedList.size());
		for(Command command:relatedList) {
			if(command.gettype().equals("交于")) {
				command.execute();
			}
		}
	}
	public void addRelateList(Command command) {
		relatedList.add(command);
	}
}
