/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modul;

import nontrx.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modul.Konf;
import org.json.simple.JSONObject;

/**
 *
 * @author VINCENT
 */
public class Daftar_BRW_NOVA extends Konf{
    public String id,pass,counter,hasilx,namax,ans,nova;
    public JSONObject jo=new JSONObject();
    public String[] katv;
    
    public SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
      
    public Daftar_BRW_NOVA(String idv,String novaku, String idtrxva,String status) {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            
            statv = con.prepareCall("{call DAFTAR_BRW_NOVA(?,?,?,?,?) }");
            statv.setString(1, idv);
            statv.setString(2, novaku);
            statv.setString(3, idtrxva);
            statv.setString(4, status);
            
            statv.execute();
            statv.close();
            con.close();
        }catch(Exception ex){
             ex.printStackTrace();
        }finally{
            try {
              con.close();
            } catch (SQLException ex) {}
        }
        
    }
    
    public String getAns () {
        return ans;
    }
    
    public String getResp() {
        return hasilx;
    }
    
}