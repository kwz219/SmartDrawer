package Logic;

import org.json.JSONObject;

import com.baidu.aip.speech.AipSpeech;
/**
 * 
 * @author zwk
 * 
 */
public class VoiceRecognizer {
	private static VoiceRecognizer vr=new VoiceRecognizer();
	private static final String APP_ID = "17167019";
    private static final String API_KEY = "FU9sWMosBBOET0NW3yBpe8Cz";
    private static final String SECRET_KEY = "rIpNvTehPkLCiVfUzFUmM8TxGyqLP042";
    AipSpeech client;
    private VoiceRecognizer() {
    	 AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
    }
    
    public String getResultBypath(String voicepath) {
    	JSONObject res = client.asr(voicepath, "wav", 16000, null);
    	String finalres=res.get("result").toString().split("\"")[1];
    	return finalres.substring(0,finalres.length()-1);
    }
    
    public String getResultBybytes(byte[] data) {
    	JSONObject res = client.asr(data, "pcm", 16000, null);
    	String finalres=res.get("result").toString().split("\"")[1];
    	return finalres.substring(0,finalres.length()-1);
    }
}
