package com.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.ProductDTO;
import com.project.model.Product;
import com.project.service.ProductService;
import com.project.utility.ErrorStructure;
import com.project.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class ProductController {
	
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@Operation(description = "The endpoint is used to add a new product to the database", responses = {
			@ApiResponse(responseCode = "200", description = "Product saved successfully!"),
			@ApiResponse(responseCode = "400", description = "Invalid inputs")})
	@PostMapping("/products")
	public ResponseEntity<ResponseStructure<Object>> addProduct(@RequestBody ProductDTO productDTO) {
		return productService.addProduct(productDTO);
	}

	@Operation(description = "This is get method to find the product based on Id", responses =  {@ApiResponse(responseCode = "302", description = "Product Found"),
			@ApiResponse(responseCode = "404", description = "Product not found by given Id", content = {@Content(schema = @Schema(implementation = ErrorStructure.class))})
			})
	@GetMapping("/products/{productId}")
	public ResponseEntity<ResponseStructure<Object>> findProductById(@PathVariable int productId) {
		return productService.findProductById(productId);
	}
	
	@PutMapping("/products/{productId}")
	public ResponseEntity<ResponseStructure<Object>> updateProduct(@PathVariable int productId, @RequestBody ProductDTO productDTO) {
		return productService.updateProduct(productId, productDTO);
	}
	
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<ResponseStructure<Object>> deleteProduct(@PathVariable int productId) {
		return productService.deleteProduct(productId);
	}
	
	@GetMapping("/products")
	public ResponseEntity<ResponseStructure<Object>> findAllProducts() {
		return productService.findAllProducts();
	}
}














