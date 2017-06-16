package com.jg.helloworld.test;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.ClientResponse;

import com.jg.helloworld.dao.*;

public class WSTest {

	@Test
	public void test() {
		Client client = Client.create();
		String wsURL = "http://localhost:8080/hello-world/rest/hello-ws/";
		String sText = "this is a test"; 

		WebResource resource = client.resource(wsURL+sText.replaceAll("\\s", "%20"));
		ClientResponse response = resource.get(ClientResponse.class);
		
		//Run the assertion
		assertEquals("Should return 200 status code", 200, response.getStatus());
		assertEquals("Return string must equal to input", sText, response.getEntity(String.class));
		
		resource = client.resource(wsURL);
		response = resource.get(ClientResponse.class);
		
		//Negative test
		assertEquals("Should return 404 status code", 404, response.getStatus());

	}
	@Test
	public void testDAO(){
		MessageDao dao = new MessageDao();
		boolean bResult = dao.insertMsg("unit test text");
		assertTrue("Insert operation should return true if successful", bResult);
		
		//Negative test
		bResult = dao.insertMsg("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
		assertFalse("Insert operation should fail - msg too big", bResult);
		dao.close();
	}

}
