package com.wangtingzheng.smartconf.file;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

public class InformationConverter {
    public JSONObject fileToJson(String path)
    {
        String text = null;
        String str;
        try {
            BufferedReader in = new BufferedReader(new java.io.FileReader(path));
            while ((str = in.readLine()) != null) {
                text +=str;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        text = text.replace("null", "");
        return JSONObject.parseObject(text.toString());
    }

    public void stringToFile(String text, String path)
    {
        File file = new File(path);
        try(FileWriter writer = new FileWriter(file);
            BufferedWriter out =  new BufferedWriter(writer)
        ){
            out.write(text);
            System.out.println("world");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public JSONObject StringToJson(String msg)
    {
        return JSONObject.parseObject(msg);
    }

    public String JsonToString(JSONObject jsonObject)
    {
        return jsonObject.toJSONString();
    }

    public String getValue(String path, String item, String value)
    {
        JSONObject object = fileToJson(path);
        JSONObject nodeOject = object.getJSONObject(item);
        return nodeOject.getString(value);
    }
}
