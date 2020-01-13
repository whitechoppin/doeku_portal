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
public class CEK_IDN_P extends Konf{
    public String hasilx,ans,ctrku;
    public String idx,getnama,
    getktp,getnpwp,getemail,getjumlah,getnorek,getnarek,getbank,getkantor,getAlamat_k,
    getTelp_k,getTelp_p,tgl_masuk,getnova,getprov,getkota,getalamat,
    getlurah,getcamat,getfototdp,getfotonpwp,getfotoakta,getnegara,getfotosiup,getfotosk;
    
    
    public String[] katv;
    public JSONObject jo = new JSONObject();
    public SimpleDateFormat  formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public SimpleDateFormat formats2 = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime2 = new Date();
    public String dateString2= formats2.format(currentTime2);
      
    public CEK_IDN_P(String idku,String cntr) {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            statv = con.prepareCall("{ call CEK_IDN_P(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            
            statv.setString(1,idku);
            
            rs = statv.executeQuery();
            
            idx = statv.getString("idku");
            getnama = statv.getString("namax");
            getktp = statv.getString("ktpx");
            getemail = statv.getString("emailx");
            getjumlah = statv.getString("saldox");
            getnorek = statv.getString("norekx");
            getnarek = statv.getString("nama_rekx");
            getbank = statv.getString("n_bankx");
            getTelp_p = statv.getString("telp_p");
            getnova = statv.getString("nova");
            tgl_masuk = statv.getString("tgl_masukx");
            getprov = statv.getString("provx");
            getkota = statv.getString("kotax");
            getcamat = statv.getString("camatx");
            getlurah = statv.getString("lurahx");
            getalamat = statv.getString("alamatx");
            getfotosk = statv.getString("fto_sk");
            getfotonpwp = statv.getString("fto_npwp");
            getfototdp = statv.getString("fto_tdp");
            getfotoakta = statv.getString("fto_akta");
            getfotosiup = statv.getString("fto_siup");
            getnegara = statv.getString("negaraku");
            getTelp_k = statv.getString("telp_kantor");
            
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
            jo.put("com","CEK_IDN_I");
            jo.put("id_idn",idx);
            jo.put("nama",getnama);
            jo.put("alamat",getalamat);
            jo.put("ktp",getktp);
            jo.put("email",getemail);
            jo.put("saldo",getjumlah);
            jo.put("norek",getnorek);
            jo.put("atas_nama",getnarek);
            jo.put("bank",getbank);
            jo.put("telp_pribadi",getTelp_p);
            jo.put("telp_kantor",getTelp_k);
            jo.put("nova",getnova);
            jo.put("provinsi",getprov);
            jo.put("kota",getkota);
            jo.put("kecamatan",getcamat);
            jo.put("kelurahan",getlurah);
            
            jo.put("foto_tdp",getfototdp);
            jo.put("foto_siup",getfotosiup);
            jo.put("foto_npwp",getfotonpwp);
            jo.put("foto_sk",getfotosk);
            jo.put("foto_akta",getfotoakta);
            jo.put("negara",getnegara);
            
            jo.put("tgl_masuk",tgl_masuk);
            jo.put("counter",Integer.parseInt(ctrku));
            
        }else if(hasilx.equalsIgnoreCase("2")){
            jo.put("resultcode","0001");
            jo.put("result","Data Tidak Ada");
            jo.put("datetime",dateString);
            jo.put("com","CEK_IDN");
            jo.put("counter",Integer.parseInt(ctrku));
            
        }else{
            jo.put("resultcode","0002");
            jo.put("datetime",dateString);
            jo.put("com","CEK_IDN");
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
