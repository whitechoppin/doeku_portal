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

import modul.BNI_Regist;
import modul.Konf;
import org.json.simple.JSONObject;

/**
 *
 * @author VINCENT
 */
public class CEK_PINJAM extends Konf{
    public String hasilx,ans,ctrku;
    public String bgg,tgll,idx,jthtempo,total,sisa_hari,cicil,idku,idnx,tenorku,jum_trf,jum_awal,asu,bgku,biaya,stt_v,pinjam,getstat;
    public String jumDenda,nama,email,trxAmout,idtrxva,tlp,countDenda;
    public Boolean setuju;
    
    public String[] katv;
    public JSONObject jo = new JSONObject();
    public SimpleDateFormat  formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public SimpleDateFormat formats2 = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime2 = new Date();
    public String dateString2= formats2.format(currentTime2);
      
    public CEK_PINJAM(String idx,String cntr) throws Exception {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            statv = con.prepareCall("{ call CEK_PINJAM(?,?,?,?,?,?,?,?,?,?," +
                                            "?,?,?,?,?,?,?,?,?,?" +
                                            ",?,?,?,?,?,?,?,?,?,?)}");
            
            statv.setString(1,idx);
            
            rs = statv.executeQuery();
            
            hasilx = statv.getString("stat");
            jthtempo = statv.getString("jatuh");
            total = statv.getString("totals");
            idku = statv.getString("idbrw");
            tenorku = statv.getString("tenorx");
            setuju =statv.getBoolean("setuju");
            idnx =statv.getString("idnx");
            jum_trf = "0";
            jum_awal = statv.getString("jum_ajuan");
            biaya = statv.getString("biayav");
            pinjam = statv.getString("jum_pinjamv");
            asu = statv.getString("asu");
            getstat = statv.getString("stv");
            bgku = statv.getString("bgku");
            tgll = statv.getString("tglku");
            cicil = statv.getString("cicilku");
            bgg = statv.getString("bg_persen");
            sisa_hari = statv.getString("sisa_hari");

            // Variable Update Billing
            countDenda = statv.getString("count_denda");
            nama = statv.getString("v_nama");
            email = statv.getString("v_email");
            tlp = statv.getString("telp");
            trxAmout = statv.getString("v_ttl_tagihan");
            idtrxva = statv.getString("idtrxva");
            jumDenda = statv.getString("jum_denda");

            /*if(getstat.equalsIgnoreCase("6")) {
                stt_v="1";
            }else{cek
                stt_v="0";
            }*/
            
            ctrku = cntr;
            
            statv.close();
            con.close();
            System.out.println(hasilx);
            System.out.println("Denda :"+countDenda);
            System.out.println("Tagihan :"+trxAmout);
        }catch(Exception ex){
             ex.printStackTrace();
        }finally{
            try {
              con.close();
            } catch (SQLException ex) {}
        }
        
        if(hasilx.equalsIgnoreCase("1")) {
            System.out.println("Posting to Update Billing :"+nama+","+email+","+tlp+","+trxAmout+","+idx+","+idtrxva);
            // Saat ada tagihan yang kena denda
            if(countDenda.equals("1")){
                BNI_Regist bni = new BNI_Regist();
                String nova = bni.setUpdateBilling(nama,email,tlp,trxAmout,idx,idtrxva);
            }

            jo.put("resultcode","0000");
            jo.put("datetime",dateString);
            jo.put("com","CEK_PINJAM");
            jo.put("id_trx",idx);
            jo.put("id_brw",idku);
            jo.put("id_idn",idnx);
            jo.put("jatuh_tempo",tgll);
            jo.put("sisa",total);
            jo.put("total",total);
            jo.put("bayar",0);
            jo.put("setuju",setuju);
            jo.put("tenor",tenorku);
            jo.put("asuransi",asu);
            jo.put("bunga",bgku);
            jo.put("cicil",cicil);
            jo.put("biaya",biaya);
            jo.put("jum_trf",jum_trf);
            jo.put("jum_awal",jum_awal);
            jo.put("jum_pinjam",pinjam);
            jo.put("bg",bgg);
            jo.put("sisa_hari",sisa_hari);
            jo.put("denda", jumDenda);
            jo.put("status_pinjam",stt_v);
        }else if(hasilx.equalsIgnoreCase("2")){
            jo.put("resultcode","0001");
            jo.put("result","Data Tidak Ada");
            jo.put("datetime",dateString);
            jo.put("com","CEK_PINJAM");
            jo.put("counter",Integer.parseInt(ctrku));
            
        }else{
            jo.put("resultcode","0002");
            jo.put("datetime",dateString);
            jo.put("com","CEK_PINJAM");
            jo.put("counter",Integer.parseInt(ctrku));
        }
        ans = jo.toString();
        //System.out.println(ans);
    }
    
    public String getAns () {
        return ans;
    }
}