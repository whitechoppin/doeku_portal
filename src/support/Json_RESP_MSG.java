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
import nontrx.ACC_IDN;
import nontrx.LOGIN_IDN;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Json_RESP_MSG {
  public String Records,getid,getpesan,getuser,getcounter,hasil;
  
    public Json_RESP_MSG(String Records) throws Exception {
        this.Records=Records;
        
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getid = jsonObject.get("id_pesan").toString();
            getuser = jsonObject.get("pengirim").toString();
            getpesan = jsonObject.get("pesan").toString();
            getcounter= jsonObject.get("counter").toString();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        RESP_MSG ai = new RESP_MSG(getid,getuser,getpesan,getcounter);
        hasil = ai.getAns();
  }
    
  public String getRes() {
      return hasil;
  } 
}