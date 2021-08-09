package com.barcode.BarcodeProject;

import com.barcode.BarcodeProject.dto.categoryDto.CategoryDto;
import com.barcode.BarcodeProject.dto.productDto.ProductDto;
import com.barcode.BarcodeProject.service.CategoryService;
import com.barcode.BarcodeProject.service.ProductService;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@SpringBootTest(classes = BarcodeProjectApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class BarcodeProjectApplicationTests {

	@Autowired
	public ProductService productService;

	@Autowired
	public CategoryService categoryService;

	public void initDatabase() {
		CategoryDto categoryDto=prepareCategoryForSave("Media");
		CategoryDto categoryDto1=prepareCategoryForSave("Food");
		CategoryDto category1 = categoryService.saveCategory(categoryDto);
		CategoryDto category2 = categoryService.saveCategory(categoryDto1);

		ProductDto productDto = prepareProductForSave("product1","factory1",category1.getId(),"image1");
		ProductDto productDto1 = prepareProductForSave("product2","factory2",category2.getId(),"image2");
		productService.save(productDto);
		productService.save(productDto1);
	}


	public ProductDto prepareProductForSave(String name, String factory, int categoryId,String image) {
		return ProductDto.builder()
				.name(name)
				.factory(factory)
				.categoryId(categoryId)
				.image(image)
				.points(10)
				.build();
	}

	public ProductDto saveProduct() {
		CategoryDto category=prepareCategoryForSave("category");
		CategoryDto savedCategory = categoryService.saveCategory(category);
		return prepareProductForSave("product","factory1",savedCategory.getId(),"image1");
	}

	public CategoryDto prepareCategoryForSave(String name){
		return CategoryDto.builder()
				.name(name)
				.build();
	}

	public void cleanDatabase() {
		productService.deleteAll();
		categoryService.deleteAll();
	}

}
