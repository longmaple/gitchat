package com.longmaple.ttmall.productsvr.repository;

import org.springframework.data.repository.CrudRepository;

import com.longmaple.ttmall.productsvr.model.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,String>  {
    public List<Product> findByVendorId(String vendorId);
    public Product findByVendorIdAndProductId(String vendorId,String productId);
}
