/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trx;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import modul.Konf;
import nontrx.REGISTRASI_DAVESTPAY;
import org.json.simple.JSONObject;

/**
 *
 * @author VINCENT
 */

import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import modul.Konf;
import nontrx.REGISTRASI_DAVESTPAY;
import org.json.simple.JSONObject;

/**
 *
 * @author VINCENT
 */
public class PINJAM_BRW extends Konf{
    public String hasilx,ans,cekAgen,resultCode,message,ctrku;
    public String namaAgen,alamatAgen,kelAgen,kecAgen,kotaAgen,provAgen,kodePos,telp,email,norek,namaBank,namaRek,sttRegis;

    public String[] katv;
    public JSONObject jo = new JSONObject();
    public SimpleDateFormat  formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);

    public SimpleDateFormat formats2 = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime2 = new Date();
    public String dateString2= formats2.format(currentTime2);

    public PINJAM_BRW(String getid,String getnova,String jum_pinjam,String durasi,String tp,String suara,String diri,String loc,String getctr) {
        try {
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            statv = con.prepareCall("{ call CEK_REGISTRASI(?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?)}");
            statv.setString(1, getid);
            //Set Davestpay Register
            statv.execute();
            namaAgen = statv.getString("nama_agen");
            alamatAgen = statv.getString("alamat_agen");
            kelAgen = statv.getString("kel_agen");
            kecAgen = statv.getString("kec_agen");
            kotaAgen = statv.getString("kota_agen");
            provAgen = statv.getString("prov_agen");
            kodePos = statv.getString("kodepos");
            telp = statv.getString("telp");
            norek = statv.getString("nomorrekening");
            namaBank = statv.getString("nama_bank");
            namaRek = statv.getString("namarek");
            email = statv.getString("emailku");
            sttRegis = statv.getString("stat");
            ctrku = getctr;

            System.out.println("Name :"+namaAgen);

            if(sttRegis.equalsIgnoreCase("1")) {
                REGISTRASI_DAVESTPAY registrasiDavestpay = new REGISTRASI_DAVESTPAY();
                String result = registrasiDavestpay.sendPost(namaAgen, alamatAgen, kelAgen, kecAgen, kotaAgen, provAgen, kodePos, telp, email, norek, namaBank, namaRek, getctr);
                String response = registrasiDavestpay.insertIDAgen(result, getid);
                String[] splitArr=response.split(",");
                resultCode = splitArr[0];
                message = splitArr[1];
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
            }
        }

        if (sttRegis.equals("2")) {
            try {
                //Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                statv = con.prepareCall("{ call TRX_BRW(?,?,?,?,?,?,?,?,?,?," +
                        "?,?,?,?,?,?,?,?,?,?," +
                        "?,?) }");
                statv.setString(1, getid);
                statv.setString(2, getnova);
                statv.setInt(3, Integer.parseInt(jum_pinjam));
                statv.setInt(4, Integer.parseInt(durasi));
                statv.setInt(5, Integer.parseInt(tp));
                statv.setString(6, suara);
                statv.setString(7, diri);
                statv.setString(8, loc);

                rs = statv.executeQuery();
                hasilx = statv.getString("stat");
                ctrku = getctr;

                statv.close();
                con.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    con.close();
                } catch (SQLException ex)
                {

                }
            }

            if (hasilx.equalsIgnoreCase("1")) {
                jo.put("status_registrasi", resultCode);
                jo.put("message_registrasi",message);
                jo.put("resultcode", "0000");
                jo.put("datetime", dateString);
                jo.put("com", "PINJAM_BRW");
                jo.put("id_brw", getid);
                jo.put("jumlah", Integer.parseInt(jum_pinjam));
                jo.put("counter", Integer.parseInt(ctrku));
            } else if (hasilx.equalsIgnoreCase("3")) {
                jo.put("resultcode", "0001");
                jo.put("result", "ID Tidak Dikenali");
                jo.put("datetime", dateString);
                jo.put("com", "PINJAM_BRW");
                jo.put("counter", Integer.parseInt(ctrku));
            } else if (hasilx.equalsIgnoreCase("2")) {
                jo.put("resultcode", "0001");
                jo.put("result", "Proses Peminjaman Belum Dapat Diajukan Kembali");
                jo.put("datetime", dateString);
                jo.put("com", "PINJAM_BRW");
                jo.put("counter", Integer.parseInt(ctrku));
            } else if (hasilx.equalsIgnoreCase("5")) {
                jo.put("resultcode", "0001");
                jo.put("result", "Mohon Lengkapi Data Anda");
                jo.put("datetime", dateString);
                jo.put("com", "PINJAM_BRW");
                jo.put("counter", Integer.parseInt(ctrku));
            } else {
                jo.put("resultcode", "0001");
                jo.put("datetime", dateString);
                jo.put("com", "PINJAM_BRW");
                jo.put("counter", Integer.parseInt(ctrku));
            }
            ans = jo.toString();
            //System.out.println(ans);
        } else{
            jo.put("resultcode", "0002");
            jo.put("message_registrasi", message);
            jo.put("resultreg","FAILED REGISTER");
            jo.put("datetime", dateString);
            jo.put("com","PINJAM_BRW");
            jo.put("counter",Integer.parseInt(ctrku));
        }
        ans = jo.toString();
    }

    public String getAns () {
        return ans;
    }
}
