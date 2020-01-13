package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import modul.Exser;

/**
 *
 * @author VINCENT
 */
public class Gateway {

    public static void main(String[] args) {
        int port = 37779;
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
