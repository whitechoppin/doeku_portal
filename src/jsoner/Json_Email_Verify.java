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
import nontrx.EMAIL_CONF;
import nontrx.REJECT_R;
import nontrx.RESET_PASS_CONF;
import nontrx.RESET_PASV;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Json_Email_Verify {
  public String Records,hasil,getpos,getunik,getbaru,getemail,getid,getcounter;
  
    public Json_Email_Verify(String Records) throws Exception {
        this.Records=Records;
    
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getpos = jsonObject.get("pos").toString();
            getunik = jsonObject.get("kode").toString();
            getcounter= jsonObject.get("counter").toString();;
           
        } catch (Exception e) {
            e.printStackTrace();
        }    
        
        //(String emailku,String baru,String unik,String pos,String cntr)
        EMAIL_CONF cw = new EMAIL_CONF(getunik,getpos,getcounter);
        hasil = cw.getAns();
 }
    
    public String getRes() {
        return hasil;
    }
}