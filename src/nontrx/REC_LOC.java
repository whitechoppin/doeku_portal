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
import java.util.Random;
import modul.Konf;
import modul.Smser;
import org.json.simple.JSONObject;

/**
 *
 * @author VINCENT
 */
public class REC_LOC extends Konf{
    public String hasilx,ans,ctrku;
    public String idx,getnama,getalamat,getprov,getkota,getcamat,getlurah,
    gettempat,getlahir,getktp,getnpwp,getemail,getjumlah,getnorek,getnarek,getbank,getkantor,getAlamat_k,
    getTelp_k,getTelp_p,dapat,tgl_masuk,getnova,gettrx,getkal;
    
    
    public String[] katv;
    public JSONObject jo = new JSONObject();
    public SimpleDateFormat  formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public SimpleDateFormat formats2 = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime2 = new Date();
    public String dateString2= formats2.format(currentTime2);
    
    public SimpleDateFormat formats3 = new SimpleDateFormat("HH:mm:ss");
    public Date currentTime3 = new Date();
    public String dateString3= formats3.format(currentTime3);
      
    public REC_LOC(String idku,String longku,String latku,String cntr) throws Exception {
         Random rn=new Random();
         int num=rn.nextInt(100000);
         
         if(num<10000) {
             num+=10000;
         }
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            statv = con.prepareCall("{ call REC_LOC(?,?,?,?)}");
            
            statv.setString(1,idku);
            statv.setString(2,longku);
            statv.setString(3,latku);
            
            rs = statv.executeQuery();
            
            hasilx = statv.getString("stat");
            
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
            jo.put("com","REC_LOC");
            jo.put("no_telp",idku);
            jo.put("counter",Integer.parseInt(ctrku));
        }else{
            jo.put("resultcode","0002");
            jo.put("datetime",dateString);
            jo.put("com","REC_LOC");
            jo.put("counter",Integer.parseInt(ctrku));
        }
        ans = jo.toString();
        //System.out.println(ans);
    }
    
    public String getAns () {
        return ans;
    }
}
