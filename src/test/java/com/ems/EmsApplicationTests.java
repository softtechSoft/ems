package com.ems;

import java.util.Date;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
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
