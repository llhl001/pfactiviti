package com.sdyy.common.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sdyy.common.service.ConfigService;



public class JDBCUtil {
	
	static Connection conn=null;
	public static Connection getConnection() throws IOException{
		String driverClassName = ConfigService.getConfigRealTime(ConfigService.JDBC_DRIVER_CLASS_NAME);
		String url = ConfigService.getConfigRealTime(ConfigService.JDBC_URL);
		String user = ConfigService.getConfigRealTime(ConfigService.JDBC_USER_NAME);
		String password = ConfigService.getConfigRealTime(ConfigService.JDBC_PASSWORD);
		try {
			Class.forName(driverClassName);
			try {
				conn =DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public static void close(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

