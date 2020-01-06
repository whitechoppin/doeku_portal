/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trx;

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
public class PULL_DANA extends Konf{
    public String hasilx,ans;
    
    public String[] katv;
    public JSONObject jo = new JSONObject();
    
    public SimpleDateFormat  formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public SimpleDateFormat formats2 = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime2 = new Date();
    public String dateString2= formats2.format(currentTime2);
    
    public SimpleDateFormat formats3 = new SimpleDateFormat("HH:mm:ss");
    public Date currentTime3 = new Date();
    public String dateString3 = formats3.format(currentTime3);
      
    public PULL_DANA (String getid,String bank,String norek,String nama,String jum,String getcounter) {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            statv = con.prepareCall("{ call PULL_DANA(?,?,?,?,?,?,?,?) }");
            statv.setString(1,getid);
            statv.setString(2,bank);
            statv.setString(3,norek);
            statv.setString(4,nama);
            statv.setInt(5,Integer.parseInt(jum));
            statv.setString(6,dateString2);
            statv.setString(7,dateString3);
            
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
            jo.put("com","TARIK_DANA");
            jo.put("id_idn",getid);
            jo.put("jumlah",Integer.parseInt(jum));
            jo.put("counter",Integer.parseInt(getcounter));
            
        }else if(hasilx.equalsIgnoreCase("2")){
            jo.put("resultcode","0001");
            jo.put("result","Saldo Tidak Mencukupi");
            jo.put("datetime",dateString);
            jo.put("com","TARIK_DANA");
            jo.put("counter",Integer.parseInt(getcounter));
            
        }else{
            jo.put("resultcode","0002");
            jo.put("datetime",dateString);
            jo.put("com","TARIK_DANA");
            jo.put("counter",Integer.parseInt(getcounter));
            
        }
        ans = jo.toString();
        //System.out.println(ans);
    }
    
    public String getAns () {
        return ans;
    }
}
