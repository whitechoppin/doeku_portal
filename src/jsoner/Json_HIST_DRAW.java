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
import nontrx.HIST_CAIR;
import nontrx.HIST_TOPUP;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Json_HIST_DRAW {
  public String Records,getid,getmulai,getakhir,getcounter,hasil;
  
    public Json_HIST_DRAW(String Records) throws Exception {
        this.Records=Records;
        
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getid = jsonObject.get("id_idn").toString();
            getmulai = jsonObject.get("tgl_dari").toString();
            getakhir = jsonObject.get("tgl_akhir").toString();
            getcounter= jsonObject.get("counter").toString();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        HIST_CAIR lg = new HIST_CAIR(getid,getmulai,getakhir,getcounter);
        hasil = lg.getAns();
  }
    
  public String getRes() {
      return hasil;
  }
}