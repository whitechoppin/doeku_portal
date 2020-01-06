/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import trx.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import modul.Konf;
import org.json.simple.JSONObject;

/**
 *
 * @author VINCENT
 */
public class CEK_MSG extends Konf{
    public String hasilx,ans,ctrku;
    
    public String[] katv;
    public JSONObject jo = new JSONObject();
    public JSONObject jo2 = new JSONObject();
    public SimpleDateFormat  formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public SimpleDateFormat formats2 = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime2 = new Date();
    public String dateString2= formats2.format(currentTime2);
    public ArrayList<String> ar = new ArrayList<String>();    
    
    public CEK_MSG(String getid,String getctr) {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            statv = con.prepareCall("{ call MSG_CEK(?,?) }");
            statv.setString(1,getid);
            
            rs = statv.executeQuery();
            
            hasilx = statv.getString("stat");
            
            if(hasilx.equalsIgnoreCase("1")) {
                int hitung=0;
                while(rs.next()){
                    hitung++;
                    String a = rs.getString("id_pesan");
                    String b = rs.getString("judul");
                    String c = rs.getString("pesan");
                    String d = rs.getString("tgl");
                    String e = rs.getString("jam");
                    String f = rs.getString("status");
                    String g = rs.getString("divisi");
                    
                    jo2.put("urut",hitung);
                    jo2.put("id_pesan",a);
                    jo2.put("judul",b);
                    jo2.put("pesan",c);
                    jo2.put("waktu",d+" "+e);
                    jo2.put("status",f);
                    jo2.put("divisi",g);
                    
                    ar.add(jo2.toString());
                    
                }
                //jo.put("total",hitung);
                
            }
            
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
            jo.put("com","CEK_MSG");
            jo.put("id_user",getid);
            jo.put("data",ar);
            
        }else if(hasilx.equalsIgnoreCase("2")){
            jo.put("resultcode","0001");
            jo.put("result","Data Tidak Ada");
            jo.put("datetime",dateString);
            jo.put("com","CEK_MSG");
            jo.put("counter",Integer.parseInt(ctrku));
            
        }else{
            jo.put("resultcode","0002");
            jo.put("datetime",dateString);
            jo.put("com","CEK_MSG");
            jo.put("counter",Integer.parseInt(ctrku));
        }
        ans = jo.toString();
        //System.out.println(ans);
    }
    
    public String getAns () {
        return ans;
    }
}
