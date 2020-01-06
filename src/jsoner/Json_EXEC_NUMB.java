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
import nontrx.EXEC_V;
import nontrx.EXEC_VERIFY;
import nontrx.LOGIN_IDN;
import nontrx.REQ_VERIFY;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Json_EXEC_NUMB {
  public String Records,getid,getkd,getcounter,hasil;
  
    public Json_EXEC_NUMB(String Records) throws Exception {
        this.Records=Records;
        
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getid = jsonObject.get("nomor").toString();
            getkd = jsonObject.get("kode").toString();
            getcounter= jsonObject.get("counter").toString();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        EXEC_VERIFY ai = new EXEC_VERIFY(getid,getkd,getcounter);
        hasil = ai.getAns();
  }
    
  public String getRes() {
      return hasil;
  } 
}