package com.company;
import java.lang.*;
public class Main extends Thread{

    public static void main(String[] args) {
        Thread t = Thread.currentThread();
    Main o = new Main();
    try {
        o.start();
    }catch(Exception ee){
        System.out.print("Hrkwer");
    }
    com.company.Server server = new com.company.Server();
    server.display();
    }
    @Override
    public void run() {
        com.company.Client client = new com.company.Client();

        client.design();
    }
}
