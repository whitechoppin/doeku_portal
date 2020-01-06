/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modul;

import auto_dana.Json_AUTO_DANA;
import auto_dana.Json_GET_AUTO_DANA;
import auto_dana.Json_STOP_DANA;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import jsoner.JSON_lIST_faq;
import jsoner.Json_ACC_IDN;
import jsoner.Json_ACC_IDN_PINJAM;
import jsoner.Json_ACC_PINJAM;
import jsoner.Json_CEK_BRW;
import jsoner.Json_CEK_IDN_I;
import jsoner.Json_CEK_IDN_P;
import jsoner.Json_CEK_PINJAM;
import jsoner.Json_CEK_PRE_PINJAM;
import jsoner.Json_DENDA;
import jsoner.Json_Days_Left;
import jsoner.Json_EXEC_NUMB;
import support.Json_END_MSG;
import jsoner.Json_EXEC_V;
import jsoner.Json_Email_Verify;
import jsoner.Json_HIST_DRAW;
import jsoner.Json_HIST_PAY;
import jsoner.Json_HIST_TOPUP;
import jsoner.Json_LIST_ACC_PINJAM;
import jsoner.Json_LIST_PINJAM;
import jsoner.Json_LOG_PINJAM;
import jsoner.Json_Login_BRW;
import jsoner.Json_Login_IDN;
import jsoner.Json_PINJAM_BRW;
import jsoner.Json_PULL_DANA;
import jsoner.Json_REQ_V;
import jsoner.Json_Rec_Loc;
import jsoner.Json_Regist_BRW;
import jsoner.Json_Regist_IDN_I;
import jsoner.Json_Regist_IDN_P;
import jsoner.Json_Reject_R;
import jsoner.Json_Reset_Pass;
import jsoner.Json_Reset_Pass_V;
import jsoner.Json_Sim_Hitung;
import jsoner.Json_Ubah_Pass;
import jsoner.Json_Update_BRW;
import jsoner.Json_VERIFY_NUMB;
import jsoner.Json_VERIFY_PAY;
import jsoner.Json_count_rate;
import jsoner.Jsonerv;

import org.json.simple.JSONObject;
import support.Json_ACC_MSG;
import support.Json_CEK_MSG;
import support.Json_DET_MSG;
import support.Json_RESP_MSG;
import support.Json_Reject_MSG;

/**
 *
 * @author VINCENT
 */
public class Cek_Utama extends Thread{
    protected String com,hasil,req,ket,exthasil,ipku;
   
    public SimpleDateFormat  formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public JSONObject jo = new JSONObject();
    
    public Cek_Utama(String req,String ipku) {
        this.req=req;
        this.ipku=ipku;
    }
  
