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
import modul.Konf;
import modul.Regist_Digisign;
import org.json.simple.JSONObject;

/**
 *
 * @author VINCENT
 */
public class UPDATE_BRW extends Konf{
    public String hasilx,idx,ans,ctrku,nova;
    
    public String[] katv;
    public JSONObject jo = new JSONObject();
    public SimpleDateFormat  formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public SimpleDateFormat formats2 = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime2 = new Date();
    public String dateString2= formats2.format(currentTime2);
      
    public UPDATE_BRW(String noid,
            String getnama,
            String jenkel,String alamat,String prov,String kota,String cama,String lurah,
            String t_lahir,String tgl_lahir,String kpos,String telp_r,String ktp,String npwp,String nikah,String tanggung,
            String prov_k,String kota_k,String camat_k,String lurah_k,String kpos2,
            String getnorek,String nrek,String nbank,
            String nusaha,String nalamat,String telpk,String telpd,String fotod,String foto_ktp,
            String foto_npwp,String fotorek,String foto_dalam,String foto_luar,String foto_depan,String lama,String stat_usaha,
            String nama_d,String alamat_d,String hub_d,String telp_pr,String getctr) {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            statv = con.prepareCall("{ call UPDATE_BRW(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            
            statv.setString(1,noid);
            statv.setString(2,getnama);
            statv.setString(3,jenkel);
            
            statv.setString(4,dateString2);
            
            statv.setString(5,alamat);
            statv.setString(6,prov);
            statv.setString(7,kota);
            statv.setString(8,cama);
            statv.setString(9,lurah);
            statv.setString(10,t_lahir);
            statv.setString(11,tgl_lahir);
            statv.setString(12,kpos);
            statv.setString(13,ktp);
            statv.setString(14,npwp);
            statv.setString(15,tanggung);
            statv.setString(16,telp_r);
            statv.setString(17,nikah);
           
            statv.setString(18,prov_k);
            statv.setString(19,kota_k);
            statv.setString(20,camat_k);
            statv.setString(21,lurah_k);
            statv.setString(22,kpos2);
            statv.setString(23,getnorek);
            statv.setString(24,nrek);
            statv.setString(25,nbank);
            statv.setString(26,nusaha);
            statv.setString(27,nalamat);
            statv.setString(28,telpk);
            statv.setString(29,telpd);
            statv.setString(30,fotod);
            statv.setString(31,foto_ktp);
            statv.setString(32,foto_npwp);
            statv.setString(33,fotorek);
            statv.setString(34,foto_dalam);
            statv.setString(35,foto_depan);
            statv.setString(36,foto_luar);
            statv.setString(37,stat_usaha);
            statv.setString(38,lama);
            statv.setString(39,nama_d);
            statv.setString(40,hub_d);
            statv.setString(41,alamat_d);
            statv.setString(42,telp_pr);
            
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
            jo.put("com","UPDATE_BRW");
            jo.put("iduser",noid);
            jo.put("nova",nova);
            jo.put("counter",Integer.parseInt(ctrku));
       
            CEK_BRW cb = new CEK_BRW(noid,"773752");
            String emailku = cb.getEmail();
            String telpku = cb.getTelp();
            
            Regist_Digisign rd = new Regist_Digisign();
            try {
                rd.sendPeminjam(getnama,emailku,telpku,kpos,ktp,
                 alamat,prov,kota,cama,lurah,jenkel,t_lahir,tgl_lahir,npwp);
            } catch (Exception ex) {
                Logger.getLogger(UPDATE_BRW.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else if(hasilx.equalsIgnoreCase("2")){
            jo.put("resultcode","0001");
            jo.put("result","Data User Sudah Ada");
            jo.put("datetime",dateString);
            jo.put("com","UPDATE_BRW");
            jo.put("counter",Integer.parseInt(ctrku));
        }else if(hasilx.equalsIgnoreCase("3")){
            jo.put("resultcode","0001");
            jo.put("result","Registrasi Berhasil,Jumlah Pinjaman Melebihi Batas");
            jo.put("datetime",dateString);
            jo.put("com","UPDATE_BRW");
            jo.put("iduser",idx);
            jo.put("nova",nova);
            jo.put("counter",Integer.parseInt(ctrku));
        }else{
            jo.put("resultcode","0002");
            jo.put("datetime",dateString);
            jo.put("com","UPDATE_BRW");
            jo.put("counter",Integer.parseInt(ctrku));
        }
        ans = jo.toString();
        //System.out.println(ans);
    }

    public String getAns () {
        return ans;
    }
}
