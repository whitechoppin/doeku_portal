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

import nontrx.END_MSG;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Json_END_MSG {
  public String Records,getid,getidpesan,getcounter,hasil;
  
    public Json_END_MSG(String Records) throws Exception {
        this.Records=Records;
        
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getid = jsonObject.get("iduser").toString();
            getidpesan = jsonObject.get("idpesan").toString();
            getcounter= jsonObject.get("counter").toString();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        END_MSG lg = new END_MSG(getid,getidpesan,getcounter);
        hasil = lg.getAns();
  }
    
  public String getRes() {
      return hasil;
  }
}