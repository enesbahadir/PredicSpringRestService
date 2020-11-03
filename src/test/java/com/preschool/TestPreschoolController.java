package com.preschool;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.preschool.controller.PreschoolController;
import com.preschool.model.Preschool;
import com.preschool.repository.PreschoolRepository;
import com.preschool.service.PreschoolService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(PreschoolController.class)
public class TestPreschoolController {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PreschoolService preschoolService;

    @MockBean
    private PreschoolRepository preschoolRepository;

    public ResultMatcher ok = MockMvcResultMatchers.status().isOk();
    public ResultMatcher created = MockMvcResultMatchers.status().isCreated();
    public ResultMatcher accepted = MockMvcResultMatchers.status().isAccepted();

    @Test
    public void getAllPreschoolsAPI() throws Exception
    {

        mvc.perform( MockMvcRequestBuilders
                .get("http://localhost:8080/preschools")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(ok);
                //.andExpect(MockMvcResultMatchers.jsonPath("$._embedded").exists())
                //.andExpect(MockMvcResultMatchers.jsonPath("$.preschools[*].preschoolId").isNotEmpty());
    }

    @Test
    public void getPreschoolByIdAPI() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .get("http://localhost:8080/preschools/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(ok)
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void createPreschoolAPI() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .post("http://localhost:8080/preschools")
                .content(asJsonString(new Preschool( "firstName",
                        "01/10/2021", 1000)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(created);
                //.andExpect(MockMvcResultMatchers.jsonPath("$.preschoolId").exists());
    }

    @Test
    public void updatePreschoolAPI() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .put("http://localhost:8080/preschools/{id}", 2)
                .content(asJsonString(new Preschool( "secondName",
                        "01/10/2021", 1200)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(ok)
                .andExpect(MockMvcResultMatchers.jsonPath("$.preschoolName").value("secondName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endOfEarlyRegistrationDate").value("01/10/2021"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(1200));
    }

    @Test
    public void deletePreschoolAPI() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders.delete("http://localhost:8080/preschools/{id}", 1) )
                .andExpect(accepted);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
