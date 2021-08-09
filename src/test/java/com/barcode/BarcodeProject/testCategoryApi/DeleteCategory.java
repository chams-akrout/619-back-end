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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

public class DeleteCategory extends BarcodeProjectApplicationTests {

    private ResultActions result;
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    CategoryDto category;

    @Before("@DeleteCategory")
    public void init() {
        category = categoryService.saveCategory(prepareCategoryForSave("category"));
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @When("the client calls delete category")
    public void theClientCallsDeleteCategory() throws Exception {
        result = this.mvc.perform(delete("http://localhost:9080/categories/" + category.getId()));

    }

    @Then("the category is deleted")
    public void theCategoryIsDeleted() {
        Optional<CategoryDto> searchedCategory=categoryService.findById(category.getId());
        assertThat(searchedCategory).isNotPresent();
    }

    @After("@DeleteCategory")
    public void tearDown() {
        cleanDatabase();
    }
}
