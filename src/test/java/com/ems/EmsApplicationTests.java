package com.ems;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
class EmsApplicationTests {

	@Test
	void contextLoads() 
	{
		String name="202002";
		
		System.out.println(String.format("%s年%s月",name.substring(0,4),name.substring(4,6)));
		
		System.out.println(String.format("%,d",Integer.parseInt(name)));
	}
}
