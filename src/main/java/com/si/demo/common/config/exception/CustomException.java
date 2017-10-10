package com.si.demo.common.config.exception;

/*
* 类描述：
* @auther linzf
* @create 2017/10/9 0009 
*/
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = -3766733786200320204L;

    public CustomException(){}

    public CustomException(String msg){
        super(msg);
    }

}
