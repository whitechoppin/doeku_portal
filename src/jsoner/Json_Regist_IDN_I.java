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

import nontrx.REGIST_IDN_I;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Json_Regist_IDN_I {
  public String Records,hasil,
          getnama,getalamat,getEmail,telp_p,sumberku,norek,narek,bank,
          ktp,fotodiri,fotoktp,fotonpwp,smber_dana,pasv,prov,kota,getnegara,camat,lurah,tgllahir,kotalahir,getkpos,getcounter;
  
    public Json_Regist_IDN_I(String Records) throws Exception {
        this.Records=Records;
    
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getnama = jsonObject.get("nama").toString();
            getalamat = jsonObject.get("alamat").toString();
            getEmail = jsonObject.get("email").toString();
            telp_p = jsonObject.get("telp_pribadi").toString();
            sumberku = jsonObject.get("sumber").toString();
            norek = jsonObject.get("norek").toString();
            narek = jsonObject.get("nama_rek").toString();
            bank = jsonObject.get("bank").toString();
            ktp = jsonObject.get("ktp").toString();
            fotodiri = jsonObject.get("foto_diri").toString();
            fotoktp = jsonObject.get("foto_ktp").toString();
            fotonpwp = jsonObject.get("foto_npwp").toString();
            pasv = jsonObject.get("pass").toString();
            prov = jsonObject.get("provinsi").toString();
            kota = jsonObject.get("kota").toString();
            camat = jsonObject.get("kecamatan").toString();
            lurah = jsonObject.get("kelurahan").toString();
            tgllahir = jsonObject.get("tgl_lahir").toString();
            kotalahir = jsonObject.get("kota_lahir").toString();
            getkpos = jsonObject.get("kode_pos").toString();
            smber_dana = jsonObject.get("sumber_dana").toString();
            //getnegara = jsonObject.get("negara").toString();
            getnegara = "-";
     
            getcounter= jsonObject.get("counter").toString();
           
        } catch (Exception e) {
            e.printStackTrace();
        }    
    
    REGIST_IDN_I cw = new REGIST_IDN_I(getnama,getalamat,getEmail,telp_p,sumberku,norek,narek,bank,
                                        ktp,fotodiri,fotoktp,fotonpwp,pasv,prov,kota,camat,lurah,tgllahir,kotalahir,getkpos,smber_dana,getnegara,getcounter);
    
    hasil = cw.getAns();
 }
    
    public String getRes() {
        return hasil;
    }
}