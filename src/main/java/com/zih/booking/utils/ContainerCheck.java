package com.zih.booking.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description : 集装箱号校验
 * @Author : wangzhichao
 * @Date: 2020-12-18 16:33
 */
public class ContainerCheck {

    public static boolean checkDigit(String containerNumber){
        if(StringUtils.isNotEmpty(containerNumber)){
            containerNumber = containerNumber.trim();
        }
        if (containerNumber == null || containerNumber.length() != 11)
        {
            return false;
        }
        Map<String, Integer> mapofCode = new HashMap<String, Integer>();
        mapofCode.put("A", 10);
        mapofCode.put("B", 12);
        mapofCode.put("C", 13);
        mapofCode.put("D", 14);
        mapofCode.put("E", 15);
        mapofCode.put("F", 16);
        mapofCode.put("G", 17);
        mapofCode.put("H", 18);
        mapofCode.put("I", 19);
        mapofCode.put("J", 20);
        mapofCode.put("K", 21);
        mapofCode.put("L", 23);
        mapofCode.put("M", 24);
        mapofCode.put("N", 25);
        mapofCode.put("O", 26);
        mapofCode.put("P", 27);
        mapofCode.put("Q", 28);
        mapofCode.put("R", 29);
        mapofCode.put("S", 30);
        mapofCode.put("T", 31);
        mapofCode.put("U", 32);
        mapofCode.put("V", 34);
        mapofCode.put("W", 35);
        mapofCode.put("X", 36);
        mapofCode.put("Y", 37);
        mapofCode.put("Z", 38);
        String constainerCode = containerNumber;
        int positon = 1;
//        int sum = 0;
        double sum = 0;
        try
        {
            for (int i = 0; i < constainerCode.length() - 1; i++)
            {
                String a = constainerCode.substring(i, i+1);
                if (mapofCode.containsKey(a))
                {
                    sum += mapofCode.get(a) * 1.00 * Math.pow(2, positon - 1);
                }
                else
                {
                    sum += Double.parseDouble(a) * Math.pow(2, positon - 1);
                }
                positon++;
            }
            double checkdigit = sum % 11 % 10;
            Boolean result = checkdigit == Integer.valueOf(constainerCode.substring(constainerCode.length() - 1),16);
            return result;
        }
        catch (Exception ex)
        {
            Boolean result = false;
            return result;
        }
    }
}
