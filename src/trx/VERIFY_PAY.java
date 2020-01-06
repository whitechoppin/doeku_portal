/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trx;

import modul.TopUp;
import nontrx.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modul.BNI_Regist;
import modul.Konf;
import org.json.simple.JSONObject;

/**
 *
 * @author VINCENT
 */
public class VERIFY_PAY extends Konf{
    public String counter,hasilx,ans,namaku,emailku,telpku,jumku,tenorku,sttku,idagen,namaPendana,resultCode,idtrx;
    public JSONObject jo=new JSONObject();
    public String[] katv;
    public List<String> list = new ArrayList<String>();
    
    public SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
      
    public VERIFY_PAY(String nova,String cntrv) throws Exception {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            
            statv = con.prepareCall("{call VERIFY_BAYAR(?,?,?,?,?,?,?,?,?,?,?) }");
            statv.setString(1, nova);
            /*statv.registerOutParameter(4, java.sql.Types.VARCHAR);
            statv.registerOutParameter(5, java.sql.Types.VARCHAR);*/
            
            statv.execute();

            sttku = statv.getString("stt");
            idtrx =statv.getString("idku");
            namaku = statv.getString("namaku");
            idagen = statv.getString("p_id_agen");
            namaPendana = statv.getString("v_nama_pendana");
            emailku = statv.getString("emailku");
            telpku = statv.getString("telpku");
            jumku = statv.getString("jumku");
            tenorku = statv.getString("tenorv");
            hasilx = statv.getString("stat");
     
            if(jumku!="0") {
                BNI_Regist bni = new BNI_Regist();
                //String abc = bni.sendPendana(nama_pendana,email_pendana,telp_pendana,tgl_pendana,tagih_pendana,id_trxku);
                String cba = bni.setPeminjam(namaku,emailku,telpku,"30",jumku,idtrx);
                
            }
            
            statv.close();
            con.close();
            
        }catch(Exception ex){
             ex.printStackTrace();
        }finally{
            try {
              con.close();
            } catch (SQLException ex) {}
        }
        
        if(hasilx.equalsIgnoreCase("1")) {
                System.out.println(sttku);
                if(sttku.equals("IDN"))
                {
                    BNI_Regist bni = new BNI_Regist();
                    String abc = bni.setPeminjam(namaku,emailku,telpku,tenorku,jumku,idtrx);
                    TopUp topUp = new TopUp();
                    String result = topUp.sendPost(idagen,namaPendana,"Doeku","Doeku","Doeku","Doeku",jumku,"Doeku","Doeku");
                    resultCode = topUp.resultCodeTopUp(result);
                }
                jo.put("resultcode_topup",resultCode);
                jo.put("resultcode","0000");
                jo.put("datetime",dateString);
                jo.put("com","VERIFY_PAY");
                jo.put("nova",nova);
                /*jo.put("stt",sttku);
                jo.put("lokit",idtrx);*/
                jo.put("counter",Integer.parseInt(cntrv));

                ans = jo.toString();
        }else{
                jo.put("resultcode","0003");
                jo.put("datetime",dateString);
                jo.put("counter",Integer.parseInt(cntrv));
                jo.put("com","VERIFY_PAY");
                
                ans = jo.toString();
        }  
    }
    
    public String getAns () {
        return ans;
    }
    
    public String getResp() {
        return hasilx;
    }
    
    /*public static void main(String[] args) throws NoSuchAlgorithmException {
        UbahMD5 kl = new UbahMD5("ashj234jas"+"28378");
        LOGIN l = new LOGIN("DPAY00003",kl.ubah(),"28378");
        System.out.println(l.getAns());
    }*/
}