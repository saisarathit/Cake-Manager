package com.waracle.cakemanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemanager.entity.Cake;
import com.waracle.cakemanager.repository.CakeRepository;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.waracle.cakemanager.util.Constants.CONTENT_DISPOSITION;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CakeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CakeRepository cakeRepository;

    @Test
    public void testCakesRootRestCall_whenRequestEndpoint_returnCakesFromSystem() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        Assert.assertNotNull(response);
        List<Cake> cakeList = (List<Cake>) cakeRepository.findAll();
        Assert.assertTrue(response.contains(cakeList.get(0).getTitle()));
    }

    @Test
    public void testCakesRestCall_whenRequestCakesEndpoint_returnAttachment() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cakes");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        Assert.assertNotNull(response);
        Assert.assertTrue(Objects.requireNonNull(mvcResult.getResponse().getHeader(HttpHeaders.CONTENT_DISPOSITION)).contains(CONTENT_DISPOSITION));
    }
    @Test
    public void testCakesRestCall_whenRequestEndpointWithFileName_returnGivenFileNameAttachment() throws Exception {
        String fileName = "test.json";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cakes?fileName="+fileName);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        Assert.assertNotNull(response);
        Assert.assertTrue(Objects.requireNonNull(mvcResult.getResponse().getHeader(HttpHeaders.CONTENT_DISPOSITION)).contains(CONTENT_DISPOSITION));
        Assert.assertTrue(Objects.requireNonNull(mvcResult.getResponse().getHeader(HttpHeaders.CONTENT_DISPOSITION)).contains(fileName));
    }

    @Test
    public void testSaveCakeRestCall_whenRequestPostEndpoint_shouldSaveRecord() throws Exception {
        Cake newCake = new Cake("Test Cake", "This is junit cake", "url");
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cake")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(newCake));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isCreated()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        Assert.assertNotNull(response);
        Assert.assertTrue(response.contains(newCake.getTitle()));
    }

    @Test
    public void testSaveCakesRestCall_whenRequestPostEndpoint_shouldSaveRecords() throws Exception {

        List<Cake> cakeList =  Arrays.asList(new Cake("Test Cake1", "This is junit cake", "url"),
                new Cake("Test Cake2", "This is junit cake", "url"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cakes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(cakeList));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isCreated()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        Assert.assertNotNull(response);
        Assert.assertTrue(response.contains(cakeList.get(0).getTitle()));
    }
}
