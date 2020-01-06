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
public class COUNT_RATE extends Konf{
    public String id,pass,counter,hasilx,namax,ans,nova;
    public JSONObject jo=new JSONObject();
    public String[] katv;
    public List<String> list = new ArrayList<String>();
    
    public SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
      
    public COUNT_RATE(String cntrv) {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            
            /*statv = con.prepareCall("{call ACC_IDN(?,?,?,?) }");
            statv.setString(1, idv);
            
            statv.execute();
            
            hasilx = statv.getString("stat");
            id = statv.getString("idagen");
            nova = statv.getString("nova");*/
            
            hasilx = "1";
            
            //statv.close();
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
                jo.put("com","COUNT_RATE");
                jo.put("NPL",0);
                jo.put("TBK",100);
                jo.put("counter",Integer.parseInt(cntrv));
                
                ans = jo.toString();
        }else{
                jo.put("resultcode","0003");
                jo.put("datetime",dateString);
                jo.put("counter",Integer.parseInt(cntrv));
                jo.put("com","COUNT_RATE");
                
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