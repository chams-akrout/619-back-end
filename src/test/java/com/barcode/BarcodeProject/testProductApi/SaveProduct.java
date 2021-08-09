package com.barcode.BarcodeProject.testProductApi;

import com.barcode.BarcodeProject.BarcodeProjectApplicationTests;
import com.barcode.BarcodeProject.dto.productDto.ProductDto;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Transactional
public class SaveProduct extends BarcodeProjectApplicationTests {

    private ResultActions result;
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Before("@SaveProduct")
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @When("the client calls save product")
    public void theClientCallsSaveProduct() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        result = this.mvc.perform(post("http://localhost:9080/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(saveProduct()))
                .accept(MediaType.APPLICATION_JSON)
        );
    }

    @Then("the client receives http status code of {int}")
    public void theClientReceivesHttpStatusCodeOf(int statusCode) {
        MockHttpServletResponse response = result.andReturn().getResponse();
        assertEquals(response.getStatus(), statusCode);
    }

    @And("the product is saved")
    public void theProductIsSaved() {
        List<ProductDto> products = productService.findAll();
        assertEquals(products.size(), 1);
        assertEquals(products.get(0).getName(), "product");
    }

    @After("@SaveProduct")
    public void tearDown(){
        cleanDatabase();
    }


}
