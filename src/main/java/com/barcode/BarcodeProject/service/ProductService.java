package com.barcode.BarcodeProject.service;

import com.barcode.BarcodeProject.dao.ICategoryDao;
import com.barcode.BarcodeProject.dao.IProductDao;
import com.barcode.BarcodeProject.dto.productDto.ForeignBarCodeDto;
import com.barcode.BarcodeProject.dto.productDto.ProductDto;
import com.barcode.BarcodeProject.mapper.ProductMapper;
import com.barcode.BarcodeProject.model.Category;
import com.barcode.BarcodeProject.model.Product;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private IProductDao productDao;

    @Autowired
    private ICategoryDao categoryDao;

    @Autowired
    RestTemplate restTemplate;
    @Value("${barcode.api.key}")
    String apiKey;

    public List<ProductDto> findByCategory(Category category) {
        List<Product> products = productDao.findByCategory(category);

        return !products.isEmpty() ? products.stream()
                .map(ProductMapper::toProductDto).collect(Collectors.toList()) : Collections.emptyList();
    }

    public ProductDto save(ProductDto productDto) {
        Product savedProduct = productDao.save(ProductMapper.toProduct(productDto));
        return ProductMapper.toProductDto(savedProduct);
    }

    public List<ProductDto> findAll() {
        List<Product> products = productDao.findAll();
        return !products.isEmpty() ? products.stream()
                .map(ProductMapper::toProductDto).collect(Collectors.toList()) : Collections.emptyList();
    }

    public Optional<ProductDto> update(ProductDto productDto, int id) {
        Optional<Product> existingProduct = productDao.findById(id);
        if (existingProduct.isPresent()) {
            Product updatedProduct = productDao.save(ProductMapper.toProduct(productDto));
            return Optional.of(ProductMapper.toProductDto(updatedProduct));
        }
        return Optional.empty();
    }

    public Optional<ProductDto> findById(int id) {
        Optional<Product> existingProduct = productDao.findById(id);
        return existingProduct.map(ProductMapper::toProductDto);
    }

    public boolean delete(int id) {
        Optional<Product> existingProduct = productDao.findById(id);
        if (existingProduct.isPresent()) {
            productDao.delete(existingProduct.get());
            return true;
        }
        return false;
    }

    public List<ProductDto> getLocalProductsByForeignBarcode(ForeignBarCodeDto foreignBarcodeDto) {
        List<Product> products = getCategoryFromBarcode(foreignBarcodeDto.getBarcode());
        return !products.isEmpty() ? products.stream()
                .map(ProductMapper::toProductDto).collect(Collectors.toList()) : Collections.emptyList();

    }

    public List<ProductDto> getLocalProductsByStringForeignBarcode(String foreignBarcode) {
        List<Product> products = getCategoryFromBarcode(foreignBarcode);
        return !products.isEmpty() ? products.stream()
                .map(ProductMapper::toProductDto).collect(Collectors.toList()) : Collections.emptyList();

    }

    private List<Product> getCategoryFromBarcode(String barcode) {
        StringBuilder urlBuilder = constuctUrl(barcode);
        String rep = restTemplate.getForObject(urlBuilder.toString(), String.class);
        String name = extractCategoryName(rep);
        Category categoryfromDB = categoryDao.findAll().stream().filter(c -> name.contains(c.getName())).collect(Collectors.toList()).get(0);
        return productDao.findByCategory(categoryfromDB);
    }

    private String extractCategoryName(String rep) {
        JsonObject jsonResponse = new Gson().fromJson(rep, JsonObject.class);
        String[] category = jsonResponse.get("products").getAsJsonArray().get(0).getAsJsonObject().get("category").getAsString().split(">");
        return category[category.length - 1];
    }

    private StringBuilder constuctUrl(String foreignBarcode) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("https://api.barcodelookup.com/v2/products?");
        urlBuilder.append("barcode=");
        urlBuilder.append(foreignBarcode);
        urlBuilder.append("&key=");
        urlBuilder.append(apiKey);
        return urlBuilder;
    }


}
