package com.hsspace.hs.direct;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * MachineÀà
 * Git to£º http://hs.mccspace.com:3000/Qing_ning/web-mvc/
 *
 * @TIME 2020/7/17 16:31
 * @AUTHOR º«Ë¶~
 */

public enum  Machine {
    ENUM;

    private Map<String, String> machine = new HashMap<>();

    private String path;

    Machine(){
        path = System.getProperty("user.dir");
        reload();
    }

    public String getIp(String machine){
        return this.machine.get(machine);
    }

    public void uploadIp(String machine,String ip){
        this.machine.put(machine,ip);
    }

    public void save(){
        try {
            FileOutputStream fos = new FileOutputStream(path+"/machine.data");
            ObjectOutputStream output = new ObjectOutputStream(fos);
            output.writeObject(machine);
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload(){
        try {
            FileInputStream fis = new FileInputStream(path+"/machine.data");
            ObjectInputStream ois = new ObjectInputStream(fis);
            machine = (Map<String, String>) ois.readObject();
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