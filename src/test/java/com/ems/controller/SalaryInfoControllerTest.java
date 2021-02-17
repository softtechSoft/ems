//package com.ems.controller;
//
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.softtech.EmsApplication;
//import com.softtech.controller.SalaryInfoController;
//import com.softtech.entity.SalaryInfo;
//import com.softtech.entity.SalaryInfoComment;
//import com.softtech.service.SalaryInfoServiceImpl;
//
//@ContextConfiguration(classes = EmsApplication.class)
//@RunWith(SpringRunner.class)
//@WebMvcTest(SalaryInfoController.class)
//public class SalaryInfoControllerTest {
//
//	@Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private SalaryInfoServiceImpl salaryInfoService;
//
////    @BeforeEach
////    public void setUp() {
////
////        mockMvc = MockMvcBuilders.standaloneSetup(target).build();
////    }
//
//    @Test
//    public void test1() throws Exception {
//    	SalaryInfo salary = new SalaryInfo();
//    	salary.setEmployeeID("testID");
//        Mockito.when(salaryInfoService.querySalaryInfo(new HashMap<>())).thenReturn(salary);
//
//        List<SalaryInfoComment> column = new ArrayList<SalaryInfoComment>();
//        SalaryInfoComment coment = new SalaryInfoComment();
//        coment.setColumnName("name");
//        coment.setComment("test");;
//        column.add(coment);
//        Mockito.when(salaryInfoService.querySalaryInfoComment()).thenReturn(column);
//
//        ObjectMapper jsonMapper=new ObjectMapper();
//        Map<String,Object> map = new HashMap<>();
//		map.put("column", column);
//		map.put("data", salary);
//		String result = jsonMapper.writeValueAsString(map);
//
////        MockHttpSession session = new MockHttpSession();
////        session.setAttribute("userMailAdress", "testAddress");
////		session.setAttribute("userEmoplyeeID", "testID");
////		session.setAttribute("userAuthority", "testAutho");
////		session.setAttribute("userEmployeeName", "testName");
////		session.setAttribute("userUpdatePsw", "false");
////
////		MockHttpServletRequest request = new MockHttpServletRequest();
////        request.setSession(session);
//
////        ResultActions resultAction = this.mockMvc.perform(MockMvcRequestBuilders.get("/request-salarydetail").param("yearMonth", "202101"));
////        resultAction.andExpect(MockMvcResultMatchers.status().isOk());
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/request-salarydetail").param("yearMonth", "202101"))
//        .andExpect(MockMvcResultMatchers.status().isOk()
//        		);
////        mockMvc.perform(MockMvcRequestBuilders.get("/request-salarydetail").param("yearMonth", "202101"))
////        .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
////        .andExpect(MockMvcResultMatchers.status().isOk()
////        		);
//
//
//    	System.out.println("111111111111");
//    }
//
//}
