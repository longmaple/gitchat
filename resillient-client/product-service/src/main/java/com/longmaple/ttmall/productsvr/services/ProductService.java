package com.longmaple.ttmall.productsvr.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.longmaple.ttmall.productsvr.client.VendorFeignClient;
import com.longmaple.ttmall.productsvr.model.Product;
import com.longmaple.ttmall.productsvr.model.ProductInfo;
import com.longmaple.ttmall.productsvr.model.Vendor;
import com.longmaple.ttmall.productsvr.repository.ProductRepository;
import com.longmaple.ttmall.productsvr.util.UserContextHolder;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

@Service
public class ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private VendorFeignClient vendorFeignClient;

	@HystrixCommand(fallbackMethod = "fallbackProductList",
			threadPoolKey = "productByVendorThreadPool",
			threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "35"),
					@HystrixProperty(name="maxQueueSize", value = "15") },
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
	})
	public ProductInfo getProductsByVendor(String vendorId) {
		
		logger.debug("此方法使用 @HystrixCommand 进行注释。ID = " + 
						UserContextHolder.getCorrelationId());
		// 远程调用 Vendor Service 的 API
		Vendor vendor = vendorFeignClient.getVendor(vendorId);
		String vendorName = vendor.getName();
		ProductInfo productInfo = new ProductInfo();
		List<Product> products = productRepository.findByVendorId(vendorId);
		productInfo.setProducts(products);
		productInfo.setVendorName(vendorName);
		return productInfo;
	}

	@SuppressWarnings("unused")
	private ProductInfo fallbackProductList(String vendorId) {
		List<Product> fallbackList = new ArrayList<>();
		Product product = new Product();
		product.setProductName("备用大米");
		product.setCategory("食品");
		product.setInStock(5);
		product.setPrice(new BigDecimal("199.99"));
		fallbackList.add(product);
		ProductInfo productInfo = new ProductInfo();
		productInfo.setVendorName("备用供应商");
		productInfo.setProducts(fallbackList);
		return productInfo;
	}
}
