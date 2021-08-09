package com.barcode.BarcodeProject.testProductApi;

import com.barcode.BarcodeProject.BarcodeProjectApplicationTests;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class GetAllProducts extends BarcodeProjectApplicationTests {

    private ResultActions result;
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Before("@GetAllProducts")
    public void init() throws Exception {
        initDatabase();
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @When("the client calls get all products")
    public void theClientCallsGetAllProducts()  throws Exception {
        result = this.mvc.perform(get("http://localhost:9080/products"));
    }

    @Then("the client receives status code of {int}")
    public void theClientReceivesStatusCodeOf(int statusCode) throws IOException {
        MockHttpServletResponse response = result.andReturn().getResponse();
        assertEquals(response.getStatus(), statusCode);
    }

    @And("the client receives a list of products")
    public void theClientReceivesAListOfProducts() throws Exception{
        result.andExpect(jsonPath("$[0].name", is("product1")))
                .andExpect(jsonPath("$[1].name", is("product2")));
    }

    @After("@GetAllProducts")
    public void tearDown() {
        cleanDatabase();
    }
}
