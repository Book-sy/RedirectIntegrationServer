package com.hsspace.hs;

import java.io.File;
import java.util.Scanner;

import com.hsspace.hs.direct.Machine;
import com.hsspace.hs.direct.Redirect;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class Main {

    public static void main(String[] args) throws Exception {

        Tomcat tomcat = new Tomcat();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Scanner input = new Scanner(System.in);
                    while (true) {
                        String[] in = input.nextLine().split(" ");
                        if (in[0].equals("stop")) {
                            Redirect.ENUM.save();
                            Machine.ENUM.save();
                            tomcat.stop();
                            input.close();
                            System.exit(0);
                            break;
                        } else if (in[0].equals("add") && in.length == 3) {
                            Redirect.ENUM.uploadDirect(in[1], in[2]);
                        } else if (in[0].equals("uploadIp") && in.length == 3) {
                            Machine.ENUM.uploadIp(in[1], in[2]);
                        } else if (in[0].equals("listRedirect")) {
                            System.out.println("==========");
                            for(String key : Redirect.ENUM.direct.keySet()){
                                String value = Redirect.ENUM.direct.get(key);
                                System.out.println(key+"  -->  "+value);
                            }
                            System.out.println("==========");
                        } else if (in[0].equals("listMachine")) {
                            System.out.println("==========");
                            for(String key : Machine.ENUM.machine.keySet()){
                                String value = Machine.ENUM.machine.get(key);
                                System.out.println(key+"  -->  "+value);
                            }
                            System.out.println("==========");
                        } else if (in[0].equals("help")) {
                            System.out.println("stop --停止服务");
                            System.out.println("add </xxx> <m1:80> --更新服务记录");
                            System.out.println("uploadIp <m1> <127.0.0.1> --更新地址");
                            System.out.println("listRedirect  --重定向集群列表");
                            System.out.println("listMachine  --动态地址对应列表");
                        }
                    }
                } catch (LifecycleException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/

        tomcat.setPort(Integer.getInteger("port", 80));
        tomcat.getConnector();
        Context ctx = tomcat.addWebapp("", new File("src/main/webapp").getAbsolutePath());
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(
                new DirResourceSet(resources, "/WEB-INF/classes", new File("target/classes").getAbsolutePath(), "/"));
        ctx.setResources(resources);
        tomcat.start();
        tomcat.getServer().await();
    }
}
