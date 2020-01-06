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
import nontrx.INSERT_ID_AGEN;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class TopUp {

	public TopUp(){}

	// HTTP POST request
	public String sendPost(String idagen,String pengirim,String rek,String bk,String bktuju,String rektuju,String nom,String tgltrans,String jamtrans) throws Exception {

		String url = "http://183.91.70.178:14000";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                Random rn=new Random();
                int num=rn.nextInt(10000);
         
		//add reuqest header
		String a = "hD!4dM1n54123@"+idagen+num;
		String b = ubahMD5(a);
		//String urlParameters = "<?xml version='1.0'?><posh><command>KIRIMSMS</command><counter>"+num+"</counter><password>"+b+"</password><idproduk>HDISYS</idproduk><notelp>"+nomor+"</notelp><isisms>"+kode+"</isisms></posh>";
		//String urlParameters = "<posh><command>KIRIMSMS</command><counter>"+num+"</counter><password>"+b+"</password><idproduk>HDISYS</idproduk><notelp>"+nomor+"</notelp><isisms>Kode DoeKu "+kode+" Silahkan Masukkan Segera Kode Anda.</isisms></posh>";
		String urlParameters = "<posh><command>KONFDEPOSITDOEKU</command><idagen>"+idagen+"</idagen><counter>"+num+"</counter><namapengirim>"+pengirim+"</namapengirim><rekeningpengirim>"+rek+"</rekeningpengirim><bankpengirim>"+bk+"</bankpengirim><banktujuan>"+bktuju+"</banktujuan><rekeningtujuan>"+rektuju+"</rekeningtujuan><nominal>"+nom+"</nominal><tanggaltransfer>"+tgltrans+"</tanggaltransfer><jamtransfer>"+jamtrans+"</jamtransfer><tipepembayaran>DOEKU</tipepembayaran><password>"+b+"</password><idproduk>HDISYS</idproduk></posh>";

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
		return response.toString();

	}

	public String resultCodeTopUp(String fXmlFile) {
		String resultCode = null;
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(new InputSource(new StringReader(fXmlFile)));
			doc.getDocumentElement().normalize();
			Node posh = doc.getElementsByTagName("posh").item(0);

			if (posh.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) posh;

				resultCode=eElement.getElementsByTagName("resultcode").item(0).getTextContent();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultCode;
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
        
        /*public static void main(String[] args) throws Exception {
            TopUp sms = new TopUp();
            //sms.sendPost("+6285241409231","12345");
            sms.sendPost("DP003435","","","","DOEKU","DOEKU","50000","13-05-2019","QE:31:11");
        }*/
}

