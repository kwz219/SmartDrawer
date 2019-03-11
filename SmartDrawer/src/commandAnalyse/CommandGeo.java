
package commandAnalyse;

public class CommandGeo {
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
		this.changeWeight=this.changeWeight+changeWeight;
	}
}
