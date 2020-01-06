package nontrx;

import modul.Konf;
import org.json.simple.JSONObject;

import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CEK_MITRA_BRW extends Konf {

    public String idAgen,hasilx,ans;
    public JSONObject jo=new JSONObject();
    public String[] katv;
    public List<String> list = new ArrayList<String>();

    public SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);

    public CEK_MITRA_BRW(String idv) {

        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            statv = con.prepareCall("{call CEK_MITRA_BRW(?,?) }");
            System.out.println("ID Agen"+idv);
            statv.setString(1,idv);
            statv.execute();
            hasilx = statv.getString("v_nama");
            System.out.println("Return SP :"+statv.getString("v_nama"));
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

    public String getHasil() {
        return idAgen;
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
