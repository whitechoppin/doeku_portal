package support;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author VINCENT
 */

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Json_ACC_MSG {
  public String Records,getid,getdivisi,getlevel,getdivisigetkodeuser,getjudul,getpesan,getcounter,hasil;
  
    public Json_ACC_MSG(String Records) throws Exception {
        this.Records=Records;
        
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getid = jsonObject.get("id_user").toString();
            getjudul = jsonObject.get("judul").toString();
            getdivisi = jsonObject.get("divisi").toString();
            getlevel = jsonObject.get("level").toString();
            getpesan = jsonObject.get("pesan").toString();
            getcounter= jsonObject.get("counter").toString();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        ACC_MSG ai = new ACC_MSG(getid,getjudul,getdivisi,getlevel,getpesan,getcounter);
        hasil = ai.getAns();
  }
    
  public String getRes() {
      return hasil;
  } 
}