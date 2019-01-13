package Model;

import Logic.PointsFittingHelper.Pointtype;
/**
 * Last modification time 2019/01/13
 * @author zwk
 *
 */
public class PointIndex {
       public Pointtype Type;
       public int Listindex;
       public int Innerindex;
       
       public PointIndex(int listindex,int innerindex,Pointtype type) {
    	        Type=type;
    	        Listindex=listindex;
    	        Innerindex=innerindex;
       }
}
