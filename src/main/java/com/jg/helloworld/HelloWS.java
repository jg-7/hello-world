package com.jg.helloworld;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.jg.helloworld.dao.*;

import javax.ws.rs.GET;


@Path("/hello-ws/{param}")
public class HelloWS {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getText(@PathParam("param") String text){
		MessageDao dao = new MessageDao();
		dao.insertMsg(text);
		dao.close();
		
		return text;
	}
	

}
