package auto_dana;



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

public class Json_AUTO_DANA {
  public String Records,getid,getgrade,getfrom_skor,getto_skor,getalasan,getcounter,hasil;
  
    public Json_AUTO_DANA(String Records) throws Exception {
        this.Records=Records;
        
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getid = jsonObject.get("id_idn").toString();
            getgrade = jsonObject.get("grade").toString();
            getfrom_skor = jsonObject.get("dari_skor").toString();
            getto_skor = jsonObject.get("sampai_skor").toString();
            getalasan = jsonObject.get("alasan").toString();
            getcounter= jsonObject.get("counter").toString();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        AUTO_DANA ai = new AUTO_DANA(getid,getgrade,getfrom_skor,getto_skor,getalasan,getcounter);
        hasil = ai.getAns();
  }
    
  public String getRes() {
      return hasil;
  } 
}