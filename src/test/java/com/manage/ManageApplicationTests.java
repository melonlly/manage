package com.manage;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alibaba.fastjson.JSONObject;
import com.manage.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManageApplicationTests {

	@Test
	public void contextLoads() {
	}

	private MockMvc mockMvc; // 模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化。

	@Autowired
	private WebApplicationContext wac; // 注入WebApplicationContext

	@Before // 在测试开始前初始化工作
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testPage() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", new Integer(0));
		params.put("size", new Integer(5));
		User user = new User();
		user.setIs_admin(1);
		params.put("user", user);

		MvcResult result = mockMvc.perform(post("/user/list")
				.content(JSONObject.toJSONString(params)))
//				.andExpect(status().isOk())// 模拟向testRest发送get请求
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
				.andReturn();// 返回执行请求的结果

		System.out.println(result.getResponse().getContentAsString());
	}
	@Test
	public void tesetRemoveUser() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", new Integer(3));
		MvcResult result = mockMvc.perform(post("/user/remove")
				.content(JSONObject.toJSONString(params)))
				.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	@Test
	public void testCatalog() throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		MvcResult result = mockMvc.perform(post("/catalog/all")
				.content(JSONObject.toJSONString(params)))
				.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
}
