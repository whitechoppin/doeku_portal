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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Json_CEK_BRW {
  public String Records,getid,getpass,getcounter,hasil;
  
    public Json_CEK_BRW(String Records) throws Exception {
        this.Records=Records;
        
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getid = jsonObject.get("idbrw").toString();
            getcounter= jsonObject.get("counter").toString();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        CEK_BRW lg = new CEK_BRW(getid,getcounter);
        hasil = lg.getAns();
  }
    
  public String getRes() {
      return hasil;
  }
}