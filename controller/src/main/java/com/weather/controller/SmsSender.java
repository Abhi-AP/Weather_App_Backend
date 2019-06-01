package com.weather.controller;
import java.io.*;
import java.net.*;
public class SmsSender
{
    private String receiver;
    public SmsSender(String receiver)
    {
        this.receiver=receiver;
    }
    public String sendIt(String messageToSend)
    {
        try
        {
            String apikey="apikey=5qgjYMJhkbs-fA5GtMVDEmRA4SLrgjWEoIFGxtcM1C";
            String sender = "&sender=" + "TXTLCL";
            String message="&message="+messageToSend;
            System.out.println(messageToSend.length());
            String numbers="&numbers="+"91"+this.receiver;
            HttpURLConnection con=(HttpURLConnection)new URL("https://api.textlocal.in/send/?").openConnection();
            String data=apikey+numbers+message+sender;
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Length", Integer.toString(data.length()));
            con.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
            while ((line = rd.readLine()) != null)
            {
				stringBuffer.append(line);
			}
            rd.close();
            System.out.println(stringBuffer);
            return stringBuffer.toString();
        }
        catch(Exception e)
        {
            System.out.println("Error "+e.toString());
            return e.toString();
        }
    }

}