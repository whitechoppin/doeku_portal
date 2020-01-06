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
public class LIST_PINJAM extends Konf{
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
    
    public LIST_PINJAM(String idku,String halmu,String cntr) {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            statv = con.prepareCall("{ call LIST_PINJAM(?,?,?)}");
            
            statv.setString(1,idku);
            statv.setString(2,halmu);
            
            rs = statv.executeQuery();
            
            hasilx = statv.getString("stat");
            
            if(hasilx.equalsIgnoreCase("1")) {
                int hitung=0;
                while(rs.next()){
                    hitung++;
                    String a = rs.getString("a.id_brw");
                    String b = rs.getString("a.jumlah");
                    String c = rs.getString("a.tenor");
                    String e = rs.getString("a.id_trxbrw");
                    String f = rs.getString("a.skor");
                    String i = rs.getString("a.bunga_brw");
                    String j = rs.getString("a.jum_fix");
                    String g = rs.getString("b.nama");
                    String h = rs.getString("b.nama_k");
                    
                    jo2.put("urut",hitung);
                    jo2.put("id_brw",a);
                    jo2.put("jumlah",b);
                    jo2.put("tenor",c);
                    jo2.put("id_trxb",e);
                    jo2.put("skor",f);
                    jo2.put("nama",g);
                    jo2.put("bunga",i);
                    jo2.put("total",j);
                    jo2.put("nama_usaha",h);
                    
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
            jo.put("com","LIST_PINJAMAN");
            jo.put("id_idn",idku);
            jo.put("data",ar);
            
        }else if(hasilx.equalsIgnoreCase("2")){
            jo.put("resultcode","0001");
            jo.put("result","Data Tidak Ada");
            jo.put("datetime",dateString);
            jo.put("com","LIST_PINJAMAN");
            jo.put("counter",Integer.parseInt(ctrku));
            
        }else{
            jo.put("resultcode","0002");
            jo.put("datetime",dateString);
            jo.put("com","LIST_PINJAMAN");
            jo.put("counter",Integer.parseInt(ctrku));
        }
        ans = jo.toString();
        //System.out.println(ans);
    }
    
    public String getAns () {
        return ans;
    }
}
