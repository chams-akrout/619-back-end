package com.barcode.BarcodeProject.testCategoryApi;

import com.barcode.BarcodeProject.BarcodeProjectApplicationTests;
import com.barcode.BarcodeProject.dto.categoryDto.CategoryDto;
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

public class UpdateCategory extends BarcodeProjectApplicationTests {

    private ResultActions result;
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    CategoryDto category;

    @Before("@UpdateCategory")
    public void init() {
      CategoryDto  categoryDto =prepareCategoryForSave("Media");
      category=categoryService.saveCategory(categoryDto);
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @When("the client calls update category")
    public void theClientCallsUpdateCategory()  throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        result = this.mvc.perform(put("http://localhost:9080/categories/" + category.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(prepareCategoryForSave("Books")))
                .accept(MediaType.APPLICATION_JSON)
        );

    }

    @Then("the category is updated")
    public void theCategoryIsUpdated() {
        Optional<CategoryDto> searchedCategory=categoryService.findById(category.getId());
        assertThat(searchedCategory.get().getName()).isEqualTo("Books");
    }

    @After("@UpdateCategory")
    public void tearDown() {
        cleanDatabase();
    }
}
