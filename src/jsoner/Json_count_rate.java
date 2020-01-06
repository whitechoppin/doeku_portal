package jsoner;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author VINCENT
 */

import nontrx.ACC_IDN;
import nontrx.COUNT_RATE;
import nontrx.LOGIN_IDN;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Json_count_rate {
  public String Records,getid,getcounter,hasil;
  
    public Json_count_rate(String Records) throws Exception {
        this.Records=Records;
        
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getid = jsonObject.get("IP").toString();
            getcounter= jsonObject.get("counter").toString();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        COUNT_RATE ai = new COUNT_RATE(getcounter);
        hasil = ai.getAns();
  }
    
  public String getRes() {
      return hasil;
  } 
}