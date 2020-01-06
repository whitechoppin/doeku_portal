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
import trx.PULL_DANA;
import nontrx.REQ_V;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Json_PULL_DANA {
  public String Records,getid,getbank,getjum,getnorek,getnama,getcounter,hasil;
  
    public Json_PULL_DANA(String Records) throws Exception {
        this.Records=Records;
        
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getid = jsonObject.get("id_idn").toString();
            getbank = jsonObject.get("bank").toString();
            getjum = jsonObject.get("jumlah").toString();
            getnorek = jsonObject.get("norek").toString();
            getnama = jsonObject.get("atas_nama").toString();
            getcounter= jsonObject.get("counter").toString();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    
         
        PULL_DANA lg = new PULL_DANA(getid,getbank,getnorek,getnama,getjum,getcounter);
        hasil = lg.getAns();
  }
    
  public String getRes() {
      return hasil;
  }
}