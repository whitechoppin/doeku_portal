package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import modul.Exser;

/**
 *
 * @author VINCENT
 */
public class Gateway {

    private static boolean USE_HTTPS=true;

    public static void main(String[] args) throws Exception {
        int port = 37773;
        ServerSocket listenSock = null;
        
        try {
			if(!USE_HTTPS)
			{
                            listenSock = new ServerSocket(port);
			}
			else //HTTPS
			{
                            
                                KeyStore keyStore = KeyStore.getInstance("JKS");
                            try {
                                keyStore.load(new FileInputStream("/home/hdi/test/doeku/doeku.jks"),"Hd1hd!".toCharArray());
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

								// Create key manager
				KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
				keyManagerFactory.init(keyStore, "Hd1hd!".toCharArray());
				KeyManager[] km = keyManagerFactory.getKeyManagers();

				// Create trust manager
				TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
				trustManagerFactory.init(keyStore);
				TrustManager[] tm = trustManagerFactory.getTrustManagers();

				// Initialize SSLContext
                                SSLContext sslContext = SSLContext.getInstance("TLSv1");
				sslContext.init(km,  tm, null);            

				SSLServerSocketFactory sslServerSocketfactory = sslContext.getServerSocketFactory();
				SSLServerSocket sslServerSocket = (SSLServerSocket)sslServerSocketfactory.createServerSocket(port, 1000);
				
                            listenSock = sslServerSocket;
			}
	
            
            System.out.println("Vellei Doeku Master is running...");

            while (true) {
                Socket clientSocket = listenSock.accept();
				
                System.out.println("Connected");
                
                if(USE_HTTPS)
                {
                    ((SSLSocket)clientSocket).setEnabledCipherSuites(((SSLSocket)clientSocket).getSupportedCipherSuites());
                    ((SSLSocket)clientSocket).getSession();
                }
				
                
                System.out.println("Going to exser");
                
                Exser exv = new Exser(clientSocket);
                exv.start();
                
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
