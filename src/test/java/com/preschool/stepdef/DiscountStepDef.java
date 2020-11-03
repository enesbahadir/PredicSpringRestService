package com.preschool.stepdef;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.preschool.TestConfiguration;
import com.preschool.model.Discount;
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
public class DiscountStepDef {

    @Autowired
    private MockMvc mvc;
    private String reqBody = null;

    ResultActions action;

    @Given("^the predic application discount page$")
    public void preparePredicAppDiscountPage() throws Throwable {
    }

    @When("^the user push \"Discounts\" button on discount page$")
    public void setRequestBody() throws Throwable
    {
        mvc.perform( MockMvcRequestBuilders
                .get("http://localhost:8080/discounts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Then("^discount list is appeared on discount page$")
    public void assertDiscountListIsAppearedOnDiscountPage() throws Throwable
    {
        action.andExpect(status().is(200));
    }

    @Given("^the predic application add discount form$")
    public void preparePredicAppAddDiscountFormPage() throws Throwable {
    }

    @When("^the user fill the discount form, push \"New Discount\" button$")
    public void setRequestBodyAndMakeRequest(Discount discount) throws Throwable
    {
        mvc.perform( MockMvcRequestBuilders
                .post("http://localhost:8080/discounts")
                .content(asJsonString(discount))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Then("^new discount is added to discount list$")
    public void assertNewDiscountIsAddedToDiscounts() throws Throwable
    {
        action.andExpect(status().is(200));

    }

    @Given("^the predic application edit discount form$")
    public void preparePredicAppEditDiscountFormPage() throws Throwable {
    }

    @When("^the user push \"Edit Discount\" button, fill the edit discount form$")
    public void setEditDiscountRequest(Discount discount, int id) throws Throwable
    {
        mvc.perform( MockMvcRequestBuilders
                .put("http://localhost:8080/discounts/"+id)
                .content(asJsonString(discount))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Then("^selected discount is edited$")
    public void assertDiscountIsEdited() throws Throwable
    {
        action.andExpect(status().is(200));

    }

    @When("^the user push \"Delete Discount\" button$")
    public void setDeleteDiscountRequest(int id) throws Throwable
    {
        mvc.perform( MockMvcRequestBuilders
                .delete("http://localhost:8080/discounts/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Then("^selected discount is deleted$")
    public void assertDiscountIsDeleted() throws Throwable
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
