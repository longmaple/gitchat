package com.longmaple.ttmall.vendorsvr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longmaple.ttmall.vendorsvr.data.VendorRepo;
import com.longmaple.ttmall.vendorsvr.model.Vendor;
import com.longmaple.ttmall.vendorsvr.event.MyMessageSource;

import java.util.UUID;


@Service
public class VendorService {

	@Autowired
	private VendorRepo orgRepository;

	@Autowired
	MyMessageSource msgSourceBean;

	public Vendor getVendor(String vendorId) {
		return orgRepository.findById(vendorId).get();
	}

	public void saveVendor(Vendor vendor) {
		vendor.setVendorId( UUID.randomUUID().toString());
		orgRepository.save(vendor);
		msgSourceBean.publishVendorChange("SAVE", vendor.getVendorId());
	}

	public void updateVendor(Vendor vendor){
		orgRepository.save(vendor);
		msgSourceBean.publishVendorChange("UPDATE", vendor.getVendorId());
	}

	public void deleteVendor(Vendor vendor) {
		orgRepository.delete(vendor);
		msgSourceBean.publishVendorChange("DELETE", vendor.getVendorId());
	}
}