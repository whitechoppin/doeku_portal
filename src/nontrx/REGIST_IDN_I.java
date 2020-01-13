/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nontrx;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import modul.Konf;
import modul.Regist_Digisign;
import org.json.simple.JSONObject;

/**
 *
 * @author VINCENT
 */
public class REGIST_IDN_I extends Konf{
    public String hasilx,noid,ans;
    
    public String[] katv;
    public JSONObject jo = new JSONObject();
    
    public SimpleDateFormat  formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public SimpleDateFormat formats2 = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime2 = new Date();
    public String dateString2= formats2.format(currentTime2);
      
    public REGIST_IDN_I (String getnama,String getalamat,String getEmail,String telp_p,String sumberku,String norek,
                        String narek,String bank,String ktp,String fotodiri,String fotoktp,String fotonpwp,String pasv,String prov,
                        String kota,String camat,String lurah,String tgllahir,String kotalahir,String kpos,String smber_dana,String negara,String getcounter) {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            statv = con.prepareCall("{ call REGIST_IDN_I(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            statv.setString(1,getnama);
            statv.setString(2,getEmail);
            statv.setString(3,tgllahir);
            statv.setString(4,kotalahir);
            statv.setString(5,getalamat);
            statv.setString(6,telp_p);
            statv.setString(7,sumberku);
            statv.setString(8,narek);
            statv.setString(9,norek);
            statv.setString(10,bank);
            statv.setString(11,ktp);
            statv.setString(12,fotodiri);
            statv.setString(13,fotoktp);
            statv.setString(14,fotonpwp);
            statv.setString(15,pasv);
            statv.setString(16,dateString);
            statv.setString(17,prov);
            statv.setString(18,kota);
            statv.setString(19,camat);
            statv.setString(20,lurah);
            statv.setString(21,kpos);
            statv.setString(22,smber_dana);
            statv.setString(23,negara);
            
            rs = statv.executeQuery();
            
            hasilx = statv.getString("stat");
            noid = statv.getString("idagen");
            
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
            jo.put("com","REGIST_IDN_I");
            jo.put("counter",Integer.parseInt(getcounter));

            CEK_IDN_I cp = new CEK_IDN_I(noid,"773752");
            String emailku = cp.getEmail();
            String telpku = cp.getTelp();

            Regist_Digisign rd = new Regist_Digisign();
            try {
                rd.sendPeminjam(getnama,emailku,telpku,kpos,ktp,getalamat,prov,kota,camat,lurah,"Pria",tgllahir,tgllahir,"");
            } catch (Exception ex) {
                Logger.getLogger(REGIST_IDN_I.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else if(hasilx.equalsIgnoreCase("2")){
            jo.put("resultcode","0001");
            jo.put("result","Data User Sudah Ada");
            jo.put("datetime",dateString);
            jo.put("com","REGIST_IDN_I");
            jo.put("counter",Integer.parseInt(getcounter));
            
        }else{
            jo.put("resultcode","0002");
            jo.put("datetime",dateString);
            jo.put("com","REGIST_IDN_I");
            jo.put("counter",Integer.parseInt(getcounter));
            
        }
        ans = jo.toString();
        //System.out.println(ans);
    }
    
    public String getAns () {
        return ans;
    }
}
