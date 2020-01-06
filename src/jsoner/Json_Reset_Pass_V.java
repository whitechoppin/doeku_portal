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
import nontrx.RESET_PASS_CONF;
import nontrx.RESET_PASV;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Json_Reset_Pass_V {
  public String Records,hasil,getpos,getunik,getbaru,getemail,getid,getcounter;
  
    public Json_Reset_Pass_V(String Records) throws Exception {
        this.Records=Records;
    
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getbaru = jsonObject.get("pass").toString();
            getpos = jsonObject.get("pos").toString();
            getunik = jsonObject.get("kode").toString();
            getcounter= jsonObject.get("counter").toString();;
           
        } catch (Exception e) {
            e.printStackTrace();
        }    
        
        //(String emailku,String baru,String unik,String pos,String cntr)
        RESET_PASS_CONF cw = new RESET_PASS_CONF(getbaru,getunik,getpos,getcounter);
        hasil = cw.getAns();
 }
    
    public String getRes() {
        return hasil;
    }
}