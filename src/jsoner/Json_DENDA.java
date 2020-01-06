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
import nontrx.LOGIN_IDN;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import trx.DENDA;

public class Json_DENDA {
  public String Records,getid,getcounter,hasil;
  
    public Json_DENDA(String Records) throws Exception {
        this.Records=Records;
        
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getid = jsonObject.get("id_trx").toString();
            getcounter= jsonObject.get("counter").toString();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        DENDA ai = new DENDA(getid,getcounter);
        hasil = ai.getAns();
  }
    
  public String getRes() {
      return hasil;
  } 
}