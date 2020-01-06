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
import nontrx.CEK_IDN_I;
import nontrx.CEK_PRE_PINJAM;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Json_CEK_PRE_PINJAM {
  public String Records,getid,getpass,getcounter,hasil;
  
    public Json_CEK_PRE_PINJAM(String Records) throws Exception {
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
    
        CEK_PRE_PINJAM lg = new CEK_PRE_PINJAM(getid,getcounter);
        hasil = lg.getAns();
  }
    
  public String getRes() {
      return hasil;
  }
}