package com.hsspace.hs.direct;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * RedirectÀà
 * Git to£º http://hs.mccspace.com:3000/Qing_ning/web-mvc/
 *
 * @TIME 2020/7/17 14:51
 * @AUTHOR º«Ë¶~
 */

public enum  Redirect {

    ENUM;

    private Map<String, String> direct = new HashMap<>();

    private String path;

    Redirect(){
        path = System.getProperty("user.dir");
        reload();
    }

    public void uploadDirect(String url,String ip){
        this.direct.put(url,ip);
    }

    public String getRedirectUrl(String url){
        String address = direct.get(url);
        if(address == null)
            return "#";
        String[] ipArr = address.split(":");
        ipArr[0] = Machine.ENUM.getIp(ipArr[0]);
        return ipArr[0]+":"+ipArr[1];
    }

    public void save(){
        try {
            FileOutputStream fos = new FileOutputStream(path+"/Redirect.data");
            ObjectOutputStream output = new ObjectOutputStream(fos);
            output.writeObject(direct);
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload(){
        try {
            FileInputStream fis = new FileInputStream(path+"/Redirect.data");
            ObjectInputStream ois = new ObjectInputStream(fis);
            direct = (Map<String, String>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
