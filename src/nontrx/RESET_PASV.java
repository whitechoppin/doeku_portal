/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nontrx;

import Emailer.Em_Notif_Reset;
import java.net.URLEncoder;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modul.Em_Reset_Pass;
import modul.Konf;
import org.json.simple.JSONObject;

/**
 *
 * @author VINCENT
 */
public class RESET_PASV extends Konf{
    public String hasilx,ans,ctrku;
    public String idx,getemail,pesan,stt;
    
    public String[] katv;
    public JSONObject jo = new JSONObject();
    public SimpleDateFormat  formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public SimpleDateFormat formats2 = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime2 = new Date();
    public String dateString2= formats2.format(currentTime2);
      
    public RESET_PASV(String idku,String pos,String cntr) {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            statv = con.prepareCall("{ call RESET_PASV(?,?,?,?,?,?)}");
            
            statv.setString(1,idku);
            statv.setString(2,pos);
            
            rs = statv.executeQuery();
            
            hasilx = statv.getString("stat");
            
            idx = statv.getString("kode");
            getemail = statv.getString("emailku");
            stt = statv.getString("sttku");
            
            ctrku = cntr;
            
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
            jo.put("resultcode","0000");
            jo.put("datetime",dateString);
            jo.put("com","RESET_PASS");
            jo.put("status",stt);
            jo.put("counter",Integer.parseInt(ctrku));
            
            Em_Reset_Pass er = new Em_Reset_Pass();
            er.SendEm(getemail, idx);
            
        }else if(hasilx.equalsIgnoreCase("2")){
            jo.put("resultcode","0001");
            jo.put("result","Data Tidak Ditemukan");
            jo.put("datetime",dateString);
            jo.put("com","RESET_PASS");
            jo.put("counter",Integer.parseInt(ctrku));
            
        }else{
            jo.put("resultcode","0002");
            jo.put("datetime",dateString);
            jo.put("com","RESET_PASS");
            jo.put("counter",Integer.parseInt(ctrku));
        }
        ans = jo.toString();
        //System.out.println(ans);
    }
    
    public String getAns () {
        return ans;
    }
}
