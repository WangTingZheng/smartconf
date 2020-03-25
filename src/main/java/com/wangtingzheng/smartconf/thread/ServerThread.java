package com.wangtingzheng.smartconf.thread;

import com.alibaba.fastjson.JSONObject;
import com.wangtingzheng.smartconf.file.InformationConverter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


/**
 * 该类为多线程类，用于服务端
 */
public class ServerThread implements Runnable {

    private Socket client = null;
    public ServerThread(Socket client){
        this.client = client;
    }

    @Override
    public void run() {
        try{
            //获取Socket的输出流，用来向客户端发送数据
            PrintStream out = new PrintStream(client.getOutputStream());
            //获取Socket的输入流，用来接收从客户端发送过来的数据
            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            boolean flag =true;
            while(flag){
                //接收从客户端发送过来的数据
                String str =  buf.readLine();
                if(str == null || "".equals(str)){
                    flag = false;
                }else{
                    if("bye".equals(str)){
                        flag = false;
                    }else{
                        InformationConverter informationConverter = new InformationConverter();
                        informationConverter.stringToFile(str, "B:\\workspace\\IDEA\\smartconf\\wifis.json");
                        System.out.println(str);
                    }
                    out.println("echo:" + str);
                }
            }
            out.close();
            client.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}