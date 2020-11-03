package com.preschool.stepdef;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.preschool.TestConfiguration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestConfiguration.class)
public class RegisterStepDefs {

    @Autowired
    private MockMvc mvc;
    private String reqBody = null;

    ResultActions action;

    @Given("^the predic application register page$")
    public void preparePredicAppRegisterPage() throws Throwable{ }

    @When("^ the user fill register form with \"<username>\" and \"<password>\", push the \"Register\" button$")
    public void setRequestBodyAndMakeRequest(String username, String password) throws Throwable
    {
        this.reqBody = "username=" + username + "&password=" + password;
        mvc.perform( MockMvcRequestBuilders
                .post("http://localhost:8080/register")
                .content(asJsonString(this.reqBody))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Then("^Registration process should be successful$")
    public void assertResponseStatusCode() throws Throwable
    {
        action.andExpect(status().is(200));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
