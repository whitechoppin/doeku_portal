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

import nontrx.CEK_BRW;
import nontrx.CEK_IDN_I;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Json_CEK_IDN_I {
  public String Records,getid,getpass,getcounter,hasil;
  
    public Json_CEK_IDN_I(String Records) throws Exception {
        this.Records=Records;
        
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
            
            
            System.out.println("ADIT:"+jsonObject);
 
            getid = jsonObject.get("ididn").toString();
            getcounter= jsonObject.get("counter").toString();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        CEK_IDN_I lg = new CEK_IDN_I(getid,getcounter);
        hasil = lg.getAns();
  }
    
  public String getRes() {
      return hasil;
  }
}