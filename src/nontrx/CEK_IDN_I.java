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
public class CEK_IDN_I extends Konf{
    public String hasilx,ans,ctrku;
    public String idx,getnama,
    getktp,getnpwp,getemail,getjumlah,getnorek,getnarek,getbank,getkantor,getSumber,
    getTelp_k,getTelp_p,tgl_masuk,getnova,getprov,getkota,getalamat,gettgllahir,getkotalahir,
            get,getlurah,getcamat,getfotodiri,getfotonpwp,getnegara,getfotoktp;
    
    
    public String[] katv;
    public JSONObject jo = new JSONObject();
    public SimpleDateFormat  formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public SimpleDateFormat formats2 = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime2 = new Date();
    public String dateString2= formats2.format(currentTime2);
      
    public CEK_IDN_I(String idku,String cntr) {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            statv = con.prepareCall("{ call CEK_IDN_I(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            
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
            getTelp_p = statv.getString("telp_px");
            getnova = statv.getString("nova");
            tgl_masuk = statv.getString("tgl_masukx");
            getprov = statv.getString("provx");
            getkota = statv.getString("kotax");
            getcamat = statv.getString("camatx");
            getlurah = statv.getString("lurahx");
            gettgllahir = statv.getString("tgl_lahirx");
            getkotalahir = statv.getString("kota_lahirx");
            getSumber = statv.getString("sumberx");
            getfotodiri = statv.getString("fto_diri");
            getfotonpwp = statv.getString("fto_npwp");
            getfotoktp = statv.getString("fto_ktp");
            getnegara = statv.getString("negaraku");
            
            
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
            jo.put("nova",getnova);
            jo.put("provinsi",getprov);
            jo.put("kota",getkota);
            jo.put("negara",getnegara);
            jo.put("kecamatan",getcamat);
            jo.put("kelurahan",getlurah);
            jo.put("tgl_lahir",gettgllahir);
            jo.put("kota_lahir",getkotalahir);
            
            jo.put("foto_diri",getfotodiri);
            jo.put("foto_ktp",getfotoktp);
            jo.put("foto_npwp",getfotonpwp);
            
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
