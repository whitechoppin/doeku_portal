package nontrx;

import modul.Konf;
import org.json.simple.JSONObject;

import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class INSERT_MITRA_BARU extends Konf {

    public String agenID,agenNama,stat_user,hasilx,ans;
    public JSONObject jo=new JSONObject();
    public String[] katv;
    public List<String> list = new ArrayList<String>();

    public SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);

    public INSERT_MITRA_BARU(String idAgen,String namaAgen, String alamatAgen, String kelAgen, String kecAgen, String kotaAgen, String provAgen,
                             String telpAgen, String emailAgen, String jumRekening, String norekAgen1, String namaBank1, String namaRekAgen1,
                             String norekAgen2, String namaBank2, String namaRekAgen2,String counter) throws Exception{

        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            statv = con.prepareCall("{call INSERT_MITRA_BARU(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            statv.setString(1,idAgen);
            statv.setString(2,namaAgen);
            statv.setString(3,alamatAgen);
            statv.setString(4,kelAgen);
            statv.setString(5,kecAgen);
            statv.setString(6,kotaAgen);
            statv.setString(7,provAgen);
            statv.setString(8,telpAgen);
            statv.setString(9,emailAgen);
            statv.setString(10,jumRekening);
            statv.setString(11,norekAgen1);
            statv.setString(12,namaBank1);
            statv.setString(13,namaRekAgen1);
            statv.setString(14,norekAgen2);
            statv.setString(15,namaBank2);
            statv.setString(16,namaRekAgen2);
            statv.execute();
            agenID = statv.getString("v_id_brw");
            hasilx = statv.getString("stat");
            stat_user = statv.getString("stat_user");
//            agenID = idAgen;
            agenNama = namaAgen;

            statv.close();
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try {
                con.close();
            } catch (Exception ex) {}
        }
    }

    public String getAns () {
        return ans;
    }

    public String getResp() {
        return hasilx+","+agenID+","+agenNama+","+stat_user;
    }

}
