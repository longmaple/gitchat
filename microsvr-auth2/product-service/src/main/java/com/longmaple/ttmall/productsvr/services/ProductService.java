package com.longmaple.ttmall.productsvr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longmaple.ttmall.productsvr.config.ServiceConfig;
import com.longmaple.ttmall.productsvr.model.Product;
import com.longmaple.ttmall.productsvr.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	ServiceConfig config;

	public Product getProduct(String vendorId, String productId) {
		Product product = productRepository.findByVendorIdAndProductId(vendorId, productId);
		return product;
	}

	public List<Product> getProductsByVendor(String vendorId){
		return productRepository.findByVendorId( vendorId );
	}
	
	public void saveProduct(Product product){
		product.setProductId( UUID.randomUUID().toString());

		productRepository.save(product);

	}

	public void updateProduct(Product product){
		productRepository.save(product);
	}

	public void deleteProduct(Product product){
		productRepository.delete(product);
	}

}
