package com.ems.service;


import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.softtech.EmsApplication;
import com.softtech.entity.Transport;
import com.softtech.service.TransportAllService;
import com.softtech.service.TransportServiceImpl;

/**
 * 参考資料：
 * 　https://spring.pleiades.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-testing-spring-boot-applications-detecting-web-app-type
 * @author softtech
 *
 */
@ContextConfiguration(classes = EmsApplication.class)
@SpringBootTest
public class TransportAllServiceTest {

	// テスト対象サービスにもくしたい引数を生成する。
	@Mock
	private TransportServiceImpl mockTransportService;

	// テスト対象サービスのインスタンスを自動生成する
	@InjectMocks
    private TransportAllService mockTransportAllService;


    @Test
    public void test1() throws Exception { 


    	Map<String, String> mapper = new HashMap<String, String>();
    	mapper.put("employeeID", "testID");

    	// テスト対象関数内に存在する他のクラス関数などをもくする。
    	Transport transport = new Transport();
    	transport.setStartDate("20210304");
    	Mockito.when(mockTransportService.queryTransport(mapper)).thenReturn(transport);

    	// テストする。
    	Transport tt = mockTransportAllService.getTransportInf(mapper);

    	// 結果確認
    	String rtn = tt.getStartDate();
    	assertEquals(rtn,"2021/03/04");
    }
    @Test
    public void test2() throws Exception {


    	Map<String, String> mapper = new HashMap<String, String>();
    	mapper.put("employeeID", "testID");

    	// テスト対象関数内に存在する他のクラス関数などをもくする。
    	Transport transport = new Transport();
    	transport.setStartDate("20210304");
    	//Mockito.when(mockTransportService.queryTransport(mapper)).thenReturn(transport);
    	Mockito.when(mockTransportService.queryTransport(mapper)).thenReturn(null);
    	// テストする。
    	Transport tt = mockTransportAllService.getTransportInf(mapper);

    	// 結果確認
    	
    	String rtn = tt.getStartDate();

    	assertEquals(rtn,"");
    }
}
