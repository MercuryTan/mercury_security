package com.tx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

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

    @Test
    public void whenCreateSuccess() throws Exception {
        long birthday = new Date().getTime();
        String content = "{\"userName\":\"\",\"password\":\"111111\",\"birthday\":\""+birthday+" \"}";
        String returnContent =  mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
               .content(content)
        )
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
        .andReturn().getResponse().getContentAsString();

        System.out.println("--"+returnContent);

    }

    @Test
    public void whenUpdateSuccess() throws Exception {
        long birthday = new Date().getTime();
        String content = "{ \"id\":\"1\", \"userName\":\"\",\"password\":\"222\",\"birthday\":\""+birthday+" \"}";
        String returnContent =  mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();

        //System.out.println("--"+returnContent);

    }

}