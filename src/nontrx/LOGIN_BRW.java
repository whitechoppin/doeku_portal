/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nontrx;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import modul.CekMitraDP;
import modul.Konf;
import modul.LoginDP;
import org.json.simple.JSONObject;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import sun.security.provider.MD5;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 *
 * @author VINCENT
 */
public class LOGIN_BRW extends Konf{
    public String id,pass,counter,hasilx,namax,ans,idku,stt;
    public JSONObject jo=new JSONObject();
    public String[] katv;
    public List<String> list = new ArrayList<String>();
    
    public SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public SimpleDateFormat formats2 = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime2 = new Date();
    public String dateString2 = formats2.format(currentTime2);
    
    public SimpleDateFormat formats3 = new SimpleDateFormat("HH:mm:ss ");
    public Date currentTime3 = new Date();
    public String dateString3 = formats3.format(currentTime3);
      
    public LOGIN_BRW(String idv,String pass,String passMD,String code,String ipku,String cntrv) {
        
        try{
            //System.out.println("Trying connect to database "+DB_URL);
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            //System.out.println("Success connect to database");
            
            statv = con.prepareCall("{call LOGIN_BRW(?,?,?,?,?,?,?,?,?,?) }");
            statv.setString(1, idv);
            statv.setString(2, pass);
            statv.setString(3, cntrv);
            statv.setString(4, code);
            statv.setString(5, ipku);
            statv.setString(6, dateString2);
            statv.setString(7, dateString3);
            /*statv.registerOutParameter(4, java.sql.Types.VARCHAR);
            statv.registerOutParameter(5, java.sql.Types.VARCHAR);*/
            
            statv.execute();
            
            hasilx = statv.getString("stat");
            namax = statv.getString("namaz");
            idku = statv.getString("idku");

            stt="0";
            if(!hasilx.equalsIgnoreCase("1")) {
                LoginDP dp = new LoginDP();
                String cek = dp.sendPost(idv,passMD,cntrv);

                String tes = dp.hasilku(cek);
                String idq = dp.idku(cek);

                if(tes.equals("0000")) {

                    REGIST_BRW_DP dpKU = new REGIST_BRW_DP(idq, cntrv);
                    hasilx = "1";
//                    idku = dpKU.getHasil();
//                    namax = dpKU.getnama();
//                    stt="1";

                    CEK_MITRA_BRW cekMitraBrw = new CEK_MITRA_BRW(idq);
                    String status = cekMitraBrw.getResp();

                    String password = "hD!4dM1n54123@"+cntrv;
                    String md5Password = md5(password);

                    if(status == null)
                    {
                        CekMitraDP cdp = new CekMitraDP();
                        String result = cdp.sendPost(idq,cntrv,"adminhdi",md5Password);
                        String response = cdp.insertDataMitra(result, cntrv);
                        String[] responseArr = response.split(",");

                        hasilx = responseArr[0];
                        idku = responseArr[1];
                        namax = responseArr[2];
                        stt = responseArr[3];

                        //System.out.println("Hasil :"+hasilx+","+idku+","+namax+","+stt);
                    }
                }
            }
            
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
                jo.put("com","LOGIN_BRW");
                jo.put("idbrw",idku);
                jo.put("nama",namax);
                jo.put("stat_user",stt);
                jo.put("counter",Integer.parseInt(cntrv));
                
                ans = jo.toString();
        }else if(hasilx.equalsIgnoreCase("2")) {
                jo.put("resultcode","0001");
                jo.put("result","Akun Anda Telah Di Non-Aktifkan");
                jo.put("datetime",dateString);
                jo.put("counter",Integer.parseInt(cntrv));
                jo.put("com","LOGIN_BRW");
                
                ans = jo.toString();
        }else{
                jo.put("resultcode","0003");
                jo.put("datetime",dateString);
                jo.put("counter",Integer.parseInt(cntrv));
                jo.put("com","LOGIN_BRW");
                
                ans = jo.toString();
        }  
    }
    
    public String getAns () {
        return ans;
    }
    
    public String getResp() {
        return hasilx;
    }

    public String md5(String password) throws Exception
    {
            String digest = null;
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(password.getBytes("UTF-8"));

            //converting byte array to Hexadecimal String
            StringBuilder sb = new StringBuilder(2*hash.length);
            for(byte b : hash)
            {
                sb.append(String.format("%02x", b&0xff));
            }
            digest = sb.toString();
            return digest;
    }
}