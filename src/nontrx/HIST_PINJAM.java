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
import modul.Konf;
import static modul.Konf.DB_PASSWORD;
import static modul.Konf.DB_URL;
import static modul.Konf.DB_USER;
import org.json.simple.JSONObject;

/**
 *
 * @author VINCENT
 */
public class HIST_PINJAM extends Konf{
    public String hasilx,ans,ctrku;
    public String idx,getnama,getalamat,getprov,getkota,getcamat,getlurah,
    gettempat,getlahir,getktp,getnpwp,getemail,getjumlah,getnorek,getnarek,getbank,getkantor,getAlamat_k,
    getTelp_k,getTelp_p,dapat,tgl_masuk,getnova;
    
    
    public String[] katv;
    public JSONObject jo = new JSONObject();
    public SimpleDateFormat  formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public SimpleDateFormat formats2 = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime2 = new Date();
    public String dateString2= formats2.format(currentTime2);
      
    public HIST_PINJAM(String idku,String cntr) {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            /*con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            statv = con.prepareCall("{ call CEK_BRW(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            
            statv.setString(1,idku);
            
            rs = statv.executeQuery();
            
            hasilx = statv.getString("stat");
            
            idx = statv.getString("idku");
            getnama = statv.getString("namax");
            getalamat = statv.getString("alamatx");
            getprov= statv.getString("prov");
            getkota = statv.getString("kotax");
            getcamat = statv.getString("camat");
            getlurah = statv.getString("lurah");
            gettempat = statv.getString("t_lahir");
            getlahir = statv.getString("tgl_lahirx");
            getktp = statv.getString("ktpx");
            getnpwp = statv.getString("npwpx");
            getemail = statv.getString("emailx");
            getjumlah = statv.getString("jumlah_t");
            getnorek = statv.getString("norekx");
            getnarek = statv.getString("nama_rekx");
            getbank = statv.getString("n_bankx");
            getkantor = statv.getString("n_kantor");
            getAlamat_k = statv.getString("alamat_kx");
            getTelp_k = statv.getString("telp_kx");
            getTelp_p = statv.getString("telp_px");
            getnova = statv.getString("nova");
            dapat = statv.getString("dapat");
            tgl_masuk = statv.getString("tgl_masukx");*/
            
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
            jo.put("com","DETAIL_PINJAM");
            jo.put("angsuran",0);
            jo.put("biaya",0);
            jo.put("jumlah",0);
            jo.put("jatuh_tempo","-");
            jo.put("jumlah_lunas",0);
            jo.put("tagihan",0);
            jo.put("counter",Integer.parseInt(ctrku));
            
        }else if(hasilx.equalsIgnoreCase("2")){
            jo.put("resultcode","0001");
            jo.put("result","Data Tidak Ada");
            jo.put("datetime",dateString);
            jo.put("com","DETAIL_PINJAM");
            jo.put("counter",Integer.parseInt(ctrku));
        }else{
            jo.put("resultcode","0002");
            jo.put("datetime",dateString);
            jo.put("com","DETAIL_PINJAM");
            jo.put("counter",Integer.parseInt(ctrku));
        }
        ans = jo.toString();
        //System.out.println(ans);
    }
    
    public String getAns () {
        return ans;
    }
}

