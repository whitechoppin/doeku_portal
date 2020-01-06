/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nontrx;

import Emailer.Em_Verify;
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
public class REGIST_BRW extends Konf{
    public String hasilx,idx,ans,ctrku,nova,KODE;
    
    public String[] katv;
    public JSONObject jo = new JSONObject();
    public SimpleDateFormat  formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public SimpleDateFormat formats2 = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime2 = new Date();
    public String dateString2= formats2.format(currentTime2);
      
    public REGIST_BRW(String getnama,String getemail,String telpp,
            String jenkel,String passku,String imeiku,String alamat,String prov,String kota,String cama,String lurah,
            String t_lahir,String tgl_lahir,String kpos,String telp_r,String ktp,String npwp,String nikah,String tanggung,String ref,String getctr) {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            
            statv = con.prepareCall("{ call REGIST_BRW(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            statv.setString(1,getnama);
            statv.setString(2,getemail);
            statv.setString(3,telpp);
            statv.setString(4,jenkel);
            statv.setString(5,passku);
            statv.setString(6,dateString2);
            statv.setString(7,imeiku);
            statv.setString(8,alamat);
            statv.setString(9,prov);
            statv.setString(10,kota);
            statv.setString(11,cama);
            statv.setString(12,lurah);
            statv.setString(13,t_lahir);
            statv.setString(14,tgl_lahir);
            statv.setString(15,kpos);
            statv.setString(16,ktp);
            statv.setString(17,npwp);        
            statv.setString(18,tanggung);
            
            statv.setString(19,telp_r);
            statv.setString(20,nikah);
            statv.setString(21,ref);
            
            
            
            rs = statv.executeQuery();
            
            hasilx = statv.getString("stat");
            
            idx = statv.getString("idagen");
            KODE = statv.getString("kode");
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
            jo.put("com","REGIST_BRW");
            jo.put("iduser",idx);
            jo.put("nova",nova);
            jo.put("counter",Integer.parseInt(ctrku));
            
            Em_Verify ev = new Em_Verify();
            ev.SendEm(getemail, KODE);
            
        }else if(hasilx.equalsIgnoreCase("2")){
            jo.put("resultcode","0001");
            jo.put("result","Data User Sudah Ada");
            jo.put("datetime",dateString);
            jo.put("com","REGIST_BRW");
            jo.put("counter",Integer.parseInt(ctrku));
        }else if(hasilx.equalsIgnoreCase("3")){
            jo.put("resultcode","0001");
            jo.put("result","Registrasi Berhasil,Jumlah Pinjaman Melebihi Batas");
            jo.put("datetime",dateString);
            jo.put("com","REGIST_BRW");
            jo.put("iduser",idx);
            jo.put("nova",nova);
            jo.put("counter",Integer.parseInt(ctrku));
        }else{
            jo.put("resultcode","0002");
            jo.put("datetime",dateString);
            jo.put("com","REGIST_BRW");
            jo.put("counter",Integer.parseInt(ctrku));
        }
        ans = jo.toString();
        //System.out.println(ans);
    }
    
    public String getAns () {
        return ans;
    }
}
