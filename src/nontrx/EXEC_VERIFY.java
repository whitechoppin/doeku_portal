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
import static modul.Konf.DB_PASSWORD;
import static modul.Konf.DB_URL;
import static modul.Konf.DB_USER;
import modul.Smser;
import org.json.simple.JSONObject;

/**
 *
 * @author VINCENT
 */
public class EXEC_VERIFY extends Konf{
    public String id,pass,counter,hasilx,namax,ans,nova;
    public JSONObject jo=new JSONObject();
    public String[] katv;
    public List<String> list = new ArrayList<String>();
    
    public SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public SimpleDateFormat formats2 = new SimpleDateFormat("HH:mm:ss ");
    public Date currentTime2 = new Date();
    public String dateString2 = formats2.format(currentTime2);
      
    public EXEC_VERIFY(String idv,String kd,String cntrv) throws Exception {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            
            statv = con.prepareCall("{call VERIF2(?,?,?) }");
            statv.setString(1, idv);
            statv.setString(2, kd);
            
            /*statv.registerOutParameter(4, java.sql.Types.VARCHAR);
            statv.registerOutParameter(5, java.sql.Types.VARCHAR);*/
            
            statv.execute();
            
            hasilx = statv.getString("stat");
            
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
                jo.put("com","EXEC_NUMB");
                jo.put("counter",Integer.parseInt(cntrv));
                
                ans = jo.toString();
        }else{
                jo.put("resultcode","0003");
                jo.put("datetime",dateString);
                jo.put("result","Nomor AAnda Telah Terdaftar");
                jo.put("counter",Integer.parseInt(cntrv));
                jo.put("com","EXEC_NUMB");
                
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