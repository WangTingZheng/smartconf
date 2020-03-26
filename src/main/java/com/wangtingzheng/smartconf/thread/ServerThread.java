package com.wangtingzheng.smartconf.thread;

import com.alibaba.fastjson.JSONObject;
import com.wangtingzheng.smartconf.file.InformationConverter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


/**
 *
 */
public class ServerThread implements Runnable {

    private Socket client = null;
    public ServerThread(Socket client){
        this.client = client;
    }

    @Override
    public void run() {
        try{
            PrintStream out = new PrintStream(client.getOutputStream());
            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            boolean flag =true;
            while(flag){
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