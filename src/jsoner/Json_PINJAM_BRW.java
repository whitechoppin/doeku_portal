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

import nontrx.ACC_IDN;
import nontrx.LOGIN_IDN;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import trx.PINJAM_BRW;

public class Json_PINJAM_BRW {
  public String Records,getid,getnova,getsuara,getdiri,getloc,getjum,gettenor,gettipe,getalasan,getcounter,hasil;
  
    public Json_PINJAM_BRW(String Records) throws Exception {
        this.Records=Records;
        
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getid = jsonObject.get("id_brw").toString();
            getnova = jsonObject.get("nova").toString();
            getjum = jsonObject.get("jumlah").toString();
            gettenor = jsonObject.get("tenor").toString();
            gettipe = jsonObject.get("tipe").toString();
            getcounter= jsonObject.get("counter").toString();
            getsuara= jsonObject.get("rekam_suara").toString();
            getdiri = jsonObject.get("rekam_diri").toString();
            getloc = jsonObject.get("rekam_loc").toString();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        PINJAM_BRW ai = new PINJAM_BRW(getid,getnova,getjum,gettenor,gettipe,getsuara,getdiri,getloc,getcounter);
        hasil = ai.getAns();
  }
    
  public String getRes() {
      return hasil;
  } 
}