package commandAnalyse;
//�����Ӧ��ֻ��Ψһʵ��
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
}
