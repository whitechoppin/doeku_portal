/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modul;

/**
 *
 * @author Vellei
 */
import Emailer.Em_Notif_Reset;
import com.sun.mail.smtp.SMTPTransport;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class Em_Pass_DP {
        private static final String SMTP_SERVER = "mail.doeku.id";
        private static final String USERNAME = "noreply@doeku.id";
        private static final String PASSWORD = "hdi098098HDI";

        private static final String EMAIL_FROM = "noreply@doeku.id";
        
        private static final String EMAIL_TO_CC = "";

        //private static final String EMAIL_TEXT = "Password Baru Anda Adalah 1234567";
        private static final String EMAIL_SUBJECT = "Password DavestPay Anda";
        public String EMAIL_TEXT;
        public String EMAIL_TO; 
        
    public Em_Pass_DP() {
        
    }
    
    public void SendEm(String em,String passweb,String passtrx) {

            Properties prop = System.getProperties();
            prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.port", "587"); // default

        EMAIL_TO = em; 
        //String linkku = "https://doeku.id/form/peminjam/forget-password/"+code;
        //String linkku = "<a href='https://doeku.id/form/peminjam/forget-password/"+code+"'>Reset Pass</a>";
        //String linkku = "123";
        EMAIL_TEXT = "Berikut Password DavestPay Anda :"+ 
				 " Password Web : "+passweb+
				 " Password Transaksi : "+passtrx+
				 " Terima Kasih.";
        
        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {
		
			// from
            msg.setFrom(new InternetAddress(EMAIL_FROM));
			// to 
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(EMAIL_TO, false));
			// cc
            msg.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(EMAIL_TO_CC, false));

			// subject
            msg.setSubject(EMAIL_SUBJECT);
            msg.setText(EMAIL_TEXT);
			
            msg.setSentDate(new Date());

			// Get SMTPTransport
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
			
			// connect
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);
			
			// send
            t.sendMessage(msg, msg.getAllRecipients());

            System.out.println("Response: " + t.getLastServerResponse());

            t.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }
        /*private static final String SMTP_SERVER = "mail.doeku.id";
        private static final String USERNAME = "noreply@doeku.id";
        private static final String PASSWORD = "hdi098098HDI";
	private static final String EMAIL_FROM = "noreply@doeku.id";
        private static final String EMAIL_TO_CC = "";
	private static final String EMAIL_SUBJECT = "Reset Password";
		
        public String EMAIL_TO;
        public String EMAIL_TEXT;
        
      public void Em_Reset_Pass(String emv,String psv) {
			// for example, smtp.mailgun.org
        EMAIL_TO = emv;
        

        
        EMAIL_TEXT = "<html lang='en'><head><meta charset='utf-8'>"+
"<head><meta charset='utf-8'>"+
    "<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"+
    "<meta http-equiv='x-ua-compatible' content='ie=edge'>"+
"</head>"+
"<body style='font-family: roboto, sans-serif;background: #f8f8f8;color: #5e5e5e;'>"+
"<div class='card' style='width: 524px;margin: 0 auto;background: #ffffff;margin-top: -.5rem;padding: 2.5rem;'>"+
    "<div class='card-header' style='margin: 0;padding: 0;'>"+
        "<h1 style='margin: 0;padding-top: 0;color: #01b0b1;'>"+
         	"<img src='https://doeku.id/assets/images/logo.png' width='30%'>"+
        "</h1>"+
    "</div>"+
        "<p>"+
		"<br>Hai,"+
		"</p><br><br>"+
		"<p>"+
		"Password Anda Telah Kami Reset Dengan Password : "+psv+"<br>"+
		"</p>"+
		"<p>Silahkan Login Dengan ID Admin/Email Ke Dalam System DoeKu & Lakukan Perubahan Password Akses Anda.</p><br>"+
		"<span style='font-size:11px;'>==Email Ini Dihasilkan Oleh Sistem PinDavest, Harap Jangan Membalas Email Ini==</span>"+
    
"</div>"+
"</body>"+
"</html>";
	  //EMAIL_TEXT = "tesji";
            
            Properties prop = System.getProperties();
            prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.port", "587"); // default


        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {
				// from
            msg.setFrom(new InternetAddress(EMAIL_FROM));
	// to 
            msg.setRecipients(Message.RecipientType.TO,
            //InternetAddress.parse(EMAIL_TO, false));
           InternetAddress.parse(emv));

			// cc
            msg.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(EMAIL_TO_CC, false));

			// subject
            msg.setSubject(EMAIL_SUBJECT);
			
			// content 
            msg.setText(EMAIL_TEXT);
			
            msg.setSentDate(new Date());

			// Get SMTPTransport
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
			
			// connect
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);
			
			// send
            t.sendMessage(msg, msg.getAllRecipients());

            System.out.println("Response: " + t.getLastServerResponse());

            t.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }
      
      public static void main(String[] args) {
        
          Em_Reset_Pass er = new Em_Reset_Pass();
          er.Em_Reset_Pass("vt3st3@gmail.com", "123");
          
      }*/
    public static void main(String[] args) {
            Em_Pass_DP dp = new Em_Pass_DP();
            dp.SendEm("vt3st3r@gmail.com", "123", "321");
    }

}
     