package com.wangtingzheng.smartconf.server;

import com.wangtingzheng.smartconf.thread.ServerThread;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(20005);
        Socket client = null;
        boolean f = true;
        while(f){
            client = server.accept();
            System.out.println("Connected!");
            new Thread(new ServerThread(client)).start();
        }
        server.close();
    }
}