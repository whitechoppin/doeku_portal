/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nontrx;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import modul.Konf;
import org.json.simple.JSONObject;

/**
 *
 * @author VINCENT
 */
public class SIM_HITUNG extends Konf{
    public String hasilx,ans,ctrku;
    public String tgljatuhtempo;
    public int bungarupiah,denda,asuransi,biaya,komisi,sisa,kms,jum_terima;
    public float total;
    public float bungapersen;
    public String bungapersen_text;
    
    public String[] katv;
    public JSONObject jo = new JSONObject();
    public SimpleDateFormat  formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public SimpleDateFormat formats2 = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime2 = new Date();
    public String dateString2= formats2.format(currentTime2);
    public String dt = dateString2;  // Start date
              
    public SIM_HITUNG(int jum,int tenor,String cntr) {
        
        try{
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            statv = con.prepareCall("{ call GET_SET(?,?)}");
            
            statv.setString(1,"all");
            
            rs = statv.executeQuery();
            
            while(rs.next()){
                   kms = rs.getInt("biaya_layanan");
            }
            
            //idx = statv.getString("kode");
            
            ctrku = cntr;
            
            statv.close();
            con.close();
            
            ctrku = cntr;
           
           asuransi = 2*jum/100; 
           
            
           if(tenor==61) {
               biaya = 4*jum/100;
               total = Math.round(jum+(((0.15/100)*tenor)*jum)+asuransi+biaya);
               bungapersen = (float)0.15;
               bungapersen_text = "0.15";
           }else if(tenor==75) {
               biaya = 5*jum/100;
               total = Math.round(jum+(((0.13/100)*tenor)*jum)+asuransi+biaya);
               bungapersen = (float)0.13;
               bungapersen_text = "0.13";
           }else{
               biaya = 6*jum/100;
               total = Math.round(jum+(((0.10/100)*tenor)*jum)+asuransi+biaya);
               bungapersen = (float)0.10;
               bungapersen_text = "0.10";
           }
           
           bungarupiah = (int) (jum*(bungapersen/100))*tenor;
           denda =  (int) (jum*(0.1/100));
           
           total=0;
           total = jum+bungarupiah;
           jum_terima = jum-biaya-asuransi;
            /*bunga16 = Math.round((jum*16/12/30*tenor)/100)+jum;
            bunga18 = Math.round((jum*18/12/30*tenor)/100)+jum;
            bunga20 = Math.round((jum*20/12/30*tenor)/100)+jum;*/
            
            komisi = ((jum*kms)/100);
            
            //sisa = jum-komisi+bunga;
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(dt));
            c.add(Calendar.DATE, tenor);  // number of days to add
            dt = sdf.format(c.getTime());
            
            hasilx="1";
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
            jo.put("com","SIM_HITUNG");
            jo.put("bunga_persen",bungapersen_text);
            jo.put("bunga_rupiah",bungarupiah);
            jo.put("denda",denda);
            jo.put("biaya",biaya);
            jo.put("asuransi",asuransi);
            jo.put("total",total);
            jo.put("jum_terima",jum_terima);
            jo.put("jatuh_tempo",dt);
            jo.put("counter",Integer.parseInt(ctrku));
        }else{
            jo.put("resultcode","0002");
            jo.put("datetime",dateString);
            jo.put("com","SIM_HITUNG");
            jo.put("counter",Integer.parseInt(ctrku));
        }
        ans = jo.toString();
        //System.out.println(ans);
    }

    public String getAns () {
        return ans;
    }
}
