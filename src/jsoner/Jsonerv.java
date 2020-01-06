package jsoner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author VINCENT
 */

public class Jsonerv {
  static String b;
    String com;
    
    public Jsonerv (String a) {
        b=a;
    }
    
    public String getCom() {
        return com;
    }
    
    public void bacaJSON() {
        JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(b);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            com = (String) jsonObject.get("com");
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}