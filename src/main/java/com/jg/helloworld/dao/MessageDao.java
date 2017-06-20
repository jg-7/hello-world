package com.jg.helloworld.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import org.json.JSONObject;
import org.json.JSONArray;

public class MessageDao{
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet rs;
		
	public MessageDao(){
		String connURL = "jdbc:mysql://localhost:3306/jg_schema";
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.connection = DriverManager.getConnection(connURL, "root", "password");
		}catch(Exception e){
			System.out.println("Opening connection failed with error: " + e.getMessage());
		}	
	}
	
	public boolean insertMsg(String usr, String txt){				
		//System.out.println("conn is closed="+connection.isClosed());
		try{
			statement = connection.prepareStatement("INSERT INTO message (username, msg_text) VALUES ('"+usr+"', '"+txt+"');");
			statement.execute();		
		}catch(SQLException se){
			System.out.println("Execution of insert failed with error: "+se.getMessage());
			return false;
		}
		//System.out.println("res="+res);
		return true;		
	}
	
	public boolean insertRpl(String usr, String msgid, String rpl){				
		//System.out.println("conn is closed="+connection.isClosed());
		try{
			statement = connection.prepareStatement("UPDATE message SET rpl_text='"+rpl+"' WHERE id="+msgid+";");
			statement.execute();		
		}catch(SQLException se){
			System.out.println("Execution of update failed with error: "+se.getMessage());
			return false;
		}
		//System.out.println("res="+res);
		return true;		
	}
	
	public JSONObject getMsgs(String usr){
		System.out.println(">>>Start getMsgs()");
		String msg;
		String created;
		int id;
		
		JSONObject rowJSON;
		JSONObject rsJSON = new JSONObject();
		
		try{
			statement = connection.prepareStatement("SELECT * FROM message where username='"+usr+"';");
			ResultSet rs = statement.executeQuery();
			while(rs!=null&&rs.next()){
				id=rs.getInt("id");
				msg=rs.getString("msg_text");
				created=rs.getString("created");
				
				rowJSON = new JSONObject();
				rowJSON.put("msg", msg);
				rowJSON.put("created", created);
				
				rsJSON.put(String.valueOf(id), rowJSON);
				
			}
		}catch(SQLException se){
			System.out.println("Execution of insert failed with error: "+se.getMessage());
		}
		System.out.println(rsJSON.toString());
		System.out.println("<<<End getMsg()");
		return rsJSON;
	}
	
	public void close(){
		try{
			statement.close();
			connection.close();
		}catch(SQLException se){
			System.out.println("Closing connection/statement failed with error: "+se.getMessage());
		}
	}
}