/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nontrx;

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
public class REGIST_BRW_DP extends Konf{
    public String id,pass,counter,hasilx,namax,ans,nova;
    public JSONObject jo=new JSONObject();
    public String[] katv;
    public List<String> list = new ArrayList<String>();
    
    public SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
      
    public REGIST_BRW_DP(String idv,String cntrv) {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            
            statv = con.prepareCall("{call REGIST_BRW_DP(?,?,?,?,?) }");
            statv.setString(1,dateString);
            statv.setString(2,idv);
            
            statv.execute();
            
            hasilx = statv.getString("stat");
            id = statv.getString("idagen");
            namax = statv.getString("namaku");
            
            statv.close();
            con.close();
            
        }catch(Exception ex){
             ex.printStackTrace();
        }finally{
            try {
              con.close();
            } catch (Exception ex) {}
        }
          
    }
    
    public String getHasil() {
        return id;
    }
    
    public String getnama() {
        return namax;
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