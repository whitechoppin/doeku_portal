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
public class LOGIN_IDN extends Konf{
    public String id,pass,counter,hasilx,namax,tipeku,statku,ans,idku;
    public JSONObject jo = new JSONObject();
    public String[] katv;
    public List<String> list = new ArrayList<String>();
    
    public SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public SimpleDateFormat formats2 = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime2 = new Date();
    public String dateString2 = formats2.format(currentTime2);
    
    public SimpleDateFormat formats3 = new SimpleDateFormat("HH:mm:ss ");
    public Date currentTime3 = new Date();
    public String dateString3 = formats3.format(currentTime3);
      
    public LOGIN_IDN(String idv,String pass,String code,String ipku,String cntrv) {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            
            statv = con.prepareCall("{call LOGIN_IDN(?,?,?,?,?,?,?,?,?,?,?) }");
            statv.setString(1, idv);
            statv.setString(2, pass);
            statv.setString(3, cntrv);
            statv.setString(4, code);
            statv.setString(5, ipku);
            statv.setString(6, dateString2);
            statv.setString(7, dateString3);
            /*statv.registerOutParameter(4, java.sql.Types.VARCHAR);
            statv.registerOutParameter(5, java.sql.Types.VARCHAR);*/
            
            statv.execute();
            
            hasilx = statv.getString("stat");
            namax = statv.getString("namaz");
            idku = statv.getString("idku");
            statku = statv.getString("tipeku");
            
            
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
                jo.put("com","LOGIN_IDN");
                jo.put("id_idn",idku);
                jo.put("nama",namax);
                jo.put("tipe",statku);
                jo.put("counter",Integer.parseInt(cntrv));
                
                ans = jo.toString();
        }else if(hasilx.equalsIgnoreCase("2")) {
                jo.put("resultcode","0001");
                jo.put("result","Akun Anda Telah Di Non-Aktifkan");
                jo.put("datetime",dateString);
                jo.put("com","LOGIN_IDN");
                
                ans=jo.toString();
        }else{
                jo.put("resultcode","0003");
                jo.put("datetime",dateString);
                jo.put("com","LOGIN_IDN");
                
                ans=jo.toString();
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