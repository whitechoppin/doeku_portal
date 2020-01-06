package nontrx;

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

public class REGISTRASI_DAVESTPAY {

    public REGISTRASI_DAVESTPAY(){}

    public String sendPost(String namaAgen, String alamatAgen, String kelAgen, String kecAgen, String kotaAgen, String provAgen,
                           String kodePos,String telp,String email,String noRek, String namaBank, String namaRek, String counter) throws Exception
    {

        String passwordWeb = "Sk4t3l";
        String passwordTrx = "Hdi54321";
        String kk = "";
        String url = "http://183.91.70.178:14000";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        Random rn = new Random();
        int num = rn.nextInt(100000);

        //add reuqest header
        //String a = "hD!4dM1n"+num;
//        String a = kode + num;
//        String b = ubahMD5(a);

        //String urlParameters = "<?xml version='1.0'?><posh><command>LOGIN</command><counter>"+num+"</counter><idagen>"+userku+"</idagen><password>"+b+"</password><idproduk>HDISYS</idproduk></posh>";
        String urlParameters = "<?xml version='1.0'?><posh><command>REGISTRASI</command><namaagen>"+namaAgen+"</namaagen><alamat>"+alamatAgen+"</alamat><kelurahan>"+kelAgen+"</kelurahan>" +
                "<kecamatan>"+kecAgen+"</kecamatan><kota>"+kotaAgen+"</kota><provinsi>"+provAgen+"</provinsi><kodepos>"+kodePos+"</kodepos>"+
                "<telp>"+telp+"</telp><email>"+email+"</email>"+
                "<nomorrekening>"+noRek+"</nomorrekening><namabank>"+namaBank+"</namabank><atasnamarekening>"+namaRek+"</atasnamarekening>"+
                "<passwordweb>"+passwordWeb+"</passwordweb><passwordtrx>"+passwordTrx+"</passwordtrx><counter>"+counter+"</counter><idproduk>HDISYS</idproduk></posh>";

        String panjang = new Integer(urlParameters.length()).toString();

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : "+url);
        System.out.println("Post parameters : "+urlParameters);
        System.out.println("Response Code : "+responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

            while((inputLine =in.readLine())!=null)

        {
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

    public String insertIDAgen(String fXmlFile, String idBrw) {
        String response = null;
        String message  = null;
        String resultCode = null;
        String idAgen;
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = dBuilder.parse(new InputSource(new StringReader(fXmlFile)));
            doc.getDocumentElement().normalize();
            Node posh = doc.getElementsByTagName("posh").item(0);

            if (posh.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) posh;

                resultCode = eElement.getElementsByTagName("resultcode").item(0).getTextContent();
                System.out.println(resultCode);

                if(resultCode.equals("0000")) {
                    idAgen = eElement.getElementsByTagName("idagen").item(0).getTextContent();
                    INSERT_ID_AGEN insertIDAgen = new INSERT_ID_AGEN(idAgen, idBrw);
                    response = insertIDAgen.getResp();
                    System.out.println("Response Insert ID Agen :" + response);
                    message = eElement.getElementsByTagName("result").item(0).getTextContent();
                }

                if(resultCode.equals("0001")){
                    message = eElement.getElementsByTagName("result").item(0).getTextContent();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultCode+","+message;
    }
}
