package com.jg.helloworld;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Connection;

import javax.ws.rs.GET;


@Path("/hello-ws/{param}")
public class HelloWS {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getText(@PathParam("param") String text){
		//String txt = dbCon();
		//System.out.println("txt="+txt);
		return text;
	}
	
	public String dbCon(){
		String txt="";
		int id=0;
		String connURL = "jdbc:mysql://localhost:3306/jg_schema";
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(connURL, "root", "password");
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM messages");
			ResultSet rs = statement.executeQuery();
			if(rs!=null){
				while(rs.next()){
					id = rs.getInt("ID");
					txt = rs.getString("TEXT");
					//Date dt = rs.getDate("Created");
				}
			}
			
		}catch(Exception se){
			System.out.println(se.getMessage());
		}
		return txt;
		
	}
}
