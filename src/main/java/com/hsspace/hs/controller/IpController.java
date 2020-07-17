package com.hsspace.hs.controller;

import com.hsspace.hs.direct.Machine;
import com.hsspace.hs.framework.ModelAndView;
import com.hsspace.hs.framework.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * IpController��
 * Git to�� http://hs.mccspace.com:3000/Qing_ning/web-mvc/
 *
 * @TIME 2020/7/17 16:37
 * @AUTHOR ��˶~
 */

public class IpController {

    @GetMapping("/uploadIp")
    public ModelAndView uploadIp(HttpSession session, HttpServletRequest request) {
        String machine = request.getParameter("machine");
        Machine.ENUM.uploadIp(machine,getIp(request));
        return null;
    }

    public String getIp(HttpServletRequest request){
        /**
         * ��ȡ�����������Զ���Ǹ�ip
         */
        String ip = request.getHeader("x-forwarded-for");
        if (ipIsNullOrEmpty(ip)){
            /**
             * apache http���������ϵ�ip
             */
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ipIsNullOrEmpty(ip)){
            /**
             * weblogic������ϵ�ͷ
             */
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipIsNullOrEmpty(ip)){
            /**
             * ��ʵip
             */
            ip = request.getHeader("X-Real-IP");
        }
        if (ipIsNullOrEmpty(ip)){
            /**
             * �����ʵ��ip
             */
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    private boolean ipIsNullOrEmpty(String ip){
        if(ip == null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            return true;
        }
        return false;
    }

}
