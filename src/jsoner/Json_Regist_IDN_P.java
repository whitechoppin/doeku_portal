package jsoner;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author VINCENT
 */
import java.io.StringReader;

import nontrx.REGIST_IDN_P;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Json_Regist_IDN_P {
  public String Records,hasil,
          getnama,getalamat,getEmail,telp_p,sumberku,norek,telp_k,narek,bank,
          ktp,fotosiup,fototdp,fotonpwp,fotosk,fotoakta,pasv,prov,kota,negaraku,smber_dana,camat,lurah,getkpos,getcounter;
  
    public Json_Regist_IDN_P(String Records) throws Exception {
        this.Records=Records;
    
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getnama = jsonObject.get("nama").toString();
            getalamat = jsonObject.get("alamat").toString();
            getEmail = jsonObject.get("email").toString();
            telp_p = jsonObject.get("telp_pribadi").toString();
            telp_k = jsonObject.get("telp_kantor").toString();
            sumberku = jsonObject.get("sumber").toString();
            norek = jsonObject.get("norek").toString();
            narek = jsonObject.get("nama_rek").toString();
            bank = jsonObject.get("bank").toString();
            fotosiup = jsonObject.get("foto_siup").toString();
            fototdp = jsonObject.get("foto_tdp").toString();
            fotonpwp = jsonObject.get("foto_npwp").toString();
            fotosk = jsonObject.get("foto_sk").toString();
            fotoakta = jsonObject.get("foto_akta").toString();
            pasv = jsonObject.get("pass").toString();
            prov = jsonObject.get("provinsi").toString();
            kota = jsonObject.get("kota").toString();
            camat = jsonObject.get("kecamatan").toString();
            lurah = jsonObject.get("kelurahan").toString();
            getkpos = jsonObject.get("kode_pos").toString();
            smber_dana = jsonObject.get("sumber_dana").toString();
            negaraku = jsonObject.get("negara").toString();
     
            getcounter= jsonObject.get("counter").toString();
           
        } catch (Exception e) {
            e.printStackTrace();
        }    
    
        REGIST_IDN_P cw = new REGIST_IDN_P(getnama,getalamat,getEmail,telp_p,sumberku,norek,narek,bank,
                                        fotosiup,fototdp,fotonpwp,fotosk,fotoakta,pasv,prov,kota,camat,lurah,getkpos,smber_dana,negaraku,telp_k,getcounter);
    
    hasil = cw.getAns();
 }
    
    public String getRes() {
        return hasil;
    }
}