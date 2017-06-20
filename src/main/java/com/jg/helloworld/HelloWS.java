package com.jg.helloworld;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jg.helloworld.dao.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;



@Path("/hello-ws")
public class HelloWS {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{param}")
	public String getText(@PathParam("param") String text){
		System.out.println("in getText() text="+text);
		
		MessageDao dao = new MessageDao();
		dao.insertMsg("unknown", text);
		dao.close();
		
		return text;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/post")
	public String user(@FormParam("user") String user, @FormParam("text") String text){
		System.out.println("in user() user="+user+" text="+text);
		
		MessageDao dao = new MessageDao();
		dao.insertMsg(user, text);
		JSONObject rsJSON = dao.getMsgs(user);
		rsJSON.put("last-msg", text);
		System.out.println("rsSON="+rsJSON.toString());
		
		dao.close();
		
		return rsJSON.toString(); 
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/postrpl")
	public String setReply(@FormParam("user") String user, @FormParam("msgid") String msgid, @FormParam("rpl") String rpl){
		System.out.println(">>> setReply() user="+user+" msgid="+msgid+" rpl="+rpl);
		
		MessageDao dao = new MessageDao();
		int intRet = dao.insertRpl(user, msgid, rpl);
		System.out.println("setReply()::intRet="+intRet);
		
		dao.close();
		
		return String.valueOf(intRet);
	}
}
