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
import nontrx.LIST_PINJAM;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Json_LIST_PINJAM {
  public String Records,getid,getcounter,hasil,halmu;
  
    public Json_LIST_PINJAM(String Records) throws Exception {
        this.Records=Records;
        
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getid = jsonObject.get("id_idn").toString();
            halmu = jsonObject.get("halaman").toString();
            getcounter= jsonObject.get("counter").toString();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        LIST_PINJAM ai = new LIST_PINJAM(getid,halmu,getcounter);
        hasil = ai.getAns();
  }
    
  public String getRes() {
      return hasil;
  } 
}