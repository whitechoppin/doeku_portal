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

    public static void main(String[] args) {
        int port = 37773;
        ServerSocket listenSock;

        try {
            listenSock = new ServerSocket(port);

            System.out.println("Vellei Doeku is running...");

            while (true) {
                Socket clientSocket = listenSock.accept();
                Exser exv = new Exser(clientSocket);
                exv.start();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}