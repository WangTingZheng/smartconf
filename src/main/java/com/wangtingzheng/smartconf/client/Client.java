package com.wangtingzheng.smartconf.client;

import com.wangtingzheng.smartconf.utils.WifiList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client{
    public static void main(String[] args) throws IOException {
        //客户端请求与本机在20006端口建立TCP连接
        Socket client = new Socket("127.0.0.1", 20005);
        client.setSoTimeout(10000);
        //获取键盘输入
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        //获取Socket的输出流，用来发送数据到服务端
        PrintStream out = new PrintStream(client.getOutputStream());
        //获取Socket的输入流，用来接收从服务端发送过来的数据
        BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
        boolean flag = true;

        while (flag)
        {
            System.out.println("开始发送？");
            String str = input.readLine();
            if (str.equals("yes")) {
                Map<String, String> wifiMap = new HashMap<>();
                wifiMap.put("ssid", "Name1");
                wifiMap.put("psk", "1234");
                wifiMap.put("key_mgmt", "dede");
                wifiMap.put("priority", "2");

                Map<String, String> wifiMap1 = new HashMap<>();
                wifiMap1.put("ssid", "Name2");
                wifiMap1.put("psk", "1234");
                wifiMap1.put("key_mgmt", "dede");
                wifiMap1.put("priority", "2");

                List<Map<String, String>> wifi = new ArrayList<>();
                wifi.add(wifiMap);
                wifi.add(wifiMap1);

                WifiList wifiList = new WifiList(wifi);
                String data = wifiList.listToString(false);
                out.println(data);
                try {
                    //从服务器端接收数据有个时间限制（系统自设，也可以自己设置），超过了这个时间，便会抛出该异常
                    String echo = buf.readLine();
                    System.out.println(echo);
                } catch (SocketTimeoutException e) {
                    System.out.println("Time out, No response");
                }
            }
            else if(str.equals("no"))
            {
                flag = false;
            }
        }
        input.close();
        if (client != null) {
            //如果构造函数建立起了连接，则关闭套接字，如果没有建立起连接，自然不用关闭
            client.close();    //只关闭socket，其关联的输入输出流也会被关闭
        }
    }
}