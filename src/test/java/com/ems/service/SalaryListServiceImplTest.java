package com.ems.service;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.softtech.EmsApplication;
import com.softtech.entity.SalaryInfo;
import com.softtech.mapper.salarylistMapper;
import com.softtech.service.SalaryListServiceImpl;
import com.softtech.util.DateUtil;


@ContextConfiguration(classes = EmsApplication.class)
@SpringBootTest
public class SalaryListServiceImplTest {

	// テスト対象サービスにもくしたい引数を生成する。
	@Mock
	private salarylistMapper salarylistMapper;


	// テスト対象サービスのインスタンスを自動生成する
	@InjectMocks
    private SalaryListServiceImpl salaryListServiceImpl;

    @Test
    public void test1() throws Exception {

    	String monthP = DateUtil.chgMonthToYM("202102");
    	SalaryInfo salaryInfo =new SalaryInfo();
    	salaryInfo.setEmployeeID("ED002");

    	// テスト対象関数内に存在する他のクラス関数などをもくする。
    	//List<SalaryInfo> salaryinfolist =  salarylistMapper.getsalaryinfolist(monthP);
    	List<SalaryInfo> salaryinfolist = new ArrayList<SalaryInfo>();
    	salaryinfolist.add(salaryInfo);
    	Mockito.when(salarylistMapper.getsalaryinfolist(monthP)).thenReturn(salaryinfolist);

    	// テストする。
    	List<SalaryInfo> sl= salaryListServiceImpl.querySalaryList(monthP);

    	// 結果確認
    	for(SalaryInfo s:sl) {

    	String rtn = s.getEmployeeID();
    	assertEquals(rtn,"ED002");
    }

}

}
