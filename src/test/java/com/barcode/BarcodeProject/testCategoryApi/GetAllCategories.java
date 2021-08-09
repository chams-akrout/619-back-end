package com.barcode.BarcodeProject.testCategoryApi;

import com.barcode.BarcodeProject.BarcodeProjectApplicationTests;
import com.barcode.BarcodeProject.dto.categoryDto.CategoryDto;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class GetAllCategories extends BarcodeProjectApplicationTests {

    private ResultActions result;
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Before("@GetAllCategories")
    public void init() throws Exception {
        CategoryDto categoryDto=prepareCategoryForSave("Media");
        CategoryDto categoryDto1=prepareCategoryForSave("Food");
        categoryService.saveCategory(categoryDto);
        categoryService.saveCategory(categoryDto1);
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @When("the client calls get all categories")
    public void theClientCallsGetAllCategories() throws Exception {
        result = this.mvc.perform(get("http://localhost:9080/categories"));
    }

    @Then("the client receives a list of categories")
    public void theClientReceivesAListOfCategories() throws Exception{
        result.andExpect(jsonPath("$[0].name", is("Media")))
                .andExpect(jsonPath("$[1].name", is("Food")));
    }

    @After("@GetAllCategories")
    public void tearDown() {
        cleanDatabase();
    }
}
