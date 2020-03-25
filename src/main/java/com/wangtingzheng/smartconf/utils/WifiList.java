package com.wangtingzheng.smartconf.utils;

import java.util.*;

public class WifiList {


    List<Map<String, String>> wifilist = new ArrayList<>();

    public WifiList(List<Map<String, String>> wifilist) {
        this.wifilist = wifilist;
    }

    public WifiList(Map<String, String> wifiMap) {
        wifilist.add(wifiMap);
    }

    public WifiList(String ssid, String psk, String keyMgmt, String priority) {
        Map<String, String> wifiMap = new HashMap<>();
        wifiMap.put("ssid", ssid);
        wifiMap.put("psk", psk);
        wifiMap.put("key_mgmt", keyMgmt);
        wifiMap.put("priority", priority);

        wifilist.add(wifiMap);
    }


    public List<Map<String, String>> getWifilist() {
        return wifilist;
    }

    public void setWifilist(List<Map<String, String>> wifilist) {
        this.wifilist = wifilist;
    }

    public String listToString(boolean isformat)
    {
        String res = isformat?"{\n":"{";
        int num = 0;

        for(Map<String,String> map: wifilist)
        {
            int flag = 0;
            Set<String> set;
            int setSize;
            Iterator<String> iterator;
            String item = null;


            res += ("\"" + num + "\"" + ":{" + (isformat?"\n":""));
            set = map.keySet();
            setSize = set.size();
            iterator = set.iterator();
            while (iterator.hasNext())
            {
                flag ++;
                String next = iterator.next();
                item = "\"" + next + "\":\"" + map.get(next) +"\"";
                if(flag != setSize)
                {
                    item += ",";
                }
                item += isformat?"\n":"";
                res += item;
            }
            res += "}";
            if(wifilist.indexOf(map)!= wifilist.size()-1)
            {
                res +=",";
            }
            res += isformat?"\n":"";
            num ++;
        }
        res += "}";
        return res;
    }
}
