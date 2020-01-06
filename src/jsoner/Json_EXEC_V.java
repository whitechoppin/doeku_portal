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
import nontrx.EXEC_V;
import nontrx.REQ_V;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Json_EXEC_V {
  public String Records,getid,getkode,getcounter,hasil;
  
    public Json_EXEC_V(String Records) throws Exception {
        this.Records=Records;
        
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getid = jsonObject.get("nomor").toString();
            getkode = jsonObject.get("kode").toString();
            getcounter= jsonObject.get("counter").toString();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
     
        EXEC_V lg = new EXEC_V(getid,getkode,getcounter);
        hasil = lg.getAns();
  }
    
  public String getRes() {
      return hasil;
  }
}