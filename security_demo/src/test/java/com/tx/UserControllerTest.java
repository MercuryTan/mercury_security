package com.tx;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc //自动装配mockmvc
public class UserControllerTest {

   /* @Autowired
    WebApplicationContext wac;*/

    @Autowired
    private MockMvc mockMvc;

   /* @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(wac).build();
    }*/


    @Test
    public void whenQuerySuccess() throws Exception {
        String str = mockMvc.perform(MockMvcRequestBuilders.get("/user")
//                .param("userName","mercury")
                //输入参数为对象时
                .param("userName","stt")
                .param("age","12")
                .param("ageTo","90")
                //分页参数
                /*.param("size","15")
                .param("page","2")
                .param("sort","age,desc")*/
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value("3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString(); //获取返回值
        System.out.println(str);
    }

    @Test
    public void whenGetInfoSuccess() throws Exception {
        String str =
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getInfo/1/55")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString();

        System.out.println(str);
    }

}