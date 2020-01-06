/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nontrx;

import Emailer.Em_IDN_ACC;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modul.BNI_Regist;
import modul.Konf;
import static modul.Konf.DB_PASSWORD;
import static modul.Konf.DB_URL;
import static modul.Konf.DB_USER;
import modul.Notif_Req;
import org.json.simple.JSONObject;

/**
 *
 * @author VINCENT
 */
public class ACC_IDN_PINJAM extends Konf{
    public String id,pass,counter,hasilx,namax,ans,nova,idn,id_trx
            ,nama_pendana,nama_peminjam
            ,telp_pendana,telp_peminjam
            ,email_pendana,email_peminjam
            ,tagih_pendana,tagih_peminjam
            ,tgl_peminjam,tgl_pendana,id_trxku
            ;
    public JSONObject jo=new JSONObject();
    public String[] katv;
    public List<String> list = new ArrayList<String>();
    
    
    public SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
      
    public ACC_IDN_PINJAM(String idv,String nodocpeminjam, String nodocpendana,String nomor,String suara,String diri,String loc,String cntrv) throws Exception {
        this.idn=idv;
        this.id_trx=nomor;
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            
            statv = con.prepareCall("{call ACC_IDN_PINJAM(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            statv.setString(1, nomor);
            statv.setString(2, idv);
            statv.setString(3, nodocpeminjam);
            statv.setString(4, nodocpendana);
            statv.setString(5, suara);
            statv.setString(6, diri);
            statv.setString(7, loc);
            /*statv.registerOutParameter(4, java.sql.Types.VARCHAR);
            statv.registerOutParameter(5, java.sql.Types.VARCHAR);*/
            
            statv.execute();
            
            hasilx = statv.getString("stat");
            
            nama_pendana = statv.getString("nama_pendana");
            nama_peminjam = statv.getString("nama_peminjam");
            telp_pendana = statv.getString("tel_pendana");
            telp_peminjam = statv.getString("tel_peminjam");
            email_pendana = statv.getString("email_pendana");
            email_peminjam = statv.getString("email_peminjam");
            tagih_pendana = statv.getString("tagih_pendana");
            tagih_peminjam = statv.getString("tagih_peminjam");
            tgl_peminjam = statv.getString("tgl_peminjam");
            tgl_pendana = statv.getString("tgl_pendana");
            id_trxku = statv.getString("id_trxku");
            
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
                /*BNI_Regist bni = new BNI_Regist();
                String abc = bni.sendPendana(nama_pendana,email_pendana,telp_pendana,tgl_pendana,tagih_pendana,id_trxku);
                String cba = bni.setPeminjam(nama_peminjam,email_peminjam,telp_peminjam,tgl_peminjam,tagih_peminjam,id_trx);*/
                
                jo.put("resultcode","0000");
                jo.put("datetime",dateString);
                jo.put("com","ACC_IDN_PINJAM");
                jo.put("nova","-");
                jo.put("counter",Integer.parseInt(cntrv));
                
                ans = jo.toString();
                
                Notif_Req b = new Notif_Req();
                b.Notif_ku(email_peminjam,"Pinjaman Anda Telah DiSetujui Pendana Silahkan Tunggu Pencairan Dana Anda Di DavestPay","Pinjaman Diterima");
                //bni.sendPeminjam(nama_peminjam,email_peminjam,telp_peminjam,tgl_peminjam,tagih_peminjam);
            
                /*Em_IDN_ACC am = new Em_IDN_ACC();
            try 
                am.sendPost(id_trx, idn);
            } catch (Exception ex) {
                Logger.getLogger(ACC_IDN_PINJAM.class.getName()).log(Level.SEVERE, null, ex);
            }*/
                // Generate & Posting Report Pendana Peminjam
                //REPORT_PEMINJAM_PENDANA rpp = new REPORT_PEMINJAM_PENDANA(nomor, nodocpeminjam, nodocpendana);
        }else if(hasilx.equalsIgnoreCase("3")){
                jo.put("resultcode","0001");
                jo.put("result","Saldo Tidak Mencukupi");
                jo.put("datetime",dateString);
                jo.put("counter",Integer.parseInt(cntrv));
                jo.put("com","ACC_IDN_PINJAM");
                
                ans = jo.toString();
        }else if(hasilx.equalsIgnoreCase("2")){
                jo.put("resultcode","0001");
                jo.put("result","ID Pendana Tidak Dikenali");
                jo.put("datetime",dateString);
                jo.put("counter",Integer.parseInt(cntrv));
                jo.put("com","ACC_IDN_PINJAM");
                
                ans = jo.toString();
            
        }else{
                jo.put("resultcode","0001");
                jo.put("datetime",dateString);
                jo.put("counter",Integer.parseInt(cntrv));
                jo.put("com","ACC_IDN_PINJAM");
                
                ans = jo.toString();
        }  
    }
    
    public String getAns () {
        return ans;
    }
    
    public String getResp() {
        return hasilx;
    }
    
    /*public static void main(String[] args) throws NoSuchAlgorithmException {
        UbahMD5 kl = new UbahMD5("ashj234jas"+"28378");
        LOGIN l = new LOGIN("DPAY00003",kl.ubah(),"28378");
        System.out.println(l.getAns());
    }*/
}