package commandAnalyse;
import java.util.ArrayList;

public class CommandList {
	private ArrayList<Command> list=new ArrayList<Command>();
	public ArrayList<Command> getList() {
		return list;
	}

	public void setList(ArrayList<Command> list) {
		this.list = list;
	}
	public void add(Command com) {
		list.add(com);
	}
	public void printAll() {
		for(int i=0;i<list.size();i++) {
			list.get(i).print();
		}
	}
}
