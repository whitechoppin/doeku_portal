/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modul;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vellei
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Notif_Req {
        public String idku;
	public Notif_Req(){}
        public JSONObject jo=new JSONObject();
	// HTTP POST request
        
        public void Notif_Req(){}
        
	public void Notif_ku(String emailku,String pesanku,String titleku) throws Exception {
                String tos = null;
		String url = "http://157.230.250.222:8081/api/v1/onesignal";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                Random rn=new Random();
                int num=rn.nextInt(10000);
         
		//add reuqest header
		
                
                /*jo.put("nama", nama);
                jo.put("email", email);
                jo.put("telephonel", telp);
                jo.put("trx_amount", jum);
                jo.put("day_expired", 1);
                jo.put("deskripsi","Tagihan Pinjaman DoeKu");
                
                String urlParameters = jo.toString();;*/
                String urlParameters = "email="+emailku+"&message="+pesanku+"titel="+titleku;
                //String urlParameters = "&message="+pesanku+"broadcast=peminjam";
         
                String panjang = new Integer(urlParameters.length()).toString();
	
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		//System.out.println(response.toString());
                
                 JSONParser parser = new JSONParser();
        

	}
        
        /*public static void main(String[] args) {
            try {
                    Notif_Req b = new Notif_Req();
                    b.Notif_ku("pinjam@gmail.com","Proses Pinjaman Anda Akan Kami Validasi Dalam Waktu 1x24 Jam","coba");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }*/
     
}

