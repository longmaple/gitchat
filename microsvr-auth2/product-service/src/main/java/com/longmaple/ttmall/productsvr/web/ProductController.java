package com.longmaple.ttmall.productsvr.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.longmaple.ttmall.productsvr.client.VendorClient;
import com.longmaple.ttmall.productsvr.model.Product;
import com.longmaple.ttmall.productsvr.model.ProductInfo;
import com.longmaple.ttmall.productsvr.model.Vendor;
import com.longmaple.ttmall.productsvr.services.ProductService;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

@RestController
@RequestMapping(value="/vendors/{vendorId}/products")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
    @Autowired
    private ProductService productService;
    
    @Autowired
    private VendorClient vendorClient;

    @GetMapping("/")
    public ProductInfo getProducts(@PathVariable("vendorId") String vendorId) {
    	Vendor vendor = vendorClient.getVendor(vendorId);
    	String vendorName = vendor.getName();
    	logger.info("供应商 = " + vendorName);
        List<Product> products = productService.getProductsByVendor(vendorId);
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProducts(products);
        productInfo.setVendorName(vendorName);
        return productInfo;
    }

    @GetMapping("/{productId}")
    public Product getProducts( @PathVariable("vendorId") String vendorId,
                                @PathVariable("productId") String productId) {
        return productService.getProduct(vendorId, productId);
    }

    @RequestMapping(value="{productId}", method = RequestMethod.PUT)
    public String updateProducts( @PathVariable("productId") String productId) {
        return String.format("Put action");
    }

    @RequestMapping(value="/",method = RequestMethod.POST)
    public void saveProducts(@RequestBody Product product) {
       productService.saveProduct(product);
    }

    @RequestMapping(value="{productId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteProducts( @PathVariable("productId") String productId) {
        return String.format("Delete action");
    }
}
