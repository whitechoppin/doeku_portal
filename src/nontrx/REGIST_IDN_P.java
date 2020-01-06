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
import modul.Konf;
import org.json.simple.JSONObject;

/**
 *
 * @author VINCENT
 */
public class REGIST_IDN_P extends Konf{
    public String hasilx,ans;
    
    public String[] katv;
    public JSONObject jo = new JSONObject();
    
    public SimpleDateFormat  formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public SimpleDateFormat formats2 = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime2 = new Date();
    public String dateString2= formats2.format(currentTime2);
      
    public REGIST_IDN_P (String getnama,String getalamat,String getEmail,String telp_p,String sumberku,String norek,
                        String narek,String bank,String fotosiup,String fototdp,String fotonpwp,String fotosk,String fotoakta,String pasv,String prov,
                        String kota,String camat,String lurah,String kpos,String smber_dana,String negaraku,String telp_k,String getcounter) {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            statv = con.prepareCall("{ call REGIST_IDN_P(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            statv.setString(1,getnama);
            statv.setString(2,getEmail);
            statv.setString(3,getalamat);
            statv.setString(4,telp_p);
            statv.setString(5,sumberku);
            statv.setString(6,narek);
            statv.setString(7,norek);
            statv.setString(8,bank);
            statv.setString(9,fotosiup);
            statv.setString(10,fototdp);
            statv.setString(11,fotonpwp);
            statv.setString(12,fotosk);
            statv.setString(13,fotoakta);
            statv.setString(14,pasv);
            statv.setString(15,dateString);
            statv.setString(16,prov);
            statv.setString(17,kota);
            statv.setString(18,camat);
            statv.setString(19,lurah);
            statv.setString(20,kpos);
            statv.setString(21,smber_dana);
            statv.setString(22,negaraku);
            statv.setString(23,telp_k);
            
            rs = statv.executeQuery();
            
            hasilx = statv.getString("stat");
            
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
            jo.put("com","REGIST_IDN_P");
            jo.put("counter",Integer.parseInt(getcounter));
            
        }else if(hasilx.equalsIgnoreCase("2")){
            jo.put("resultcode","0001");
            jo.put("result","Data User Sudah Ada");
            jo.put("datetime",dateString);
            jo.put("com","REGIST_IDN_P");
            jo.put("counter",Integer.parseInt(getcounter));
            
        }else{
            jo.put("resultcode","0002");
            jo.put("datetime",dateString);
            jo.put("com","REGIST_IDN_P");
            jo.put("counter",Integer.parseInt(getcounter));
            
        }
        ans = jo.toString();
        //System.out.println(ans);
    }
    
    public String getAns () {
        return ans;
    }
}
