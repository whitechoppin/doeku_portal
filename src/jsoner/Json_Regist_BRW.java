package jsoner;


import nontrx.REGIST_BRW;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Json_Regist_BRW {
  public String Records,
          getreferal,hasil,getnama,getalamat,getprov,getkota,getcamat,getlurah,
          gettempat,getlahir,getktp,getnpwp,getemail,getprovk,getkotak,getcamatk,getlurahk,getkpos1,getkpos2,getjumlah,
          getnorek,getnamarek,getnbank,getusaha,getalamatusaha,gettelpk,gettelpp,gettelpr,gettelpd,getfoto_diri,getfoto_ktp,
          getfoto_npwp,getfoto_rek,getfoto_dalama,getfoto_luar,getfoto_depan,getstat_usaha,getlama,getnama_d,getalamat_d,
          gethub_d,getnikah,getsexs,getpass,getjml,getdurasi,getsuara,getdiri,getloc,getimeiku,getcounter;
  
    public Json_Regist_BRW(String Records) throws Exception {
        this.Records=Records;
        
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;
 
            getnama = jsonObject.get("nama").toString();
            
            getemail = jsonObject.get("email").toString();
            gettelpp = jsonObject.get("telp_pribadi").toString();
            
            getalamat = jsonObject.get("alamat").toString();
            getprov = jsonObject.get("provinsi").toString();
            getkota = jsonObject.get("kota").toString();
            getcamat = jsonObject.get("kecamatan").toString();
            getlurah = jsonObject.get("kelurahan").toString();
            gettempat = jsonObject.get("tempat_lahir").toString();
            getlahir = jsonObject.get("tgl_lahir").toString();
            getktp = jsonObject.get("ktp").toString();
            getnpwp = jsonObject.get("npwp").toString();
            getjumlah = jsonObject.get("jum_tanggung").toString();
            
            getsexs = jsonObject.get("jenkel").toString();
            getpass = jsonObject.get("pass").toString();
            gettelpr = jsonObject.get("telp_rumah").toString();
            getkpos1 = jsonObject.get("kodepos_pribadi").toString();
            getnikah = jsonObject.get("nikah").toString();
     
            getreferal  = jsonObject.get("referal").toString();
            getimeiku = jsonObject.get("imei").toString();
            getcounter= jsonObject.get("counter").toString();
            
            //getreferal = "";
            
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    
    REGIST_BRW cv = new REGIST_BRW(getnama, getemail, gettelpp,getsexs,getpass,getimeiku,getalamat,getprov,
            getkota,getcamat,getlurah,gettempat,getlahir,getkpos1,gettelpr,getktp,getnpwp,getnikah,getjumlah,getreferal,getcounter);
    hasil = cv.getAns();
 }
    
    public String getRes() {
        return hasil;
    }
}