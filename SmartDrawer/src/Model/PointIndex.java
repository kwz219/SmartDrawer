package Model;

import Logic.PointsFittingHelper.Pointtype;
/**
 * Last modification time 2019/01/13
 * @author zwk
 *
 */
public class PointIndex {
       public Pointtype Type;
       public String BelongedGraph;
       public int Innerindex;
       private int Listindex;
       
       public PointIndex(int innerindex,Pointtype type,String Belongedname) {
    	        Type=type;
    	        BelongedGraph=Belongedname;
    	        Innerindex=innerindex;
       }
       public void  setListIndex(int index) {
    	     Listindex=index;
       }
       public int getListIndex() {
    	       return Listindex;
       }
}
