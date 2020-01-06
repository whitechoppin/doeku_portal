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

import nontrx.LOGIN_BRW;
import nontrx.LOGIN_IDN;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Json_Login_BRW {
  public String Records,getid,getcode,getip,getpass,getcounter,hasil,getmd5;
  
    public Json_Login_BRW(String Records,String ipku) throws Exception {
        this.Records=Records;
        
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getid = jsonObject.get("idbrw").toString();
            getcode = jsonObject.get("code").toString();
            getip = jsonObject.get("ipku").toString();
            getpass = jsonObject.get("pass").toString();
            getmd5 = jsonObject.get("pass_dp").toString();
            
            getcounter= jsonObject.get("counter").toString();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        System.out.println("Resp : "+getcode);
        
    LOGIN_BRW lg = new LOGIN_BRW(getid, getpass,getmd5,getcode,getip,getcounter);
    hasil = lg.getAns();
        System.out.println("Hasil : "+hasil);
  }
    
  public String getRes() {
      return hasil;
  }
  
}