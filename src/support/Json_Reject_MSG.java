package support;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author VINCENT
 */
import jsoner.*;
import java.io.StringReader;
import nontrx.REJECT_R;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Json_Reject_MSG {
  public String Records,hasil,gettrx,getid,getcounter;
  
    public Json_Reject_MSG(String Records) throws Exception {
        this.Records=Records;
    
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            gettrx = jsonObject.get("id_msg").toString();
            getid = jsonObject.get("id_user").toString();
            getcounter= jsonObject.get("counter").toString();;
           
        } catch (Exception e) {
            e.printStackTrace();
        }    
    
        DEL_MSG cw = new DEL_MSG(gettrx, getid, gettrx);
        hasil = cw.getAns();
 }
    
    public String getRes() {
        return hasil;
    }
}