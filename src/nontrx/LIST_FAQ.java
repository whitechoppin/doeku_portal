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
import modul.Konf;
import org.json.simple.JSONObject;

/**
 *
 * @author VINCENT
 */
public class LIST_FAQ extends Konf{
    public String hasilx,ans,ctrku;
    public String idx,getnama,getalamat,getprov,getkota,getcamat,getlurah,
    gettempat,getlahir,getktp,getnpwp,getemail,getjumlah,getnorek,getnarek,getbank,getkantor,getAlamat_k,
    getTelp_k,getTelp_p,dapat,tgl_masuk,getnova;
    
    
    public String[] katv;
    public JSONObject jo = new JSONObject();
    public JSONObject jo2 = new JSONObject();
    public SimpleDateFormat  formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public SimpleDateFormat formats2 = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime2 = new Date();
    public String dateString2= formats2.format(currentTime2),bungaku,dendaku;
    public ArrayList<String> ar = new ArrayList<String>();  
    
    public LIST_FAQ(String idku,String cntr) {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            statv = con.prepareCall("{ call LIST_FAQ(?,?)}");
            statv.setString(1,idku);
            rs = statv.executeQuery();
            
            //bungaku = statv.getString("bg");
            dendaku = "0";
            hasilx = statv.getString("stat");
            
            if(hasilx.equalsIgnoreCase("1")) {
                int hitung=0;
                while(rs.next()){
                    hitung++;
                    String a = rs.getString("a.pertanyaan");
                    String b = rs.getString("a.jawaban");
                    String c = rs.getString("a.waktu");
                    
                    jo2.put("urut",hitung);
                    jo2.put("tanya",a);
                    jo2.put("jawab",b);
                    jo2.put("waktu",c);
                    
                    ar.add(jo2.toString());
                    
                }
                //jo.put("total",hitung);
                
            }
            
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
            jo.put("com","LIST_FAQ");
            jo.put("data",ar);
            
        }else if(hasilx.equalsIgnoreCase("2")){
            jo.put("resultcode","0001");
            jo.put("result","Data Tidak Ada");
            jo.put("datetime",dateString);
            jo.put("com","LIST_FAQ");
            jo.put("counter",Integer.parseInt(ctrku));
            
        }else{
            jo.put("resultcode","0002");
            jo.put("datetime",dateString);
            jo.put("com","LIST_FAQ");
            jo.put("counter",Integer.parseInt(ctrku));
        }
        ans = jo.toString();
        //System.out.println(ans);
    }
    
    public String getAns () {
        return ans;
    }
}
