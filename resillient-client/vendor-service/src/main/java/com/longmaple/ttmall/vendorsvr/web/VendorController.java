package com.longmaple.ttmall.vendorsvr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.longmaple.ttmall.vendorsvr.model.Vendor;
import com.longmaple.ttmall.vendorsvr.service.VendorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value="/vendors")
public class VendorController {

	@Autowired
	private VendorService vendorService;

	@GetMapping("/{vendorId}")
	public Vendor getVendor( @PathVariable("vendorId") String vendorId) {
		return vendorService.getVendor(vendorId);
	}

}