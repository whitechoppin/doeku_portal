package modul;

import nontrx.INSERT_MITRA_BARU;
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
import java.util.Random;

public class CekMitraDP {

    public CekMitraDP(){}

    // HTTP POST request
    public String sendPost(String idAgen,String ctr,String username,String password) throws Exception {
        String kk = "";
        String url = "http://183.91.70.178:14000";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        Random rn=new Random();
        int num=rn.nextInt(100000);

        //add reuqest header
        //String a = "hD!4dM1n"+num;
//        String a = kode+num;
//        String b = ubahMD5(a);

        //String urlParameters = "<?xml version='1.0'?><posh><command>LOGIN</command><counter>"+num+"</counter><idagen>"+userku+"</idagen><password>"+b+"</password><idproduk>HDISYS</idproduk></posh>";
        String urlParameters = "<?xml version='1.0'?><posh><command>CEKMITRAADMIN</command><idagen>"+idAgen+"</idagen><counter>"+ctr+"</counter><username>"+username+"</username><password>"+password+"</password><idproduk>HDISYS</idproduk></posh>";

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

        System.out.println("Full response:"+response.toString());

        //print result
        //System.out.println(response.toString());
        String abc = response.toString();

		/*DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(abc);*/

        String vw = abc;
        //String vw = hasilku(abc);
        //String vw = hasilku(abc)+"*"+idku(abc)+"*"+namaku(abc)+"*"+pesanku(abc);
        return vw;
        //return hasilku(abc);
    }

    public String insertDataMitra(String fXmlFile, String counter) {
        String response = null;
        String idAgen,jenisMitra,namaAgen,alamatAgen,kelAgen,kecAgen,kotaAgen,provAgen,telpAgen,emailAgen,jumRekAgen,norekAgen1,bankAgen1,namaRekAgen1,norekAgen2,bankAgen2,namaRekAgen2;
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = dBuilder.parse(new InputSource(new StringReader(fXmlFile)));
            doc.getDocumentElement().normalize();
            Node posh = doc.getElementsByTagName("posh").item(0);

            if (posh.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) posh;

                idAgen=eElement.getElementsByTagName("idagen").item(0).getTextContent();
                jenisMitra=eElement.getElementsByTagName("jenismitra").item(0).getTextContent(); namaAgen = eElement.getElementsByTagName("namaagen").item(0).getTextContent();
                alamatAgen = eElement.getElementsByTagName("alamat").item(0).getTextContent(); kelAgen = eElement.getElementsByTagName("kelurahan").item(0).getTextContent();
                kecAgen = eElement.getElementsByTagName("kecamatan").item(0).getTextContent(); kotaAgen = eElement.getElementsByTagName("kota").item(0).getTextContent();
                provAgen = eElement.getElementsByTagName("provinsi").item(0).getTextContent(); telpAgen = eElement.getElementsByTagName("telp").item(0).getTextContent();
                emailAgen = eElement.getElementsByTagName("email").item(0).getTextContent();

                jumRekAgen = eElement.getElementsByTagName("jumlahrekening").item(0).getTextContent();
                norekAgen1 = eElement.getElementsByTagName("nomorrekening1").item(0).getTextContent(); bankAgen1 = eElement.getElementsByTagName("namabank1").item(0).getTextContent();
                namaRekAgen1 = eElement.getElementsByTagName("atasnamarekening1").item(0).getTextContent();

//              norekAgen2 = eElement.getElementsByTagName("nomorrekening2").item(0).getTextContent(); bankAgen2 = eElement.getElementsByTagName("namabank2").item(0).getTextContent();
//              namaRekAgen2 = eElement.getElementsByTagName("atasnamarekening2").item(0).getTextContent();

                INSERT_MITRA_BARU imb = new INSERT_MITRA_BARU(idAgen,namaAgen,alamatAgen,kelAgen,kecAgen,kotaAgen,provAgen,
                                        telpAgen,emailAgen,jumRekAgen,norekAgen1,bankAgen1,namaRekAgen1,"","","",counter);
                response = imb.getResp();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}
