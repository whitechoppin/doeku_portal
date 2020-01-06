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

public class Regist_Digisign {
    public String idku;
	public Regist_Digisign(){}
	public JSONObject jo=new JSONObject();
	// HTTP POST request
	public String sendPeminjam(String nama,String email,String telp,String kpos,String ktp,
        String alamat,String prov,String kota,String camat,String lurah,
        String jenkel,String tmp,String tgl,String npwp) throws Exception {
                //this.idku=notrx;
                String tos = null;
		String url = "http://api.davestpay.com/vPA/regist";
                //String url = "http://183.91.70.178/vPA/submit_digisign";
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
                String urlParameters = "nama="+nama+
                "&email="+email+
                "&tlp="+telp+"&kpos="+kpos+"&alamat="+alamat+
		"&ktp="+ktp+"&prov="+prov+"&kota="+kota+"&camat="+camat+"&lurah="+lurah+
		"&jenkel="+jenkel+"&tmp="+tmp+"&tgl="+tgl+"&npwp="+npwp;
         
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
                
                 /*JSONParser parser = new JSONParser();
        
                try {
                    Object objV = parser.parse(response.toString());

                    JSONObject jsonObject = (JSONObject) objV;

                    String tes = jsonObject.get("data").toString();
                
                    try {
                    Object objx = parser.parse(tes);

                    JSONObject jsonObject2 = (JSONObject) objx;

                    tos = jsonObject2.get("virtual_account").toString();
                    //System.out.println(tos);
                    Daftar_IDN_NOVA idn = new Daftar_IDN_NOVA(idku,tos);


                } catch (Exception e) {
                    e.printStackTrace();
                    e.getMessage();
                }

                } catch (Exception e) {
                    e.printStackTrace();
                    e.getMessage();
                }*/
                
                return tos;

	}

