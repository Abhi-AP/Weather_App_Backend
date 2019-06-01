package com.weather.controller;
public class Subscribe
{
    private String mobile_number;
    public String setNum(String mobile_number)
    {
        this.mobile_number=mobile_number;
        return "Added";
    }
    public void setCity(String citys)
    {
    }
    public String getNum()
    {
        return this.mobile_number;
    }
    public String sendMessage(String message)
    {
        SmsSender sms=new SmsSender(this.mobile_number);
        return sms.sendIt(message);
    }
}

