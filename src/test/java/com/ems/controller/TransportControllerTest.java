package com.ems.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.softtech.EmsApplication;
import com.softtech.controller.TransportController;
import com.softtech.entity.Transport;
import com.softtech.service.TransportAllService;
import com.softtech.service.TransportServiceImpl;

@ContextConfiguration(classes = EmsApplication.class)
@RunWith(SpringRunner.class)
@WebMvcTest(TransportController.class)
public class TransportControllerTest {

	@Autowired
    private MockMvc mockMvc;

	// TransportControllerに全部サービスをＭｏｃｋする。
    @MockBean
    private TransportServiceImpl service;
    @MockBean
    private TransportAllService allService;

    @Test
    public void test1() throws Exception {

    	Transport transport = new Transport();
        Mockito.when(service.queryTransport(new HashMap<String, String>())).thenReturn(transport);

        mockMvc.perform(get("/workdetail"))
        .andExpect(status().isOk());
        //.andExpect(view().name("/ems/transpirt"));

    }

}
