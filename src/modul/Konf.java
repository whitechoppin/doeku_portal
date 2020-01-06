/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modul;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VINCENT
 */
public class Konf extends Thread{
    /*public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://183.91.70.178:3305/pinjam_aja";
    public static final String DB_USER = "vincent";
    public static final String DB_PASSWORD = "v1nc3nt";*/
    
    public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://36.37.98.122:3306/doeku";
    public static final String DB_USER = "vincent";
    public static final String DB_PASSWORD = "vincent321";
  
    /*public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/doeku";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "v333w";)*/
    //public static final String DB_QUERY = "SELECT * FROM user_d";
    
    public ResultSet rs = null;
    public Connection con = null;
    public Statement statx = null;
    public CallableStatement statv = null;
   
    public Konf() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Konf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
