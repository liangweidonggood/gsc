package com.lwd.gsc.module.system.controller;

import com.baomidou.mybatisplus.test.autoconfigure.AutoConfigureMybatisPlus;
import com.lwd.gsc.module.system.model.User;
import com.lwd.gsc.module.system.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(IndexController.class)
@AutoConfigureMockMvc
@AutoConfigureMybatisPlus
class IndexControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @Test
    void testGetUserById() throws Exception {
        assertNotNull(mockMvc);
        // 创建一个User对象
        User user = new User();
        user.setId(1L);
        user.setUsername("Alice");
        user.setPassword("123");

        // 当调用userService.getUserById(1L)时，返回user对象
        when(userService.getUserById(1L)).thenReturn(user);

        // 模拟发送GET请求到/users/1，并验证响应状态码为200，响应内容为JSON格式的user对象
        mockMvc.perform(get("/system/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("Alice"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("123"));
    }
}
