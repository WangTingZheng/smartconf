package com.wangtingzheng.smartconf;


import com.alibaba.fastjson.JSONObject;
import com.wangtingzheng.smartconf.file.InformationConverter;

// 一个发送，一个接收
//发送的发送WiFi的SSID, BSSID和密码
//接收的接收到后，设置为最高优先级，写成树莓派支持的WiFi配置文件，移动到某一个位置
//运行连接WiFi的命令（模拟）
public class App {

    public static void main(String[] args)
    {
        String data = "{\"0\":{\"psk\":\"1234\",\"key_mgmt\":\"dede\",\"priority\":\"2\",\"ssid\":\"Name1\"},\"1\":{\"psk\":\"1234\",\"key_mgmt\":\"dede\",\"priority\":\"2\",\"ssid\":\"Name2\"}}\n";
        InformationConverter informationConverter = new InformationConverter();
        informationConverter.stringToFile(data, "B:\\workspace\\IDEA\\smartconf\\wifis.json");
    }
}
