package com.jg.helloworld.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.eclipse.jdt.internal.compiler.ast.AssertStatement;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class UnitTest{
	@Test(timeout = 1)
	public void testMe(){
		String str = "Test Me";
		//assertEquals("Test Me", str);
		//assertNotNull(str);
		
		for(int i=0;i<100000;i++){
			
		}
				
		String s1 = new String("abc");
		String s2 = new String("abc");
		String s3 = "def";
		String s4 = "def";
		String s5 = "abc";
		
		//assertEquals(s1, s2);
		//assertEquals(s3, s4);
		//assertSame(s1, s2);
		//assertSame(s3, s4);
		assertEquals(s1, s5);
 		//assertSame(s1, s5);

	}
	
	@Test
	public void testMeToo(){
		Client client = Client.create();
		WebResource resource = client.resource("http://localhost:8080/hello-world/rest/hello-ws");
		ClientResponse response = resource.get(ClientResponse.class);
		assertEquals("Should return 200", 200, response.getStatus());
	}
}
