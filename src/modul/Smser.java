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

public class Smser {

	public Smser(){}

	// HTTP POST request
	public void sendPost(String nomor,String kode) throws Exception {

		String url = "http://183.91.70.178:14301";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                Random rn=new Random();
                int num=rn.nextInt(10000);
         
		//add reuqest header
		String a = "hD!4dM1n"+num;
                String b = ubahMD5(a);
                //String urlParameters = "<?xml version='1.0'?><posh><command>KIRIMSMS</command><counter>"+num+"</counter><password>"+b+"</password><idproduk>HDISYS</idproduk><notelp>"+nomor+"</notelp><isisms>"+kode+"</isisms></posh>";
                String urlParameters = "<?xml version='1.0'?><posh><command>KIRIMSMS</command><counter>"+num+"</counter><password>"+b+"</password><idproduk>HDISYS</idproduk><notelp>"+nomor+"</notelp><isisms>Kode DoeKu "+kode+" Silahkan Masukkan Segera Kode Anda.</isisms></posh>";
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
		System.out.println(response.toString());

	}
        
        public String ubahMD5(String password) throws NoSuchAlgorithmException {
            
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
            return sb.toString();
        
        }
        
        public static void main(String[] args) throws Exception {
            Smser sms = new Smser();
            //sms.sendPost("+6285241409231","12345");
            sms.sendPost("+628975034534","12345");
        }
}