	public String doPayment(String idTrx, String debitNumber, String creditNumber, String valueAmount, String emailAddress,
                            String paymentMethod, String beneficiaryName, String destBankCode, String beneficiaryAddress,
                            String remark) throws Exception{
        this.idku=idTrx;
        String tos = null;
        String status = null;
        String nidtrxva = null;
        String url = "http://157.230.250.222:8081/api/do-payment";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        Random rn=new Random();
        int num=rn.nextInt(10000);

        String urlParameters = "debitAccountNo="+debitNumber+"&creditAccountNo="+creditNumber+"&valueAmount="+valueAmount
                +"&beneficiaryEmailAddress="+emailAddress+"&paymentMethod="+paymentMethod+"&beneficiaryName="+beneficiaryName
                +"&destinationBankCode="+destBankCode+"&beneficiaryAddress1="+beneficiaryAddress+"&remark="+remark;

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

        JSONParser parser = new JSONParser();
        try {
            Object objV = parser.parse(response.toString());

            JSONObject jsonObject = (JSONObject) objV;
            status = jsonObject.get("status").toString();
            JSONObject dataObj = (JSONObject) jsonObject.get("data");

            try {
                String payResponse = dataObj.get("doPaymentResponse").toString();
                System.out.println(payResponse);

                Object payResponseObj = parser.parse(payResponse);
                JSONObject objPay = (JSONObject) payResponseObj;

                String clientID = objPay.get("clientId").toString();
                String parameter = objPay.get("parameters").toString();

                Object paramObj = parser.parse(parameter);
                JSONObject objParam = (JSONObject) paramObj;

                String creditAccount = objParam.get("creditAccountNo").toString();
                String responseMessage = objParam.get("responseMessage").toString();
                String responseDoPay = objParam.get("responseCode").toString();

                tos = creditAccount+","+responseMessage+","+responseDoPay;

                System.out.println(tos);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tos;
    }

    public String setUpdateBilling(String nama,String email,String telp,String jum,String notrx,String idtrxva) throws Exception {
        this.idku=notrx;
        String tos = null;
        String status = null;
        String nidtrxva = null;
        String url = "http://157.230.250.222:8081/api/updatebilling";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        Random rn=new Random();
        int num=rn.nextInt(10000);

        String urlParameters = "nama="+nama+"&email="+email+"&telephone="+telp+"&trx_amount="+jum+"&trx_id="+idtrxva+"&day_expired=1&deskripsi=Update Tagihan";

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

        try {
            Object objV = parser.parse(response.toString());

            JSONObject jsonObject = (JSONObject) objV;
            status = jsonObject.get("status").toString();
            String tes = jsonObject.get("data").toString();

            try {
                Object objx = parser.parse(tes);
                JSONObject jsonObject2 = (JSONObject) objx;


                if(status.equals("200")) {
                    tos = jsonObject2.get("virtual_account").toString();
                    nidtrxva = jsonObject2.get("trx_id").toString();
                    Daftar_BRW_NOVA idn = new Daftar_BRW_NOVA(idku, tos, nidtrxva, "2");
                } else {
                    String newTos = setPeminjam(nama,email,telp,"",jum,notrx);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tos;
    }
        
	public String setPeminjam(String nama,String email,String telp,String tgl,String jum,String notrx) throws Exception {
                this.idku=notrx;
                String tos = null;
                String idva = null;
		String url = "http://157.230.250.222:8081/api/createbilling";
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
                String urlParameters = "nama="+nama+"&email="+email+"&telephone="+telp+"&trx_amount="+jum+"&day_expired=1&deskripsi=Pendana";
         
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
                System.out.println(response.toString());
                JSONParser parser = new JSONParser();

                try {
                    Object objV = parser.parse(response.toString());

                    JSONObject jsonObject = (JSONObject) objV;

                    String tes = jsonObject.get("data").toString();
                
                    try {
                    Object objx = parser.parse(tes);

                    JSONObject jsonObject2 = (JSONObject) objx;

                    tos = jsonObject2.get("virtual_account").toString();
                    idva = jsonObject2.get("trx_id").toString();

                    //System.out.println(tos);
                    Daftar_BRW_NOVA idn = new Daftar_BRW_NOVA(idku, tos, idva,"1");

                } catch (Exception e) {
                    e.printStackTrace();
                        e.getMessage();
                }

                } catch (Exception e) {
                    e.printStackTrace();
                    e.getMessage();
                }
                
                return tos;

	}
      /* public String sendPeminjam(String nama,String email,String telp,String tgl,String jum,String tenor,String notrx) throws Exception {

		this.idku=notrx;
                String tos = null;
		String url = "http://157.230.250.222:8081/api/createbilling";
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
               /* String urlParameters = "nama="+nama+"&email="+email+"&telephone="+telp+"&trx_amount="+jum+"&day_expired="+tenor+"&deskripsi=Peminjam";
         
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
             
                //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                 /*JSONParser parser = new JSONParser();
        
                try {
                    Object objV = parser.parse(response.toString());

                    JSONObject jsonObject = (JSONObject) objV;

                    String tes = jsonObject.get("data").toString();
                
                    try {
                    Object objx = parser.parse(tes);

                    JSONObject jsonObject2 = (JSONObject) objx;

                    tos = jsonObject2.get("virtual_account").toString();
                    //System.out.println(tos);
                    Daftar_BRW_NOVA idn = new Daftar_BRW_NOVA(idku,tos);


                } catch (Exception e) {
                    e.printStackTrace();
                }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                  //System.out.println("masuk ji()");
                 return tos;

                
	}*/
        
        /*public static void main(String[] args) throws Exception {
        
            BNI_Regist b = new BNI_Regist();
            String av = b.sendPendana("cococo","vt3st3r@gmail.com","081242321567","10","3000","");
            System.out.print(av);;
        
        }*/

        public static void main(String[] args) throws Exception {
            Regist_Digisign ab = new Regist_Digisign();
            ab.sendPeminjam("tester","tribuntes1@gmail.com","08124204366","94111","73618748923","jl tes","sulawesi tengah", "poso", "camat", "lurah", "wanita", "jakarta", "1980-09-28", "316789450353453");
;        }
        
        
}

