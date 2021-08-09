package com.barcode.BarcodeProject.testProductApi;

import com.barcode.BarcodeProject.BarcodeProjectApplicationTests;
import com.barcode.BarcodeProject.dto.productDto.ProductDto;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UpdateProduct extends BarcodeProjectApplicationTests {

    private ResultActions result;
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    ProductDto product;

    @Before("@UpdateProduct")
    public void init() {
        ProductDto productDto = saveProduct();
        productDto.setName("updated");
        product = productService.save(productDto);
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @When("the client calls update product")
    public void theClientCallsUpdateProduct() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        product.setName("product");
        result = this.mvc.perform(put("http://localhost:9080/products/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(product))
                .accept(MediaType.APPLICATION_JSON)
        );
    }


    @Then("the product is updated")
    public void theProductIsUpdated() {
        Optional<ProductDto> foundProduct = productService.findById(product.getId());
        assertThat(foundProduct.get().getName()).isEqualTo("product");
    }

    @After("@UpdateProduct")
    public void tearDown() {
        cleanDatabase();
    }
}
