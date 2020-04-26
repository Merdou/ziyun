package com.hxzy.common.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 返回消息
 */
@Getter
@Setter
public class ResponseMessage {

    //消息码
    private int code;

    //消息说明
    private String message;

    //内容
    private Object data;

    /**
     * 成功消息
     * @param message 消息
     * @param data  内容
     * @return
     */
    public static ResponseMessage  success(String message,Object data){
        ResponseMessage rm=new ResponseMessage();
        rm.setCode(0);
        rm.setData(data);
        rm.setMessage(message);
        return rm;
    }

    /**
     * 成功消息
     * @param message 消息
     * @return
     */
    public static ResponseMessage  success(String message){
         return  success(message,null);
    }

    /**
     * 成功消息
     * @return
     */
    public static ResponseMessage success(){
        return success("操作成功");
    }

    /**
     * 失败
     * @param code  编号
      * @param message 内容
     * @return
     */
    public static ResponseMessage failed(int code,String message){
        ResponseMessage rm=new ResponseMessage();
        rm.setCode(code);
        rm.setMessage(message);
        return rm;
    }

    /**
     * 失败 500
     * @return
     */
    public static ResponseMessage failed(){
        return failed(500,"操作失败");
    }

}