    @Override
    public void run() {
        
        exthasil = "";
        
        try {
            Jsonerv ext = new Jsonerv(req);
            ext.bacaJSON();
            com = ext.getCom();
            
            System.out.println("Terima Command : "+com);
            
            if(com.equalsIgnoreCase("LOGIN_BRW")) {
                Json_Login_BRW cv = new Json_Login_BRW(req,ipku);
                hasil = cv.getRes();
                ket="NONTRX";
            }else if(com.equalsIgnoreCase("LOGIN_IDN")){
                Json_Login_IDN cv1 = new Json_Login_IDN(req,ipku);
                hasil = cv1.getRes();
                ket="NONTRX";
            }else if(com.equalsIgnoreCase("REGIST_BRW")){
                Json_Regist_BRW cv2 = new Json_Regist_BRW(req);
                hasil = cv2.getRes();
                ket="NONTRX";
            }else if(com.equalsIgnoreCase("REGIST_IDN_I")){
                Json_Regist_IDN_I cv3 = new Json_Regist_IDN_I(req);
                hasil = cv3.getRes();
                ket="NONTRX";
            }else if(com.equalsIgnoreCase("REGIST_IDN_P")){
                Json_Regist_IDN_P cv3 = new Json_Regist_IDN_P(req);
                hasil = cv3.getRes();
                ket="NONTRX";    
            }else if(com.equalsIgnoreCase("ACC_IDN")){
                Json_ACC_IDN cv4 = new Json_ACC_IDN(req);
                hasil = cv4.getRes();
                ket="NONTRX";   
            }else if(com.equalsIgnoreCase("CEK_BRW")){
                Json_CEK_BRW cv5 = new Json_CEK_BRW(req);
                hasil = cv5.getRes();
                ket="NONTRX";
            }else if(com.equalsIgnoreCase("CEK_IDN_I")){
                Json_CEK_IDN_I cv6 = new Json_CEK_IDN_I(req);
                hasil = cv6.getRes();
                ket="NONTRX";    
            }else if(com.equalsIgnoreCase("CEK_IDN_P")){
                Json_CEK_IDN_P cv6 = new Json_CEK_IDN_P(req);
                hasil = cv6.getRes();
                ket="NONTRX";    
            }else if(com.equalsIgnoreCase("HIST_TOPUP")){
                Json_HIST_TOPUP cv7 = new Json_HIST_TOPUP(req);
                hasil = cv7.getRes();
                ket="NONTRX";        
            }else if(com.equalsIgnoreCase("ACC_PINJAM")){
                Json_ACC_PINJAM cv7 = new Json_ACC_PINJAM(req);
                hasil = cv7.getRes();
                ket="NONTRX";    
            }else if(com.equalsIgnoreCase("LIST_PINJAMAN")){
                Json_LIST_PINJAM cv7 = new Json_LIST_PINJAM(req);
                hasil = cv7.getRes();
                ket="NONTRX";    
            }else if(com.equalsIgnoreCase("LIST_ACC_PINJAM")){
                Json_LIST_ACC_PINJAM cv7 = new Json_LIST_ACC_PINJAM(req);
                hasil = cv7.getRes();
                ket="NONTRX";    
            }else if(com.equalsIgnoreCase("CEK_PRE_PINJAM")){
                Json_CEK_PRE_PINJAM cv7 = new Json_CEK_PRE_PINJAM(req);
                hasil = cv7.getRes();
                ket="NONTRX";     
            }else if(com.equalsIgnoreCase("CONF_PASS")){
                Json_Reset_Pass_V cv7 = new Json_Reset_Pass_V(req);
                hasil = cv7.getRes();
                ket="NONTRX";     
            }else if(com.equalsIgnoreCase("CEK_PINJAM")){
                Json_CEK_PINJAM cv7 = new Json_CEK_PINJAM(req);
                hasil = cv7.getRes();
                ket="NONTRX";     
            }else if(com.equalsIgnoreCase("HIST_PAY")){
                Json_HIST_PAY cv7 = new Json_HIST_PAY(req);
                hasil = cv7.getRes();
                ket="NONTRX";     
            }else if(com.equalsIgnoreCase("REQ_V")){
                Json_REQ_V cv7 = new Json_REQ_V(req);
                hasil = cv7.getRes();
                ket="NONTRX";    
            }else if(com.equalsIgnoreCase("EXEC_V")){
                Json_EXEC_V cv7 = new Json_EXEC_V(req);
                hasil = cv7.getRes();
                ket="NONTRX";    
            }else if(com.equalsIgnoreCase("RESET_PASS")){
                Json_Reset_Pass cv7 = new Json_Reset_Pass(req);
                hasil = cv7.getRes();
                ket="NONTRX";    
            }else if(com.equalsIgnoreCase("END_MSG")){
                Json_END_MSG cv7 = new Json_END_MSG(req);
                hasil = cv7.getRes();
                ket="NONTRX";    
            }else if(com.equalsIgnoreCase("SIM_HITUNG")){
                Json_Sim_Hitung cv7 = new Json_Sim_Hitung(req);
                hasil = cv7.getRes();
                ket="NONTRX";    
            }else if(com.equalsIgnoreCase("LOG_PINJAMAN")){
                Json_LOG_PINJAM cv7 = new Json_LOG_PINJAM(req);
                hasil = cv7.getRes();
                ket="NONTRX";     
            }else if(com.equalsIgnoreCase("HIST_WITHDRAW")){
                Json_HIST_DRAW cv7 = new Json_HIST_DRAW(req);
                hasil = cv7.getRes();
                ket="NONTRX";    
            }else if(com.equalsIgnoreCase("UBAH_PASS")){
                Json_Ubah_Pass cv7 = new Json_Ubah_Pass(req);
                hasil = cv7.getRes();
                ket="NONTRX";    
            }else if(com.equalsIgnoreCase("ACC_MSG")){
                Json_ACC_MSG cv7 = new Json_ACC_MSG(req);
                hasil = cv7.getRes();
                ket="PESAN";    
            }else if(com.equalsIgnoreCase("RESP_MSG")){
                Json_RESP_MSG cv7 = new Json_RESP_MSG(req);
                hasil = cv7.getRes();
                ket="PESAN";    
            }else if(com.equalsIgnoreCase("CEK_MSG")){
                Json_CEK_MSG cv7 = new Json_CEK_MSG(req);
                hasil = cv7.getRes();
                ket="PESAN";    
            }else if(com.equalsIgnoreCase("DETAIL_MSG")){
                Json_DET_MSG cv7 = new Json_DET_MSG(req);
                hasil = cv7.getRes();
                ket="PESAN";    
            }else if(com.equalsIgnoreCase("PINJAM_BRW")){
                Json_PINJAM_BRW cv8 = new Json_PINJAM_BRW(req);
                hasil = cv8.getRes();
                ket="TRX";    
            }else if(com.equalsIgnoreCase("DENDA")){
                Json_DENDA cv8 = new Json_DENDA(req);
                hasil = cv8.getRes();
                ket="TRX";    
            }else if(com.equalsIgnoreCase("ACC_IDN_PINJAM")){
                Json_ACC_IDN_PINJAM cv8 = new Json_ACC_IDN_PINJAM(req);
                hasil = cv8.getRes();
                ket="TRX";
            }else if(com.equalsIgnoreCase("REJECT_RENT")){
                Json_Reject_R cv8 = new Json_Reject_R(req);
                hasil = cv8.getRes();
                ket="TRX";    
            }else if(com.equalsIgnoreCase("REJECT_MSG")){
                Json_Reject_MSG cv8 = new Json_Reject_MSG(req);
                hasil = cv8.getRes();
                ket="NONTRX";    
            }else if(com.equalsIgnoreCase("GET_AUTO_DANA")){
                Json_GET_AUTO_DANA cv8 = new Json_GET_AUTO_DANA(req);
                hasil = cv8.getRes();
                ket="NONTRX";    
            }else if(com.equalsIgnoreCase("DAYS_LEFT")){
                Json_Days_Left cv8 = new Json_Days_Left(req);
                hasil = cv8.getRes();
                ket="NONTRX";     
            }else if(com.equalsIgnoreCase("STOP_AUTO_DANA")){
                Json_STOP_DANA cv8 = new Json_STOP_DANA(req);
                hasil = cv8.getRes();
                ket="NONTRX";    
            }else if(com.equalsIgnoreCase("VERIFY_NUMB")){
                Json_VERIFY_NUMB cv8 = new Json_VERIFY_NUMB(req);
                hasil = cv8.getRes();
                ket="NONTRX";    
            }else if(com.equalsIgnoreCase("EXEC_NUMB")){
                Json_EXEC_NUMB cv8 = new Json_EXEC_NUMB(req);
                hasil = cv8.getRes();
                ket="NONTRX";    
            }else if(com.equalsIgnoreCase("UPDATE_BRW")){
                Json_Update_BRW cv8 = new Json_Update_BRW(req);
                hasil = cv8.getRes();
                ket="NONTRX";    
            }else if(com.equalsIgnoreCase("LIST_FAQ")){
                JSON_lIST_faq cv8 = new JSON_lIST_faq(req);
                hasil = cv8.getRes();
                ket="NONTRX";        
            }else if(com.equalsIgnoreCase("TARIK_DANA")){
                Json_PULL_DANA cv8 = new Json_PULL_DANA(req);
                hasil = cv8.getRes();
                ket="TRX";    
            }else if(com.equalsIgnoreCase("AUTO_DANA")){
                Json_AUTO_DANA cv8 = new Json_AUTO_DANA(req);
                hasil = cv8.getRes();
                ket="TRX";
            }else if(com.equalsIgnoreCase("REC_LOC")){
                Json_Rec_Loc cv8 = new Json_Rec_Loc(req);
                hasil = cv8.getRes();
                ket="TRX";    
            }else if(com.equalsIgnoreCase("VERIFY_PAY")){
                Json_VERIFY_PAY cv8 = new Json_VERIFY_PAY(req);
                hasil = cv8.getRes();
                ket="TRX";    
            }else if(com.equalsIgnoreCase("COUNT_RATE")){
                Json_count_rate cv8 = new Json_count_rate(req);
                hasil = cv8.getRes();
                ket="TRX";    
             }else if(com.equalsIgnoreCase("EMAIL_CONF")){
                Json_Email_Verify cv8 = new Json_Email_Verify(req);
                hasil = cv8.getRes();
                ket="NONTRX";     
            }else{
                jo.put("resultcode","0002");
                jo.put("datetime",dateString);
                
                hasil = jo.toString();
            }
        
        }catch(Exception ex) {
            //System.out.println(ex.getCause());
            jo.put("resultcode","0009");
            jo.put("datetime",dateString);
            
            hasil = jo.toString();
            
            Logger.getLogger(Cek_Utama.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Logv lg = new Logv("IPv:"+ipku,req,hasil+exthasil,ket);
        lg.start();
    }
    
    public String getRes () {
        return hasil;
    }   
}