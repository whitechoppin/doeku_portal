package jsoner;

import nontrx.UPDATE_BRW;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Json_Update_BRW {
  public String Records,
          hasil,getnama,getalamat,getprov,getkota,getcamat,getlurah,getid,
          gettempat,getlahir,getktp,getnpwp,getemail,getprovk,getkotak,getcamatk,getlurahk,getkpos1,getkpos2,getjumlah,
          getnorek,getnamarek,getnbank,getusaha,getalamatusaha,gettelpk,gettelpp,gettelpr,gettelpd,getfoto_diri,getfoto_ktp,
          getfoto_npwp,getfoto_rek,getfoto_dalama,getfoto_luar,getfoto_depan,getstat_usaha,getlama,getnama_d,getalamat_d,gettlp_prd,
          gethub_d,getnikah,getsexs,getpass,getjml,getdurasi,getsuara,getdiri,getloc,getimeiku,getcounter;
  
    public Json_Update_BRW(String Records) throws Exception {
        this.Records=Records;
        
    JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(Records);
            
            JSONObject jsonObject = (JSONObject) obj;

            getid = jsonObject.get("id_brw").toString();
            getnama = jsonObject.get("nama").toString();
            
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
            gettelpr = jsonObject.get("telp_rumah").toString();
            getkpos1 = jsonObject.get("kodepos_pribadi").toString();
            getnikah = jsonObject.get("nikah").toString();
            
            getnorek = jsonObject.get("norek").toString();
            getnamarek = jsonObject.get("atas_nama").toString();
            getnbank = jsonObject.get("bank").toString();
            getprovk = jsonObject.get("provinsi_u").toString();
            getkotak = jsonObject.get("kota_u").toString();
            getcamatk = jsonObject.get("kecamatan_u").toString();
            getlurahk = jsonObject.get("kelurahan_u").toString();
            gettelpd = jsonObject.get("telp_darurat").toString();
            gettelpk = jsonObject.get("telp_usaha").toString();
            getkpos2 = jsonObject.get("kodepos_usaha").toString();
          
          getusaha = jsonObject.get("nama_usaha").toString();
          getalamatusaha = jsonObject.get("alamat_usaha").toString();
          gettlp_prd = jsonObject.get("telp_pribadi").toString();
          getfoto_diri = jsonObject.get("foto_diri").toString();
          getfoto_ktp = jsonObject.get("foto_ktp").toString();
          getfoto_npwp = jsonObject.get("foto_npwp").toString();
          getfoto_rek = jsonObject.get("foto_rek").toString();
          getfoto_dalama = jsonObject.get("foto_dalam").toString();
          getfoto_luar = jsonObject.get("foto_luar").toString();
          getfoto_depan = jsonObject.get("foto_depan").toString();
          getstat_usaha = jsonObject.get("status_usaha").toString();
          getlama = jsonObject.get("lama_usaha").toString();
          getnama_d = jsonObject.get("nama_darurat").toString();
          getalamat_d = jsonObject.get("alamat_darurat").toString();
          gethub_d = jsonObject.get("hub_darurat").toString();
          
          getcounter= jsonObject.get("counter").toString();
            
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    
        UPDATE_BRW CW= new UPDATE_BRW(getid,getnama,getsexs,getalamat,getprov,getkota,getcamat,getlurah,gettempat,getlahir
            ,getkpos1,gettelpr,getktp,getnpwp,getnikah,getjumlah
            ,getprovk,getkotak,getcamatk,getlurahk
            ,getkpos2,getnorek,getnamarek,getnbank,
            getusaha,getalamatusaha,gettelpk,gettelpd,getfoto_diri,getfoto_ktp,getfoto_npwp
            ,getfoto_rek,getfoto_dalama,getfoto_luar,getfoto_depan,getlama,getstat_usaha,getnama_d,getalamat_d,
            gethub_d,gettlp_prd,getcounter);
            
        hasil = CW.getAns();
    }
    public String getRes() {
        return hasil;
    }
}