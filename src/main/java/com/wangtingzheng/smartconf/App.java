package com.wangtingzheng.smartconf;


import com.alibaba.fastjson.JSONObject;
import com.wangtingzheng.smartconf.file.InformationConverter;


public class App {

    public static void main(String[] args)
    {
        String data = "{\"0\":{\"psk\":\"1234\",\"key_mgmt\":\"dede\",\"priority\":\"2\",\"ssid\":\"Name1\"},\"1\":{\"psk\":\"1234\",\"key_mgmt\":\"dede\",\"priority\":\"2\",\"ssid\":\"Name2\"}}\n";
        InformationConverter informationConverter = new InformationConverter();
        informationConverter.stringToFile(data, "B:\\workspace\\IDEA\\smartconf\\wifis.json");
    }
}
