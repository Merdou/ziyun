package com.hxzy.common.util;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *  MD5工具
 */
public class MD5Util {
    //16进制
    private static final String hexDigIts[] = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};

    //随机生成盐的随机数
    private static List<String> charArr = new ArrayList<>(62);
    static{
        for(int i=0;i<10;i++){
            charArr.add(i+"");
        }

        //A-Z
        for(int i=0;i<26;i++){
            charArr.add((char)(i+65)+"");
        }
        //a-z
        for(int i=0;i<26;i++){
            charArr.add((char)(i+97)+"");
        }

    }
    /**
     * 随机生成盐
     * @param count 盐多少位数
     * @return
     */
    public static String randomSalt(int count){
        StringBuffer stringBuffer = new StringBuffer();//存盐
        if(count<2||count>7){
            count=5;
        }
        Random rd = new Random();
        int len = charArr.size();
        //随机生成salt
        for(int i=0;i<count;i++){
            stringBuffer.append(charArr.get(rd.nextInt(len)));
        }
        return stringBuffer.toString();
    }
    /**
     * 生成加了盐的密码
     * @param pwd
     * @param salt
     * @return
     */
    public static String MD5Encode(String pwd,String salt) {
        String newPwd = pwd.substring(0,2)+"@"+salt+"!"+pwd.substring(2);
        newPwd = MD5EncodeCore(newPwd, "UTF-8");
        return newPwd;
    }



    /**
     * MD5加密
     * @param origin 明文密码字符
     * @param charsetname 编码
     * @return
     */
    private static String MD5EncodeCore(String origin, String charsetname){
        String resultString = null;
        try{
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");   //SHA , AES对称加密和解密
            if(null == charsetname || "".equals(charsetname)){
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            }else{
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            }
        }catch (Exception e){
        }
        return resultString;
    }
    private static String byteArrayToHexString(byte b[]){
        StringBuffer resultSb = new StringBuffer();
        for(int i = 0; i < b.length; i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b){
        int n = b;
        if(n < 0){
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigIts[d1] + hexDigIts[d2];
    }

    public static void main(String[] args) {
        System.out.println(MD5Encode("admin","PO5lB"));
    }

}
