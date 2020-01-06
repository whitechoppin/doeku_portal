/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modul;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author VINCENT
 */
public class Logv extends Thread{
    String req,resp,fldr,ipku;
    protected static final Logger logger = Logger.getLogger(Cek_Utama.class.getName());
    protected FileHandler fileHandler; 
    
    protected Date currentTime = new Date();
    
    protected SimpleDateFormat th = new SimpleDateFormat("yyyy");
    protected SimpleDateFormat bl = new SimpleDateFormat("MM");
    protected SimpleDateFormat tgal = new SimpleDateFormat("dd");
    
    protected String tahun = th.format(currentTime);
    protected String bulan = bl.format(currentTime);
    protected String tanggal = tgal.format(currentTime);
    
    public Logv(String ipku,String req,String resp,String fldr) {
        this.req=req;
        this.resp=resp;
        this.fldr=fldr;
        this.ipku=ipku;
        
        File dirth = new File("logv/"+fldr+"/"+tahun);
        File dirbl = new File("logv/"+fldr+"/"+tahun+"/"+bulan);
        File dirtl = new File("logv/"+fldr+"/"+tahun+"/"+bulan+"/"+tanggal+".log");
        
        if(!dirth.exists()) {
            dirth.mkdirs();
        }
        
        if(!dirbl.exists()) {
            dirbl.mkdirs();
        }
    }
    
    @Override
    public void run() {
       
        try {
               fileHandler = new FileHandler("logv/"+fldr+"/"+tahun+"/"+bulan+"/"+tanggal+".log", true);
               fileHandler.setFormatter(new SimpleFormatter());
               logger.addHandler(fileHandler);
        }catch(Exception ex){}
        
        req=this.getName()+" "+req;
        resp=this.getName()+" "+resp;
        
        logger.severe(ipku+req);
        logger.info(resp);
        fileHandler.close();
       
    }
}