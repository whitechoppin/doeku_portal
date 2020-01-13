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
import org.json.simple.JSONObject;

/**
 *
 * @author VINCENT
 */
public class CEK_BRW extends Konf{
    public String hasilx,ans,ctrku,lengkap,getsexv;
    public String idx,getnama,getalamat,getprov,getkota,getcamat,getlurah,getprov_k,getkota_k,getcamat_k,getlurah_k,
    gettempat,getlahir,getktp,getnpwp,getemail,getjumlah,getnorek,getnarek,getbank,getkantor,getAlamat_k,stat_user,
    getTelp_k,getTelp_p,gettelp_d,gettelp_r,dapat,nama_d,alamat_d,nikah,st_usaha,hub_d,tgl_masuk,getnova,gettrx,activeUser;
    public int limit,minimal;
    
    public String[] katv;
    public JSONObject jo = new JSONObject();
    public SimpleDateFormat  formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public SimpleDateFormat formats2 = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime2 = new Date();
    public String dateString2= formats2.format(currentTime2);
      
    public CEK_BRW(String idku,String cntr) {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            statv = con.prepareCall("{ call CEK_BRW(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            
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
            gettelp_d = statv.getString("telp_dx");
            gettelp_r = statv.getString("telp_rx");
            getnova = statv.getString("novaku");
            gettrx = statv.getString("trx_id");
            limit = statv.getInt("limit_pinjam");
            minimal = statv.getInt("min_pinjamv");
            getprov_k= statv.getString("prov_k");
            getkota_k = statv.getString("kotax_k");
            getcamat_k = statv.getString("camat_k");
            getlurah_k = statv.getString("lurah_k");
            tgl_masuk = statv.getString("tgl_masukx"); 
            nama_d = statv.getString("nama_dx"); 
            hub_d = statv.getString("hub_dx"); 
            alamat_d = statv.getString("alamat_dx"); 
            nikah = statv.getString("nikah");
            st_usaha = statv.getString("st_usaha");
            lengkap = statv.getString("lengkap");
            stat_user = statv.getString("stat_user");
            getsexv = statv.getString("jniskel");
            stat_user = statv.getString("stat_user");
            activeUser = statv.getString("active_user");
            
            if(gettrx==null) {
                gettrx="Tidak Ada";
            }
            ctrku = cntr;
            
            statv.close();
            con.close();
            
            System.out.println("Stat User :"+hasilx);
            
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
            jo.put("com","CEK_BRW");
            jo.put("id_brw",idx);
            jo.put("nama",getnama);
            jo.put("alamat",getalamat);
            jo.put("provinsi",getprov);
            jo.put("kota",getkota);
            jo.put("kecamatan",getcamat);
            jo.put("kelurahan",getlurah);
            jo.put("tempat_lahir",gettempat);
            jo.put("tgl_lahir",getlahir);
            jo.put("ktp",getktp);
            jo.put("npwp",getnpwp);
            jo.put("email",getemail);
            jo.put("nova",getnova);
            jo.put("jum_tanggung",getjumlah);
            jo.put("norek",getnorek);
            jo.put("telp_rumah",gettelp_r);
            jo.put("nikah",nikah);
            jo.put("atas_nama",getnarek);
            jo.put("bank",getbank);
            jo.put("kantor",getkantor);
            jo.put("alamat_kantor",getAlamat_k);
            jo.put("telp_kantor",getTelp_k);
            jo.put("telp_pribadi",getTelp_p);
            jo.put("telp_darurat",gettelp_d);
            jo.put("nama_darurat",nama_d);
            jo.put("hub_darurat",hub_d);
            jo.put("alamat_darurat",alamat_d);
            jo.put("tgl_masuk",tgl_masuk);
            jo.put("limit",limit);
            jo.put("prov_u",getprov_k);
            jo.put("kota_u",getkota_k);
            jo.put("camat_u",getcamat_k);
            jo.put("lurah_u",getlurah_k);
            jo.put("min_pinjam",minimal);
            jo.put("id_trx",gettrx);
            jo.put("status_usaha",st_usaha);
            jo.put("lengkap",lengkap);
            jo.put("stat_user",stat_user);
            jo.put("jenkel",getsexv);
            
            jo.put("counter",Integer.parseInt(ctrku));
            
        }else if(hasilx.equalsIgnoreCase("2")){
            jo.put("resultcode","0001");
            jo.put("result","Data Tidak Ada");
            jo.put("datetime",dateString);
            jo.put("com","CEK_BRW");
            jo.put("counter",Integer.parseInt(ctrku));
        }else if(hasilx.equalsIgnoreCase("3")){
            jo.put("resultcode","0000");
            jo.put("stat_user", stat_user);
            jo.put("result","User tidak active");
            jo.put("datetime",dateString);
            jo.put("com","CEK_BRW");
            jo.put("counter",Integer.parseInt(ctrku));
        }else{
            jo.put("resultcode","0002");
            jo.put("datetime",dateString);
            jo.put("com","CEK_BRW");
            jo.put("counter",Integer.parseInt(ctrku));
        }
        ans = jo.toString();
        //System.out.println(ans);
    }
    
    public String getAns () {
        return ans;
    }
    
    public String getTelp () {
        return getTelp_p;
    }
    
    public String getEmail() {
        return getemail;
    }
    
}
