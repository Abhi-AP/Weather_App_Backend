package com.weather.controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.weather.controller.Subscribe;
import java.net.*;
import java.io.*;
import org.springframework.*;
@SpringBootApplication
@RestController
public class MainApp 
{
	Subscribe sub=new Subscribe();

	@PostMapping(value="/add")
	public void addNumber(@RequestParam String number,@RequestParam String city)
	{
		System.out.println(sub.setNum(number));
		String weather_det=getDetails(city);
		System.out.println(weather_det);
		sub.sendMessage(weather_det);
	}
	public String getDetails(String city)
	{
		String details="";
		try
		{
		String link="http://api.apixu.com/v1/current.json?key=daef30a4c6e24036853112449190605&q="+city;
		URL url = new URL(link);
		HttpURLConnection con=(HttpURLConnection)url.openConnection();
		con.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		StringBuffer res=new StringBuffer();
		while((details=in.readLine())!=null)
		{
			res.append(details);
		}
		String ans=res.toString();
		String temp_c="",condition="",citys="",humidity="";
		String p="";
		for(int i=0;i<ans.length();++i)
		{
			char ch=ans.charAt(i);
			if(ch>='a'&&ch<='z'||ch>='A'&&ch<='Z'||ch=='_')
			{
				p=p+ch;
			}
			else
			{
				if(p.equalsIgnoreCase("temp_c"))
					temp_c=ans.substring(i+2, i+6);
				else if(p.equalsIgnoreCase("text"))
				{
					for(int j=i+3;;j++)
					{
						char c=ans.charAt(j);
						if(c>='a'&&c<='z'||c>='A'&&c<='Z'||c=='_')
							condition+=ans.charAt(j);
						else	
							break;
					}
				}
				else if(p.equalsIgnoreCase("name"))
				{
					for(int j=i+3;;j++)
					{
						char c=ans.charAt(j);
						if(c>='a'&&c<='z'||c>='A'&&c<='Z'||c=='_')
							citys+=ans.charAt(j);
						else	
							break;
					}
				}
				else if(p.equalsIgnoreCase("humidity"))
				{
					humidity=ans.substring(i+2,i+4);
				}
				p="";
			}
		}
		String fin="City is "+citys+". Temperature here is "+temp_c+"°C."+" Humidity here is "+humidity+"%"+" and climate type here is "+condition+".";
		return (fin.toString());
		}catch(Exception e)
		{
			return (e.toString());
		}
	}
	public static void main(String[] args) 
	{
		SpringApplication.run(MainApp.class, args);
	}

}
