package com.jg.helloworld.test;

import static org.junit.Assert.*;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.ClientResponse;

public class WSTest {

	@Test
	public void test() {
		Client client = Client.create();
		WebResource resource = client.resource("http://localhost:8080/hello-world/rest/hello-ws/this%20is%20a%20test");
		ClientResponse response = resource.get(ClientResponse.class);
		
		//Run the assertion
		assertEquals("Should return 200 status code", 200, response.getStatus());
		assertEquals("Return string must equal to input", "this is a test", response.getEntity(String.class));
	}

}
