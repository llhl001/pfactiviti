package com.sdyy.common.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
 

/**
 * @author sunpeibin @date 2014-8-31 
 * @Description:TODO(这里用一句话描述这个方法的作用)
 * @param
 * @return
 * @throws Exception
 */
public class GetProperties {
    public static boolean getuserid(String userid){   
    	 Properties prop = new Properties();  
    	 String url = GetProperties.class.getClassLoader().getResource(
    			    "config.properties").toString().substring(6);
    	 String empUrl = url.replace("%20", " ");
         InputStream in = null;
         boolean flag=false;
         try {   
        	 in =new BufferedInputStream(new FileInputStream(empUrl));
             prop.load(in);
             flag = prop.contains(userid);
         } catch (IOException e) {   
             e.printStackTrace();   
         }
         return flag;
    }  

}
