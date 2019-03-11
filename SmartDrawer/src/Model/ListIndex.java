package Model;

import Logic.PointsFittingHelper.Pointtype;

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
