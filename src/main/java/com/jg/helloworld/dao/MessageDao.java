package com.jg.helloworld.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageDao{
	private Connection connection;
	
	public MessageDao(){
		String connURL = "jdbc:mysql://localhost:3306/jg_schema";
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.connection = DriverManager.getConnection(connURL, "root", "password");
		}catch(Exception e){
			System.out.println("Opening connection failed with error: " + e.getMessage());
		}	
	}
	
	public boolean insertMsg(String txt){		
		try{
			System.out.println("conn is closed="+connection.isClosed());
		
			PreparedStatement statement = connection.prepareStatement("INSERT INTO message (msg_text) VALUES ('"+txt+"');");
			statement.execute();		
		}catch(SQLException se){
			System.out.println("Execution of insert failed with error: "+se.getMessage());
			return false;
		}
		//System.out.println("res="+res);
		return true;		
	}
	
	public void close(){
		try{
			connection.close();
		}catch(SQLException se){
			System.out.println("Closing connection failed with error: "+se.getMessage());
		}
	}
}