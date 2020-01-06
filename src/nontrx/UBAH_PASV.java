/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nontrx;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import Emailer.Em_Notif;
import modul.Konf;
import org.json.simple.JSONObject;

/**
 *
 * @author VINCENT
 */
public class UBAH_PASV extends Konf{
    public String hasilx,ans,ctrku;
    public String idx,getemail,pesan;
    
    public String[] katv;
    public JSONObject jo = new JSONObject();
    public SimpleDateFormat  formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public SimpleDateFormat formats2 = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime2 = new Date();
    public String dateString2= formats2.format(currentTime2);
      
    public UBAH_PASV(String idku,String lama,String baru,String cntr) {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            statv = con.prepareCall("{ call UBAH_PASS(?,?,?,?,?)}");
            
            statv.setString(1,idku);
            statv.setString(2,lama);
            statv.setString(3,baru);
            
            rs = statv.executeQuery();
            
            hasilx = statv.getString("stat");
            
            getemail = statv.getString("emailku");
            
            pesan = "Password Anda di DoeKu Telah Berhasil Di Ubah. Terima Kasih.";
            
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
            jo.put("com","UBAH_PASS");
            jo.put("counter",Integer.parseInt(ctrku));
            
            Em_Notif em = new Em_Notif();
            try {
                em.sendPost(getemail,pesan,"Perubahan Password Anda Di DoeKu");
            } catch (Exception ex) {
                Logger.getLogger(UBAH_PASV.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(hasilx.equalsIgnoreCase("2")){
            jo.put("resultcode","0001");
            jo.put("result","Data Tidak Ada");
            jo.put("datetime",dateString);
            jo.put("com","UBAH_PASS");
            jo.put("counter",Integer.parseInt(ctrku));
            
        }else{
            jo.put("resultcode","0002");
            jo.put("datetime",dateString);
            jo.put("com","UBAH_PASS");
            jo.put("counter",Integer.parseInt(ctrku));
        }
        ans = jo.toString();
        //System.out.println(ans);
    }
    
    public String getAns () {
        return ans;
    }
}
