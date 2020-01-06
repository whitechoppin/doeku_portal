package nontrx;

import modul.Konf;
import org.json.simple.JSONObject;

import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class INSERT_ID_AGEN extends Konf {

    public String agenID, agenNama, stat_user, hasilx, ans;
    public JSONObject jo = new JSONObject();
    public String[] katv;
    public List<String> list = new ArrayList<String>();

    public SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);

    public INSERT_ID_AGEN(String idAgen, String idBrw) throws Exception {

        try {
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            statv = con.prepareCall("{call INSERT_ID_AGEN(?,?,?) }");
            statv.setString(1, idAgen);
            statv.setString(2, idBrw);
            statv.execute();
            hasilx = statv.getString("stat");

            statv.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception ex) {
            }
        }
    }

    public String getAns() {
        return ans;
    }

    public String getResp() {
        return hasilx;
    }
}


