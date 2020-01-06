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
import nontrx.HIST_PAY;
import nontrx.HIST_TOPUP;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Json_HIST_PAY {
  public String Records,getid,getmulai,halku,getakhir,getcounter,hasil;
  
    public Json_HIST_PAY(String Records) throws Exception {
        this.Records=Records;
        
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
            
            getid = jsonObject.get("id_brw").toString();
            halku = jsonObject.get("halaman").toString();
            /*getmulai = jsonObject.get("tgl_dari").toString();
            getakhir = jsonObject.get("tgl_akhir").toString();*/
            getcounter= jsonObject.get("counter").toString();
            
            getmulai = "2019-07-31";
            getakhir = "2019-07-31";
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        HIST_PAY lg = new HIST_PAY(getid,halku,getcounter);
        hasil = lg.getAns();
  }
    
  public String getRes() {
      return hasil;
  }
}