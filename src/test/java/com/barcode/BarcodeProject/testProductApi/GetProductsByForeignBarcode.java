package com.barcode.BarcodeProject.testProductApi;

import com.barcode.BarcodeProject.BarcodeProjectApplicationTests;
import com.barcode.BarcodeProject.dto.productDto.ForeignBarCodeDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class GetProductsByForeignBarcode extends BarcodeProjectApplicationTests {

    private ResultActions result;
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    ForeignBarCodeDto barcode;

    @Before("@GetProductsByForeignBarcode")
    public void init() throws Exception {
        initDatabase();
        barcode = ForeignBarCodeDto.builder().barcode("2940156555536").build();
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @When("the client calls get local products by foreign barcode")
    public void theClientCallsGetLocalProductsByForeignBarcode() throws Exception {
        ObjectWriter ow = getObjectWriter();

        result = this.mvc.perform(post("http://localhost:9080/products/foreignBarcode")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(barcode))
                .accept(MediaType.APPLICATION_JSON)

        );
    }

    @When("the client calls get local products by foreign barcode by long value")
    public void theClientCallsGetLocalProductsByForeignBarcodeByString() throws Exception {
        ObjectWriter ow = getObjectWriter();

        result = this.mvc.perform(post("http://localhost:9080/products/longForeignBarcode")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(Long.parseLong(barcode.getBarcode())))
                .accept(MediaType.APPLICATION_JSON)
        );
    }

    @Then("the client receives a list of local products")
    public void theClientReceivesAListOfLocalProducts() throws Exception {
        result.andExpect(jsonPath("$[0].name", is("product1")));

    }

    @After("@GetProductsByForeignBarcode")
    public void tearDown() {
        cleanDatabase();
    }

    private ObjectWriter getObjectWriter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        return mapper.writer().withDefaultPrettyPrinter();
    }
}
