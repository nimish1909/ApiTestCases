package Utilities;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader {
/*
 * This method is for converting the response into json object
 */
	public JSONObject convertJsonObject(String response) throws ParseException {
		
	    Object obj = new JSONParser().parse(response); 
	      
	    // type casting obj to JSONObject 
	    JSONObject jsonObject = (JSONObject) obj;
		return jsonObject;
	}
	
	
}
