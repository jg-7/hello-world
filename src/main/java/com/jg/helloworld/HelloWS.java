package com.jg.helloworld;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import com.jg.helloworld.dao.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;



@Path("/hello-ws")
public class HelloWS {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{param}")
	public String getText(@PathParam("param") String text){
		System.out.println("in getText() text="+text);
		
		MessageDao dao = new MessageDao();
		dao.insertMsg(text);
		dao.close();
		
		return text;
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/post")
	public String user(@FormParam("user") String user, @FormParam("text") String text){
	System.out.println("in user() user="+user+" text="+text);
		return text;
	}
}
