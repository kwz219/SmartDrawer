package Logic;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;

public class HandWrittingRecognizer {
      private static HandWrittingRecognizer recognizer=new HandWrittingRecognizer();
      public static final String APP_ID = "16031272";
      public static final String API_KEY = "GX3eC9peyMB4wQtd8RubgG78";
      public static final String SECRET_KEY = "f8gO4d142tSPfKfp7ub1T5kEqxxshkOh";
  	  AipOcr client;
  	  HashMap<String, String> options = new HashMap<String, String>();
      
  	  private HandWrittingRecognizer() {
    	      client=new AipOcr(APP_ID,API_KEY,SECRET_KEY);
    	      options.put("recognize_granularity", "big");
      }
  	  
  	  public static  HandWrittingRecognizer getRecognizer() {
  		  return recognizer;
  	  }
  	  
  	  public String getRecognizeResult(String imgpath) {
  		String result="";
  		JSONObject res = client.handwriting(imgpath,options);
  		System.out.println(res.toString(2));
  		if(res.get("words_result")!=null) {
  		result=res.get("words_result").toString().split("\"")[3];
  		}
  		return result;
  	  }
  	  
  	  public String getRecognizeResult(byte[] image) {
  		String result="";
  		JSONObject res = client.handwriting(image,options);
  		System.out.println(res.toString(2));
  		if(res.get("words_result")!=null) {
  		result=res.get("words_result").toString().split("\"")[3];
  		}
  		return result;
  	  }
}
