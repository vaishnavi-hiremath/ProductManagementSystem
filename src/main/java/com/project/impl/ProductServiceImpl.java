package com.project.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.dto.ProductDTO;
import com.project.exception.ProductNotFoundByIdException;
import com.project.model.Product;
import com.project.repository.ProductRepository;
import com.project.service.ProductService;
import com.project.utility.ResponseStructure;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	private ResponseStructure<Object> responseStructure;

	public ProductServiceImpl(ProductRepository productRepository, ResponseStructure<Object> responseStructure) {
		super();
		this.productRepository = productRepository;
		this.responseStructure = responseStructure;
	}

	@Override
	public ResponseEntity<ResponseStructure<Object>> addProduct(ProductDTO productDTO) {
		
		responseStructure.setStatuscode(HttpStatus.CREATED.value())
		  				 .setMessage("Product Created successfully!!")
		  				 .setData(productRepository.save(mapToProduct(productDTO, new Product())));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(responseStructure);
	}

	@Override
	public ResponseEntity<ResponseStructure<Object>> findProductById(int productId) {
		return productRepository.findById(productId)
								.map(product->ResponseEntity.status(HttpStatus.FOUND)
															.body(responseStructure.setStatuscode(HttpStatus.FOUND.value())
																				   .setMessage("Product Object is Found")
																				   .setData(product)))
								.orElseThrow(()-> new ProductNotFoundByIdException("Product Object Not Found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<Object>> updateProduct(int productId, ProductDTO productDTO) {
		
		return productRepository.findById(productId)
								.map(p->ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
													  .setMessage("Product Updated Successfully!")
													  .setData(productRepository.save(mapToProduct(productDTO, p)))))
								.orElseThrow(()->new ProductNotFoundByIdException("Product Not Found By Id"));
	}
	
	@Override
	public ResponseEntity<ResponseStructure<Object>> deleteProduct(int productId) {
		return productRepository.findById(productId).map(product->{
														productRepository.delete(product);
														return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
																								  .setMessage("Product Object deleted successfully!")
																								  .setData(product));
													}).orElseThrow(()->new ProductNotFoundByIdException("Product Not found by Id"));
	}

	@Override
	public ResponseEntity<ResponseStructure<Object>> findAllProducts() {
		return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
												  .setMessage("All Products Found!")
												  .setData(productRepository.findAll()));
	}
	
	private Product mapToProduct(ProductDTO productDTO, Product product) {
		return product.setProductName(productDTO.getProductName())
				.setProductCategory(productDTO.getProductCategory())
				.setProductPrice(productDTO.getProductPrice());
	}

}
