/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trx;

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
public class PINJAM_ACC extends Konf{
    public String hasilx,ans,ctrku;
    
    public String[] katv;
    public JSONObject jo = new JSONObject();
    public SimpleDateFormat  formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public SimpleDateFormat formats2 = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime2 = new Date();
    public String dateString2= formats2.format(currentTime2);
      
    public PINJAM_ACC(String getidnota,String getidku,String getctr) throws Exception {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            statv = con.prepareCall("{ call ACC_IDN_PINJAM(?,?,?) }");
            statv.setString(1,getidnota);
            statv.setString(2,getidku);
            
            rs = statv.executeQuery();
            
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
            jo.put("com","ACC_IDN_PINJAM");
            jo.put("id_trx",getidnota);
            jo.put("id_idn",getidku);
            jo.put("counter",Integer.parseInt(ctrku));
            
            Em_IDN_ACC em = new Em_IDN_ACC();
            em.sendPost(getidnota,getidku);
        }else if(hasilx.equalsIgnoreCase("2")) {
            jo.put("resultcode","0001");
            jo.put("result","ID Tidak Dikenali");
            jo.put("datetime",dateString);
            jo.put("com","ACC_IDN_PINJAM");
            jo.put("counter",Integer.parseInt(ctrku));
        }else if(hasilx.equalsIgnoreCase("3")) {
            jo.put("resultcode","0001");
            jo.put("result","Saldo Tidak Mencukupi");
            jo.put("datetime",dateString);
            jo.put("com","ACC_IDN_PINJAM");
            jo.put("counter",Integer.parseInt(ctrku));
        }else{
            jo.put("resultcode","0001");
            jo.put("datetime",dateString);
            jo.put("com","ACC_IDN_PINJAM");
            jo.put("counter",Integer.parseInt(ctrku));
        }
        ans = jo.toString();
        //System.out.println(ans);
    }
    
    public String getAns () {
        return ans;
    }
}
