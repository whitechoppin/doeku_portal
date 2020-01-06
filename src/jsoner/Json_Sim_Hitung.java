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
import nontrx.SIM_HITUNG;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Json_Sim_Hitung {
  public String Records,hasil,gettrx,getjum,getcounter;
  public int getjum2,gettenor;
  
    public Json_Sim_Hitung(String Records) throws Exception {
        this.Records=Records;
    
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getjum = jsonObject.get("jumlah").toString();
            gettenor = Integer.parseInt(jsonObject.get("tenor").toString());
            getcounter= jsonObject.get("counter").toString();;
            getjum2=Integer.valueOf(getjum);
        } catch (Exception e) {
            e.printStackTrace();
        }    
    
        SIM_HITUNG cw = new SIM_HITUNG(getjum2,gettenor,getcounter);
        hasil = cw.getAns();
 }
    
    public String getRes() {
        return hasil;
    }
}