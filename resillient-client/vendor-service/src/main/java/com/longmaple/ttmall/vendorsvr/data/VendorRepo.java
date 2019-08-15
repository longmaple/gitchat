package com.longmaple.ttmall.vendorsvr.data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.longmaple.ttmall.vendorsvr.model.Vendor;

public interface VendorRepo extends CrudRepository<Vendor, String>  {
    public Optional<Vendor> findById(String vendorId);
}