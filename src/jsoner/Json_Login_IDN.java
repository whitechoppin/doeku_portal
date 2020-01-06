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

import nontrx.LOGIN_IDN;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Json_Login_IDN {
  public String Records,getid,getcode,getip,getpass,getcounter,hasil;
  
    public Json_Login_IDN(String Records,String ipku) throws Exception {
        this.Records=Records;
        
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getid = jsonObject.get("id_idn").toString();
            getcode = jsonObject.get("code").toString();
            getip = jsonObject.get("ipku").toString();
            getpass = jsonObject.get("pass").toString();
            getcounter= jsonObject.get("counter").toString();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    LOGIN_IDN lg = new LOGIN_IDN(getid, getpass,getcode,getip,getcounter);
    hasil = lg.getAns();
  }
    
  public String getRes() {
      return hasil;
  } 
}