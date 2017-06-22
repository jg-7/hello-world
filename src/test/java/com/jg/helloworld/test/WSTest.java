package com.jg.helloworld.test;

import static org.junit.Assert.*;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
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
		
		//Negative test
		resource = client.resource("http://localhost:8080/hello-world/rest/hello-wss/");
		response = resource.get(ClientResponse.class);		
		assertEquals("Should return 404 status code", 404, response.getStatus());
		
	}
	
	@Test
	public void testDAO(){
		MessageDao dao = new MessageDao();
		boolean bResult = dao.insertMsg("tester", "unit test text", "Toronto");
		assertTrue("Insert operation should return true if successful", bResult);
		
		//Negative test
		bResult = dao.insertMsg("tester", "01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890", "Toronto");
		assertFalse("Insert operation should fail - msg too big", bResult);
		dao.close();
	}
	
	@Test
	public void testPost(){
		Client client = Client.create();
		String wsURL = "http://localhost:8080/hello-world/rest/hello-ws/post";
		String sUsr = "tester";
		String sText = "post test"; 

		WebResource resource = client.resource(wsURL+"?user="+sUsr+"&text="+sText.replaceAll("\\s", "%20"));
		ClientResponse response = resource.post(ClientResponse.class);
		
		//Run the assertion
		assertEquals("Should return 200 status code", 200, response.getStatus());
		
		//Negative test
		resource = client.resource("http://localhost:8080/hello-world/rest/hello-ws/");
		response = resource.get(ClientResponse.class);
		assertEquals("Should return 405 status code", 405, response.getStatus());
	}
	
	
	@Test
	public void testPostRpl(){
		Client client = Client.create();
		String wsURL = "http://localhost:8080/hello-world/rest/hello-ws/postrpl";
		String sUsr = "tester";
		String sMsgId = "1";
		String sRpl = "test rpl"; 

		WebResource resource = client.resource(wsURL);
		Form form = new Form();
		form.add("user", sUsr);
		form.add("msgid", sMsgId);
		form.add("rpl", sRpl);


		ClientResponse response = resource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, form);

		
		//Run the assertion
		assertEquals("Should return 200 status code", 200, response.getStatus());
		assertEquals("Should return 1 if all OK", "1", response.getEntity(String.class));
		
		//Negative test
		form = new Form();
		form.add("user", sUsr);
		form.add("msgid", "11111");
		form.add("rpl", sRpl);
		response = resource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, form);
		assertEquals("Should return 0 since id does not exist", "0", response.getEntity(String.class));
	}
}
