/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auto_dana;

import trx.*;
import Emailer.Em_IDN_ACC;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import modul.Konf;
import org.json.simple.JSONObject;

/**
 *
 * @author VINCENT
 */
public class GET_AUTO_DANA extends Konf{
    public String hasilx,getgrade,getdari,getsampai,getalasan,ans,ctrku;
    
    public String[] katv;
    public JSONObject jo = new JSONObject();
    public SimpleDateFormat  formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public SimpleDateFormat formats2 = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime2 = new Date();
    public String dateString2= formats2.format(currentTime2);
      
    public GET_AUTO_DANA(String getidku,String getctr) throws Exception {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            statv = con.prepareCall("{ call GET_AUTO_DANA(?,?,?,?,?,?) }");
            statv.setString(1,getidku);
            
            rs = statv.executeQuery();
            
            getgrade = statv.getString("gradev");
            getdari = statv.getString("dari");
            getsampai = statv.getString("sampai");
            getalasan = statv.getString("alasanv");
            hasilx = statv.getString("stat");
            ctrku = getctr;
            
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
            jo.put("com","GET_AUTO_INV");
            jo.put("grade",getgrade);
            jo.put("dari_skor",getdari);
            jo.put("sampai_skor",getsampai);
            jo.put("alasan",getalasan);
            jo.put("counter",Integer.parseInt(ctrku));
        }else{
            jo.put("resultcode","0001");
            jo.put("datetime",dateString);
            jo.put("com","GET_AUTO_INV");
            jo.put("counter",Integer.parseInt(ctrku));
        }
        ans = jo.toString();
        //System.out.println(ans);
    }
    
    public String getAns () {
        return ans;
    }
}
