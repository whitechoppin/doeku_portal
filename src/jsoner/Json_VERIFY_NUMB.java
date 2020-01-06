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
import nontrx.ACC_PINJAM;
import nontrx.LOGIN_IDN;
import nontrx.REQ_VERIFY;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Json_VERIFY_NUMB {
  public String Records,getid,getcounter,hasil;
  
    public Json_VERIFY_NUMB(String Records) throws Exception {
        this.Records=Records;
        
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getid = jsonObject.get("nomor").toString();
            getcounter= jsonObject.get("counter").toString();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        REQ_VERIFY ai = new REQ_VERIFY(getid,getcounter);
        hasil = ai.getAns();
  }
    
  public String getRes() {
      return hasil;
  } 
}