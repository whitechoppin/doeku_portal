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
import nontrx.REC_LOC;
import nontrx.REJECT_R;
import nontrx.RESET_PASS_CONF;
import nontrx.RESET_PASV;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Json_Rec_Loc {
  public String Records,hasil,getid,getlong,getlat,getcounter;
  
    public Json_Rec_Loc(String Records) throws Exception {
        this.Records=Records;
    
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getid = jsonObject.get("user").toString();
            getlong = jsonObject.get("longitude").toString();
            getlat = jsonObject.get("latitude").toString();
            getcounter= jsonObject.get("counter").toString();;
           
        } catch (Exception e) {
            e.printStackTrace();
        }    
        
        //(String emailku,String baru,String unik,String pos,String cntr)
        REC_LOC cw = new REC_LOC(getid,getlong,getlat,getcounter);
        hasil = cw.getAns();
 }
    
    public String getRes() {
        return hasil;
    }
}