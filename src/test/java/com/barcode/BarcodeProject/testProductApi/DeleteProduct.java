package com.barcode.BarcodeProject.testProductApi;

import com.barcode.BarcodeProject.BarcodeProjectApplicationTests;
import com.barcode.BarcodeProject.dto.productDto.ProductDto;
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

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteProduct extends BarcodeProjectApplicationTests {

    private ResultActions result;
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    ProductDto product;

    @Before("@DeleteProduct")
    public void init() {
        product = productService.save(saveProduct());
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @When("the client calls delete product")
    public void theClientCallsDeleteProduct() throws Exception {
        result = this.mvc.perform(delete("http://localhost:9080/products/" + product.getId()));
    }

    @Then("the product is deleted")
    public void theProductIsDeleted() {
        Optional<ProductDto> searchedProduct = productService.findById(product.getId());
        assertThat(searchedProduct).isNotPresent();
    }

    @After("@DeleteProduct")
    public void tearDown(){
        cleanDatabase();
    }
}
