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

import nontrx.ACC_IDN_PINJAM;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Json_ACC_IDN_PINJAM {
  public String Records,getid,getnodocpeminjam,getnodocpendana,getcounter,hasil,getsuara,getdiri,getloc,getreff;
  
    public Json_ACC_IDN_PINJAM(String Records) throws Exception {
        this.Records=Records;
        
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getreff = jsonObject.get("id_trx").toString();
            getid = jsonObject.get("id_idn").toString();
            getnodocpeminjam = jsonObject.get("no_doc_peminjam").toString();
            getnodocpendana = jsonObject.get("no_doc_pendana").toString();
            getsuara = jsonObject.get("rekam_suara").toString();
            getdiri = jsonObject.get("rekam_diri").toString();
            getloc = jsonObject.get("id_idn").toString();
            getcounter= jsonObject.get("counter").toString();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        ACC_IDN_PINJAM ai = new ACC_IDN_PINJAM(getid,getnodocpeminjam,getnodocpendana,getreff,getsuara,getdiri,getloc,getcounter);
        hasil = ai.getAns();
  }
    
  public String getRes() {
      return hasil;
  } 
}