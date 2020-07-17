package com.hsspace.hs;

import com.hsspace.hs.direct.Machine;
import com.hsspace.hs.direct.Redirect;

/**
 * testÀà
 * Git to£º http://hs.mccspace.com:3000/Qing_ning/web-mvc/
 *
 * @TIME 2020/7/17 15:09
 * @AUTHOR º«Ë¶~
 */

public class test {

    public static void main(String[] ar){
        //var rd = Redirect.ENUM;
        //rd.direct.put("9666","777");
        //rd.save();
        Machine.ENUM.save();

        Redirect.ENUM.uploadDirect("/hs","m1:80");
        Machine.ENUM.uploadIp("m1","www.baidu.com");


        Machine.ENUM.save();
        Redirect.ENUM.save();
        System.out.println();
    }
}
