package com.mmtzj.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-1-15
 * Time: 下午9:01
 * To change this template use File | Settings | File Templates.
 */
public class BaseUtil {

    public static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static Integer getIntValue(String value){
        try{
            return Integer.parseInt(value);
        }catch (Exception e){
            return 0;
        }
    }

    public static long getRandom(int min, int max){
        double rand = Math.random();
        int range = max - min;
        return min + Math.round(rand * range);
    }

}
