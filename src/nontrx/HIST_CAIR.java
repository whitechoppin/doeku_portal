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
public class HIST_CAIR extends Konf{
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
    public String dateString2= formats2.format(currentTime2);
      
    public ArrayList<String> ar = new ArrayList<String>();
    
    public HIST_CAIR(String idku,String tgl_mulai,String tgl_akhir,String cntr) {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            statv = con.prepareCall("{ call HIST_DRAW_IDN(?,?,?,?)}");
            
            statv.setString(1,idku);
            statv.setString(2,tgl_mulai);
            statv.setString(3,tgl_akhir);
            
            rs = statv.executeQuery();
            
            hasilx = statv.getString("stat");
            
            if(hasilx.equalsIgnoreCase("1")) {
                int hitung=0;
                
                while(rs.next()){
                    hitung++;
                    String jum = rs.getString("jumlah");
                    String tgl = rs.getString("tgl_req");
                    String jam = rs.getString("jam_req");
                    String st = rs.getString("stat");
                    
                    jo2.put("urut",hitung);
                    jo2.put("jumlah",jum);
                    jo2.put("tgl",tgl);
                    jo2.put("waktu",jam);
                    jo2.put("status",st);
                    
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
            jo.put("com","HIST_WITHDRAW");
            jo.put("id_idn",idku);
            jo.put("data",ar);
            
        }else if(hasilx.equalsIgnoreCase("2")){
            jo.put("resultcode","0001");
            jo.put("result","Data Tidak Ada");
            jo.put("datetime",dateString);
            jo.put("com","HIST_WITHDRAW");
            jo.put("counter",Integer.parseInt(ctrku));
            
        }else{
            jo.put("resultcode","0002");
            jo.put("datetime",dateString);
            jo.put("com","HIST_WITHDRAW");
            jo.put("counter",Integer.parseInt(ctrku));
        }
        ans = jo.toString();
        //System.out.println(ans);
    }
    
    public String getAns () {
        return ans;
    }
}
