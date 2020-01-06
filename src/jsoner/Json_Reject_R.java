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
import java.io.StringReader;
import nontrx.REJECT_R;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Json_Reject_R {
  public String Records,hasil,gettrx,getid,getcounter;
  
    public Json_Reject_R(String Records) throws Exception {
        this.Records=Records;
    
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            gettrx = jsonObject.get("id_trans").toString();
            getid = jsonObject.get("id_idn").toString();
            getcounter= jsonObject.get("counter").toString();;
           
        } catch (Exception e) {
            e.printStackTrace();
        }    
    
        REJECT_R cw = new REJECT_R(gettrx, getid, gettrx);
        hasil = cw.getAns();
 }
    
    public String getRes() {
        return hasil;
    }
}