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


public class Json_Ubah_Pass {
  public String Records,hasil,gettrx,getid,getpasslama,getpassbaru,getcounter;
  
    public Json_Ubah_Pass(String Records) throws Exception {
        this.Records=Records;
    
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getid = jsonObject.get("id_user").toString();
            getpasslama = jsonObject.get("pass_lama").toString();
            getpassbaru = jsonObject.get("pass_baru").toString();
            getcounter= jsonObject.get("counter").toString();;
           
        } catch (Exception e) {
            e.printStackTrace();
        }    
    
        UBAH_PASV cw = new UBAH_PASV(getid,getpasslama,getpassbaru,getcounter);
        hasil = cw.getAns();
 }
    
    public String getRes() {
        return hasil;
    }
}