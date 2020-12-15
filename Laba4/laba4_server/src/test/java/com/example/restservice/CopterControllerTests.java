/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.restservice;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@WebAppConfiguration
@SpringBootTest
@AutoConfigureMockMvc
public class CopterControllerTests extends AbstractTestNGSpringContextTests {
	private Logger logger = Logger.getLogger(CopterControllerTests.class);
	@Autowired
	private MockMvc mockMvc;

	@DataProvider(name = "dataFor_testMailService")
	public Object[][] getData() {
		return new Object[][]{
				{
						new ArrayList<String>(Arrays.asList("post:add","post:findById")),
						new ArrayList<String>(Arrays.asList(asJsonString(new Email("test@ykr.net", "TestBody", "TestSubj", "11")),"FOUND")),
						new ArrayList<Email>(Arrays.asList(new Email("test@ykr.net", "TestBody", "TestSubj", "11"),new Email("", "", "", "11"))),
				},
				{
						new ArrayList<String>(Arrays.asList("get:deleteAll","post:add","get:getAll")),
						new ArrayList<String>(Arrays.asList("OK",asJsonString(new Email("test@ykr.net", "TestBody", "TestSubj", "11")),asJsonString(Arrays.asList(new Email("test@ykr.net", "TestBody", "TestSubj", "11"))))),
						new ArrayList<Email>(Arrays.asList(new Email(), new Email("test@ykr.net", "TestBody", "TestSubj", "11"),new Email())),
				},
				{
						new ArrayList<String>(Arrays.asList("post:findByEmail")),
						new ArrayList<String>(Arrays.asList(asJsonString(Arrays.asList(new Email("email1@mail.com", "Sho ti", "Hurma", "001"), new Email("email1@mail.com", "I vprincipi nichoho", "Zal", "002"))))),
						new ArrayList<Email>(Arrays.asList(new Email("email1@mail.com", "", "TestSubj", ""))),
				},
				{
						new ArrayList<String>(Arrays.asList("post:findByEmail")),
						new ArrayList<String>(Arrays.asList("[]")),
						new ArrayList<Email>(Arrays.asList(new Email("unexpected@mail.com", "", "TestSubj", ""))),
				},
				{
						new ArrayList<String>(Arrays.asList("post:findBySubj")),
						new ArrayList<String>(Arrays.asList(asJsonString(Arrays.asList(new Email("email1@mail.com", "I vprincipi nichoho", "Zal", "002"), new Email("email3@mail.com", "Hello boys", "Zal", "003"))))),
						new ArrayList<Email>(Arrays.asList(new Email("", "", "Zal", ""))),
				},
				{
						new ArrayList<String>(Arrays.asList("post:findBySubj")),
						new ArrayList<String>(Arrays.asList("[]")),
						new ArrayList<Email>(Arrays.asList(new Email("", "", "UnexpectedSubj", ""))),
				},
				{
						new ArrayList<String>(Arrays.asList("post:deleteById", "post:findById")),
						new ArrayList<String>(Arrays.asList("DELETED", "NOT FOUND")),
						new ArrayList<Email>(Arrays.asList(new Email("", "", "", "002"), new Email("", "", "", "002"))),
				},
				{
						new ArrayList<String>(Arrays.asList("post:deleteById")),
						new ArrayList<String>(Arrays.asList("Email is not exist")),
						new ArrayList<Email>(Arrays.asList(new Email("", "", "", "111"))),
				},
				{
						new ArrayList<String>(Arrays.asList("get:deleteAll", "get:getAll")),
						new ArrayList<String>(Arrays.asList("OK", "[]")),
						new ArrayList<Email>(Arrays.asList(new Email(), new Email())),
				},
				{
						new ArrayList<String>(Arrays.asList("get:getAll")),
						new ArrayList<String>(Arrays.asList(asJsonString(Arrays.asList(new Email("email1@mail.com", "Sho ti", "Hurma", "001"),
								new Email("email1@mail.com", "I vprincipi nichoho", "Zal", "002"),
								new Email("email3@mail.com", "Hello boys", "Zal", "003"))))),
						new ArrayList<Email>(Arrays.asList(new Email())),
				},
		};
	}

	@BeforeMethod
	public void doBeforeTest() throws Exception {
		logger.info("---------------Add emails before test---------------");
		ArrayList<Email> emailArray = new ArrayList<Email>(Arrays.asList(
				new Email("email1@mail.com", "Sho ti", "Hurma", "001"),
				new Email("email1@mail.com", "I vprincipi nichoho", "Zal", "002"),
				new Email("email3@mail.com", "Hello boys", "Zal", "003")
		));

		for(int i=0; i<emailArray.size();i++) {
			this.mockMvc.perform(post("/add")
					.content(asJsonString(emailArray.get(i)))
					.contentType(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isOk());
		}
	}

	@AfterMethod
	public void doAfterTest() throws Exception {
		logger.info("---------------Delete all emails after test---------------");

		this.mockMvc.perform(get("/deleteAll"))
				.andExpect(status().isOk());
	}

	@Test(dataProvider = "dataFor_testMailService")
	public void testDataProvidedCopter(ArrayList<String> requests,ArrayList<String> responds,ArrayList<Email> contents) throws Exception{
		logger.info("---------------TestMailService---------------");

		for(int i=0; i<requests.size();i++){
			logger.info("Perform action " + requests.get(i));
			if (requests.get(i).split(":")[0].equals("post")){
				this.mockMvc.perform(post("/"+requests.get(i).split(":")[1])
						.content(asJsonString(contents.get(i)))
						.contentType(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(status().isOk())
						.andExpect(content().string(responds.get(i)));
			}
			else if (requests.get(i).split(":")[0].equals("get")){
				this.mockMvc.perform(get("/"+requests.get(i).split(":")[1]))
						.andDo(print())
						.andExpect(status().isOk())
						.andExpect(content().string(responds.get(i)));
			}
		}
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
