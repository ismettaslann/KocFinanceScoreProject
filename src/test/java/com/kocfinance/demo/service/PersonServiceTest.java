package com.kocfinance.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kocfinance.demo.dto.PersonDTO;
import com.kocfinance.demo.entity.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PersonServiceTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    ObjectMapper om = new ObjectMapper();

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void insertNewPersonScore() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        Person request  = Person.builder()
                                .nameAndSurname("İsmet Arslan")
                                .identityNumber("24365562145")
                                .phoneNumber("5064444444")
                                .residence("1")
                                .incomeTranche("1")
                                .build();

        String jsonRequest = om.writeValueAsString(request);

        MvcResult result = mockMvc.perform(post("/score/insert-new-person-score/").content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();

        String resultContent = result.getResponse().getContentAsString();
        PersonDTO response = om.readValue(resultContent, PersonDTO.class);
        Assert.assertTrue(response.isStatus() == Boolean.TRUE);

    }
}