package Logic;
/**
 * 
 * @author zwk
 * not yet used,for further use of time calculation
 */
public class TimerHelper {
	    private Long starttime;
	    private Long endtime;
	    
	    
	    public TimerHelper() {
	    	Long time=System.currentTimeMillis();
	    	starttime=time;
	    	endtime=time;
	    
	    }
	    
	    public boolean Isdrawing() {
	    	  boolean result=true;
	    	  Long tmptime=System.currentTimeMillis();
	    	  if((tmptime-starttime)>1000) {
	    		  result=false;
	    	  }
	    	  return result;
	    }
	    
	    public void Timestart() {
	    	  starttime=System.currentTimeMillis();
	    }
	    
	    public void Reset() {
	    	Long time=System.currentTimeMillis();
	    	starttime=time;
	    	endtime=time;
	    }

		public void setStarttime(Long starttime) {
			this.starttime = starttime;
		}

		public void setEndtime(Long endtime) {
			this.endtime = endtime;
		}
}
