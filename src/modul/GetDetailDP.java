/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modul;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Node;
import org.w3c.dom.Element;



public class GetDetailDP {

	public GetDetailDP(){}

	// HTTP POST request
	public String sendPost(String userku) throws Exception {
                String kk = "";
		String url = "http://183.91.70.178:14000";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                Random rn=new Random();
                int num=rn.nextInt(100000);
         
		//add reuqest header
		//String a = "hD!4dM1n"+num;
                //String a = kode+num;
                //String b = ubahMD5(a);
               
                String abc="";
                abc ="hD!4dM1n54123@"+num;
                String kode = ubahMD5(abc);
                //String urlParameters = "<?xml version='1.0'?><posh><command>LOGIN</command><counter>"+num+"</counter><idagen>"+userku+"</idagen><password>"+b+"</password><idproduk>HDISYS</idproduk></posh>";
                String urlParameters = "<?xml version='1.0'?><posh><command>CEKMITRA</command><counter>"+num+"</counter><idagen>HDI15003</idagen><idcek>"+userku+"</idcek><password>"+kode+"</password><idproduk>HDISYS</idproduk></posh>";
                
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
                String abcd = response.toString();
                
                /*DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(abc);*/
                
                String vw = abcd;
                //String vw = hasilku(abc);
                //String vw = hasilku(abc)+"*"+idku(abc)+"*"+namaku(abc)+"*"+pesanku(abc);
return vw;
//return hasilku(abc);
                
                
              
	}
            

  public String hasilku(String fXmlFile) {
      String k = "";
    try {

	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	org.w3c.dom.Document doc = dBuilder.parse(new InputSource(new StringReader(fXmlFile)));
			
	doc.getDocumentElement().normalize();
			
	Node posh = doc.getElementsByTagName("posh").item(0);
			
              
		if (posh.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) posh;

                        k=eElement.getElementsByTagName("resultcode").item(0).getTextContent();
		}
                
//	}
    } catch (Exception e) {
	e.printStackTrace();
    }
            return k;
  }
  
  public String idku(String fXmlFile) {
      String k = "";
    try {

	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	org.w3c.dom.Document doc = dBuilder.parse(new InputSource(new StringReader(fXmlFile)));
			
	doc.getDocumentElement().normalize();
			
	Node posh = doc.getElementsByTagName("posh").item(0);
			
              
		if (posh.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) posh;

                        k=eElement.getElementsByTagName("idagen").item(0).getTextContent();
		}
                
//	}
    } catch (Exception e) {
	e.printStackTrace();
    }
            return k;
  }
  
  public String namaku(String fXmlFile) {
      String k = "";
    try {

	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	org.w3c.dom.Document doc = dBuilder.parse(new InputSource(new StringReader(fXmlFile)));
			
	doc.getDocumentElement().normalize();
			
	Node posh = doc.getElementsByTagName("posh").item(0);
			
              
		if (posh.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) posh;

                        k=eElement.getElementsByTagName("namaagen").item(0).getTextContent();
		}
                
//	}
    } catch (Exception e) {
	e.printStackTrace();
    }
            return k;
  }
  
  public String pesanku(String fXmlFile) {
      String k = "";
    try {

	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	org.w3c.dom.Document doc = dBuilder.parse(new InputSource(new StringReader(fXmlFile)));
			
	doc.getDocumentElement().normalize();
			
	Node posh = doc.getElementsByTagName("posh").item(0);
			
              
		if (posh.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) posh;

                        k=eElement.getElementsByTagName("result").item(0).getTextContent();
		}
                
//	}
    } catch (Exception e) {
	e.printStackTrace();
    }
            return k;
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
            GetDetailDP lg =new GetDetailDP();
            String sendPost = lg.sendPost("DP003435");
            String has = lg.hasilku(sendPost);
            System.out.println(has);
    }*/
}



