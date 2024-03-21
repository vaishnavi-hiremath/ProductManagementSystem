package com.project.service;

import org.springframework.http.ResponseEntity;

import com.project.dto.ProductDTO;
import com.project.utility.ResponseStructure;

public interface ProductService {

	ResponseEntity<ResponseStructure<Object>> addProduct(ProductDTO productDTO);

	ResponseEntity<ResponseStructure<Object>> findProductById(int productId);

	ResponseEntity<ResponseStructure<Object>> updateProduct(int productId, ProductDTO productDTO);

	ResponseEntity<ResponseStructure<Object>> deleteProduct(int productId);

	ResponseEntity<ResponseStructure<Object>> findAllProducts();

}
