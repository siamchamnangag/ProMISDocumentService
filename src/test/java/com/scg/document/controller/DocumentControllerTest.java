package com.scg.document.controller;

import com.scg.document.Application;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by tanatloke on 7/13/2017 AD.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration
public class DocumentControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @org.junit.Test
    public void testCreateDocumentFromTemplateShouldSuccess() throws Exception {
        //
        MvcResult result = mockMvc.perform(post("/documents/1")).andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();

        assertTrue(content.startsWith("https://github.com/siamchamnangag/ProMISFileService/raw/master/src/main/resources/PMOC_"));
        assertTrue(content.endsWith(".xlsx"));
    }

}