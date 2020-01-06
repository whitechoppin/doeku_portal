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

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Json_Verify_Email {
  public String Records,hasil,getpos,getid,getcounter;
  
    public Json_Verify_Email(String Records) throws Exception {
        this.Records=Records;
    
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getid = jsonObject.get("id_user").toString();
            getpos = jsonObject.get("pos").toString();
            getcounter= jsonObject.get("counter").toString();;
           
        } catch (Exception e) {
            e.printStackTrace();
        }    
    
        RESET_PASV cw = new RESET_PASV(getid,getpos,getcounter);
        hasil = cw.getAns();
 }
    
    public String getRes() {
        return hasil;
    }
}