package Model;

import Logic.PointsFittingHelper.Pointtype;
/**
 * 
 * @author zwk
 * 该类存储的信息是某个类型的点在对应图形列表中的位置
 */
public class ListIndex {
       private Pointtype type;
       private int index;
    public ListIndex(Pointtype listtype,int listindex) {
    	      type=listtype;
    	      index=listindex;
    }
	public Pointtype getType() {
		return type;
	}
	public void setType(Pointtype type) {
		this.type = type;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
