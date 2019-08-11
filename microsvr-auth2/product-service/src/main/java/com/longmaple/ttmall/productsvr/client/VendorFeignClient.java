package com.longmaple.ttmall.productsvr.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.longmaple.ttmall.productsvr.model.Vendor;


@FeignClient("vendorservice")
public interface VendorFeignClient {
	
    @RequestMapping(
            method= RequestMethod.GET,
            value="/vendorsvr/vendors/{vendorId}",
            consumes="application/json")
    Vendor getVendor(@PathVariable("vendorId") String vendorId);
    
}