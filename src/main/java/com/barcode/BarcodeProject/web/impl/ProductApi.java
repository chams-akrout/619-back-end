package com.barcode.BarcodeProject.web.impl;

import com.barcode.BarcodeProject.common.ApiMessage;
import com.barcode.BarcodeProject.dao.ICategoryDao;
import com.barcode.BarcodeProject.dao.IProductDao;
import com.barcode.BarcodeProject.model.Category;
import com.barcode.BarcodeProject.model.Product;
import com.barcode.BarcodeProject.web.IProductApi;
import com.barcode.BarcodeProject.web.CategoryDto.CategoryViewDto;
import com.barcode.BarcodeProject.web.ProductDto.ForeignBarCodeDto;
import com.barcode.BarcodeProject.web.ProductDto.ProductCreateOrUpdateDto;
import com.barcode.BarcodeProject.web.ProductDto.ProductViewDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@Api(value = "productApi")
public class ProductApi implements IProductApi {
	@Autowired
	private IProductDao productDao;
	@Autowired
	private ICategoryDao categoryDao;
	private Object httpResponseBody;
	private HttpStatus httpStatus;
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	RestTemplate restTemplate;
	@Value("${barcode.api.key}")
	String apiKey;
	@Override
	public ResponseEntity<?> saveProduct(@RequestBody ProductCreateOrUpdateDto product) {
		try {
			configureModeMapper();
			Product productToBeSaved = modelMapper.map(product, Product.class);
			Product savedProduct = productDao.save(productToBeSaved);
			ProductViewDto productToBeReturned = modelMapper.map(savedProduct, ProductViewDto.class);
			httpResponseBody = productToBeReturned;
			httpStatus = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(httpResponseBody, httpStatus);

	}

	@Override
	public ResponseEntity<?> getAllProducts() {
		try {
			configureModeMapper();
			List<Product> products = productDao.findAll();
			Collections.sort(products, (d1, d2) -> {
				return d2.getPoints() - d1.getPoints();
			});
			httpResponseBody = !products.isEmpty() ? products.stream().map(product -> modelMapper.map(product, ProductViewDto.class)).collect(Collectors.toList()) : Collections.emptyList();
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(httpResponseBody, httpStatus);

	}

	@Override
	public ResponseEntity<?> updateProduct(@RequestBody ProductCreateOrUpdateDto product, @PathVariable int productId) {
		try {
			configureModeMapper();
			Optional<Product> existingProduct = productDao.findById(productId);
			if (existingProduct.isPresent()) {
				Product productToBeUpdated = modelMapper.map(product, Product.class);
				productToBeUpdated.setId(productId);
				Product updatedProduct = productDao.save(productToBeUpdated);
				httpResponseBody = modelMapper.map(updatedProduct, ProductViewDto.class);
				httpStatus = HttpStatus.OK;
			} else {
				httpResponseBody = ApiMessage.PRODUCT_NOT_FOUND;
				httpStatus = HttpStatus.NOT_FOUND;
			}

		} catch (Exception e) {
			httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(httpResponseBody, httpStatus);
	}

	@Override
	public ResponseEntity<?> deleteProduct(@PathVariable int productId) {
		try {

			Optional<Product> existingProduct = productDao.findById(productId);
			if (existingProduct.isPresent()) {
				productDao.delete(existingProduct.get());
				httpResponseBody = ApiMessage.PRODUCT_DELETED_SUCCESSFULLY;
				httpStatus = HttpStatus.OK;
			} else {
				httpResponseBody = ApiMessage.PRODUCT_NOT_FOUND;
				httpStatus = HttpStatus.NOT_FOUND;
			}

		} catch (Exception e) {
			httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(httpResponseBody, httpStatus);
	}

	@Override
	public ResponseEntity<?> getProductById(@PathVariable int productId) {
		try {
			configureModeMapper();
			Optional<Product> existingProduct = productDao.findById(productId);
			if (existingProduct.isPresent()) {
				httpResponseBody = modelMapper.map(existingProduct.get(), ProductViewDto.class);
				httpStatus = HttpStatus.OK;
			} else {
				httpResponseBody = ApiMessage.PRODUCT_NOT_FOUND;
				httpStatus = HttpStatus.NOT_FOUND;
			}

		} catch (Exception e) {
			httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(httpResponseBody, httpStatus);
	}

	@Override
	public ResponseEntity<?> getProductByCategory(@RequestBody Category category) {
		try {
			configureModeMapper();
			List<Product> products = productDao.findByCategory(category);

			httpResponseBody = !products.isEmpty() ? products.stream().map(product -> modelMapper.map(product, ProductViewDto.class)).collect(Collectors.toList()) : Collections.emptyList();
			httpStatus = HttpStatus.OK;

		} catch (Exception e) {
			httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(httpResponseBody, httpStatus);
	}

	@Override
	public ResponseEntity<?> getLocalProductsByForeignBarcode(@RequestBody ForeignBarCodeDto foreignBarcodeDto) {
		try {
			configureModeMapper();
			List<Product> products = getCategoryFromBarcode(foreignBarcodeDto.getBarcode());
			Collections.sort(products, (d1, d2) -> {
				return d2.getPoints() - d1.getPoints();
			});
			httpResponseBody = products.stream().map(product->modelMapper.map(product,ProductViewDto.class)).collect(Collectors.toList());
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(httpResponseBody, httpStatus);
	}

	@Override
	public ResponseEntity<?> getLocalProductsByStringForeignBarcode(@RequestBody String foreignBarcode) {
		try {
			configureModeMapper();
			List<Product> products = getCategoryFromBarcode(foreignBarcode);
			Collections.sort(products, (d1, d2) -> {
				return d2.getPoints() - d1.getPoints();
			});
			httpResponseBody = products.stream().map(product->modelMapper.map(product,ProductViewDto.class)).collect(Collectors.toList());
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(httpResponseBody, httpStatus);
	}
	@Override
	public ResponseEntity<?> updateProductAddScore(@RequestBody ProductCreateOrUpdateDto product,@PathVariable  int productId) {
		try {
			configureModeMapper();
			Optional<Product> existingProduct = productDao.findById(productId);
			if (existingProduct.isPresent()) {
				Product productToBeUpdated = modelMapper.map(product, Product.class);
				productToBeUpdated.setId(productId);
				productToBeUpdated.setPoints(productToBeUpdated.getPoints()+3);
				Product updatedProduct = productDao.save(productToBeUpdated);
				httpResponseBody = modelMapper.map(updatedProduct, ProductViewDto.class);
				httpStatus = HttpStatus.OK;
			} else {
				httpResponseBody = ApiMessage.PRODUCT_NOT_FOUND;
				httpStatus = HttpStatus.NOT_FOUND;
			}

		} catch (Exception e) {
			httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(httpResponseBody, httpStatus);
	}

	@Override
	public ResponseEntity<?> updateProductSubstractScore(@RequestBody ProductCreateOrUpdateDto product,@PathVariable  int productId) {
		try {
			configureModeMapper();
			Optional<Product> existingProduct = productDao.findById(productId);
			if (existingProduct.isPresent()) {
				Product productToBeUpdated = modelMapper.map(product, Product.class);
				productToBeUpdated.setId(productId);
				productToBeUpdated.setPoints(productToBeUpdated.getPoints()-2);
				Product updatedProduct = productDao.save(productToBeUpdated);
				httpResponseBody = modelMapper.map(updatedProduct, ProductViewDto.class);
				httpStatus = HttpStatus.OK;
			} else {
				httpResponseBody = ApiMessage.PRODUCT_NOT_FOUND;
				httpStatus = HttpStatus.NOT_FOUND;
			}

		} catch (Exception e) {
			httpResponseBody = ApiMessage.SERVER_ERROR_OCCURED;
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(httpResponseBody, httpStatus);
	}

	private List<Product> getCategoryFromBarcode(String barcode) {
		StringBuilder urlBuilder = constuctUrl(barcode);
		String rep=	restTemplate.getForObject(urlBuilder.toString(),String.class);
		String name = extractCategoryName(rep);
		Category categoryfromDB=categoryDao.findAll().stream().filter(c->name.contains(c.getName())).collect(Collectors.toList()).get(0);
		List<Product> products=productDao.findByCategory(categoryfromDB);
		return products;
	}

	private String extractCategoryName(String rep) {
		JsonObject jsonResponse = new Gson().fromJson(rep, JsonObject.class);
		String[] category=jsonResponse.get("products").getAsJsonArray().get(0).getAsJsonObject().get("category").getAsString().split(">");
		String name = category[category.length-1];
		return name;
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

	private void configureModeMapper() {
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}




}
