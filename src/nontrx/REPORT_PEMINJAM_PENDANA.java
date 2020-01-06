/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nontrx;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modul.Konf;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.json.simple.JSONObject;

/**
 *
 * @author asus
 */
public class REPORT_PEMINJAM_PENDANA extends Konf{
    public String id,pass,counter,hasilx,nama,ans,nova,idbrw,ididn,docpeminjam,docpendana,email;
    public JSONObject jo=new JSONObject();
    public String[] katv;
    public List<String> list = new ArrayList<String>();
    
    public SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public REPORT_PEMINJAM_PENDANA(String idv,String no_doc_peminjam,String no_doc_pendana) throws Exception
    {
        String fileName = "PERJANJIAN LAYANAN PINJAMAN UANG ANTARA PENDANA DAN PEMINJAM";
        String path = "/var/www/html/Doeku/src/reports/ReportDoeku1.jasper";
//      String path = "src\\reports\\ReportDoeku1.jasper";
        
        try {
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stat = con.createStatement();
            rs = stat.executeQuery("select tb.id_brw, tb.acc_idn, tb.jum_fix, tb.no_doc_peminjam, tb.no_doc_pendana, p.nama, p.alamat, p.email, p.telp_p from transaksi_brw tb INNER JOIN peminjam p ON p.id_brw=tb.id_brw where tb.id_trxbrw='"+idv+"'");
            
            JRDataSource jRDataSource = new JRResultSetDataSource(rs);
            JasperPrint jasperPrint = JasperFillManager.fillReport(path, null, jRDataSource);
//            JasperExportManager.exportReportToPdfFile(jasperPrint, "/pdf_doeku/"+fileName+"_"+no_doc_peminjam+".pdf");
            
            File pdf = new File("/var/www/html/Doeku/pdf_doeku/"+fileName+"_"+no_doc_peminjam+".pdf");
//            File pdf = new File("E:\\"+fileName+"_"+no_doc_peminjam+".pdf");
            
            FileOutputStream outStream = new FileOutputStream(pdf);
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
            String result = sendDocument(idv, no_doc_peminjam);
            
        } catch (SQLException ex) {
            Logger.getLogger(REPORT_PEMINJAM_PENDANA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String sendDocument(String idv, String no_doc_peminjam){
        String resutl = null;
        try {
            statv = con.prepareCall("{call REPORT_PEMINJAM (?,?,?,?,?)}");
            statv.setString(1, idv);
            statv.execute();
            idbrw = statv.getString("p_idbrw");
            nama =  statv.getString("p_nama");
            email = statv.getString("p_email");
            docpeminjam = statv.getString("p_docpeminjam");
            String urlPdfPeminjam = "http://182.23.15.250/var/www/html/Doeku/pdf_doeku";
            postingDocumentPeminjam(nama, email, no_doc_peminjam, urlPdfPeminjam, "10", "PAB", "0");     
            
            resutl = "Success";
            statv.close();
            con.close();
            
        } catch (Exception e){
            e.printStackTrace();
        }
        return resutl;
    }
    
    public void postingDocumentPeminjam(String nama, String email, String doc_id, String path,
            String page, String tipe, String jenis){
        String parameters = null;
        String url="http://api.davestpay.com/vPA/submit_digisign";
        URL obj;
        try {
            obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            
            parameters = "nama="+nama+"&email="+email+"&doc_id="+doc_id+"&pathku="+path+"&page="+page+"&tipe="+tipe+"&jenis="+jenis;
            
            String panjang = new Integer(parameters.length()).toString();
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(parameters);
            wr.flush();
            wr.close();
            
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + parameters);
            System.out.println("Response Code : " + responseCode);
            
        } catch (Exception ex) {
            Logger.getLogger(REPORT_PEMINJAM_PENDANA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
