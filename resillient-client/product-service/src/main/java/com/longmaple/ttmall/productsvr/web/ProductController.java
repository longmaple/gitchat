package com.longmaple.ttmall.productsvr.web;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.longmaple.ttmall.productsvr.model.ProductInfo;
import com.longmaple.ttmall.productsvr.services.ProductService;
import com.longmaple.ttmall.productsvr.util.UserContextHolder;

@RestController
@RequestMapping(value="vendors/{vendorId}/products")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ProductInfo getProducts(@PathVariable("vendorId") String vendorId) {
    	logger.debug("此方法没有使用 @HystrixCommand 进行注释。可以获取: " + 
    			UserContextHolder.getCorrelationId());
        ProductInfo products = productService.getProductsByVendor(vendorId);
        return products;
    }
}
