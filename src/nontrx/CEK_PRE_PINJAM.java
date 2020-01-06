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
public class CEK_PRE_PINJAM extends Konf{
    public String id,counter,hasilx,nama,alasan,ans,bunga;
    public int jumlah,tenor;
    public JSONObject jo=new JSONObject();
    public String[] katv;
    public List<String> list = new ArrayList<String>();
    
    public SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
      
    public CEK_PRE_PINJAM(String idv,String cntrv) {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            
            statv = con.prepareCall("{call CEK_PRE_PINJAM(?,?,?,?,?,?) }");
            statv.setString(1, idv);
            
            /*statv.registerOutParameter(4, java.sql.Types.VARCHAR);
            statv.registerOutParameter(5, java.sql.Types.VARCHAR);*/
            
            statv.execute();
            
            hasilx = statv.getString("stat");
            
            id = statv.getString("idv");
            tenor = statv.getInt("tenorv");
            bunga = statv.getString("bungav");
            jumlah = statv.getInt("jumlahv");
            
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
                jo.put("com","CEK_PRE_PINJAM");
                jo.put("id_brw",id);
                jo.put("jumlah",jumlah);
                jo.put("tenor",tenor);
                jo.put("bunga",bunga);
                jo.put("alasan",alasan);
                jo.put("counter",Integer.parseInt(cntrv));
                
                ans = jo.toString();
        }else{
                jo.put("resultcode","0001");
                jo.put("datetime",dateString);
                jo.put("counter",Integer.parseInt(cntrv));
                jo.put("com","CEK_PRE_PINJAM");
                
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