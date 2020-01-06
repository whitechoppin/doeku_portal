/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nontrx;

import Emailer.Em_End_Chat;
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
public class END_MSG extends Konf{
    public String id,pass,counter,hasilx,namax,ans,nova;
    public JSONObject jo=new JSONObject();
    public String[] katv;
    public List<String> list = new ArrayList<String>();
    
    public SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
      
    public END_MSG(String idv,String idpesan,String cntrv) throws Exception {
        
        hasilx = "1";
        
        /*try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            
            statv = con.prepareCall("{call END_MSG(?,?,?) }");
            statv.setString(1, idv);
            statv.setString(2, idpesan);
            /*statv.registerOutParameter(4, java.sql.Types.VARCHAR);
            statv.registerOutParameter(5, java.sql.Types.VARCHAR);*/
            
        /*    statv.execute();
            
            hasilx = statv.getString("stat");
            
            statv.close();
            con.close();
            
        }catch(Exception ex){
             ex.printStackTrace();
        }finally{
            try {
              con.close();
            } catch (SQLException ex) {}
        }*/
        
        if(hasilx.equalsIgnoreCase("1")) {
                jo.put("resultcode","0000");
                jo.put("datetime",dateString);
                jo.put("com","END_MSG");
                jo.put("counter",Integer.parseInt(cntrv));
                /*Em_End_Chat em = new Em_End_Chat();
                em.sendPost(idpesan,idv);*/
                ans = jo.toString();
        }else{
                jo.put("resultcode","0001");
                jo.put("result","ID Pesan Tidak Dikenali");
                jo.put("datetime",dateString);
                jo.put("counter",Integer.parseInt(cntrv));
                jo.put("com","END_MSG");
                
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