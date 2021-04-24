package com.ems.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.softtech.EmsApplication;
import com.softtech.controller.WorkDetailListController;
import com.softtech.service.WorkDetailListService;
/**
 * 参考資料：
 * 　https://qiita.com/a-pompom/items/3f834119c756e5286730
 * 　https://spring.pleiades.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-testing
 * 　https://terasolunaorg.github.io/guideline/5.4.1.RELEASE/ja/UnitTest/ImplementsOfUnitTest/ImplementsOfTestByLayer.html
 * @author softtech
 *
 */
@ContextConfiguration(classes = EmsApplication.class)
@RunWith(SpringRunner.class)
@WebMvcTest(WorkDetailListController.class)
public class TransportControllerTest {

	@Autowired
    private MockMvc mockMvc;

	// TransportControllerに全部サービスをＭｏｃｋする。
    @MockBean
    private WorkDetailListService service;

    @InjectMocks // モックオブジェクトの注入
    private WorkDetailListController itemController;

    @BeforeEach // テストメソッド（@Testをつけたメソッド）実行前に都度実施
    public void initmocks() {
        MockitoAnnotations.initMocks(this); // アノテーションの有効化
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build(); // MockMvcのセットアップ
    }

    @Test
    public void test1() throws Exception {

//    	Transport transport = new Transport();
//        Mockito.when(service.queryTransport(new HashMap<String, String>())).thenReturn(transport);

        mockMvc.perform(MockMvcRequestBuilders.get("/workdetaillist").accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.view().name("/ems/workdetaillist"))
        .andReturn();
    }

}
