package com.barcode.BarcodeProject.testCategoryApi;

import com.barcode.BarcodeProject.BarcodeProjectApplicationTests;
import com.barcode.BarcodeProject.dto.categoryDto.CategoryDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class SaveCategory extends BarcodeProjectApplicationTests {

    private ResultActions result;
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Before("@SaveCategory")
    public void init() throws Exception {

        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @When("the client calls save category")
    public void theClientCallsSaveCategory() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        result = this.mvc.perform(post("http://localhost:9080/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(prepareCategoryForSave("category")))
                .accept(MediaType.APPLICATION_JSON)
        );
    }

    @Then("the client receives success http code of {int}")
    public void theClientReceivesSuccessHttpCodeOf(int statusCode) {
        MockHttpServletResponse response = result.andReturn().getResponse();
        assertEquals(response.getStatus(), statusCode);
    }

    @And("the category is saved")
    public void theCategoryIsSaved() {
        List<CategoryDto> categories = categoryService.findAll();
        assertEquals(categories.size(), 1);
        assertEquals(categories.get(0).getName(), "category");
    }
    @After("@SaveCategory")
    public void tearDown(){
        cleanDatabase();
    }
}
