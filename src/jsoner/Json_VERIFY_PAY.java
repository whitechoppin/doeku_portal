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
import nontrx.RESET_PASV;
import nontrx.UBAH_PASV;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import trx.VERIFY_PAY;


public class Json_VERIFY_PAY {
  public String Records,hasil,gettrx,getid,getpasslama,getpassbaru,getcounter;
  
    public Json_VERIFY_PAY(String Records) throws Exception {
        this.Records=Records;
    
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getid = jsonObject.get("nova").toString();
            getcounter= jsonObject.get("counter").toString();;
           
        } catch (Exception e) {
            e.printStackTrace();
        }    
    
        VERIFY_PAY cw = new VERIFY_PAY(getid,getcounter);
        hasil = cw.getAns();
 }
    
    public String getRes() {
        return hasil;
    }
}