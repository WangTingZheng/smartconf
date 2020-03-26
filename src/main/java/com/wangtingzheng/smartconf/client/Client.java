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
        Socket client = new Socket("127.0.0.1", 20005);
        client.setSoTimeout(10000);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = new PrintStream(client.getOutputStream());
        BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
        boolean flag = true;

        while (flag)
        {
            System.out.println("start?");
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
            client.close();
        }
    }
}