package com.example.olivia.weat;



import android.util.Log;

import org.apache.commons.io.IOUtils;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WebService {
    private static String servlet = "https://powerful-bastion-16516.herokuapp.com/";

    static private List<String> restaurants = new ArrayList<String>(){{
        add("Restaurant1"); add("Restaurant2"); add("Restaurant3");
        add("Restaurant4"); add("Restaurant5"); add("Restaurant6");
    }};

    public static List<String> executeHttpGet(List<String> Info){
        String path = servlet + "?";
        for(String str : Info){
            path = path + str + "&";
        }
        path = path.substring(0, path.length() - 1);
        try {
            Log.d("url", path);
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                String res = IOUtils.toString(is, "utf-8");
                Log.d("res in WebService", res);
                return restaurants;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}