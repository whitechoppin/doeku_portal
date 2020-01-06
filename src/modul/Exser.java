/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modul;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Gateway;

/**
 *
 * @author VINCENT
 */
public class Exser extends Thread  {
    public Socket sock;
    public ServerSocket listenSock;
    public String str,ipku;
    
    public SimpleDateFormat  formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public Date currentTime = new Date();
    public String dateString = formats.format(currentTime);
    
    public Exser (Socket sock) {
        this.sock=sock;
    }
    
    @Override
    public void run(){
        try {
        
                BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
                String line = "";
                //ipku=sock.getRemoteSocketAddress().toString();
                ipku=sock.getInetAddress().toString();
                
                //System.out.println("Masuk run Exser..");
                
                while ((line = br.readLine()) != null) {
                    String a = "";
                    line = line.replaceAll("'", "\"");
                    //System.out.println("Terima Baris:"+line);
                    a = a + line;
                    //System.out.println(a);
                    try {
                        a=a.trim();
                        Cek_Utama cu = new Cek_Utama(a,ipku);
                        cu.run();
                        str = cu.getRes();
                    
                    } catch (Exception ex) {
                        Logger.getLogger(Gateway.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    //System.out.println("Full response : "+str);
                    bw.write(str+"\n");
                    bw.flush();
                    
                    
                }

                bw.close();
                br.close();
                sock.close();
        }catch(Exception ex) {}
    }
}